package cn.edu.cqupt.nmid.smarthouse.dao;

import cn.edu.cqupt.nmid.smarthouse.pojo.User;
import org.apache.ibatis.annotations.*;


/**
 * @author piwenjing
 * @description
 * @date 2020/1/11 10:43 PM
 */
@Mapper
public interface FamilyDao {

    /**
     * 1.
     * 获取家人列表
     *
     * @param phone
     * @return
     */
    @Select("select family from user where phone=#{phone}")
    String getFamilyList(String phone);

    /**
     * 2.
     * 根据phone获取单个user信息
     *
     * @param phone
     * @return
     */
    @Select("select * from user where phone=#{phone}")
    User getFamilyUser(String phone);

    /**
     * 判断该点击的用户是否为该用户家人。【安全操作】
     *
     * @param phone1
     * @param phone2
     * @return
     */
    @Select("select family like '%${phone1}%' from user where phone=#{phone2}")
    Boolean isFamily(@Param("phone1") String phone1, @Param("phone2") String phone2);

    /**
     * 验证成功以后，添加家人
     *
     * @param user
     * @param phone
     */
    @Update("update user set family= concat(family,#{phone}) where phone=#{user.phone}")
    void addFamily(@Param("user") User user, @Param("phone") String phone);

    /**
     * 删除指定家人
     *
     * @param user
     * @param phone
     */
    @Update("update user set family = replace(family,#{phone},'') where phone=#{user.phone}")
    void delFamily(@Param("user") User user, @Param("phone") String phone);
}
