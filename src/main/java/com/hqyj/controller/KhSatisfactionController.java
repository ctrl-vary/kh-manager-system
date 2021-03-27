package com.hqyj.controller;

import com.hqyj.pojo.KhSatisfaction;
import com.hqyj.service.KhSatisfactionService;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @program: shiyou
 * @description:
 * @Author: Sherlock
 * @Date 2021/3/25 16:32
 */
@Controller
//@RequestMapping("/khSatisfaction")
public class KhSatisfactionController {
    @Resource
    KhSatisfactionService khSatisfactionService;

    /**
     * 客户服务记录
     * @param jlId
     * @param m
     * @return
     */
    @GetMapping("/banner-list")
    public String getKhSatisfactionInfo(Integer jlId, ModelMap m){
        List<KhSatisfaction> khSatisfactions=khSatisfactionService.getSatisfactionByJlId(jlId);
        m.addAttribute("info",khSatisfactions);
        System.out.println(khSatisfactions+"list----------------------------");
        m.addAttribute("dataNum",khSatisfactions.size());
        return "banner-list";
    }
    //访问 添加客户服务列表页面页面
    @RequestMapping("/banner-add")
    public String banneradd(){
        return "banner-add";
    }
    /**
     * 删除客户服务记录
     * @param sId
     * @return
     */
    @GetMapping("/deleteKhSatisfaction")
    public String deleteKhSatisfaction(Integer sId){
        khSatisfactionService.delete(sId);
        return  "删除成功";
    }

    /**
     * 增加客户服务记录
     * @param khSatisfaction
     * @return
     */
    @PostMapping("/insertKhSatisfaction")
    public String insertKhSatisfaction(KhSatisfaction khSatisfaction){
        khSatisfactionService.insert(khSatisfaction);
        return "添加成功";
    }

    /**
     * 修改客户服务记录
     * @param khSatisfaction
     * @return
     */
    @PostMapping("/updateKhSatisfaction")
    public String updateKhSatisfaction(KhSatisfaction khSatisfaction){
        khSatisfactionService.update(khSatisfaction);
        return "更新成功";
    }
    /**
     * 获取编辑信息
     * @param sId
     * @return
     */
    @GetMapping("/getEditInfo")
    public HashMap<String,Object> getEditInfo(Integer sId){
        HashMap<String,Object> hashMap=new HashMap<>();
       KhSatisfaction khSatisfaction= khSatisfactionService
               .getSatisfactionBysId(sId);
       hashMap.put("info",khSatisfaction);
        return hashMap;
    }
}
