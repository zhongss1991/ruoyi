package com.ruoyi.system.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.system.domain.MgTecanBox;
import com.ruoyi.system.domain.MgTecanSample;
import com.ruoyi.system.mapper.MgTecanSampleMapper;
import com.ruoyi.system.service.IMgTecanSampleService;


public class FileUtils {

	public static List<File> getFiles(String path){
		
		File root = new File(path);
		List<File> files = new ArrayList<File>();
		if(!root.isDirectory()) {
			files.add(root);
		} else {
			File[] subFiles = root.listFiles();
			for (File f : subFiles) {
				files.addAll(getFiles(f.getAbsolutePath()));
			}
		}
		
		return files;
	}

	public List<MgTecanSample> getTecanSample(String url) {
		List<MgTecanSample> tecanSamples = new ArrayList<MgTecanSample>();
//		List<File> files = getFiles("C:\\Users\\user\\Desktop\\文档\\Bak");
		List<File> files = getFiles(url);
		CsvReader reader = null;
		MgTecanSample tecanSample = null;
		int j = 0;
		int k = 0;
		try {
			for (File f : files) {
				String rackId = f.getName().replaceAll(".csv", "");
				MgTecanBox tecanBox = SpringUtils.getBean(MgTecanSampleMapper.class).selectByRackId(rackId);
//				String boxName = SpringUtils.getBean(IMgTecanSampleService.class)
				reader = new CsvReader(f.toString(), ',', Charset.forName("GBK")); 
				//去除表头
				reader.readHeaders();
				if(StringUtils.isNull(tecanBox) && rackId.indexOf(".xls") < 0) {
					SpringUtils.getBean(MgTecanSampleMapper.class).insertTecanBox(rackId);
					while(reader.readRecord()) {
						tecanSample = new MgTecanSample();
						String[] strings = reader.getValues();
						k = getNum(strings);
						tecanSample.setFileName(rackId);
						if(strings.length>1 && k > 1) {
							for (int i = 0; i < strings.length; i++) {
								String cellValue = strings[i] != null && strings[i] != "" ? strings[i].trim() : "";
								switch (i) {
								case 0:
									tecanSample.setRackId(cellValue);
									break;
								case 1:
									tecanSample.setCavityId(cellValue);
									break;
								case 2:
									tecanSample.setPosition(cellValue);
									break;
								case 3:
									tecanSample.setSampleId(cellValue);
									break;
								case 4:
									tecanSample.setConcentration(cellValue);
									break;
								case 5:
									tecanSample.setConcentrationUnit(cellValue);
									break;
								case 6:
									tecanSample.setColume(cellValue);
									break;
								case 7:
									tecanSample.setUserdefined1(cellValue);
									break;
								case 8:
									tecanSample.setUserdefined2(cellValue);
									break;
								case 9:
									tecanSample.setUserdefined3(cellValue);
									break;
								case 10:
									tecanSample.setUserdefined4(cellValue);
									break;
								case 11:
									tecanSample.setUserdefined5(cellValue);
									break;
								case 12:
									tecanSample.setPlateErrors(cellValue);
									break;
								case 13:
									tecanSample.setSampleErrors(cellValue);
									break;
								case 14:
									tecanSample.setSampleInstanceid(cellValue);
									break;
								case 15:
									tecanSample.setSampleId1(cellValue);
									break;
								default:
									break;
								}
							}
							tecanSamples.add(tecanSample);
						}
					}
				}
			}
		} catch (IOException e) {
			
		}
		return tecanSamples;
	}
	
	public static int getNum(String[] s) {
		int n = 0;
		for (int i = 0; i < s.length; i++) {
			if(s[i] != null && s[i] != "") 
			{
				n++;
			}
		}
		return n;
	}
}
