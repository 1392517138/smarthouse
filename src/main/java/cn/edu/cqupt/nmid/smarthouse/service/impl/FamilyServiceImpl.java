package cn.edu.cqupt.nmid.smarthouse.service.impl;

import cn.edu.cqupt.nmid.smarthouse.dao.CommonDao;
import cn.edu.cqupt.nmid.smarthouse.dao.FamilyDao;
import cn.edu.cqupt.nmid.smarthouse.pojo.User;
import cn.edu.cqupt.nmid.smarthouse.pojo.UserInfo;
import cn.edu.cqupt.nmid.smarthouse.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired


    @Override
    public List<User> getFamily(User user) {
        String[] familylis = this.getList(user.getEmail());
        List<User> family = new LinkedList<>();
        try {
            //3.查询出的单个user添加进list
            for (String familyli : familylis) {
                family.add(familyDao.getFamilyUser(familyli));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return family;
    }

    private String[] getList(String email) {
        //1.获取家人列表
        String list = familyDao.getFamilyList(email);
        String[] familylis = null;
        //2.分割list
        if (list != null) {
            familylis = list.split(";");
        } else {
            return null;
        }
        return familylis;
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

    @Override
    public void sendFamilies(String email) {
        String[] list = this.getList(email);
        这里发送信息
    }


}
