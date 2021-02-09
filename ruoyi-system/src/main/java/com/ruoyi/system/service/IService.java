package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.system.domain.LiconicOutputList;
import com.ruoyi.system.domain.LiconicOutputSample;
import com.ruoyi.system.domain.LiconicSample;

@DataSource(value = DataSourceType.SLAVE)
public interface IService {

	public int modifyNo();
	
	public int addOrModifySample(List<LiconicSample> sample);
	
	public int insertLiconicOutputList(LiconicOutputList liconicOutputList);
	
	public int insertLiconicOutputSample(List<LiconicOutputSample> liconicOutputSample);
	
	public List<LiconicOutputList> selectOutputList(LiconicOutputList liconicOutputList);
	
	public List<LiconicSample> selectOutputSample(LiconicOutputList liconicOutputList);
	
	public LiconicOutputList selectListById(Long id);
	
	public int updateOutputList(LiconicOutputList liconicOutputList);
	
	public List<LiconicOutputList> selectRunningList();
	
	public LiconicOutputList selectLatestOutputList();
	
	public int updateLiconicSample(LiconicOutputList liconicOutputList);
	
	public List<LiconicSample> selectExportPlate(String jobNo);
	
}
