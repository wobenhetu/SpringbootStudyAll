package com.dfw.easypoi.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.dfw.easypoi.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class EasyPoiUtils {

    /**
     * 方法说明
     * @param pojoClass
     * @param dataSet
     * @param path
     * @param filename
     * @return void
     * @author 邓奋武
     * @date 2020/5/7
     * @throws
     * @update
     * @see EasyPoiUtils#exportExcel()
     * @since V1.0
     */
    public static void exportExcel(Class<?> pojoClass, Collection<?> dataSet, String path, String filename) throws IOException {

        File savefile = new File(path);
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), pojoClass, dataSet);
        FileOutputStream fos = new FileOutputStream(path+filename);
        workbook.write(fos);
        fos.close();
    }

    /**
     * 根据Map创建对应的Excel(一个excel 创建多个sheet)
     * @param list list 多个Map key title 对应表格Title key entity 对应表格对应实体 key data
     *      *             Collection 数据
     * @param path 路径
     * @param filename&emsp;文件名
     * @throws IOException
     */
    public static void  exportExcel(List<Map<String, Object>> list, String path, String filename) throws IOException{
        File savefile = new File(path);
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);

        FileOutputStream fos = new FileOutputStream(path+filename);
        workbook.write(fos);
        fos.close();
    }


    /**
     * 导入excel
     * @param file
     * @param pojoClass
     * @param params
     * @param <T>
     * @return
     */
    public static <T>List<T> importExcel(File file, Class<?> pojoClass, ImportParams params){
        List<T> list = ExcelImportUtil.importExcel(file, UserEntity.class, params);
        return list;
    }


   /**
    * 方法说明
    * @param filePath
    * @param titleRows
    * @param headerRows
    * @param pojoClass
    * @return java.util.List<T>
    * @author 邓奋武
    * @date 2020/5/7
    * @throws
    * @update
    * @see EasyPoiUtils#importExcel()
    * @since V1.0
    */
    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        //判断文件是否存在
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("模板不能为空");
        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }
}