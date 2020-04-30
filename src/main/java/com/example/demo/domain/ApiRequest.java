package com.example.demo.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ApiRequest {
    /**
     * 终端类型 ios/android/pc/terminal
     */
    private String platform;
    /**
     * 请求版本软件版本号，默认1.0
     */
    private String appVersion;
    /**
     * 请求版本接口版本号，默认1.0
     */
    private String apiVersion;
    /**
     * 终端设备
     */
    private String mac;
    /**
     * 设备当前IP地址
     */
    private String ip;
    /**
     * 公司代码
     */
    private String companyCode;
    /**
     * 令牌
     */
    private String token;
    /**
     * 设备型号
     */
    private String hmcjSbxh;
    /**
     * 设备sn号
     */
    private String hmcjSbbh;
    /**
     * 具体的请求参数
     */
    private Map<String, Object> data;

    private String page;

    private String limit;

    private String userId;

    private String userName;

    private String userJGDM;

    public static ApiRequest New(Map<String, Object> data) {
        return new ApiRequest().setData(data);
    }

    public static ApiRequest New() {
        return New(new HashMap<>());
    }

    public ApiRequest withData(String key, Object val) {
        if (null == data) {
            data = new HashMap<>();
        }
        this.data.put(key, val);
        return this;
    }

    public <T> T getData(String key) {
        if (null != data) {
            if (data.containsKey(key)) {
                return (T) data.get(key);
            }
        }
        return null;
    }

    public <T> Optional<T> getData(Class<T> cls) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            final T t = mapper.readValue(mapper.writeValueAsString(this.getData()), cls);
            return Optional.of(t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * 将不得不放在外面得参数传入data.
     */
    @SuppressWarnings("unchecked")
    public ApiRequest transParam() {
        if (!data.containsKey("page")) data.put("page", page);
        if (!data.containsKey("limit")) data.put("limit", limit);
        return this;
    }

    @Override
    public String toString() {
        return "ApiRequest{" +
                "platform='" + platform + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", apiVersion='" + apiVersion + '\'' +
                ", mac='" + mac + '\'' +
                ", ip='" + ip + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", token='" + token + '\'' +
                ", data=" + data +
                ", page='" + page + '\'' +
                ", limit='" + limit + '\'' +
                '}';
    }

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static ApiRequest str2Obj(String apiRequest) {
        ApiRequest apiRequest1 = null;
        try {
            apiRequest1 = objectMapper.readValue(apiRequest, ApiRequest.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return apiRequest1;
    }

    public static List<ApiRequest> str2ObjList(String apiRequest) {
        List<ApiRequest> apiRequestList = new ArrayList<>();
        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            apiRequestList = objectMapper.readValue(apiRequest, new TypeReference<List<ApiRequest>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return apiRequestList;
    }

    public static List<ApiRequest> inStream2ObjList(InputStream in) {
        List<ApiRequest> apiRequestList = new ArrayList<>();
        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            apiRequestList = objectMapper.readValue(in, new TypeReference<List<ApiRequest>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return apiRequestList;
    }
}
