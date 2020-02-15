package cn.edu.cqupt.nmid.smarthouse.controller;

import cn.edu.cqupt.nmid.smarthouse.pojo.Knowledge;
import cn.edu.cqupt.nmid.smarthouse.pojo.User;
import cn.edu.cqupt.nmid.smarthouse.pojo.UserInfo;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.image.Kernel;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/15 8:02 PM
 */
@Api(tags = "用于升成Models,请忽略")
@Controller
public class External {

    @RequestMapping("/User")
    public void User(@RequestBody User user) {

    }

    @RequestMapping("/UserInfo")
    public void UserInfo(@RequestBody UserInfo userInfo) {

    }

    @RequestMapping("/Knowledge")
    public void Knowledge(@RequestBody Knowledge knowledge) {

    }
}
