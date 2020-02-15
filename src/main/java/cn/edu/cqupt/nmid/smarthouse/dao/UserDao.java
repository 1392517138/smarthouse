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
    @Insert("insert into user (phone,email,pwd,nickname,borth,sex,photo,family,qnum) values (#{user.phone},#{user.email}," +
            "#{user.pwd},#{user.nickname},#{user.borth},#{user.sex},#{user.photo},#{user.family},#{user.qnum})")
    void register(@Param("user") User user);

    @Insert("INSERT INTO user_info(email,temperature,moisture,height,weight,b_Temperature," +
            "b_Sugar,b_Oxygen,h_Rate,n_Measure) VALUES (#{user.email}, 0, 0, '', '', '', '', '', '', '')")
    void register2(@Param("user") User user);

    /**
     * @Description:用户登陆
     * @Param [email, pwd]用户名，密码
     * @Date: 10:42 AM 2020/1/11
     */
    @Select("select phone,email,pwd,nickname,borth,sex,photo,family,qnum from user where email=#{email} and pwd=#{pwd}")
    User login(@Param("email") String email, @Param("pwd") String pwd);

    /**
     * @Description:修改名称、 密码、 头像
     * @Param [user]
     * @Date: 6:30 PM 2020/1/11
     */
    @Update("update user set nickname=#{user.nickname} where email=#{user.email}")
    void modNickname(@Param("user") User user);

    @Update("update user set pwd=#{user.pwd} where email=#{user.email}")
    void modPwd(@Param("user") User user);

    @Update("update user set photo=#{user.photo} where email=#{user.email}")
    void modHead(@Param("user") User user);

    @Select("select email from user where email=#{email}")
    String ifExit(String email);
}
