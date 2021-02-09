package com.ruoyi.quartz.util;

import com.ruoyi.common.utils.spring.SpringUtils;
import org.dom4j.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author zss
 * @create 2021-01-27 20:03
 */
public class CommonUtil {

    private Map<String, Object> result = new HashMap<String, Object>();
    private Map<String, Object> result1 = new HashMap<String, Object>();

    public void parseXml(String xml) {
        try {
            Document doc = DocumentHelper.parseText(xml);
            Element root = doc.getRootElement();
            this.getNode(root,"job", false);
            for(Map.Entry<String, Object> entry : result.entrySet()) {
                System.out.println(entry.getKey());
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获指定job下的所有节点属性
     * @param e
     * @param uid
     * @param isTargetNode
     */
    public void getNode(Element e, String uid, boolean isTargetNode) {
        String eName = e.getName();
        String eValue = e.getText();
        List<Attribute> attributes = e.attributes();
        if (isTargetNode) {
            System.out.println("===========节点名称: " + eName + ", 节点值：" + eValue);
            for (Attribute attribute : attributes) {
                String aName = attribute.getName();
                String aValue = attribute.getValue();
                System.out.println("属性名称:" + aName + ", 属性值: " + aValue);
            }
            isTargetNode = false;
        } else {
            for (Attribute attribute : attributes) {
                String aName = attribute.getName();
                String aValue = attribute.getValue();
                if(uid.equals(aValue)) {
                    isTargetNode = true;
                }
            }
        }
        List<Element> elementList = e.elements();
        for (Element element : elementList) {
            getNode(element, uid, isTargetNode);
        }
    }

    /**
     * 获取指定节点下所有子节点值
     * @param element
     * @param eleName1
     * @return
     */


    public static void main(String[] args) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<sys xmlns=\"http://com.liconic.sys\">\n" +
                "    <sysName>KIWI</sysName>\n" +
                "    <jobs>\n" +
                "        <job id=\"3368\" name=\"Job File\" user=\"ADMIN\" status=\"\" uid=\"aafc0eaf-0f72-4711-a438-5d3cafa20bfe\">\n" +
                "            <tasks id=\"5841\" name=\"Export\" cstat=\"Done\" run=\"0\">\n" +
                "                <props name=\"Target Partition\" val=\"Buffer\"/>\n" +
                "                <stats stat=\"Created\" user=\"ADMIN\" date=\"Wed Nov 18 11:40:07 CST 2020\"/>\n" +
                "                <stats stat=\"Waiting\" user=\"ADMIN\" date=\"Wed Nov 18 12:44:33 CST 2020\"/>\n" +
                "                <stats stat=\"Running\" user=\"ADMIN\" date=\"Wed Nov 18 12:44:35 CST 2020\"/>\n" +
                "                <stats stat=\"Done\" user=\"ADMIN\" date=\"Wed Nov 18 12:46:04 CST 2020\"/>\n" +
                "            </tasks>\n" +
                "            <tasks id=\"5842\" name=\"Export\" cstat=\"Done\" run=\"0\">\n" +
                "                <props name=\"Target Partition\" val=\"Buffer\"/>\n" +
                "                <stats stat=\"Created\" user=\"ADMIN\" date=\"Wed Nov 18 11:40:07 CST 2020\"/>\n" +
                "                <stats stat=\"Waiting\" user=\"ADMIN\" date=\"Wed Nov 18 12:44:37 CST 2020\"/>\n" +
                "                <stats stat=\"Running\" user=\"ADMIN\" date=\"Wed Nov 18 12:44:38 CST 2020\"/>\n" +
                "                <stats stat=\"Done\" user=\"ADMIN\" date=\"Wed Nov 18 12:48:40 CST 2020\"/>\n" +
                "            </tasks>\n" +
                "            <tasks id=\"5843\" name=\"Export\" cstat=\"Done\" run=\"0\">\n" +
                "                <props name=\"Target Partition\" val=\"Buffer\"/>\n" +
                "                <stats stat=\"Created\" user=\"ADMIN\" date=\"Wed Nov 18 11:40:07 CST 2020\"/>\n" +
                "                <stats stat=\"Waiting\" user=\"ADMIN\" date=\"Wed Nov 18 12:44:40 CST 2020\"/>\n" +
                "                <stats stat=\"Running\" user=\"ADMIN\" date=\"Wed Nov 18 12:44:42 CST 2020\"/>\n" +
                "                <stats stat=\"Done\" user=\"ADMIN\" date=\"Wed Nov 18 12:47:21 CST 2020\"/>\n" +
                "            </tasks>\n" +
                "            <tasks id=\"5844\" name=\"Export\" cstat=\"Done\" run=\"0\">\n" +
                "                <props name=\"Target Partition\" val=\"Buffer\"/>\n" +
                "                <stats stat=\"Created\" user=\"ADMIN\" date=\"Wed Nov 18 11:40:07 CST 2020\"/>\n" +
                "                <stats stat=\"Waiting\" user=\"ADMIN\" date=\"Wed Nov 18 12:44:43 CST 2020\"/>\n" +
                "                <stats stat=\"Running\" user=\"ADMIN\" date=\"Wed Nov 18 12:44:45 CST 2020\"/>\n" +
                "                <stats stat=\"Done\" user=\"ADMIN\" date=\"Wed Nov 18 12:49:59 CST 2020\"/>\n" +
                "            </tasks>\n" +
                "            <tasks id=\"5845\" name=\"Export\" cstat=\"Done\" run=\"0\">\n" +
                "                <props name=\"Target Partition\" val=\"Buffer\"/>\n" +
                "                <stats stat=\"Created\" user=\"ADMIN\" date=\"Wed Nov 18 11:40:07 CST 2020\"/>\n" +
                "                <stats stat=\"Waiting\" user=\"ADMIN\" date=\"Wed Nov 18 12:44:47 CST 2020\"/>\n" +
                "                <stats stat=\"Running\" user=\"ADMIN\" date=\"Wed Nov 18 12:44:48 CST 2020\"/>\n" +
                "                <stats stat=\"Done\" user=\"ADMIN\" date=\"Wed Nov 18 12:51:17 CST 2020\"/>\n" +
                "            </tasks>\n" +
                "            <tasks id=\"5840\" name=\"Job File\" cstat=\"Done\" run=\"0\">\n" +
                "                <stats stat=\"Creating\" user=\"ADMIN\" date=\"Wed Nov 18 08:53:41 CST 2020\"/>\n" +
                "                <stats stat=\"Created\" user=\"ADMIN\" date=\"Wed Nov 18 09:03:37 CST 2020\"/>\n" +
                "                <stats stat=\"Waiting\" user=\"ADMIN\" date=\"Wed Nov 18 09:03:37 CST 2020\"/>\n" +
                "                <stats stat=\"Running\" user=\"ADMIN\" date=\"Wed Nov 18 09:04:46 CST 2020\"/>\n" +
                "                <stats stat=\"Done\" user=\"ADMIN\" date=\"Wed Nov 18 11:40:08 CST 2020\"/>\n" +
                "            </tasks>\n" +
                "        </job>\n" +
                "        <job id=\"3369\" name=\"Job File\" user=\"ADMIN\" status=\"\" uid=\"20201123123\">\n" +
                "            <props name=\"Consolidation\" val=\"\"/>\n" +
                "            <tasks id=\"5846\" name=\"Job File\" info=\"Exchange Target Plates Tube Picker=TubePicker and device=Storage1, cassette=46, level=6, To device=Storage1, cassette=47, level=18\" cstat=\"Running\" run=\"1\" step=\"Exchange Target Rack\">\n" +
                "                <stats stat=\"Creating\" user=\"ADMIN\" date=\"Wed Nov 18 14:00:15 CST 2020\"/>\n" +
                "                <stats stat=\"Created\" user=\"ADMIN\" date=\"Wed Nov 18 14:26:19 CST 2020\"/>\n" +
                "                <stats stat=\"Waiting\" user=\"ADMIN\" date=\"Wed Nov 18 14:26:19 CST 2020\"/>\n" +
                "                <stats stat=\"Running\" user=\"ADMIN\" date=\"Wed Nov 18 14:28:51 CST 2020\"/>\n" +
                "            </tasks>\n" +
                "        </job>\n" +
                "    </jobs>\n" +
                "</sys>\n";
        CommonUtil util = new CommonUtil();
        util.parseXml(xml);
    }
}
