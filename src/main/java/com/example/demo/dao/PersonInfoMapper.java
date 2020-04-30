package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.PersonInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author dmy
 * @since 2019-07-26
 */
@Mapper
public interface PersonInfoMapper extends BaseMapper<PersonInfo> {
    List<PersonInfo> selectPersonInfoList(@Param("rybhs") List<String> rybhs, @Param("isId") String ztk);

    List<String> queryPersonInfoList(Map<String, Object> map);

    @Select("select x.RYBH,x.XM, x.CYZJ_ZJHM ,x.HMXX_CJSJ," +
            "x.ZJBZ,x.CSRQ,x.LXDH1,x.HJDZ_DZMC,x.CJBZ,\n" +
            "x.XBDM," +
            "x.CYZJ_CYZJDM, " +
            "x.RYFL," +
            "x.GJDM," +
            "x.MZDM ," +
            "x.QFJGMC,x.XZZ_DZMC,x.LXDH1,x.LXDH2,x.XGRYXM,x.XGYY,x.XGSJ,x.ZPDZ ,y.HMXX_CJBZ,x.HMXX_CJBH,x.YXQX,y.HMXX_DLYH_XM," +
            "y.HMXX_ZYYDM," +
            "y.HMCJ_CJSBDD_DZMC\n" +
            "from person_iris y\n" +
            "LEFT JOIN person_info x  on x.RYBH = y.RYBH \n" +
            "WHERE x.RYBH = #{rybh, jdbcType=VARCHAR} ;")
    List<PersonInfo> selectPersonInfoDetail(@Param("rybh") String rybh);

    @Select("<script>"+
            "select x.RYBH,x.XM, x.CYZJ_ZJHM ,x.HMXX_CJSJ," +
            "x.ZJBZ,x.CSRQ,x.LXDH1,x.HJDZ_DZMC,x.CJBZ,\n" +
            "x.XBDM," +
            "x.CYZJ_CYZJDM, " +
            "x.RYFL," +
            "x.GJDM," +
            "x.MZDM ," +
            "x.QFJGMC,x.XZZ_DZMC,x.LXDH1,x.LXDH2,x.XGRYXM,x.XGYY,x.XGSJ,x.ZPDZ ,y.HMXX_CJBZ,x.HMXX_CJBH,x.YXQX,y.HMXX_DLYH_XM," +
            "y.HMXX_ZYYDM," +
            "y.HMCJ_CJSBDD_DZMC\n" +
            "from person_iris y\n" +
            "LEFT JOIN person_info x  on x.RYBH = y.RYBH \n" +
            "WHERE x.RYBH in " +
            "<foreach item=\"item\" collection=\"rybhs\" separator=\",\" open=\"(\" close=\")\" index=\"\">\n" +
            "      #{item}\n" +
            "</foreach>\n"+
            "</script>"
    )
    List<PersonInfo> selectPersonsInfoDetail(@Param("rybhs") List<String> rybhs);

    @Select("SELECT MIN(HMXX_CJSJ) FROM person_info")
    Date getFirstPersonDataRecord();

    @Select("select e.RYBH,e.HMXX_SBXH, e.HMXX_CJSJ,u.JGXX_GAJGJGMC,e.JGXX_GAJGJGDM, e.HMXX_SBCSDM  sbcsdm ,e.HMXX_SBXH hmcjsbxhdm,e.HMXX_SBXH_XHDM sbbh,e.HMXX_SBBH deviceSN,e.HMCJ_CJCDDM cjcd, e.HMCJ_CJSBDD_DZMC dzmc,\n" +
            "e.HMCJ_CJSBWZ_XZB jd,e.HMCJ_CJSBWZ_YZB wd,e.HMXX_CJBH cjbh,e.HMXX_DLYH_XM cjr_xm,q.user_idCard cjr_gmsfhm ,q.RYFL bcjr_rylb,q.ZJBZ zjbz,\n" +
            "e.HMCJ_SFXXHQFSBZDM lrbz,e.HMCJ_QZCJBZ qzcjbz,q.CYZJ_CYZJDM bcjr_zjlxdm,q.CYZJ_ZJHM bcjr_zjhm,q.XM bcjr_xm,q.XBDM bcjr_xb,q.MZDM bcjr_mz,q.GJDM bcjr_gj,\n" +
            "q.CSRQ bcjr_csrq,q.QFJGMC bcjr_zjqfjg,q.YXQX bcjr_zjyxqx,q.HJDZ_DZMC bcjr_hjdz,q.XZZ_DZMC  bcjr_jzdz,q.LXDH1 bcjr_sjhm1,q.LXDH2 bcjr_sjhm2,\n" +
            "q.ZPDZ zjzp,e.HMXX_ZYYDM zyycjdm,e.HMXX_QSQKDM_ZY zyqsqkdm,e.HMXX_QSQKDM_YY yyqsqkdm,r.HMXX_ZPDZ hmzp_zy,t.HMXX_ZPDZ hmzp_yy,\n" +
            "e.HMCJ_CJHS cjtphs ,r.HMXX_TXZL zy_xxzlpf, t.HMXX_TXZL yy_xxzlpf,e.HMXX_CJBZ cjbz,q.person_id,q.client_id\n" +
            "from  person_iris e\n" +
            "LEFT JOIN person_info q on q.RYBH = e.RYBH \n" +
            "LEFT JOIN iris_organization u on u.JGXX_GAJGJGDM = e.JGXX_GAJGJGDM \n" +
            "LEFT JOIN person_iris_photo r on r.RYBH = e.RYBH and r.HMXX_ZYYBS = '1'\n" +
            "LEFT JOIN person_iris_photo t on t.RYBH = e.RYBH and t.HMXX_ZYYBS = '2'\n" +
            "WHERE e.RYBH = #{rybh} and q.upload_status = #{uploadStatus} and e.is_deleted = '2' and q.is_deleted = '2' and e.HMXX_SBXH !='02' limit 1;")
    Map<String, Object> selectForUpload(@Param("rybh") String rybh, @Param("uploadStatus") String uploadStatus);


