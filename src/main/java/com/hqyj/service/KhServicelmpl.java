package com.hqyj.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.dao.KhDao;
import com.hqyj.pojo.UserInfo;
import com.hqyj.pojo.kh;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class KhServicelmpl implements KhService {
    //创建一个userInfoDao的实现类对象
    @Autowired(required = false)
    KhDao khDao;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    //获取发件人邮箱
    @Value("${spring.mail.username}")
    String sendEmail;
    //创建发送邮件的对象
    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public String add(com.hqyj.pojo.kh kh) {
        int n = khDao.addKh(kh);
        if(n>0){
            return "添加成功";
        }
        return "添加失败";
    }

    @Override
    public HashMap<String, Object> select(kh kh) {

        HashMap<String, Object> map=new HashMap<String, Object>();
        //设置分页参数
        PageHelper.startPage(kh.getPage(),kh.getRow());

        List<kh> list=new ArrayList<>();

        //判断用户输入的查询条件是否有值
        if(kh.getConValue().equals("")){
                //查询数据库
                list=khDao.select();

        }else{
            //根据用户选择查询条件判断用户需要查询
            if(kh.getCondition().equals("客户编号")){
                //设置用户输入的查询条件
                kh.setId(Integer.parseInt(kh.getConValue()));
                list=khDao.findById(kh);
            }else if(kh.getCondition().equals("客户姓名")){
                kh.setName(kh.getConValue());
                list=khDao.findByName(kh);
            }else if(kh.getCondition().equals("客户经理编号")){
                kh.setManagerid(Integer.parseInt(kh.getConValue()));
                list=khDao.findByManagerId(kh);
            }else if(kh.getCondition().equals("客户经理姓名")){
                kh.setManager(kh.getConValue());
                list=khDao.findByManager(kh);
            }else {
                list=khDao.select();
            }
        }
        //把查询的数据转换成分页对象
        PageInfo<kh> page = new PageInfo<kh>(list);
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
    public HashMap<String, Object> selectSr(kh kh) {
        HashMap<String, Object> map=new HashMap<String, Object>();
        //设置分页参数
        PageHelper.startPage(kh.getPage(),kh.getRow());

        List<kh> list=new ArrayList<>();

        //判断用户输入的查询条件是否有值
//        if(kh.getConValue().equals("")){
            //查询数据库
            list=khDao.selectSr();
//
//        }else{
//            //根据用户选择查询条件判断用户需要查询
//            if(kh.getCondition().equals("客户编号")){
//                //设置用户输入的查询条件
//                kh.setId(Integer.parseInt(kh.getConValue()));
//                list=khDao.findById(kh);
//            }else if(kh.getCondition().equals("客户姓名")){
//                kh.setName(kh.getConValue());
//                list=khDao.findByName(kh);
//            }else if(kh.getCondition().equals("客户经理编号")){
//                kh.setManagerid(Integer.parseInt(kh.getConValue()));
//                list=khDao.findByManagerId(kh);
//            }else if(kh.getCondition().equals("客户经理姓名")){
//                kh.setManager(kh.getConValue());
//                list=khDao.findByManager(kh);
//            }else {
//                list=khDao.select();
//            }
//        }
        //把查询的数据转换成分页对象
        PageInfo<kh> page = new PageInfo<kh>(list);
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
    public kh selectById(kh kh) {
        return khDao.selectById(kh);
    }

    @Override
    public String update(kh kh) {
        System.out.println("editservice---------------------------------------");

            int num = khDao.update(kh);
        System.out.println(num+"---------------------------------------");
            if (num > 0) {
                System.out.println("editservicecg---------------------------------------");
                return "修改成功";
            }
        return "修改失败";
    }

    @Override
    public String del(kh kh) {
        int num=khDao.del(kh);
        if(num>0){
            return "删除成功";
        }
        return "删除失败";
    }

    @Override
    public List<kh> getKhByJl(Integer jlId) {
        return khDao.getKhByJl(jlId);
    }

    @Override
    public void excelWrite(HttpServletResponse response) {

        OutputStream output;
        output = null;
        try {
            // 创建HSSFWorkbook对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建HSSFSheet对象,如果要创建多个sheet，就再创建sheet对象
            HSSFSheet sheet;
            sheet = wb.createSheet("客户表");

            // 创建HSSFRow对象，先写入列名
            HSSFRow colName = sheet.createRow(0);
            // 写入入第一行列名
            colName.createCell(0).setCellValue("编号");
            colName.createCell(1).setCellValue("姓名");
            colName.createCell(2).setCellValue("性别");
            colName.createCell(3).setCellValue("照片");
            colName.createCell(4).setCellValue("联系电话");
            colName.createCell(5).setCellValue("邮箱地址");
            colName.createCell(6).setCellValue("出生日期");
            colName.createCell(7).setCellValue("学历");
            colName.createCell(8).setCellValue("身份证号");
            colName.createCell(9).setCellValue("所属地区");
            colName.createCell(10).setCellValue("客户单位");
            colName.createCell(11).setCellValue("客户经理编号");
            colName.createCell(12).setCellValue("客户经理");
            colName.createCell(13).setCellValue("备注");
            //查询员工所有信息
            List<kh> list = khDao.select();

            for (int i = 1; i <=list.size(); i++) {
                //从第二行开始写入数据
                HSSFRow row = sheet.createRow(i);
                row.createCell(0).setCellValue(list.get(i-1).getId());
                row.createCell(1).setCellValue(list.get(i-1).getName());
                row.createCell(2).setCellValue(list.get(i-1).getGender());
                row.createCell(3).setCellValue(list.get(i-1).getUrl());
//                String roleName="";
//                if(list.get(i-1).getRoleInfoList()!=null){
//                    for(RoleInfo r :list.get(i-1).getRoleInfoList()){
//                        roleName+=r.getrName()+"";
//                    }
//                }
                row.createCell(4).setCellValue(list.get(i-1).getPhone());
                row.createCell(5).setCellValue(list.get(i-1).getEmail());
                row.createCell(6).setCellValue(list.get(i-1).getBdate());
                row.createCell(7).setCellValue(list.get(i-1).getEdu());
                row.createCell(8).setCellValue(list.get(i-1).getIdn());
                row.createCell(9).setCellValue(list.get(i-1).getRegion());
                row.createCell(10).setCellValue(list.get(i-1).getCompany());
                row.createCell(11).setCellValue(list.get(i-1).getManagerid());
                row.createCell(12).setCellValue(list.get(i-1).getManager());
                row.createCell(13).setCellValue(list.get(i-1).getTips());
            }
            //输出Excel文件到页面
            output=response.getOutputStream();
            String fileName="客户表";
            //解决中文文件名下载 变成下划线的问题
            fileName=new String(fileName.getBytes("utf-8"),"ISO8859-1")+"";
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename="+fileName+".xls");
            response.setContentType("application/msexcel");
            wb.write(output);

        } catch (Exception e) {

            e.printStackTrace();
        }finally{
            try {
                output.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    @Override
    public HashMap<String, Object> sendZf1(kh kh, HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        try {
//            //从session中获取当前用户信息
//            HttpSession session = request.getSession();
            //创建普通邮件对象
            SimpleMailMessage message = new SimpleMailMessage();
            //设置发件人邮箱
            message.setFrom(sendEmail);
            //设置收件人邮箱
            message.setTo(kh.getEmail());
            //设置邮件标题
            message.setSubject("桔子有限公司-生日祝福");
//            // 生成随机验证码
//            Random rd = new Random();
//            String valCode = rd.nextInt(9999)+"";
            //设置邮件正文
            message.setText("尊敬的"+kh.getName()+"女士:" +
                    " 感谢事业路上有您的帮助，桔子有限公司真挚祝福您生日快乐，祝您事业一帆风顺，好运接二连三，财运五湖四海，智慧六出奇计，才学七步成章，做事八面玲珑，地位九五至尊，诸事十全十美！");
            //发送邮件
            javaMailSender.send(message);
            //发送成功
//            //把验证码存入session中
//            session.setAttribute("valCode",valCode);
//            session.setAttribute("name",userInfoDao.selectByEmail(user));

            map.put("info","发送成功");
            return map;

        } catch (Exception e) {
            System.out.println("发送邮件时发生异常！");
            e.printStackTrace();
        }
        map.put("info","发送邮件异常");
        return map;
    }
    public HashMap<String, Object> sendZf(kh kh, HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        try {
//            //从session中获取当前用户信息
//            HttpSession session = request.getSession();
            //创建普通邮件对象
            SimpleMailMessage message = new SimpleMailMessage();
            //设置发件人邮箱
            message.setFrom(sendEmail);
            //设置收件人邮箱
            message.setTo(kh.getEmail());
            //设置邮件标题
            message.setSubject("桔子有限公司-生日祝福");
//            // 生成随机验证码
//            Random rd = new Random();
//            String valCode = rd.nextInt(9999)+"";
            //设置邮件正文
            message.setText("尊敬的"+kh.getName()+"先生:" +
                    " 感谢事业路上有您的帮助，桔子有限公司真挚祝福您生日快乐，祝您事业一帆风顺，好运接二连三，财运五湖四海，智慧六出奇计，才学七步成章，做事八面玲珑，地位九五至尊，诸事十全十美！");
            //发送邮件
            javaMailSender.send(message);
            //发送成功
//            //把验证码存入session中
//            session.setAttribute("valCode",valCode);
//            session.setAttribute("name",userInfoDao.selectByEmail(user));

            map.put("info","发送成功");
            return map;

        } catch (Exception e) {
            System.out.println("发送邮件时发生异常！");
            e.printStackTrace();
        }
        map.put("info","发送邮件异常");
        return map;
    }

    @Override
    public String updateLinkMan(kh kh) {
        int num = khDao.updateLinkMan(kh);
        if (num > 0) {
            return "修改成功";
        }
        return "修改失败";
    }
}
