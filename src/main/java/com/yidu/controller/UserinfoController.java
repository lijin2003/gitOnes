package com.yidu.controller;

import com.yidu.entity.Userinfo;
import com.yidu.service.UserinfoService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * (Userinfo)表控制层
 *
 * @author makejava
 * @since 2021-04-08 11:00:00
 */
@Controller
public class UserinfoController {
    /**
     * 服务对象
     */
    @Resource
    private UserinfoService userinfoService;

    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @ResponseBody
    @RequestMapping("selectOne")
    public String selectOne(HttpServletRequest request) {
        return this.userinfoService.queryAll(request);
    }

    /**
     * 读取上传的excel文件,输出结果
     * @param file  上传的excel文件
     * @return
     */
    @ResponseBody
    @RequestMapping("upload")
    public String upload(@RequestParam("file") MultipartFile file,HttpServletRequest request){
        System.out.println("112345678");
        //调用读取excel文件的方法
        List<Userinfo> list=read(file);
        for (Userinfo userinfo : list) {
            System.out.println("userinfo = " + userinfo);
        }
        return "修改成功！";
    }

    /**
     * 读取excel文件,并将读取出来的数据封装到指定集合
     * @param file excel文件
     * @return
     */
    public List<Userinfo> read(MultipartFile file){
        //得到文件名字
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        List<Userinfo> list=new ArrayList<>();
        try {
            //创建读取流
            InputStream fix= file.getInputStream();
            Workbook workbook= null;
            if(originalFilename.endsWith("xls")){
                workbook=new HSSFWorkbook(fix);
            }else{
                workbook=new XSSFWorkbook(fix);
            }
            //取出表格
            Sheet userInfo = workbook.getSheet("UserInfo");
            //拿到表格最大行数
            int lastRowNum = userInfo.getLastRowNum();
            for (int i = 0; i < lastRowNum; i++) {
                //取出每一行每一列的值
                Row row = userInfo.getRow(i);
                double userid = row.getCell(0).getNumericCellValue();
                String username = row.getCell(1).getStringCellValue();
                String userpass = row.getCell(2).getStringCellValue();
                String compellation = row.getCell(3).getStringCellValue();
                double state = row.getCell(4).getNumericCellValue();
                String headimg = row.getCell(5).getStringCellValue();
                //将取出的数据封装到用户对象中
                Userinfo userinfo=new Userinfo();
                userinfo.setUserid((int)userid);
                userinfo.setUsername(username);
                userinfo.setUserpass(userpass);
                userinfo.setCompellation(compellation);
                userinfo.setState((int)state);
                userinfo.setHeadimg(headimg);
                //添加到集合
                list.add(userinfo);
            }
            fix.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}