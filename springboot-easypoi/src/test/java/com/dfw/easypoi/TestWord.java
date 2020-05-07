package com.dfw.easypoi;

import cn.afterturn.easypoi.entity.ImageEntity;
import cn.afterturn.easypoi.word.WordExportUtil;
import com.dfw.easypoi.entity.Person;
import com.dfw.easypoi.entity.UserEntity;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestWord {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd");

    /*
    * 测试导出word，按模板
    * */
    @Test
    public void testExportword() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("department", "Easypoi");
        map.put("time", format.format(new Date()));
        Person person = new Person();
        person.setName("JueYue");
        map.put("p", person);
        ImageEntity image = new ImageEntity();
        image.setHeight(200);
        image.setWidth(500);
        String imgPath = "D:\\workspaces\\SpringbootStudyAll\\springboot-easypoi\\src\\main\\resources\\images\\testCode.png";
        image.setUrl(imgPath);
        image.setType(ImageEntity.URL);
        map.put("testCode", image);
        String templatePath = "D:\\workspaces\\SpringbootStudyAll\\springboot-easypoi\\src\\main\\resources\\wordtemplate\\Image.docx";
        XWPFDocument doc = WordExportUtil.exportWord07(
                templatePath, map);
        FileOutputStream fos = new FileOutputStream("D:\\home\\excel\\image.docx");
        doc.write(fos);
        fos.close();
    }

/*
    public void testPoi(){
        String title = "导出测试";
        String text = "具体内容哈哈哈哈哈";
        try {
            //word内容
            String content="<html><body>" +
                    "<p style=\"text-align: center;\"><span style=\"font-family: 黑体, SimHei; font-size: 24px;\">"
                    + title + "</span></p>" + text + "</body></html>";
            byte b[] = content.getBytes("GBK");  //这里是必须要设置编码的，不然导出中文就会乱码。
            ByteArrayInputStream bais = new ByteArrayInputStream(b);//将字节数组包装到流中

            *//*
             * 关键地方
             * 生成word格式 *//*
            POIFSFileSystem poifs = new POIFSFileSystem();
            DirectoryEntry directory = poifs.getRoot();
            DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
            HttpServletRequest request = null;
            HttpServletResponse response = null;
            //输出文件
            request.setCharacterEncoding("utf-8");
            response.setContentType("application/msword");//导出word格式
            response.addHeader("Content-Disposition", "attachment;filename=" +
                    new String(title.getBytes("GB2312"),"iso8859-1") + ".doc");
            ServletOutputStream ostream = response.getOutputStream();
            poifs.writeFilesystem(ostream);
            bais.close();
            ostream.close();
            poifs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }*/




    @Test
    public void writeWordFile() {
        String path = "D:/";
        try {
            if (!"".equals(path)) {
                // 检查目录是否存在
                File fileDir = new File(path);
                if (fileDir.exists()) {
                    // 生成临时文件名称
                    String fileName = "a11.doc";
                    //使用传html 转word
                    String contentConcat = "<html>" +
                            "<meta charset='UTF-8'>"+
                            "<head>信息</head>" +
                            "<body>" +
                            "<table>" +
                            "<tr>" +
                            "<td style='color:red;border:1px solid red;'>姓名</td>" +
                            "<td style='font-size:36px;border:1px solid red;'>性别</td>" +
                            "<tr>" +
                            "</table>" +
                            "<img src=\"file:///D:\\workspaces\\SpringbootStudyAll\\springboot-easypoi\\src\\main\\resources\\imgs\\testCode.png\"  alt=\"上海鲜花港 - 郁金香\" />"+
                            "</body>" +
                            "</html>";
                    byte b[] = contentConcat.getBytes("UTF-8");
                    ByteArrayInputStream bais = new ByteArrayInputStream(b);
                    POIFSFileSystem poifs = new POIFSFileSystem();
                    DirectoryEntry directory = poifs.getRoot();
                    DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
                    FileOutputStream ostream = new FileOutputStream(path + fileName);
                    poifs.writeFilesystem(ostream);
                    bais.close();
                    ostream.close();

/*
                访问桌面上的html转word
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    FileInputStream fis = new FileInputStream("C:\\Users\\ghost\\Desktop\\1.html");
                    int b = -1;
                    while ((b = fis.read()) != -1) {
                        bos.write(b);
                    }
                    fis.close();
                    bos.close();
                    ByteArrayInputStream bais = new ByteArrayInputStream(bos.toByteArray());
                    POIFSFileSystem poifs = new POIFSFileSystem();
                    DirectoryEntry directory = poifs.getRoot();
                    DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
                    FileOutputStream ostream = new FileOutputStream(path + fileName);
                    poifs.writeFilesystem(ostream);
                    bais.close();
                    ostream.close();
*/
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
