package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* <p>
    * 
    * </p>
*
* @author dmy
* @since 2019-07-27
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class IrisUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号(GUID)
     */
    @TableId(value = "user_id")
    private String userId;

    /**
     * 机构信息_登录用户_警号
     */
    private String userPoliceId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 机构信息_登录用户_姓名
     */
    private String userRealname;

    /**
     * 性别：1男2女
     */
    private Integer userGender;

    /**
     * 邮件地址
     */
    private String userEmail;

    /**
     * 是否锁定 0：未锁定，1：锁定
     */
    private Integer userIsLock;

    /**
     * 更新时间
     */
    private Long userCreateTime;

    /**
     * 创建时间
     */
    private Long userUpdateTime;

    /**
     * 机构信息_登录用户_联系电话
     */
    private String userTel;

    /**
     * 机构信息_登录用户_手机号码
     */
    private String userPhone;

    /**
     * 机构信息_登录用户描述
     */
    private String userDescribe;

    /**
     * 身份证号
     */
    @TableField("user_idCard")
    private String userIdcard;
    /**
     * * 机构信息_机构ID,GUID
     */
    @TableField("JGXX_JGID")
    private String jgxxJgid;

    @TableField("JGXX_GAJGJGDM")
    private String jgxxGajgjgdm;
    /**
     * * 删除标志1:已删除2:未删除
     */
    private Integer userIsDeleted;

    private String userRemark;

    /**
     * * 采集场地代码
     */
    @TableField("user_place_code")
    private String userPlaceCode;

    /**
     * 采集场地名称
     */
    @TableField("user_place_name")
    private String userPlaceName;
    /**
     * 登录用户Id
     */
    @TableField("login_user_id")
    private String loginUserId;



}
