package cn.edu.cqupt.nmid.smarthouse.dao;

import cn.edu.cqupt.nmid.smarthouse.pojo.Knowledge;
import cn.edu.cqupt.nmid.smarthouse.pojo.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 共用的普通操纵
 *
 * @author piwenjing
 * @description
 * @date 2020/1/14 8:05 PM
 */
@Mapper
public interface CommonDao {

    /**
     * 获取用户测得得信息
     *
     * @param email
     * @return
     */
    @Select("select temperature,moisture,height,weight,btemperature,bsugar,boxygen,hrate," +
            "nmeasure from userinfo where email=#{email}")
    UserInfo getInfo(String email);

    /**
     * 更新用户测得的数据
     *
     * @param userInfo
     */
    @Update("update user_info set temperature=concat(temperature,#{userInfo.temperature}),moisture=concat(moisture,#{userInfo.moisture})," +
            "height=concat(height,#{userInfo.height}),weight=concat(weight,#{userInfo.weight}),btemperature=concat(btemperature,#{userInfo.btemperature})," +
            "bpressure=concat(bsugar,#{userInfo.bsugar}),boxygen=concat(boxygen,#{userInfo.boxygen})," +
            "hrate=concat(hrate,#{userInfo.hrate}),nmeasure=concat(nmeasure,#{userInfo.nmeasure}) where email=#{email}")
    void updateInfo(@Param("userInfo") UserInfo userInfo, @Param("email") String email);


}
