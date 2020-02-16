package cn.edu.cqupt.nmid.smarthouse.dao;

import cn.edu.cqupt.nmid.smarthouse.pojo.Knowledge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 知识小管家题库【答题小游戏】
 *
 * @author piwenjing
 * @description
 * @date 2020/1/14 8:07 PM
 */
@Mapper
public interface KnowledgeDao {
    /**
     * 得到所有问题及答案
     *
     * @return
     */
    @Select("select * from knowledge")
    List<Knowledge> getKnowledge();

    /**
     * 用户答对问题，增加答对答题数(qnum)
     *
     * @param phone
     */
    @Update("update user set qnum = qnum + 1 where phone = #{phone}")
    void addQnum(String phone);
}
