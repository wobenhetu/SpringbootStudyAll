package com.springboot.designpattern.pattern_builder;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

class XMLUtil {

    //该方法用于从XML配置文件中提取具体类类名，并返回一个实例对象
    public static Object getBean() {
        try {
            //创建文档对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = dFactory.newDocumentBuilder();

            Document doc;

            String filePath = "D:\\workspaces\\springbootdevAll\\springbootdev\\springboot-designpattern\\src\\main\\java\\com\\springboot\\designpattern\\pattern_builder\\config.xml";
            doc = builder.parse(new File(filePath));

            //获取包含类名的文本节点
            NodeList nl = doc.getElementsByTagName("className");

            Node classNode = nl.item(0).getFirstChild();

            String cName = classNode.getNodeValue();

            //通过类名生成实例对象并将其返回,这里的参数： className要使用类的路径，即
            //com.springboot.designpattern.pattern_builder.AngelBuilder
            Class c = Class.forName(cName);

            Object obj = c.newInstance();

            return obj;

        } catch (Exception e) {

            e.printStackTrace();

            return null;

        }
    }
}