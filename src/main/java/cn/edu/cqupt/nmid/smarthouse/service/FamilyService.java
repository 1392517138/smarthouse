package cn.edu.cqupt.nmid.smarthouse.service;

import cn.edu.cqupt.nmid.smarthouse.pojo.User;
import cn.edu.cqupt.nmid.smarthouse.pojo.UserInfo;

import java.util.List;

/**
 * 家人功能
 *
 * @author piwenjing
 * @description
 * @date 2020/1/11 10:40 PM
 */
public interface FamilyService {
    /**
     * 获取家庭成员
     *
     * @param user
     * @return
     */
    List<User> getFamily(User user);

    /**
     * 得到单个家庭成员的Info信息
     *
     * @param phone
     * @return
     */
    UserInfo getFamilyUserInfo(String phone);

    /**
     * 判断是否为该用户的家人
     *
     * @param phone1
     * @param emial2
     * @return
     */
    Boolean isFamily(String phone1, String emial2);

    /**
     * 获取该用户的基本信息
     *
     * @param phone
     * @return
     */
    User getFamilyUser(String phone);

    /**
     * 判断添加家人所输入的信息是否正确
     *
     * @param phone
     * @param borth
     * @return
     */
    Boolean checkAdd(User user1, String phone, String borth);

    /**
     * 验证成功以后，添加家人
     *
     * @param user
     * @param phone
     */
    void addFamily(User user, String phone);

    /**
     * 删除指定家人
     *
     * @param user
     * @param phone
     */
    void delFamily(User user, String phone);

    /**
     * 给家人发邮件
     *
     * @param phone
     */
    void sendFamilies(String phone);
}
