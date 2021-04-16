package com.yidu.service.impl;

import com.yidu.entity.Userinfo;
import com.yidu.dao.UserinfoDao;
import com.yidu.service.UserinfoService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * (Userinfo)表服务实现类
 *
 * @author makejava
 * @since 2021-04-08 10:59:59
 */
@Service("userinfoService")
public class UserinfoServiceImpl implements UserinfoService {
    @Resource
    private UserinfoDao userinfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    @Override
    public Userinfo queryById(Integer userid) {
        return this.userinfoDao.queryById(userid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Userinfo> queryAllByLimit(int offset, int limit) {
        return this.userinfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userinfo 实例对象
     * @return 实例对象
     */
    @Override
    public Userinfo insert(Userinfo userinfo) {
        this.userinfoDao.insert(userinfo);
        return userinfo;
    }

    /**
     * 修改数据
     *
     * @param userinfo 实例对象
     * @return 实例对象
     */
    @Override
    public Userinfo update(Userinfo userinfo) {
        this.userinfoDao.update(userinfo);
        return this.queryById(userinfo.getUserid());
    }

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer userid) {
        return this.userinfoDao.deleteById(userid) > 0;
    }

    /**
     * 将用户表数据写入excel文件中
     * @param request
     * @return
     */
    @Override
    public String queryAll(HttpServletRequest request) {
        //调用查询所有用户数据的方法
        List<Userinfo> userinfos = this.userinfoDao.queryAll(null);
        //创建 excel - 2010处理对象
        Workbook workbook=new XSSFWorkbook();
        //先创建sheet (创建表格中一页)
        Sheet userInfo = workbook.createSheet("UserInfo");
        /*//创建第一行
        Row row = userInfo.createRow(-1);
        //创建第一行的列,并且设置值
        row.createCell(0).setCellValue("userid");
        row.createCell(1).setCellValue("username");
        row.createCell(2).setCellValue("userpass");
        row.createCell(3).setCellValue("compellation");
        row.createCell(4).setCellValue("state");
        row.createCell(5).setCellValue("headimg");*/
        for (int i = 0; i < userinfos.size(); i++) {
            Userinfo userinfo = userinfos.get(i);
            Row rows = userInfo.createRow(i);//创建行
            rows.createCell(0).setCellValue(userinfo.getUserid());//创建列并赋值
            rows.createCell(1).setCellValue(userinfo.getUsername());
            rows.createCell(2).setCellValue(userinfo.getUserpass());
            rows.createCell(3).setCellValue(userinfo.getCompellation());
            rows.createCell(4).setCellValue(userinfo.getState());
            rows.createCell(5).setCellValue(userinfo.getHeadimg());
        }
        String realPath = request.getServletContext().getRealPath("/myFile");
        File files = new File(realPath);
        if (!files.exists()){
            files.mkdir();
        }
        //创建xlsx文件
        File file=new File(realPath+"\\UserInfo.xlsx");
        try {
            //创建写入流
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            //将excel对象写入指定的文件
            workbook.write(fileOutputStream);
            //关闭资源
            fileOutputStream.close();
            System.out.println(file.getAbsoluteFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "写入成功！";
    }
}