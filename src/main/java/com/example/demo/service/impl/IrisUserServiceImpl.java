package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.constant.LoginMessageEnum;
import com.example.demo.dao.IrisUserMapper;
import com.example.demo.domain.ApiRequest;
import com.example.demo.domain.ApiResult;
import com.example.demo.domain.JWTEntity;
import com.example.demo.entity.IrisUser;
import com.example.demo.service.IIrisUserService;
import com.example.demo.util.JWTUtils;
import com.example.demo.util.Nulls;
import com.example.demo.util.TimeFormatterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author gzli
 * @since 2019-07-26
 */
@Service("irisUserServiceImpl")
public class IrisUserServiceImpl extends ServiceImpl<IrisUserMapper, IrisUser> implements IIrisUserService {

    private static final String TOKEN_DATE = "2099-12-30";
    private static final String SPECIAL_DEVICE_MODE_CODE = "AI3000";


    @Autowired
    private IrisUserMapper irisUserMapper;



    @Override
    public ApiResult login(ApiRequest apiRequest) throws Exception {
        //支持手机号，警号登录
        Map data = apiRequest.getData();
        String userName = (String) data.get("userName");//登录用户名
        String password = (String) data.get("password");//登录密码
        QueryWrapper<IrisUser> queryWrapper = new QueryWrapper<>();
        String jgmc = "";
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(userName);
        //判断是否是手机号登录
        if (userName.length() == 11 && m.matches()) {//手机号登录
            queryWrapper.eq("user_phone", userName).eq("user_password", password).eq("user_is_deleted", "2");
        } else {
            queryWrapper.eq("user_name", userName).eq("user_password", password).eq("user_is_deleted", "2");
        }
        List<IrisUser> irisUsers = irisUserMapper.selectList(queryWrapper);
        if (irisUsers == null || irisUsers.isEmpty()) {
            queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_password", password).eq("user_police_id", userName).eq("user_is_deleted", "2");
            irisUsers = irisUserMapper.selectList(queryWrapper);
            if (irisUsers == null || irisUsers.isEmpty()) {
                return ApiResult.isErrMessage(LoginMessageEnum.L_PASSWORD_ERR.getMessage());
            } else {
                if (1 == irisUsers.get(0).getUserIsLock()) {
                    return ApiResult.isErrMessage(LoginMessageEnum.L_ISLOCK.getMessage());
                }
            }
        } else {
            if (1 == irisUsers.get(0).getUserIsLock()) {//账户锁定
                return ApiResult.isErrMessage(LoginMessageEnum.L_ISLOCK.getMessage());
            }
        }
        Map<String, Object> resultMap = new HashMap<>();
        IrisUser irisUser = irisUsers.get(0);
        resultMap.put("userId", Nulls.nullToSpace(irisUser.getUserId()));//用户编号
        resultMap.put("policeId", Nulls.nullToSpace(irisUser.getUserPoliceId()));//警号
        resultMap.put("userName", Nulls.nullToSpace(irisUser.getUserName()));//用户名
        resultMap.put("realName", Nulls.nullToSpace(irisUser.getUserRealname()));//姓名
        Integer userGender = irisUser.getUserGender();
        resultMap.put("gender", userGender != null ? String.valueOf(userGender) : "");//性别 1男2女
        resultMap.put("email", Nulls.nullToSpace(irisUser.getUserEmail()));//邮件地址
        resultMap.put("tel", Nulls.nullToSpace(irisUser.getUserTel()));//联系电话
        resultMap.put("phone", Nulls.nullToSpace(irisUser.getUserPhone()));//手机号
        resultMap.put("idCard", Nulls.nullToSpace(irisUser.getUserIdcard()));//身份证号
        resultMap.put("JGID", Nulls.nullToSpace(irisUser.getJgxxJgid()));//机构id
        resultMap.put("userJGDM", Nulls.nullToSpace(irisUser.getJgxxGajgjgdm()));//机构信息代码
        resultMap.put("userPlaceCode", Nulls.nullToSpace(irisUser.getUserPlaceCode()));//采集场地代码
        resultMap.put("userPlaceName", Nulls.nullToSpace(irisUser.getUserPlaceName()));//采集场地名称
        //resultMap.put("ip",getIpAddr(httpServletRequest));//请求ip
        String userId = irisUser.getUserId();
        //生成JWT
        String jwtToken = null;
        if (SPECIAL_DEVICE_MODE_CODE.equals(apiRequest.getHmcjSbxh())) {//永久token
            Date date = TimeFormatterUtil.ymd.get().parse(TOKEN_DATE);
//            Date date = TimeFormatterUtil.dateTimeformatter.get().parse("2019-11-26 14:10:40");
            jwtToken = JWTUtils.createJWTForever(userId, "token", date);
        } else {
            jwtToken = JWTUtils.createJWT(userId, "token", JWTEntity.JWT_TTL);
        }
        return ApiResult.isOk(jwtToken, LoginMessageEnum.L_LOGIN_OK.getMessage(), resultMap);
    }


    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
     *
     * @return ip
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        System.out.println(ip);
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;

    }

}
