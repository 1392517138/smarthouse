package cn.edu.cqupt.nmid.smarthouse.service.impl;

import cn.edu.cqupt.nmid.smarthouse.dao.CommonDao;
import cn.edu.cqupt.nmid.smarthouse.dao.KnowledgeDao;
import cn.edu.cqupt.nmid.smarthouse.dao.PlanDao;
import cn.edu.cqupt.nmid.smarthouse.pojo.Knowledge;
import cn.edu.cqupt.nmid.smarthouse.pojo.Plan;
import cn.edu.cqupt.nmid.smarthouse.pojo.User;
import cn.edu.cqupt.nmid.smarthouse.pojo.UserInfo;
import cn.edu.cqupt.nmid.smarthouse.service.CommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author piwenjing
 * @description
 * @date 2020/1/19 11:38 AM
 */
@Service
public class CommonServiceImpl implements CommonService {
    Date date = new Date();
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat ft2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Resource
    CommonDao commonDao;
    @Resource
    KnowledgeDao knowledgeDao;
    @Resource
    PlanDao planDao;

    @Override
    public UserInfo getInfo(String email) {
        return commonDao.getInfo(email);
    }

    @Override
    @Transactional
    public void updateInfo(UserInfo userInfo, String email) {
        //1.获取原本的userInfo
        UserInfo userInfo1 = commonDao.getInfo(email);
        //身高、体重、血压、血氧、心率、测量次数分别添加上时间
        userInfo.setHeight(userInfo.getHeight() + ":" + ft.format(date) + ";");
        userInfo.setWeight(userInfo.getWeight() + ":" + ft.format(date) + ";");
        userInfo.setBtemperature(userInfo.getBtemperature() + ":" + ft2.format(date) + ";");
        userInfo.setBsugar(userInfo.getBsugar() + ":" + ft2.format(date) + ";");
        userInfo.setBoxygen(userInfo.getBoxygen() + ":" + ft2.format(date) + ";");
        userInfo.setHrate(userInfo.getHrate() + ":" + ft2.format(date) + ";");
        userInfo.setNmeasure((String.valueOf(userInfo.getNmeasure()) + 1) + ":" + ft2.format(date) + ";");

        commonDao.updateInfo(userInfo, email);
    }

    /***********************************************************************/
    /**************************      答题      *****************************/
    @Override
    public List<Knowledge> getKnowledge() {
        return knowledgeDao.getKnowledge();
    }

    @Override
    @Transactional
    public void addQnum(String email) {
        knowledgeDao.addQnum(email);
    }


    /***********************************************************************/
    /**************************      计划      *****************************/

    @Override
    @Transactional
    public void addPlan(String time, String thing, String email) {
        planDao.addPlan(time, thing, email);
    }

    @Override
    public List<Plan> getPlans(String email) {
        List<Plan> plans = planDao.getPlans(email);
        return plans;
    }

    @Override
    @Transactional
    public void delPlan(int id) {
        planDao.delPlan(id);
    }

    @Override
    @Transactional
    public void updatePlan(Plan plan) {
        planDao.updatePlan(plan);
    }
}
