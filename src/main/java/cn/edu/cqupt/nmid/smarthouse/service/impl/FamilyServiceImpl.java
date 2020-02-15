package cn.edu.cqupt.nmid.smarthouse.service.impl;

import cn.edu.cqupt.nmid.smarthouse.dao.CommonDao;
import cn.edu.cqupt.nmid.smarthouse.dao.FamilyDao;
import cn.edu.cqupt.nmid.smarthouse.pojo.User;
import cn.edu.cqupt.nmid.smarthouse.pojo.UserInfo;
import cn.edu.cqupt.nmid.smarthouse.service.FamilyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @author piwenjing
 * @description
 * @date 2020/1/11 10:42 PM
 */
@Service
public class FamilyServiceImpl implements FamilyService {

    @Resource
    private FamilyDao familyDao;
    @Resource
    private CommonDao commonDao;

    @Override
    public List<User> getFamily(User user) {
        //1.获取家人列表
        String list = familyDao.getFamilyList(user);
        String[] familylis = null;
        //2.分割list
        if (list != null) {
            familylis = list.split(";");
        } else {
            return null;
        }
        List<User> family = new LinkedList<>();
        //3.查询出的单个user添加进list
        for (String familyli : familylis) {
            family.add(familyDao.getFamilyUser(familyli));
        }
        return family;
    }

    @Override
    public UserInfo getFamilyUserInfo(String email) {
        return commonDao.getInfo(email);
    }

    @Override
    public Boolean isFamily(String email1, String email2) {
        return familyDao.isFamily(email1, email2);
    }

    @Override
    public User getFamilyUser(String email) {
        return familyDao.getFamilyUser(email);
    }

    @Override
    public Boolean checkAdd(User user1, String phone, String borth) {
        if (user1.getPhone().equals(phone) && user1.getBorth().equals(borth)) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void addFamily(User user, String email) {
        //用 ";" 做分割
        familyDao.addFamily(user, email + ";");
    }

    @Override
    @Transactional
    public void delFamily(User user, String email) {
        familyDao.delFamily(user, email + ";");
    }


}
