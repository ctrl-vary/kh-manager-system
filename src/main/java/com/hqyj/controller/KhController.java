package com.hqyj.controller;
import com.hqyj.pojo.KhSer;
import com.hqyj.pojo.UserInfo;
import com.hqyj.pojo.kh;
import com.hqyj.service.KhService;
import com.hqyj.service.KhserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Console;
import java.util.HashMap;
import java.util.List;

@Controller
public class KhController {
    @Autowired
    KhService khService;

    //访问 添加客户列表页面页面
    @RequestMapping("/cate-add")
    public String cateadd(){
        return "cate-add";
    }
    //访问 添加联系人列表页面页面
    @RequestMapping("/contact-add")
    public String contactdd(){
        return "contact-add";
    }
    //访问 添加客户服务列表页面页面
    @RequestMapping("/banner-add")
    public String banneradd(){
        return "banner-add";
    }
   

    //访问 编辑客户列表页面页面
    @RequestMapping("/cate-edit")
    public String cateedit(kh kh, ModelMap m){
        System.out.println(kh.getId());
        //根据userId查询
        kh kh1=khService.selectById(kh);
        System.out.println(kh1.getUrl()+"---------------------");
        //把数据传到前端
        m.addAttribute("kh",kh1);
        return "cate-edit";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public HashMap<String,Object> edit(kh kh){
        System.out.println("edit---------------------------------------");
        HashMap<String,Object> map=new HashMap<String,Object>();
        String info=khService.update(kh);
        System.out.println(info+"---------------------------------------");
        map.put("info",info);
        return map;
    }

    //访问 客户列表页面页面
    @RequestMapping("/cate-list")
    public String kh(kh kh, ModelMap m){
        HashMap<String, Object> map=khService.select(kh);
        //把数据传到前端
        m.put("info",map);
        //将查询条件回显
        m.put("vv",kh.getConValue());
        return "cate-list";
    }

    //处理添加客户请求
    @RequestMapping("/addKh")
    @ResponseBody
    public HashMap<String,Object> addKh(kh kh){
        HashMap<String,Object> map = new HashMap<String,Object>();
        //访问注册方法
        String info = khService.add(kh);
        map.put("info",info);
        return map;
    }

    /**
     * 返回联系人列表
     * @param jlId  经理Id
     * @return
     */
    @GetMapping("/getLinkMan")
    public HashMap<String,Object> getLinkMan(Integer jlId){
        HashMap<String,Object> hashMap=new HashMap<>();
        List<kh> khList=khService.getKhByJl(jlId);
        //联系人列表
        hashMap.put("info",khList);
        //共有数据
        hashMap.put("dataNum",khList.size());
        return hashMap;
    }

    /**
     * 添加联系人
     * @param khInfo
     * @return
     */
    @GetMapping("/addLinkMan")
    public String addLinkMan(kh khInfo){
        khService.add(khInfo);
        return "添加成功";
    }
    //处理删除的ajax请求
    @RequestMapping("/del")
    @ResponseBody
    public HashMap<String,Object> del(kh kh){
        HashMap<String,Object> map=new HashMap<String,Object>();
        String info=khService.del(kh);
        map.put("info",info);
        return map;
    }

    /**
     *编辑联系人
     * @param kh
     * @return
     */
    @PostMapping("/linkMan/update")
    public String update(kh kh){
        return khService.updateLinkMan(kh);
    }


    //访问 添加客户服务日程页面
    @RequestMapping("/banner-timeadd")
    public String bannertimeadd(){
        return "banner-timeadd";
    }


    //处理删除的ajax请求
    @RequestMapping("/addKhser")
    @ResponseBody
    public HashMap<String,Object> addKhser(KhSer khSer){
        HashMap<String,Object> map=new HashMap<String,Object>();
        String info=khserService.add(khSer);
        map.put("info",info);
        return map;
    }

    //处理删除的ajax请求
    @RequestMapping("/delKhser")
    @ResponseBody
    public HashMap<String,Object> delKhser(KhSer khSer){
        HashMap<String,Object> map=new HashMap<String,Object>();
        String info=khserService.delKhser(khSer);
        map.put("info",info);
        return map;
    }

}