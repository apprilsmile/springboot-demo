package com.example.demo.controller;


import com.alibaba.fastjson.JSON;
import com.example.demo.domain.ApiRequest;
import com.example.demo.domain.ApiResult;
import com.example.demo.service.IIrisUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author gzli
 * @since 2019-07-26
 */
@RestController
@RequestMapping("/SysManage")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = {RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins = "*")
public class IrisUserController {
    @Autowired
    private IIrisUserService iriskingUserService;

    Logger logger = LoggerFactory.getLogger(PersonInfoController.class);

    @PostMapping("/login")
    public ApiResult login(@RequestBody ApiRequest apiRequest) {
        logger.debug("用户登录服务-前台请求：" + JSON.toJSONString(apiRequest));
        try {
            ApiResult apiResult = iriskingUserService.login(apiRequest);
            logger.debug("用户登录服务-后台返回：" + JSON.toJSONString(apiResult));
            return apiResult;
        } catch (Exception e) {
            logger.error("用户登录服务-异常信息：" + e);
            e.printStackTrace();
            return ApiResult.isErrMessage("登录失败");
        }
    }

}
