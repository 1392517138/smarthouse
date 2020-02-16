package cn.edu.cqupt.nmid.smarthouse.dao;

import cn.edu.cqupt.nmid.smarthouse.pojo.Plan;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 计划增加、删除、查询、修改
 *
 * @author piwenjing
 * @description
 * @date 2020/2/15 9:37 AM
 */
@Mapper
public interface PlanDao {


//    void addPlan(String time, String thing);

//    List<Plan> getPlans(String phone);

//    void delPlan(int id);

//    void updatePlan(int id);

    /**
     * 添加任务
     *
     * @param time
     * @param thing
     * @param phone
     */
    @Insert("insert into plan (phone,time,thing) values(#{phone},#{time},#{thing})")
    void addPlan(@Param("time") String time, @Param("thing") String thing, @Param("phone") String phone);

    /**
     * 得到该用户所有计划
     *
     * @param phone
     * @return
     */
    @Select("select * from plan where phone=#{phone}")
    List<Plan> getPlans(String phone);

    /**
     * 删除该任务
     *
     * @param id
     */
    @Delete("delete from plan where id=#{id}")
    void delPlan(int id);

    /**
     * 更新任务
     *
     * @param plan
     */
    @Update("update plan set time=#{plan.time},thing=#{plan.thing} where id=#{plan.id}")
    void updatePlan(@Param("plan") Plan plan);
}
