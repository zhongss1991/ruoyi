package com.ruoyi.system.utils;


import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ruoyi.system.domain.LiconicJob;


public class XmlUtils {

	//xml字符串转对象
	public static Object xmlStrToObject(Class clazz, String xmlStr) {
	   Object xmlObject = null;
	   try {
	       JAXBContext context = JAXBContext.newInstance(clazz);
	       // 进行将Xml转成对象的核心接口
	       Unmarshaller unmarshaller = context.createUnmarshaller();
	       StringReader sr = new StringReader(xmlStr);
	       xmlObject = unmarshaller.unmarshal(sr);
	   } catch (JAXBException e) {
	       e.printStackTrace();
	   }
	   return xmlObject;
	}
	
	//解析分区xml
	 public List partitions(String xml) {
		 List resList = new ArrayList();
		 try {
			Document doc = DocumentHelper.parseText(xml);
			Element rootEle = doc.getRootElement();
			//获取根节点
			Iterator it = rootEle.elementIterator("content");
			while(it.hasNext()) {
				Element recordEle = (Element) it.next();
				Iterator its = recordEle.elementIterator("partitions");
				while(its.hasNext()) {
					Element itemEle = (Element) its.next();
					String partitions = itemEle.getStringValue();
					Attribute id = itemEle.attribute("id");
					String idText = id.getText();
					resList.add(idText.trim().replace(" ", "%20"));
				}
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resList;
	 }
	 
	 //解析盒子xml
	 public static List<Map<String, String>> plates(String xml) {
		 List<Map<String, String>> platesList = new ArrayList<Map<String, String>>();
		 String barcode = null;
		 String plateId = null;
		 try {
			Document doc = DocumentHelper.parseText(xml);
			Element rootEle = doc.getRootElement();
			Iterator it = rootEle.elementIterator("content");
			while(it.hasNext()) {
				Element recordEle = (Element) it.next();
				Iterator its = recordEle.elementIterator("partitions");
				while(its.hasNext()) {
					Element itemEle = (Element) its.next();
					Iterator itPlates = itemEle.elementIterator("plates");
					while(itPlates.hasNext()) {
						Map<String, String> plateMap = new HashMap<String, String>();
						Element itemPlate = (Element) itPlates.next();
						barcode = itemPlate.elementText("barcode");
						plateId = itemPlate.attributeValue("id");
						plateMap.put("barcode", barcode);
						plateMap.put("plateId", plateId);
						platesList.add(plateMap);
					}
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return platesList;
	 }
	 
	 public static Map plates2(String xml) 
	 {
		 List platesList = new ArrayList();
		 int type1 = 0, type2 = 0;
		 String type ;
		 try {
			Document doc = DocumentHelper.parseText(xml);
			Element rootEle = doc.getRootElement();
			Iterator it = rootEle.elementIterator("content");
			while(it.hasNext()) {
				Element recordEle = (Element) it.next();
				Iterator its = recordEle.elementIterator("partitions");
				while(its.hasNext()) {
					Element itemEle = (Element) its.next();
					Iterator itPlates = itemEle.elementIterator("plates");
					while(itPlates.hasNext()) {
						Element itemPlate = (Element) itPlates.next();
						type = itemPlate.attributeValue("type");
						if(type.equals("2")) {
							type1 ++;
						} else if(type.equals("3")){
							type2 ++;
						}
					}
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("type1", type1);
		map.put("type2", type2);
		return map;
	 }
	 
	 //解析样本xml
	 public List tubes(String xml) {
		 List tubeList = new ArrayList();
		 Document doc;
		try {
			doc = DocumentHelper.parseText(xml);
			Element rootEle = doc.getRootElement();
			Iterator it = rootEle.elementIterator("content");
			while(it.hasNext()) {
				Element recordEle = (Element) it.next();
				Iterator its = recordEle.elementIterator("partitions");
				while(its.hasNext()) {
					Element itemEle = (Element) its.next();
					Iterator itPlates = itemEle.elementIterator("plates");
					while(itPlates.hasNext()) {
						Element itemTubes = (Element) itPlates.next();
						Iterator itTubes = itemTubes.elementIterator("tubePositions");
						while(itTubes.hasNext()) {
							Element itemTube = (Element) itTubes.next();
							Element itemTubeValue = itemTube.element("tube");
							if(itemTubeValue != null) {
								tubeList.add(itemTubeValue.elementText("barcode"));
							}
						}
					}
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return tubeList;
	 }
	 
	 //解析挑管子返回结果xml
	 public Map parsePickTubesResult(String xml) {
		 Document doc = null;
		 Map<String, String> result = new HashMap<String, String>();
		 String status = null;
		 String errMsg = null;
		 try {
			doc = DocumentHelper.parseText(xml);
			Element rootEle = doc.getRootElement();
			Iterator it = rootEle.elementIterator("Answer");
			while(it.hasNext()) {
				Element answerEle = (Element)it.next();
				status = answerEle.elementText("Status");
				errMsg = answerEle.elementText("ErrMsg");
			}
			System.out.println("status:" + status + ", errMsg:" + errMsg);
			result.put("status", status);
			result.put("errMsg", errMsg);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return result;
	 }
	 
	 //解析job状态
	 public List<LiconicJob> parseJob(String xml, String jobNo) 
	 {
		 List<LiconicJob> liconicJobs = new ArrayList<LiconicJob>();
		 Document doc = null;
		 String result = null;
		 String uid = null;
		 LiconicJob liconicJob = null;
		 try {
			doc = DocumentHelper.parseText(xml);
			Element rootEle = doc.getRootElement();
			Iterator it = rootEle.elementIterator("jobs");
			while(it.hasNext()) {
				Element jobsEle = (Element)it.next();
				Iterator itJob = jobsEle.elementIterator("job");
				while(itJob.hasNext()) {
					Element jobEle = (Element)itJob.next();
					uid = jobEle.attributeValue("uid");
					if(uid == jobNo || uid.equals(jobNo))
					{
						Iterator tasksIt = jobEle.elementIterator("tasks");
						while(tasksIt.hasNext())
						{
							Element tasksEle = (Element) tasksIt.next();
							liconicJob = new LiconicJob();
							liconicJob.setCstat(tasksEle.attributeValue("cstat"));
							liconicJob.setErrinfo(tasksEle.attributeValue("errinfo"));
							liconicJob.setJobNm(tasksEle.attributeValue("name"));
							liconicJob.setTaskId(tasksEle.attributeValue("id"));
							System.out.println("name:" + tasksEle.attributeValue("name") + ",errinfo:" + tasksEle.attributeValue("errinfo") + ","
									+ "cstat" + tasksEle.attributeValue("cstat"));
							liconicJobs.add(liconicJob);
						}
					}
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 return liconicJobs;
	 }
	 
	 public static void main(String[] args) {
		 String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
		 		+ "<STXRequest><Command><ID>df39e50a-d41e-47e3-8ac9-cdeaa6db5b5bb</ID>"
		 		+ "<Cmd>PickTubes</Cmd><User>ADMIN</User></Command><Answer><Status>ERR</Status>"
		 		+ "<ErrCode>3</ErrCode><ErrMsg>No connection with KIWI System</ErrMsg></Answer></STXRequest>";
		 XmlUtils x = new XmlUtils();
		 Map map = x.parsePickTubesResult(xml);
		
//		 System.out.println(re.size());
	}
}
