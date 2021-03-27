package com.hqyj.controller;

import com.alibaba.fastjson.JSONObject;
import com.hqyj.pojo.SysLog;
import com.hqyj.service.SysLogService;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @program: shiyou
 * @description:日志
 * @Author: Sherlock
 * @Date 2021/3/26 22:21
 */
@Controller
//@RequestMapping("/sys")
public class SysLogController {
    @Resource
    SysLogService sysLogService;

    /**
     * 删除日志
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public String delete(Integer id){
        sysLogService.delete(id);
        return "删除成功";
    }

    /**
     * 分页获取日志
     * @param m
     * @param pageNum
     * @return
     */
    @GetMapping("/sys-log")
    public String getInfo(ModelMap m,Integer pageNum){
        HashMap<String,Object> hashMap=sysLogService.getSysLog(pageNum);
        System.out.println(hashMap+"hashMap++++++++++++++++++");
        m.addAttribute("info",hashMap);
        return "sys-log";
    }

}
