package com.hqyj.service;

import com.hqyj.pojo.KhSatisfaction;

import java.util.List;

/**
 * @program: shiyou
 * @description:
 * @Author: Sherlock
 * @Date 2021/3/25 16:29
 */
public interface KhSatisfactionService {
    //根据经理id获取客服记录
    List<KhSatisfaction> getSatisfactionByJlId(Integer jlId);
    void delete(Integer sId);
    String insert(KhSatisfaction khSatisfaction);
    String update(KhSatisfaction khSatisfaction);
    KhSatisfaction getSatisfactionBysId(Integer sId);
}
