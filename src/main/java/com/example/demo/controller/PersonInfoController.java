package com.example.demo.controller;


import com.alibaba.fastjson.JSON;
import com.example.demo.domain.ApiRequest;
import com.example.demo.domain.ApiResult;
import com.example.demo.service.IPersonInfoService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dmy
 * @since 2019-07-26
 */
@RestController
@RequestMapping("/dataManagement")
@Slf4j
@CrossOrigin(allowCredentials="true", allowedHeaders="*", methods={RequestMethod.GET,
        RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS,
        RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH}, origins="*")
public class PersonInfoController {

    Logger logger = LoggerFactory.getLogger(PersonInfoController.class);

    @Autowired
    private IPersonInfoService iPersonInfoService;

    @PostMapping("/getPersonInfoList")
    public ApiResult getPersonInfoList(@RequestBody ApiRequest apiRequest) {
        String page = apiRequest.getPage();//当前页码
        String limit = apiRequest.getLimit();//当前页码
        // 业务流程
        logger.debug("查询人员信息列表服务-前台请求：" + JSON.toJSONString(apiRequest));
        try {
            ApiResult apiResult = iPersonInfoService.getPersonInfoList(apiRequest);
            logger.debug("查询人员信息列表服务-后台返回：" + JSON.toJSONString(apiResult));
            return apiResult;
        } catch (Exception e) {
            //日志参数
            logger.error("查询人员信息列表服务-异常信息：" + e);
            e.printStackTrace();
            return ApiResult.isErrTokenAndMsg(apiRequest.getToken(), "查询成功");
        }
    }



}
