package com.example.demo.constant;

public enum LoginMessageEnum {

    L_PASSWORD_ERR("用户名或密码错误",500),
    L_USERMESSAGE_ERR("用户名信息不正确",500),
    L_LOGIN_OK("登录成功",200),
    L_USERNAME_ERR("用戶名不能为空",500),
    L_SYSTEM_ERR("请求超时，请稍后重试",500),
    P_ORGIPASSWORD_ERR("原密码输入错误",500),
    P_NEW_PASSWORD_ERR("新密码格式错误，支持8-16位数字或字母的任意组合",500),
    P_RESETPASSWORD_OK("密码修改成功",200),
    L_ISLOCK("该用户已被锁定，请联系管理员",500),
    L_ISDELETE("该用户已停用，请确认",500),
    L_ROLEISNULL("该用户尚未分配角色，请先分配角色再登录",500),
    ADMIN_ROLE_NAME("超级管理员",0),
    SECONDARY_ROLE_NAME("管理员",0)
    ;

    private String message;
    private int code;

    LoginMessageEnum(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
