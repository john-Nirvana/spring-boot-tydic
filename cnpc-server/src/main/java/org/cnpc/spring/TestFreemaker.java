package org.cnpc.spring;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TestFreemaker {
    public static void main(String[] args) {

        Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        String templateContent = "欢迎：'${name}'，<#if test??>ddd</#if>, ok in (<#list totalList as being>${being}<#if (being_index != (totalList?size - 1) )>,</#if></#list>)";
        stringLoader.putTemplate("myTemplate", templateContent);

        cfg.setTemplateLoader(stringLoader);

        try {
            Template template = cfg.getTemplate("myTemplate", "utf-8");
            Map root = new HashMap();
            root.put("name", "javaboy2012");
            List<String> listData = new ArrayList<String>();
            listData.add("abc");
            listData.add("test");
            listData.add("danshi");

            root.put("test", "5258");
            root.put("totalList", listData);

            StringWriter writer = new StringWriter();
            try {
                template.process(root, writer);
                System.out.println(writer.toString());
            } catch (TemplateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}