    @Select("<script>\n" +
            "select   t.RYBH from person_info t\n" +
            "<where>\n" +
            "t.upload_status = #{uploadStatus} and t.is_deleted = '2'" +
            "<if test='queryType == 2 '>\n" +
            "<if test='organizations != null and  organizations.size>0'>\n" +
            " and t.JGXX_GAJGJGDM in\n" +
            "<foreach collection=\"organizations\" item=\"item\" index=\"index\" open=\"(\" close=\")\" separator=\",\">\n" +
            "#{item}" +
            "</foreach>\n" +
            "</if>\n" +
            "</if>\n" +
            "<if test='queryType == 3 '>\n" +
            "  and t.user_id = #{userId}\n" +
            "</if>\n" +
            "<if test='queryType == 4 '>\n" +
            "  and t.JGXX_GAJGJGDM in\n" +
            "<foreach collection=\"notifiedBody\" item=\"item\" index=\"index\" open=\"(\" close=\")\" separator=\",\">\n" +
            "#{item}" +
            "</foreach>\n" +
            "</if>\n" +
            "</where>\n" +
            "</script>")
    List<String> selectRybhs(Map<String, Object> param);

    Long selectPersonListCount(Map<String, Object> paramMap);

    Long getCountBySpecial(Map<String, Object> paramMap);

    List<PersonInfo> selectPersonInfoListForAdd(Map<String, Object> paramMap);

    Long getCountBySpecialForAdd(Map<String, Object> paramMap);

    List<String> selectRybhsByJgdm(Map<String, Object> data);

    List<PersonInfo> selectPersonInfos(@Param("rybhs") List<String> rybhs);


    List<String> queryRybhForSpecial(Map<String, Object> paramMap);




    @Select("<script>\n" +
            "    select   RYBH,XM, XBDM ,CYZJ_CYZJDM ,CYZJ_ZJHM , RYFL ,GJDM,MZDM ,HMXX_SBXH\n" +
            "        from person_info\n" +
            "        <where>\n" +
            "            RYBH not in (select RYBH from person_particular where is_id = #{param.isId } and is_deleted = '2' )\n" +
            "            and is_deleted='2'\n" +
            "            <if test=\"param.xm != null and param.xm != ''\">\n" +
            "                and XM like #{param.xm,jdbcType=VARCHAR}\n" +
            "            </if>\n" +
            "            <if test=\" param.xbdm != null and param.xbdm != ''\">\n" +
            "                and XBDM= #{param.xbdm,jdbcType=VARCHAR}\n" +
            "            </if>\n" +
            "            <if test=\" param.cyzjdm != null and param.cyzjdm != ''\">\n" +
            "                and CYZJ_CYZJDM= #{param.cyzjdm,jdbcType=VARCHAR}\n" +
            "            </if>\n" +
            "            <if test=\"param.zjhm != null and param.zjhm != ''\">\n" +
            "                and CYZJ_ZJHM like #{param.zjhm,jdbcType=VARCHAR}\n" +
            "            </if>\n" +
            "            <if test=\"param.ryfl != null and param.ryfl != ''\">\n" +
            "                and RYFL= #{param.ryfl,jdbcType=VARCHAR}\n" +
            "            </if>\n" +
            "            <if test=\" param.gjdm != null and param.gjdm != ''\">\n" +
            "                and GJDM= #{param.gjdm,jdbcType=VARCHAR}\n" +
            "            </if>\n" +
            "            <if test=\"param.mzdm != null and param.mzdm != ''\">\n" +
            "                and MZDM = #{param.mzdm,jdbcType=VARCHAR}\n" +
            "            </if>\n" +
            "            <if test=\"param.sblxdm != null and param.sblxdm != ''\">\n" +
            "                and HMXX_SBXH = #{param.sblxdm,jdbcType=VARCHAR}\n" +
            "            </if>\n" +
            "            <if test=\"param.queryType == 2\">\n" +
            "                and JGXX_GAJGJGDM in\n" +
            "                <foreach item=\"item\" index=\"index\" collection=\"param.organizations\" open=\"(\" separator=\",\" close=\")\">\n" +
            "                    #{item}\n" +
            "                </foreach>\n" +
            "            </if>\n" +
            "            <if test=\"param.queryType == 3\">\n" +
            "                and user_id = #{param.userId,jdbcType=VARCHAR}\n" +
            "            </if>\n" +
            "            <if test=\"param.queryType == 4\">\n" +
            "                and JGXX_GAJGJGDM in\n" +
            "                <foreach item=\"item\" index=\"index\" collection=\"param.notifiedBody\" open=\"(\" separator=\",\" close=\")\">\n" +
            "                    #{item}\n" +
            "                </foreach>\n" +
            "            </if>\n" +
            "        </where>\n" +
            "        order by HMXX_CJSJ desc"+
            "</script>")
    List<PersonInfo> getParticularPersonsForAdd(@Param("param") Map<String, Object> paramMap, Page<PersonInfo> page);
}
