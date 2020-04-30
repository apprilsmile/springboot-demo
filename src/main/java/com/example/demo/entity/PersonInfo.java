package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
public class PersonInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 人员编号(详见相关生成规则)
     */
    @TableId("RYBH")
    private String rybh;

    @TableField(exist = false)
    private String Xcjsj;
    /**
     * 虹膜编号(GUID)
     */
    @TableField("HM_ID")
    private String hmId;


    /**
     * 姓名
     */
    @TableField("XM")
    private String xm;
    /**
     * 曾用名
     */
    @TableField("CYM")
    private String cym;

    /**
     * 别名/绰号
     */
    @TableField("BMCH")
    private String bmch;

    /**
     * 外文姓名
     */
    @TableField("WWXM")
    private String wwxm;

    /**
     * 性别代码
     */
    @TableField("XBDM")
    private String xbdm;
    /**
     * 性别
     */
    @TableField(exist = false)
    private String xb;

    /**
     * 国籍代码
     */
    @TableField("GJDM")
    private String gjdm;

    /**
     * 国籍
     */
    @TableField(exist = false)
    private String gj;


    /**
     * 民族代码
     */
    @TableField("MZDM")
    private String mzdm;


    /**
     * 民族代码
     */
    @TableField(exist = false)
    private String mz;

    /**
     * 出生日期
     */
    @TableField("CSRQ")
    private Date csrq;

    /**
     * 出生日期
     */
    @TableField(exist = false)
    private String xcsrq;
    /**
     * 公民身份号码
     */
    @TableField("GMSFHM")
    private String gmsfhm;

    /**
     * 签发机关名称
     */
    @TableField("QFJGMC")
    private String qfjgmc;

    /**
     * 有效期限
     */
    @TableField("YXQX")
    private String yxqx;

    /**
     * 常用证件_常用证件代码
     */
    @TableField("CYZJ_CYZJDM")
    private String cyzjCyzjdm;

    /**
     * 常用证件_常用证件
     */
    @TableField(exist = false)
    private String cyzjCyzj;

    /**
     * 常用证件_证件号码
     */
    @TableField("CYZJ_ZJHM")
    private String cyzjZjhm;

    /**
     * 籍贯省市县代码
     */
    @TableField("JGDM")
    private String jgdm;

    /**
     * 户籍地址_行政区划代码
     */
    @TableField("HJDZ_XZQHDM")
    private String hjdzXzqhdm;

    /**
     * 户籍地址_地址名称
     */
    @TableField("HJDZ_DZMC")
    private String hjdzDzmc;

    /**
     * 现住址_行政区划代码
     */
    @TableField("XZZ_XZQHDM")
    private String xzzXzqhdm;

    /**
     * 现住址_地址名称
     */
    @TableField("XZZ_DZMC")
    private String xzzDzmc;

    /**
     * 联系电话1
     */
    @TableField("LXDH1")
    private String lxdh1;

    /**
     * 联系电话2
     */
    @TableField("LXDH2")
    private String lxdh2;

    /**
     * 临时关注标识
     */
    @TableField("LSGZBZ")
    private String lsgzbs;

    /**
     * 临时关注_结束日期
     */
    @TableField("LSGZ_JSRQ")
    private Long lsgzJsrq;

    /**
     * 临时关注_备注
     */
    @TableField("LSGZ_BZ")
    private String lsgzbz;

    /**
     * 身份证照片地址
     */
    @TableField("ZPDZ")
    private String zpdz;

    /**
     * 采集对象的人员分类代码
     */
    @TableField("RYFL")
    private String ryfl;

    /**
     * 创建时间
     */
    @TableField("CJSJ")
    private Long cjsj;

    /**
     * 修改时间
     */
    @TableField("XGSJ")
    private Long xgsj;

    /**
     * 修改原因
     */
    @TableField("XGYY")
    private String xgyy;

    /**
     * 修改人员姓名
     */
    @TableField("XGRYXM")
    private String xgryxm;

    /**
     * 当前登录用户名
     */
    @TableField("user_id")
    private String userId;

    /**
     * 警号
     */
    @TableField("user_police_id")
    private String userPoliceId;

    /**
     * 请求人_公民身份号码
     */
    @TableField("user_idCard")
    private String userIdCard;

    /**
     * 用户名对应的机构编号
     */
    @TableField("JGXX_JGID")
    private String jgxxJgid;

    /**
     * 机构信息代码
     */
    @TableField("JGXX_GAJGJGDM")
    private String jgxxGajgjgdm;

    /**
     * 删除标志1:已删除2:未删除
     */
    private Integer isDeleted;

    /**
     * 证件标志 1-有证，0-无证
     */
    @TableField("ZJBZ")
    private String zjbz;

    /**
     * 采集录入标志位 1:读证录入 2:手工录入 3:警综录入
     */
    @TableField("CJBZ")
    private Integer cjbz;

    /**
     * 虹膜信息采集编号
     */
    @TableField("HMXX_CJBH")
    private String hmxxcjbh;


    /**
     * 虹膜信息采集时间
     */
    @TableField("HMXX_CJSJ")
    private Date hmxxcjsj;

    /**
     * 虹膜信息_设备厂商代码
     */
    @TableField("HMXX_SBCSDM")
    private String hmxxSbcsdm;

    /**
     * 数据来源:1005-移动设备，1006-门禁设备，1007-手持终端,1008-第三方
     */
    @TableField("platform")
    private String platform;

    /**
     * 设备类型:01移动虹膜识别设备,02虹膜识别一体机,...
     */
    @TableField("HMXX_SBXH")
    private String hmxxSbxh;

    /**
     * 虹膜采集情况代码
     */
    @TableField(exist = false)
    private String  hmxxZyydm;


    /**
     * 虹膜采集情况
     */
    @TableField(exist = false)
    private String  hmxxZyy;

    /**
     * 虹膜信息采集人姓名
     */
    @TableField(exist = false)
    private String hmxxDlyhXm;

    /**
     * 虹膜信息采集点(机构名称)
     */
    @TableField(exist = false)
    private String jgxxGajgjgmc;

    /**
     * 第三方账号id
     */
    @TableField("client_id")
    private String clientId;


    /**
     * 胜云返回的人员ID
     */
    @TableField("person_id")
    private  String personId;

    /**
     * 调用方的人员ID
     */
    @TableField("person_code")
    private  String personCode;

    /**
     * 上传至部级库状态 1:上传成功，2：未上传，3：上传失败，4上传错误
     */
    @TableField("upload_status")
    private  String uploadStatus;

    /**
     * 上传至部级库次数
     */
    @TableField("upload_times")
    private  Integer uploadTimes;
    /**
     * 上传至部级库时间
     */
    @TableField("upload_date")
    private  Long uploadDate;

    /**
     * 上传至部级库返回信息
     */
    @TableField("upload_message")
    private  String uploadMessage;

    /**
     * 上传至部级库返回结果描述
     */
    @TableField("upload_xxms")
    private  String uploadXxms;
    /**
     * 时间
     */
    @TableField(exist = false)
    private  String dateTime;

    /**
     * 上传至部级库结果
     */
    @TableField("upload_result")
    private  String uploadResult;
    /**
     * 采集结果
     */
    @TableField("cjjg")
    private  String cjjg;


    /**
     * 联动时间
     */
    @TableField("linkage_date")
    private Long linkageDate;

    /**
     * 联动状态   1:已联动2:未联动
     */
    @TableField("linkage_type")
    private  String linkageType;


    /**
     * 人员专题GUID
     */
    @TableField(exist = false)
    private String psId;


    /**
     * 采集对象的人员分类代码
     */
    @TableField(exist = false)
    private String ryflmc;

    /**
     * 采集备注
     */
    @TableField(exist = false)
    private String hmxxCjbz;



    /**
     * 设备类型
     */
    @TableField(exist = false)
    private String sblx;


    /**
     * 设备厂商
     */
    @TableField(exist = false)
    private String sbcs;


    /**
     * 接入企业
     */
    @TableField(exist = false)
    private String jrqy;
    /**
     * 数量
     */
    @TableField(exist = false)
    private Integer amount;

    /**
     * 专题库
     */
    @TableField(exist = false)
    private String isName;

    /**
     * 标签
     */
    @TableField(exist = false)
    private String imName;

    /**
     * 虹膜采集_采集识别地点_地址名称
     */
    @TableField(exist = false)
    private String hmcjCjsbddDzmc;



    public PersonInfo() {
    }

    public PersonInfo(String cyzjCyzjdm, String cyzjZjhm) {

        this.cyzjCyzjdm = cyzjCyzjdm;
        this.cyzjZjhm = cyzjZjhm;
    }
}
