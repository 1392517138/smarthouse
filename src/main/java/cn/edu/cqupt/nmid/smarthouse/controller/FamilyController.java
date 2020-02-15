package cn.edu.cqupt.nmid.smarthouse.controller;

import cn.edu.cqupt.nmid.smarthouse.pojo.User;
import cn.edu.cqupt.nmid.smarthouse.pojo.UserInfo;
import cn.edu.cqupt.nmid.smarthouse.service.FamilyService;
import cn.edu.cqupt.nmid.smarthouse.util.LoginSessionContext;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author piwenjing
 * @description
 * @date 2020/1/11 9:52 PM
 */
@Api(tags = "家人类接口")
@RestController
public class FamilyController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginSessionContext loginSessionContext;
    @Autowired
    private FamilyService familyService;

    /**
     * -------------------------------------------------------------------------------------
     *     ********************************************************************************
     *                  获取家人列表/家人基本user信息、userinfo信息/添加家人/删除家人
     *     ********************************************************************************
     * -------------------------------------------------------------------------------------
     */

    /**
     * @Description: 获取到家人列表一级信息
     * @Param
     * @Date: 7:46 PM 2020/1/14
     */
    @ApiOperation("获取家人列表的一级信息")
    @GetMapping("/family/getFamily")
    public String getFamily(@RequestParam String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        int status = 200;
        List<User> users = null;
        try {
            User user = (User) loginSessionContext.getSession(JSESSIONID).getAttribute("user");
            users = familyService.getFamily(user);
        } catch (Exception e) {
            status = 400;
            e.printStackTrace();
        }
        returnData.put("uesrs", users);
        returnData.put("status", status);
        return returnData.toJSONString();
    }

    /**
     * @Description: 点击单个家人获取该家人测得的信息
     * @Param email
     * @Date: 4:34 PM 2020/1/15
     */
    @ApiOperation("点击单个家人获取该家人测得的信息")
    @GetMapping("/family/getFamilyUser")
    public String getFamilyUesr(@RequestParam String email, @RequestParam String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        int status = 200;
        UserInfo userInfo = null;
        try {
            User user = (User) loginSessionContext.getSession("JSESSIONID").getAttribute("user");
            if (familyService.isFamily(email, user.getEmail())) {
                userInfo = familyService.getFamilyUserInfo(email);
            } else {
                //没有该家人 【安全操作】
                status = 400;
            }
        } catch (Exception e) {
            status = 400;
            e.printStackTrace();
        }
        returnData.put("userinfo", userInfo);
        returnData.put("status", status);
        return returnData.toJSONString();
    }

    /**
     * @Description: 添加家人
     * @Param email phone borth
     * @Date: 5:25 PM 2020/1/15
     */
    @ApiOperation("添加家人")
    @PostMapping("/family/addFamily")
    public String addFamily(@RequestParam String email,
                            @RequestParam String phone,
                            @RequestParam String borth,
                            @RequestParam String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        int status = 200;
        try {
            User user = (User) loginSessionContext.getSession("JSESSIONID").getAttribute("user");
            User user1 = familyService.getFamilyUser(user.getEmail());
            //1.判断如果家人列表中已有或添加本人，返回400
            if (familyService.isFamily(email, user.getFamily()) || email.equals(user.getEmail())) {
                status = 400;
            } else {
                //2.判断是否符合需添加家人的信息,即输入的信息正确
                if (familyService.checkAdd(user1, phone, borth)) {
                    //3.添加家人
                    familyService.addFamily(user, email);
                } else {
                    status = 400;
                }
            }
        } catch (Exception e) {
            status = 400;
            e.printStackTrace();
        }
        returnData.put("status", status);
        return returnData.toJSONString();
    }

    /**
     * @Description: 删除指定家人
     * @Param email 要删除的家人的email
     * @Date: 10:39 PM 2020/1/15
     */
    @ApiOperation("删除指定家人")
    @GetMapping("/family/delFamily")
    public String delFamily(@RequestParam String email, @RequestParam String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        int status = 400;
        try {
            User user = (User) loginSessionContext.getSession("JSESSIONID").getAttribute("user");
            //1.判断如果家人列表中没有，返回400
            if (!familyService.isFamily(email, user.getFamily())) {
                status = 400;
            } else {
                familyService.delFamily(user, email);
            }
        } catch (Exception e) {
            status = 400;
            e.printStackTrace();
        }
        returnData.put("status", status);
        return returnData.toJSONString();
    }


}
