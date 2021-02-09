package com.ruoyi.system.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.LiconicSample;

public class UrlConnectionUtil {
	
	XmlUtils xmlUtils = new XmlUtils();
	
	//restful get请求
	public static String urlGet(String url) {
		URL restServiceURL;
		String output = null;
		String o = null;
		try {
			restServiceURL = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
			httpConnection.setRequestMethod("GET");
			httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			if(httpConnection.getResponseCode() != 200) {
				throw new RuntimeException("HTTP GET Request Failed with Error code : " + httpConnection.getResponseCode());
			}
			
			BufferedReader responseReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
			
			while ((output = responseReader.readLine()) != null) {
				o = output;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
	
	//restful post 请求
	public String urlPost(String url, String xmlInfo) {
		String line = "";
		String o = "";
		try {
			URL restServiceUrl = new URL(url);
			URLConnection connection = restServiceUrl.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
//			connection.setRequestProperty("Pragma:", "no-cache");
//			connection.setRequestProperty("Cache-Control", "no-cache");
			connection.setRequestProperty("Content-Type", "text/xml");
			connection.setRequestProperty("Content-length",String.valueOf(xmlInfo.getBytes().length));
			
			OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
			output.write(new String(xmlInfo.getBytes("ISO-8859-1")));
			output.flush();
			output.close();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			while ((line = br.readLine()) != null) {
				o = line;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
	
	//获取所有样本编码
	public List<LiconicSample> getRsult() {
		String url = "http://192.115.110.11:8080/content/webresources/xml/partitions";
		String plateUrl;
		String tubeUrl;
		String tubesOutPut;
		String output = urlGet(url);
		List partitionsList = xmlUtils.partitions(output);
		List<Map<String, String>> paltesList = new ArrayList<Map<String, String>>();
		List tubesList = new ArrayList();
		List<LiconicSample> liconic = new ArrayList();
		LiconicSample liconicSample = null;
		for (int i = 0; i < partitionsList.size(); i++) {
			int n = 1;
			plateUrl = "http://192.115.110.11:8080/content/webresources/xml/partitions/" + partitionsList.get(i) + "?tubesmore=0";
			String platesOutPut = urlGet(plateUrl);
			paltesList = xmlUtils.plates(platesOutPut);
			for (int j = 0; j < paltesList.size(); j++) {
				tubeUrl = "http://192.115.110.11:8080/content/webresources/xml/partitions/" + partitionsList.get(i) + "/plates/" + paltesList.get(j).get("barcode") + "";
				tubesOutPut = urlGet(tubeUrl);
				tubesList = xmlUtils.tubes(tubesOutPut);
				for (int k = 0; k < tubesList.size(); k++) {
					liconicSample =  new LiconicSample();
					liconicSample.setBarcode(tubesList.get(k).toString());
					liconicSample.setPartitions(partitionsList.get(i).toString().replace("%20", " "));
					liconicSample.setPlateId(paltesList.get(j).get("plateId"));
					liconicSample.setPlateCode(paltesList.get(j).get("barcode"));
					liconic.add(liconicSample);
//					System.out.println(tubesList.get(k).toString());
				}
			}
		}
		System.out.println(liconic.size());
		return liconic;
	}
	
	//获取空盒子数量
	public int getEmptyPlateNum(String partition) {
		
		String plateUrl = "http://192.115.110.11:8080/content/webresources/xml/partitions/" + partition + "?tubesless=1";
		String output = urlGet(plateUrl);
		List palteList = xmlUtils.plates(output);
		return palteList.size();
	}
	
	//生成 picktubes xml参数
	public String getPickTubeXml(List<LiconicSample> samples, String jobNo) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8' standalone='yes'?>");
		sb.append("<STXRequest>");
		sb.append("<Command>");
		sb.append("<ID>" + jobNo + "</ID>");
		sb.append("<Cmd>PickTubes</Cmd>");
		sb.append("<User>ADMIN</User>");
		sb.append("</Command>");
		for (int i = 0; i < samples.size(); i++) {
			sb.append("<Tubes>");
			sb.append("<Tube>" + samples.get(i).getBarcode() + "</Tube>");
			sb.append("</Tubes>");
			
		}
		sb.append("</STXRequest>");
		return sb.toString();
	}
	
	//出库盒子
	public String export(String jobNo) 
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8' standalone='yes'?>");
		sb.append("<STXRequest>");
		sb.append("<Command>");
		sb.append("<ID>" + jobNo + "a</ID>");
		sb.append("<Cmd>RetrievePlate</Cmd>");
		sb.append("<User>ADMIN</User>");
		sb.append("<Transferstation>1</Transferstation>");
		sb.append("<Parameters>");
		sb.append("<Parameter>PickJob</Parameter>");
		sb.append("<Value>" + jobNo + "</Value>");
		sb.append("</Parameters>");
		sb.append("<Parameters>");
		sb.append("<Parameter>Target Partition</Parameter>");
		sb.append("<Value>Buffer</Value>");
		sb.append("</Parameters>");
		sb.append("</Command>");
		sb.append("</STXRequest>");
		return sb.toString();
	}
	
	//整理出库完成的盒子
	public String consolidation(String jobNo,String partition, List<LiconicSample> samples)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8' standalone='yes'?>");
		sb.append("<STXRequest>");
		sb.append("<Command>");
		sb.append("<ID>" + jobNo + "b</ID>");
		sb.append("<User>ADMIN</User>");
		sb.append("<Cmd>PickTubes</Cmd>");
		sb.append("<Partition>" + partition + "</Partition>");
		sb.append("<Parameters>");
		sb.append("<Parameter>Consolidation</Parameter>");
		sb.append("</Parameters>");
		sb.append("</Command>");
		for (int i = 0; i < samples.size(); i++) {
			sb.append("<Plates><PltBCR>" + samples.get(i).getPlateCode() + "</PltBCR></Plates>");
		}
		sb.append("</STXRequest>");
		return sb.toString();
	}
	
	public static void main(String[] args) {
		UrlConnectionUtil url = new UrlConnectionUtil();
		String urlInfo = "http://192.115.110.11:8080/Scheduler/webresources/xml/pick";
		String xml = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?><STXRequest><Command><ID>439d2f59-5d09-46de-a3eb-06e08ca71f32</ID><Cmd>PickTubes</Cmd><User>ADMIN</User></Command><Tubes><Tube>FD19920863</Tube></Tubes></STXRequest>";
		String res = url.urlPost(urlInfo, xml);
		System.out.println(res);
	}
}
