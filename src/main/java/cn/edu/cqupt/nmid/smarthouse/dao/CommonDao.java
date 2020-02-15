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
    @Select("select temperature,moisture,height,weight,b_Temperature,b_Sugar,b_Oxygen,h_Rate," +
            "n_Measure from user_info where email=#{email}")
    UserInfo getInfo(String email);

    /**
     * 更新用户测得的数据
     *
     * @param userInfo
     */
    @Update("update user_info set temperature=concat(temperature,#{userInfo.temperature}),moisture=concat(moisture,#{userInfo.moisture})," +
            "height=concat(height,#{userInfo.height}),weight=concat(weight,#{userInfo.weight}),b_Temperature=concat(b_Temperature,#{userInfo.bTemperature})," +
            "b_Pressure=concat(b_Sugar,#{userInfo.bSugar}),b_Oxygen=concat(b_Oxygen,#{userInfo.bOxygen})," +
            "h_Rate=concat(h_Rate,#{userInfo.hRate}),n_Measure=concat(n_Measure,#{userInfo.nMeasure}) where email=#{email}")
    void updateInfo(@Param("userInfo") UserInfo userInfo, @Param("email") String email);


}
