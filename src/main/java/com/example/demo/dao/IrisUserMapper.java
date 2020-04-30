package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.IrisUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gzli
 * @since 2019-07-26
 */
@Mapper
public interface IrisUserMapper extends BaseMapper<IrisUser> {

}
