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
     * @param user
     * @return
     */
    @Select("select family from user where email=#{user.email}")
    String getFamilyList(@Param("user") User user);

    /**
     * 2.
     * 根据email获取单个user信息
     *
     * @param email
     * @return
     */
    @Select("select * from user where email=#{email}")
    User getFamilyUser(String email);

    /**
     * 判断该点击的用户是否为该用户家人。【安全操作】
     *
     * @param email1
     * @param email2
     * @return
     */
    @Select("select family like '%${email1}%' from user where email=#{email2}")
    Boolean isFamily(@Param("email1") String email1, @Param("email2") String email2);

    /**
     * 验证成功以后，添加家人
     *
     * @param user
     * @param email
     */
    @Update("update user set family= concat(family,#{email}) where email=#{user.email}")
    void addFamily(@Param("user") User user, @Param("email") String email);

    /**
     * 删除指定家人
     *
     * @param user
     * @param email
     */
    @Update("update user set family = replace(family,#{email},'') where email=#{user.email}")
    void delFamily(@Param("user") User user, @Param("email") String email);
}
