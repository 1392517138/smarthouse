package cn.edu.cqupt.nmid.smarthouse.controller;

import cn.edu.cqupt.nmid.smarthouse.pojo.User;
import cn.edu.cqupt.nmid.smarthouse.service.UserService;
import cn.edu.cqupt.nmid.smarthouse.service.mail.MailService;
import cn.edu.cqupt.nmid.smarthouse.service.sms.SmsService;
import cn.edu.cqupt.nmid.smarthouse.util.CheckCode;
import cn.edu.cqupt.nmid.smarthouse.util.LoginSessionContext;
import cn.edu.cqupt.nmid.smarthouse.util.RegistSessionContext;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;


/**
 * @author piwenjing
 * @description
 * @date 2020/1/10 2:51 PM
 */
@Api(tags = "用户基本操作接口")
@RestController
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SmsService smsService;
    @Autowired
    private RegistSessionContext registSessionContext;
    @Autowired
    private LoginSessionContext loginSessionContext;
    @Autowired
    private UserService userService;


    /**
     * -------------------------------------------------------------------------------------
     *     ********************************************************************************
     *                               注册/登陆/取消登陆/修改名称、密码、头像
     *     ********************************************************************************
     * -------------------------------------------------------------------------------------
     */
    /**
     * @Description:当点击发送验证码时触发，并将checkCode存入
     * @Param: * @param null
     * @Date: 6:00 PM 2020/1/10
     */
    @ApiOperation("发送短信验证码")
    @PostMapping("/register/sendCheckCode")
    public String Register(@RequestParam("phone") String phone, HttpSession session) throws Exception {
        JSONObject returnData = new JSONObject();
        int status = 200;
        try {
            String checkCode = CheckCode.getCheckCode(5);

            smsService.sendSms(phone, checkCode);
            session.setMaxInactiveInterval(600);
            session.setAttribute("checkCode", checkCode);

            registSessionContext.addSession(session);

        } catch (InterruptedException e) {
            status = 400;
            e.printStackTrace();
        }
        returnData.put("status", status);
        returnData.put("JSESSIONID", session.getId());

        return returnData.toJSONString();
    }

    /**
     * @Description:当点击提交当时候，检查验证码，通过即注册成功,且phone为unique可判断是否已经注册
     * @Param: * @param null
     * @Date: 6:02 PM 2020/1/10
     */
    @ApiOperation("完成注册，并检查验证码")
    @GetMapping("/register/checkCode")
    public String checkCode(@RequestParam("checkCode") String checkCode,
                            @RequestParam("JSESSIONID") String JSESSIONID,
                            @RequestParam("pwd") String pwd,
                            @RequestParam("phone") String phone,
                            @RequestParam("nickname") String nickname,
                            @RequestParam("borth") String borth,
                            @RequestParam("sex") String sex) {
        JSONObject returnData = new JSONObject();
        int status = 200;
        try {
            HttpSession session = registSessionContext.getSession(JSESSIONID);

            String value = ((String) session.getAttribute("checkCode")).toLowerCase();
            if (value.equals(checkCode.toLowerCase())) {

                userService.register(new User(phone, pwd, nickname, borth, sex, "default.jpg", ""));
            }
        } catch (Exception e) {
            status = 400;
            e.printStackTrace();
        }
        returnData.put("status", status);
        return returnData.toJSONString();

    }


    /**
     * @Description:用户登陆
     * @Param [phone, pwd, session]
     * @Date: 11:18 AM 2020/1/11
     */
    @ApiOperation("用户登陆")
    @PostMapping("/login")
    public String Login(@RequestParam("phone") String phone, @RequestParam("pwd") String pwd, HttpSession session) {
        JSONObject returnData = new JSONObject();
        int status = 200;
        User user = null;
        try {
            Boolean p = userService.ifExit(phone);
            if (p) {
                user = userService.login(phone, pwd);
                if (user != null) {
                    logger.info(user + "------------");
                    session.setAttribute("user", user);
                    session.setMaxInactiveInterval(0);
                    loginSessionContext.addSession(session);
                } else {
                    status = 400;
                }
            } else {
                //300代表用户没有注册
                status = 300;
            }
        } catch (Exception e) {
            status = 400;
            e.printStackTrace();
        }
        returnData.put("user", user);
        returnData.put("status", status);
        returnData.put("JSESSIONID", session.getId());
        return returnData.toJSONString();
    }

    /**
     * @Description:用户退出登陆
     * @Param [JSESSIONID]
     * @Date: 11:22 AM 2020/1/11
     */
    @ApiOperation("用户退出登陆")
    @GetMapping("/loginOut")
    public String loginOut(@RequestParam String JSESSIONID) throws Exception {
        JSONObject returnData = new JSONObject();
        int status = 200;
        try {
            HttpSession session = loginSessionContext.getSession(JSESSIONID);
            if (session != null) {
                loginSessionContext.delSession(session);
                session.setMaxInactiveInterval(1);
            } else {
                status = 400;
            }
        } catch (Exception e) {
            status = 400;
            e.printStackTrace();
        }
        returnData.put("status", status);
        return returnData.toJSONString();
    }

    /**
     * @Description:修改用户名称、 密码 、 头像、 生日、 性别、 电话
     * @Param [nickname, JSESSIONID]
     * @Date: 6:11 PM 2020/1/11
     */
    @ApiOperation("修改名称")
    @GetMapping("/modNickname")
    public String modNickname(@RequestParam String nickname, @RequestParam String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        int status = 200;
        try {
            User user = (User) loginSessionContext.getSession(JSESSIONID).getAttribute("user");
            user.setNickname(nickname);
            userService.modNickname(user);
        } catch (Exception e) {
            status = 400;
            e.printStackTrace();
        }
        returnData.put("status", status);
        return returnData.toJSONString();
    }

    @ApiOperation("修改密码")
    @GetMapping("/modPwd")
    public String modHead(@RequestParam String pwd, @RequestParam String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        int status = 200;
        try {
            User user = (User) loginSessionContext.getSession(JSESSIONID).getAttribute("user");
            user.setNickname(pwd);
            userService.modNickname(user);
        } catch (Exception e) {
            status = 400;
            e.printStackTrace();
        }
        returnData.put("status", status);
        return returnData.toJSONString();
    }

    @ApiOperation("修改头像")
    @PostMapping("/modHead")
    public String modHead(@RequestParam MultipartFile photo, @RequestParam String JSESSIONID, HttpSession session) {
        JSONObject returnData = new JSONObject();
        int status = 200;
        try {
            User user = (User) loginSessionContext.getSession(JSESSIONID).getAttribute("user");
            userService.modHead(user, photo, "/data/upload");
            userService.modNickname(user);
        } catch (Exception e) {
            status = 400;
            e.printStackTrace();
        }
        returnData.put("status", status);
        return returnData.toJSONString();
    }

    @ApiOperation("修改生日")
    @GetMapping("/modBorth")
    public String modBorth(@RequestParam String borth, @RequestParam String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        int status = 200;
        try {
            User user = (User) loginSessionContext.getSession(JSESSIONID).getAttribute("user");
            userService.modBorth(borth, user.getPhone());
        } catch (Exception e) {
            status = 400;
            e.printStackTrace();
        }
        returnData.put("status", status);
        return returnData.toJSONString();
    }

    @ApiOperation("修改性别")
    @GetMapping("/modSex")
    public String modSex(@RequestParam String sex, @RequestParam String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        int status = 200;
        try {
            User user = (User) loginSessionContext.getSession(JSESSIONID).getAttribute("user");
            userService.modSex(sex, user.getPhone());
        } catch (Exception e) {
            status = 400;
            e.printStackTrace();
        }
        returnData.put("status", status);
        return returnData.toJSONString();
    }

    @ApiOperation("修改电话")
    @GetMapping("/modPhone")
    public String modPhone(@RequestParam String phone, @RequestParam String JSESSIONID) {
        JSONObject returnData = new JSONObject();
        int status = 200;
        try {
            User user = (User) loginSessionContext.getSession(JSESSIONID).getAttribute("user");
            userService.modPhone(phone, user.getPhone());
        } catch (Exception e) {
            status = 400;
            e.printStackTrace();
        }
        returnData.put("status", status);
        return returnData.toJSONString();
    }
}
