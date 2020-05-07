package com.dfw.easypoi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.afterturn.easypoi.handler.inter.IExcelExportServer;
import com.dfw.easypoi.entity.UserEntity;
import com.dfw.easypoi.utils.EasyPoiUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.*;

public class TestExcel {


    /*
     * 测试导出单个sheet
     * */
    @Test
    public void testExcelExport() throws IOException {
        List<UserEntity> list = new ArrayList<>();
        int i = 0;
        while (i < 10) {
            UserEntity user = new UserEntity();
            user.setId(i + 1);
            user.setAge(20 + i);
            user.setEmail("abc@163.com");
            user.setName("张三" + i);
            user.setSex(i % 2 == 0 ? 1 : 2);
            user.setTime(new Date());
            list.add(user);
            i++;
        }
        EasyPoiUtils.exportExcel(UserEntity.class, list, "src/main/resources/excel/", "user.xls");
    }


    /*
    测试到处多个sheet
    * */
    @Test
    public void testExportManyExcelSheet() throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int n = 1; n < 4; n++) {
            ExportParams exportParams = new ExportParams("用户信息" + n, "用户信息" + n);
            Object entity = UserEntity.class;
            List<UserEntity> data = new ArrayList<>();
            int i = 0;
            while (i < 10) {
                UserEntity user = new UserEntity();
                user.setId(i * n + 1);
                user.setAge(20 + i);
                user.setEmail("abc@163.com");
                user.setName("张三" + i * n);
                user.setSex(i % 2 == 0 ? 1 : 2);
                user.setTime(new Date());
                data.add(user);
                i++;
            }
            // 构建map
            Map<String, Object> map = new HashMap<>();
            map.put("title", exportParams);
            map.put("entity", entity);
            map.put("data", data);
            list.add(map);
        }
        EasyPoiUtils.exportExcel(list, "src/main/resources/excel/", "user_many_sheet.xls");
    }


    /*
     测试导入excel  单个sheet的情况
    * */
    @Test
    public void testImportExcel() throws Exception {

        String filePath = "D:\\workspaces\\SpringbootStudyAll\\springboot-easypoi\\src\\main\\resources\\excel\\user.xls";
        //String filePath = "src/main/resources/excel/user.xls";
        //方法一
        List<UserEntity> list = EasyPoiUtils.importExcel(
                new File(filePath),
                UserEntity.class, new ImportParams());
        list.forEach((user) -> {
            System.out.println(user);
        });


        ///方法二
        /*try {
            ImportParams params = new ImportParams();
            ExcelImportResult<Map> result = ExcelImportUtil.importExcelMore(
                    new File(filePath),
                    Map.class, params);
            for (int i = 0; i < result.getList().size(); i++) {
                System.out.println(result.getList().get(i));
            }
            System.out.println(result.getMap());
        } catch (Exception e) {
            System.out.println(e);
        }*/


        ///  方法三
      /*  ImportParams params = new ImportParams();
        List<UserEntity> result = ExcelImportUtil.importExcel(
                new FileInputStream(new File(filePath)),
                UserEntity.class, params);
        result.forEach((user) -> {
            System.out.println(user);
        });*/

    }


    /*
    * 测试读取excel的多个sheet
    * */
    @Test
    public void testImportExcelManySheet(){
        String filePath = "D:\\workspaces\\SpringbootStudyAll\\springboot-easypoi\\src\\main\\resources\\excel\\user_many_sheet.xls";
        //String filePath = "src/main/resources/excel/user.xls";
        //方法一
        List<UserEntity> list = EasyPoiUtils.importExcel(
                new File(filePath),
                UserEntity.class, new ImportParams());
        list.forEach((user) -> {
            System.out.println(user);
        });
    }

    /*
    测试导出大数据量
    * */
    @Test
    public void testExportBigData(){

        Workbook workbook = null;
        Date         start    = new Date();
        ExportParams params   = new ExportParams("大数据测试", "测试");
        workbook = ExcelExportUtil.exportBigExcel(params, UserEntity.class, new IExcelExportServer() {

            @Override
            public List<Object> selectListForExcelExport(Object obj, int page) {
                if (((int) obj) == page) {
                    return null;
                }
                List<Object> list = new ArrayList<Object>();
                for (int i = 0; i < 10000; i++) {
                    UserEntity user = new UserEntity();
                    user.setId(i + 1);
                    user.setAge(20 + i);
                    user.setEmail("abc@163.com");
                    user.setName("张三" + i);
                    user.setSex(i % 2 == 0 ? 1 : 2);
                    user.setTime(new Date());
                    list.add(user);
                }
                return list;
            }
        }, 10);

        System.out.println(new Date().getTime() - start.getTime());
        /*File savefile = new File("D:/home/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }*/
        try {
            FileOutputStream fos = new FileOutputStream("D:\\workspaces\\SpringbootStudyAll\\springboot-easypoi\\src\\main\\resources\\excel\\bigDataExport.xlsx");
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
