package cn.edu.cqupt.nmid.smarthouse.service.impl;

import cn.edu.cqupt.nmid.smarthouse.dao.UserDao;
import cn.edu.cqupt.nmid.smarthouse.pojo.User;
import cn.edu.cqupt.nmid.smarthouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * @author piwenjing
 * @description
 * @date 2020/1/10 4:56 PM
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    @Transactional
    public void register(User user) {
        userDao.register(user);
        //同时更新userInfo信息
        userDao.register2(user);
    }

    @Override
    public User login(String phone, String pwd) {
        return userDao.login(phone, pwd);
    }

    @Override
    @Transactional
    public void modNickname(User user) {
        userDao.modNickname(user);
    }

    @Override
    @Transactional
    public void modPwd(User user) {
        userDao.modPwd(user);
    }

    @Override
    @Transactional
    public void modHead(User user, MultipartFile photo, String savepath) throws Exception {
        String name = photo.getOriginalFilename();
        String phototype = name.substring(name.lastIndexOf("."));
        String filename = user.getPhone() + phototype;
        File file = new File(savepath);
        if (!file.exists()) {
            file.mkdirs();
        }
        photo.transferTo(new File(savepath, filename));
        user.setPhoto(filename);
        userDao.modHead(user);
    }

    @Override
    public Boolean ifExit(String phone) {
        if (userDao.ifExit(phone) == null) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public void modBorth(String borth, String phone) {
        userDao.modBorth(borth, phone);
    }

    @Override
    public void modSex(String sex, String phone) {
        userDao.modSex(sex, phone);
    }

    @Override
    public void modPhone(String phone1, String phone2) {
        userDao.modPhone(phone1, phone2);
    }


}
