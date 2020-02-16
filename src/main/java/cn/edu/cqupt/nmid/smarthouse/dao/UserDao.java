package cn.edu.cqupt.nmid.smarthouse.dao;

import cn.edu.cqupt.nmid.smarthouse.pojo.User;
import org.apache.ibatis.annotations.*;

/**
 * @author piwenjing
 * @description
 * @date 2020/1/10 4:55 PM
 */
@Mapper
public interface UserDao {
    /**
     * @Description:用户注册
     * @Param [user]
     * @Date: 10:14 AM 2020/1/11
     */
    @Insert("insert into user (phone,pwd,nickname,borth,sex,photo,family) values (#{user.phone}," +
            "#{user.pwd},#{user.nickname},#{user.borth},#{user.sex},#{user.photo},#{user.family})")
    void register(@Param("user") User user);

    @Insert("INSERT INTO user_info(phone,temperature,moisture,height,weight,b_Temperature," +
            "b_Sugar,b_Oxygen,h_Rate,n_Measure) VALUES (#{user.phone}, 0, 0, '', '', '', '', '', '', '')")
    void register2(@Param("user") User user);

    /**
     * @Description:用户登陆
     * @Param [phone, pwd]用户名，密码
     * @Date: 10:42 AM 2020/1/11
     */
    @Select("select phone,pwd,nickname,borth,sex,photo,family from user where phone=#{phone} and pwd=#{pwd}")
    User login(@Param("phone") String phone, @Param("pwd") String pwd);

    /**
     * @Description:修改名称、 密码、 头像
     * @Param [user]
     * @Date: 6:30 PM 2020/1/11
     */
    @Update("update user set nickname=#{user.nickname} where phone=#{user.phone}")
    void modNickname(@Param("user") User user);

    @Update("update user set pwd=#{user.pwd} where phone=#{user.phone}")
    void modPwd(@Param("user") User user);

    @Update("update user set photo=#{user.photo} where phone=#{user.phone}")
    void modHead(@Param("user") User user);

    @Select("select phone from user where phone=#{phone}")
    String ifExit(String phone);


    /**
     * 修改 生日、 性别、 电话
     */
    @Update("update user set borth=#{borth} where phone=#{phone}")
    void modBorth(@Param("borth") String borth, @Param("phone") String phone);

    @Update("update user set sex=#{sex} where phone=#{phone}")
    void modSex(@Param("sex") String sex, @Param("phone") String phone);

    /**
     * 将phone2 修改成phone1
     *
     * @param phone1
     * @param phone2
     */
    @Update("update user set phone=#{phone1} where phone=#{phone2}")
    void modPhone(@Param("phone1") String phone1, @Param("phone2") String phone2);
}
