package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.PersonInfoMapper;
import com.example.demo.domain.ApiRequest;
import com.example.demo.domain.ApiResult;
import com.example.demo.entity.PersonInfo;
import com.example.demo.service.IPersonInfoService;
import com.example.demo.util.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dmy
 * @since 2019-07-26
 */
@Service
public class PersonInfoServiceImpl extends ServiceImpl<PersonInfoMapper, PersonInfo> implements IPersonInfoService {

    Logger logger = LoggerFactory.getLogger(PersonInfoServiceImpl.class);


    @Autowired
    private PersonInfoMapper personInfoMapper;

    @Override
    public ApiResult getPersonInfoList(ApiRequest apiRequest) throws Exception {
        //JWTToken校验
        if(!JWTUtils.checkStrToken(apiRequest.getToken())){
            return ApiResult.isErrMessage("账号已过期，请重新登录");
        }
        personInfoMapper.selectList(new QueryWrapper<PersonInfo>().lambda().eq(PersonInfo::getIsDeleted,"2").between(PersonInfo::getHmxxcjsj,"2020-01-01","2020-05-01"));
      /*  Page<PersonParticularOutputDto> page = new Page<>(Integer.parseInt(apiRequest.getPage()), Integer.parseInt(apiRequest.getLimit()));
        Map<String, Object> param = convertToParm(apiRequest);
        List<PersonParticularOutputDto> list = personInfoPMapper.selectByParticular(param, page);//page会存放总记录数
        List<IrisCode> irisCodes = getIrisCodes("1");
        list.forEach(outputDto -> {
            outputDto.setZjlx(irisCodes.stream().filter(code -> "1".equals(code.getCodeType()) && code.getCodeIndex().equals(outputDto.getZjlx())).map(IrisCode::getCodeName).findFirst().orElse(""));
        });
        page.setRecords(list);
        return ApiResult.isOk(apiRequest.getToken(), "查询成功", page);*/
        return  null;
    }



}
