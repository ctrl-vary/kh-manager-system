package com.hqyj.service;

import com.hqyj.dao.KhSatisfactionDao;
import com.hqyj.pojo.KhSatisfaction;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: shiyou
 * @description:
 * @Author: Sherlock
 * @Date 2021/3/25 16:29
 */
@Service
public class KhSatisfactionServiceImp implements KhSatisfactionService{
    @Resource
    KhSatisfactionDao khSatisfactionDao;
    @Override
    public List<KhSatisfaction> getSatisfactionByJlId(Integer jlId) {
        return khSatisfactionDao.getSatisfactionByJlId(jlId);
    }

    @Override
    public void delete(Integer sId) {
        khSatisfactionDao.delete(sId);
    }

    @Override
    public String insert(KhSatisfaction khSatisfaction) {

        int num = khSatisfactionDao.insert(khSatisfaction);
        if (num>0){
            return "添加成功";
        }
        return "添加失败";
    }

    @Override
//    public void update(KhSatisfaction khSatisfaction) {
//
//        khSatisfactionDao.update(khSatisfaction);
//    }
    public String update(KhSatisfaction khSatisfaction) {
        int num;
        num = khSatisfactionDao.update(khSatisfaction);
        if (num>0)
            return "修改成功";
        return "修改失败";
    }

    @Override
    public KhSatisfaction getSatisfactionBysId(Integer sId) {
        return khSatisfactionDao.getSatisfactionBysId(sId);
    }
}
