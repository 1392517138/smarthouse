package cn.edu.cqupt.nmid.smarthouse.controller;

import cn.edu.cqupt.nmid.smarthouse.dao.PlanDao;
import cn.edu.cqupt.nmid.smarthouse.pojo.Knowledge;
import cn.edu.cqupt.nmid.smarthouse.pojo.Plan;
import cn.edu.cqupt.nmid.smarthouse.pojo.User;
import cn.edu.cqupt.nmid.smarthouse.pojo.UserInfo;
import cn.edu.cqupt.nmid.smarthouse.service.CommonService;
import cn.edu.cqupt.nmid.smarthouse.util.LoginSessionContext;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author piwenjing
 * @description
 * @date 2020/1/11 6:52 PM
 */
@Api(tags = "与测得数据的交互接口")
@RestController
public class ContactController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    CommonService commonService;
    @Autowired
    LoginSessionContext loginSessionContext;

    /**
     * -------------------------------------------------------------------------------------
     *     ********************************************************************************
     *                  更新/查看所有数据【UserInfo】/查看问答题、答题正确加一/测量次数统计
     *     ********************************************************************************
     * -------------------------------------------------------------------------------------
     */

    /**
     * @Description:获得用户的信息
     * @Param [JSESSIONID]
     * @Date: 11:59 AM 2020/1/19
     */
    @GetMapping("/contact/getInfo")
    @ApiOperation("获得用户的信息")
    public String getInfo(@ApiParam @RequestParam("JSESSIONID") String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        int status = 200;
        UserInfo userInfo;
        try {
            User user = (User) loginSessionContext.getSession(JSESSIONID).getAttribute("user");
            userInfo = commonService.getInfo(user.getEmail());
            returnData.put("info", userInfo);
        } catch (Exception e) {
            returnData.put("info", null);
            status = 400;
            e.printStackTrace();
        }
        returnData.put("status", status);
        return returnData.toJSONString();
    }


    /**
     * @Description:更新用户测得的信息
     * @Param [userInfo]
     * @Date: 11:53 AM 2020/1/19
     */
    @PostMapping("/contact/updateInfo")
    @ApiOperation("更新用户测得的信息")
    public String updateInfo(@ApiParam("用户测得得信息") @RequestBody UserInfo userInfo, @ApiParam @RequestParam("JSESSIONID") String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        int status = 200;
        User user = (User) loginSessionContext.getSession(JSESSIONID).getAttribute("user");
        try {
            commonService.updateInfo(userInfo, user.getEmail());
        } catch (Exception e) {
            status = 400;
            e.printStackTrace();
        }
        returnData.put("status", status);
        return returnData.toJSONString();
    }

    /**
     * @Description:得到所有问题，及答案
     * @Param [JSESSIONID]
     * @Date: 9:50 AM 2020/1/29
     */
    @ApiOperation("得到所有问题、及答案")
    @GetMapping("/contact/getKnowledge")
    public String getKnowledge(@ApiParam @RequestParam("JSESSIONID") String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        int status = 200;
        List<Knowledge> knowledgeList = null;
        try {
            knowledgeList = commonService.getKnowledge();
        } catch (Exception e) {
            status = 400;
            e.printStackTrace();
        }
        returnData.put("knowledgeList", knowledgeList);
        returnData.put("status", status);
        return returnData.toJSONString();
    }

    @ApiOperation("回答对问题，正确数加一")
    @GetMapping("/contact/addQnum")
    public String addQnum(@ApiParam @RequestParam("JSESSIONID") String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        int status = 200;
        try {
            User user = (User) loginSessionContext.getSession(JSESSIONID).getAttribute("user");
            commonService.addQnum(user.getEmail());
        } catch (Exception e) {
            status = 400;
            e.printStackTrace();
        }
        returnData.put("status", status);
        return returnData.toJSONString();
    }

    /**
     * -------------------------------------------------------------------------------------
     * ********************************************************************************
     * 添加、查询、删除、更新计划
     * ********************************************************************************
     * -------------------------------------------------------------------------------------
     */


    @ApiOperation("添加任务")
    @GetMapping("/contact/addPlan")
    public String addPlan(@RequestParam("time") String time, @RequestParam("thing") String thing,
                          @RequestParam("JSESSIONID") String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        int status = 200;
        try {
            User user = (User) loginSessionContext.getSession(JSESSIONID).getAttribute("user");
            String email = user.getEmail();
            commonService.addPlan(time, thing, email);
        } catch (Exception e) {
            status = 400;
            e.printStackTrace();
        }
        returnData.put("status", status);
        return returnData.toJSONString();
    }

    @ApiOperation("得到所有任务")
    @GetMapping("/contact/getPlans")
    public String addPlan(@RequestParam("JSESSIONID") String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        int status = 200;
        List<Plan> plans = null;
        try {
            User user = (User) loginSessionContext.getSession(JSESSIONID).getAttribute("user");
            String email = user.getEmail();
            plans = commonService.getPlans(email);
        } catch (Exception e) {
            status = 400;
            e.printStackTrace();
        }
        returnData.put("status", status);
        returnData.put("plans", plans);
        return returnData.toJSONString();
    }

    @ApiOperation("删除任务")
    @GetMapping("/contact/delPlan")
    public String addPlan(@RequestParam("id") int id,
                          @RequestParam("JSESSIONID") String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        int status = 200;
        try {
            commonService.delPlan(id);
        } catch (Exception e) {
            status = 400;
            e.printStackTrace();
        }
        returnData.put("status", status);
        return returnData.toJSONString();
    }

    @ApiOperation("更新任务")
    @PostMapping("/contact/updatePlan")
    public String addPlan(@RequestBody Plan plan, @RequestParam("JSESSIONID") String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        int status = 200;
        try {
            commonService.updatePlan(plan);
        } catch (Exception e) {
            status = 400;
            e.printStackTrace();
        }
        returnData.put("status", status);
        return returnData.toJSONString();
    }
}
