package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.domain.ApiRequest;
import com.example.demo.domain.ApiResult;
import com.example.demo.entity.PersonInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dmy
 * @since 2019-07-26
 */
public interface IPersonInfoService extends IService<PersonInfo> {


    ApiResult getPersonInfoList(ApiRequest apiRequest) throws  Exception ;
}
