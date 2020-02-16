//package cn.edu.cqupt.nmid.smarthouse;
//
//import cn.edu.cqupt.nmid.smarthouse.controller.FamilyController;
//import cn.edu.cqupt.nmid.smarthouse.pojo.Knowledge;
//import cn.edu.cqupt.nmid.smarthouse.pojo.User;
//import cn.edu.cqupt.nmid.smarthouse.pojo.UserInfo;
//import cn.edu.cqupt.nmid.smarthouse.service.CommonService;
//import cn.edu.cqupt.nmid.smarthouse.service.FamilyService;
//import cn.edu.cqupt.nmid.smarthouse.service.UserService;
//import cn.edu.cqupt.nmid.smarthouse.util.LoginSessionContext;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Ignore;
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.annotation.Resource;
//import javax.websocket.Session;
//import java.util.List;
//
//@SpringBootTest
//@Slf4j
//class SmarthouseApplicationTests {
//
//    @Resource
//    UserService userService;
//    @Resource
//    FamilyService familyService;
//    @Resource
//    CommonService commonService;
//
//    @Test
//    void contextLoads() {
//    }
//
//    /**********************************************************************
//     *                      UserController 测试
//     *********************************************************************
//     */
//
//    @Test
//    public void registTest() {
//        userService.register(new User("131313", "111@qq.com", "1",
//                "呵呵呵", "2019-03-14", "1", "default.jpg", "", 0));
//    }
//
//    @Test
//    public void loginTest() {
//        User user = userService.login("1392517138@qq.com", "123");
//        if (user == null) {
//            log.warn("不存在此user");
//        }
//    }
//
//    @Test
//    public void modNicknameTest() {
//        userService.modNickname(new User("123", "1392517138@qq.com", "123",
//                "111111", "2019-03-24", "男", "default.jpg", "", 0));
//    }
//
//    @Test
//    public void modPwdTest() {
//        userService.modPwd(new User("123", "1392517138@qq.com", "1",
//                "111111", "2019-03-24", "男", "default.jpg", "", 0));
//    }
//
//    @Test
//    public void modHeadTest() {
//
//    }
//
//    /**********************************************************************
//     *                          FamilyController 测试
//     *********************************************************************
//     */
//
//    @Test
//    public void addFamilyTest() {
////        System.out.println("是否为家人: "+familyService.isFamily("123@qq.com","1392517138@qq.com"));
////        System.out.println("是否信息正确: "+familyService.checkAdd(new User("131313", "123@qq.com", "1",
////                "呵呵呵", "2019-03-14", "1", "default.jpg", "", 0),
////                "131313","2019-03-14"));
//        familyService.addFamily(new User("123", "1392517138@qq.com", "1",
//                "111111", "2019-03-24", "男", "default.jpg", "", 0), "111@qq.com");
//    }
//
//    /**
//     @Ignore
//     @Test public void getFamilyList(){
//     List<User> families = familyService.getFamily(new User("123", "1392517138@qq.com", "123",
//     "111111", "2019-03-24", "男", "default.jpg", "", 0));
//     for (User user: families
//     ) {
//     System.out.println(user);
//     }
//     }
//     */
//
//    /**********************************************************************
//     *                          ContactController 测试
//     *
//     *********************************************************************
//     */
//    @Test
//    public void addQnumTest() {
//        commonService.addQnum("111@qq.com");
//    }
//
//    @Test
//    public void getKnowledgeTest() {
//        System.out.println(commonService.getKnowledge());
//    }
//
//    @Test
//    public void infoTest() {
//        System.out.println(commonService.getInfo("1392517138@qq.com"));
//
//        commonService.updateInfo(new UserInfo(5.0, 5.0, "5", "5", "5", "5",
//                "5", "5", "5"), "1392517138@qq.com");
//        System.out.println(commonService.getInfo("1392517138@qq.com"));
//    }
//}
