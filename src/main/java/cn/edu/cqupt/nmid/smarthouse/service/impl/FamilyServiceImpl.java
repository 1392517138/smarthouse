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


    @Override
    public List<User> getFamily(User user) {
        String[] familylis = this.getList(user.getPhone());
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

    private String[] getList(String phone) {
        //1.获取家人列表
        String list = familyDao.getFamilyList(phone);
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
    public UserInfo getFamilyUserInfo(String phone) {
        return commonDao.getInfo(phone);
    }

    @Override
    public Boolean isFamily(String phone1, String phone2) {
        return familyDao.isFamily(phone1, phone2);
    }

    @Override
    public User getFamilyUser(String phone) {
        return familyDao.getFamilyUser(phone);
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
    public void addFamily(User user, String phone) {
        //用 ";" 做分割
        familyDao.addFamily(user, phone + ";");
    }

    @Override
    @Transactional
    public void delFamily(User user, String phone) {
        familyDao.delFamily(user, phone + ";");
    }

    @Override
    public void sendFamilies(String phone) {
        String[] list = this.getList(phone);

    }


}
