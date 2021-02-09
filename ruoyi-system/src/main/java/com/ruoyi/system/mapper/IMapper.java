package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.LiconicOutputList;
import com.ruoyi.system.domain.LiconicOutputSample;
import com.ruoyi.system.domain.LiconicSample;

public interface IMapper {

	public int updateNo();
	
	public int insetOrUpdateSample(List<LiconicSample> sample);
//	public int insetOrUpdateSample(LiconicSample sample);
	
	public int truncateSample();
	
	public int insertLiconicOutputList(LiconicOutputList liconicOutputList);
	
	public int insertLiconicOutputSample(List<LiconicOutputSample> liconicOutputSample);
	
	public List<LiconicOutputList> selectOutputList(LiconicOutputList liconicOutputList);
	
	public LiconicOutputList selectLatestOutputList();
	
	public List<LiconicSample> selectOutputSample(LiconicOutputList liconicOutputList);
	
	public LiconicOutputList selectListById(Long id);
	
	public List<LiconicOutputList> selectRunningList();
	
	public int updateOutputList(LiconicOutputList liconicOutputList);
	
	public int updateLiconicSample(LiconicOutputList liconicOutputList);
	
	public List<LiconicSample> selectExportPlate(String jobNo);
}
