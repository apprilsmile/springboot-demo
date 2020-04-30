package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int CODE_OK = 200;
    public static final int CODE_ERR = 500;

    private int status = ApiResult.CODE_OK; //ok
    private String token;
    private String message;
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String tag;

    public ApiResult(int status, String token, String message, T data) {
        this.status = status;
        this.token = token;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResult<T> isOk(String token, String message, T data) {
        return new ApiResult<>(CODE_OK, token, message, data);
    }

    public static <T> ApiResult<T> isOkNoToken(String message, T data) {
        return new ApiResult<T>(CODE_OK, "", message, data);
    }

    public static <T> ApiResult<T> isOkMessage(String message) {
        return new ApiResult<>(CODE_OK, "", message, null);
    }

    public static <T> ApiResult<T> isErr(String token, String message, T data) {
        return new ApiResult<>(CODE_ERR, token, message, data);
    }

    public static <T> ApiResult<T> isErrNoToken(String message, T data) {
        return new ApiResult<>(CODE_ERR, "", message, data);
    }

    public static <T> ApiResult<T> isErrTokenAndMsg(String token, String message) {
        return new ApiResult<>(CODE_ERR, token, message, null);
    }

    public static <T> ApiResult<T> isErrMessage(String message) {
        return new ApiResult<>(CODE_ERR, "", message, null);
    }

    public static <T> ApiResult<T> isErrMessage(ReturnCode returnCode) {
        return new ApiResult<>(CODE_ERR, "", returnCode.getMessage(), null);
    }

    public static <T> ApiResult<T> isErrNoToken(ReturnCode returnCode, T data) {
        return new ApiResult<>(CODE_ERR, "", returnCode.getMessage(), data);
    }

    public static <T> ApiResult<T> isOkNoToken(ReturnCode returnCode, T data) {
        return new ApiResult<>(CODE_OK, "", returnCode.getMessage(), data);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return 200 <= status && status <= 299;
    }

    public ResponseEntity<ApiResult<T>> build() {
        ApiResult<T> ret = this;
        return new ResponseEntity<ApiResult<T>>(ret, isSuccess() ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    public static ApiResult successRes(boolean isSuccess){
//        Map map = new HashMap<>();
//        map.put("isSuccess",isSuccess);
//
//    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
