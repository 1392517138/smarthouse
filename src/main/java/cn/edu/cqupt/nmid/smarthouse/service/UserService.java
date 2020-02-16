package cn.edu.cqupt.nmid.smarthouse.service;

import cn.edu.cqupt.nmid.smarthouse.pojo.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户基础功能
 *
 * @author piwenjing
 * @description
 * @date 2020/1/10 4:54 PM
 */
public interface UserService {
    /**
     * 用户注册
     *
     * @param user
     */
    void register(User user);

    /**
     * 用户登陆
     *
     * @param phone
     * @param pwd
     * @return
     */
    User login(String phone, String pwd);

    /**
     * 修改用户名称 、 密码 、 头像 、
     *
     * @param user
     */
    void modNickname(User user);

    void modPwd(User user);

    void modHead(User user, MultipartFile photo, String savepath) throws Exception;

    /**
     * 是否存在该用户
     *
     * @param phone
     * @return
     */
    Boolean ifExit(String phone);

    /**
     * 修改生日、 性别、 电话
     */
    void modBorth(String borth, String phone);

    void modSex(String sex, String phone);

    void modPhone(String phone1, String phone2);


}
