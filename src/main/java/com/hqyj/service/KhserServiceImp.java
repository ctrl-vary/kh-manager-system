package com.hqyj.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.dao.KhSerDao;
import com.hqyj.pojo.KhSer;
import com.hqyj.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class KhserServiceImp implements KhserService {

    @Autowired(required = false)
    KhSerDao khSerDao;

    @Override
    public HashMap<String, Object> select(KhSer khSer) {
        HashMap<String, Object> map=new HashMap<String, Object>();
        //设置分页参数
        PageHelper.startPage(khSer.getPage(),khSer.getRow());

        List<KhSer> list=new ArrayList<>();

        list = khSerDao.select();
        //把查询的数据转换成分页对象
        PageInfo<KhSer> page = new PageInfo<KhSer>(list);
        //获取分页的当前页集合
        map.put("list",page.getList());
        //获取总条数
        map.put("total",page.getTotal());
        //总页数
        map.put("totalPage",page.getPages());
        //上一页
        if(page.getPrePage()==0){
            map.put("pre",1);
        }else{
            map.put("pre",page.getPrePage());
        }
        //下一页
        //保持在最后一页
        if(page.getNextPage()==0){
            map.put("next",page.getPages());
        }else{
            map.put("next",page.getNextPage());
        }
        //当前页
        map.put("cur",page.getPageNum());
        return map;
    }

    @Override
    public String add(KhSer khSer) {
        int n = khSerDao.addKhser(khSer);
        if(n>0){
            return "添加成功";
        }
        return "添加失败";

    }

    @Override
    public String delKhser(KhSer khSer) {
        int n = khSerDao.delKhser(khSer);
        if(n>0){
            return "删除成功";
        }
        return "删除失败";

    }
}
