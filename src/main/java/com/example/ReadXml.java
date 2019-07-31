package com.example;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class ReadXml {

    public static void main(String[] args) throws DocumentException {
        new ReadXml().testReadXml();
    }

    public  void testReadXml() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(getClassPath("student.xml"));
        //获取根节点
        Element rootElement = document.getRootElement();
        getNodes(rootElement);
    }

    public void getNodes(Element rootElement ){
        // 当前节点值value
        String value = rootElement.getTextTrim();
        if (!StringUtils.isEmpty(value)) {
            System.out.println(rootElement.getName()+":" + value+"(节点：值)");
        }else {
            System.out.println(rootElement.getName()+"(当前节点名称)");
        }

        //获取属性信息
        List<Attribute> attributes = rootElement.attributes();
        for(Attribute attribute :attributes){
            System.out.println(attribute.getName()+":"+attribute.getText()+"(属性：值)");
        }
        // 使用迭代器遍历,继续遍历子节点
        Iterator<Element> iterator = rootElement.elementIterator();
        while (iterator.hasNext()){
            Element element = iterator.next();
            getNodes(element);
        }


    }

    public InputStream getClassPath(String xmlPath){
       return getClass().getClassLoader().getResourceAsStream(xmlPath);
    }

}
