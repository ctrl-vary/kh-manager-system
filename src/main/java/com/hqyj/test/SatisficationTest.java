package com.hqyj.test;

import com.hqyj.dao.KhSatisfactionDao;
import com.hqyj.pojo.KhSatisfaction;
import com.hqyj.pojo.Satisfaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @program: shiyou
 * @description:
 * @Author: Sherlock
 * @Date 2021/3/25 16:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SatisficationTest {
    @Resource
    KhSatisfactionDao khSatisfactionDao;
    @Test
    public void test1(){
        KhSatisfaction khSatisfaction=new KhSatisfaction();
        khSatisfaction.setCost((float)6.3);
        khSatisfaction.setDes("数据的");
        khSatisfaction.setJlId(2);
        khSatisfaction.setKhId(3);
        khSatisfaction.setKhName("oo");
        khSatisfaction.setLinkMan("19347234");
        khSatisfaction.setTime("2021");
        khSatisfaction.setSatisfaction(Satisfaction.不满意.toString());
        khSatisfactionDao.insert(khSatisfaction);
    }
    @Test
    public void test2(){
        KhSatisfaction khSatisfaction=new KhSatisfaction();
        khSatisfaction.setCost((float)6.3);
        khSatisfaction.setDes("数据的");
        khSatisfaction.setJlId(3);
        khSatisfaction.setKhId(3);
        khSatisfaction.setKhName("oo");
        khSatisfaction.setLinkMan("19347234");
        khSatisfaction.setTime("2021");
        khSatisfaction.setSatisfaction(Satisfaction.比较满意.toString());
        khSatisfactionDao.update(khSatisfaction);
    }
    @Test
    public void test4(){
        System.out.println(khSatisfactionDao.getSatisfactionByJlId(2));
    }
}
