package cn.edu.cqupt.nmid.smarthouse.service;

import cn.edu.cqupt.nmid.smarthouse.pojo.Knowledge;
import cn.edu.cqupt.nmid.smarthouse.pojo.Plan;
import cn.edu.cqupt.nmid.smarthouse.pojo.UserInfo;

import java.util.List;

/**
 * 用户测数据功能
 *
 * @author piwenjing
 * @description
 * @date 2020/1/19 11:35 AM
 */
public interface CommonService {

    /**
     * 得到用户测得的数据
     *
     * @param phone
     * @return
     */
    UserInfo getInfo(String phone);

    /**
     * 更新用户测得的数据
     *
     * @param userInfo
     */
    void updateInfo(UserInfo userInfo, String phone);

    /**
     * 获得所有题目及答案
     *
     * @return
     */
    List<Knowledge> getKnowledge();

    /**
     * 用户答题正确，答题正确数目加一
     *
     * @param phone
     */
    void addQnum(String phone);

    /**
     * 添加任务
     *
     * @param time
     * @param thing
     */
    void addPlan(String time, String thing, String phone);

    /**
     * 查询任务
     *
     * @param phone
     * @return
     */
    List<Plan> getPlans(String phone);

    /**
     * 删除任务
     *
     * @param id
     */
    void delPlan(int id);

    /**
     * 更新任务
     *
     * @param plan
     */
    void updatePlan(Plan plan);
}
