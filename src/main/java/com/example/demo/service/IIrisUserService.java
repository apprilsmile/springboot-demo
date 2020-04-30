package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.domain.ApiRequest;
import com.example.demo.domain.ApiResult;
import com.example.demo.entity.IrisUser;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author gzli
 * @since 2019-07-26
 */
public interface IIrisUserService extends IService<IrisUser> {

    ApiResult login(ApiRequest apiRequest) throws Exception;


}
