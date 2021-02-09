package com.ruoyi.system.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.system.domain.LiconicOutputList;
import com.ruoyi.system.domain.LiconicOutputSample;
import com.ruoyi.system.domain.LiconicSample;
import com.ruoyi.system.mapper.IMapper;
import com.ruoyi.system.service.IService;
import com.ruoyi.system.utils.UrlConnectionUtil;

@Service
@DataSource(value = DataSourceType.SLAVE)
public class IServiceImpl implements IService{

	@Autowired
	private IMapper mapper;
	
	@Override
	public int modifyNo() {
		// TODO Auto-generated method stub
		return mapper.updateNo();
	}

	@Override
	public int addOrModifySample(List<LiconicSample> samples) {
		//先清空表
		mapper.truncateSample();
		System.out.println("表清空成功");
		
		//一次性插入行数
		int size = 400;
		//总共插入操作次数
		int i = 0;
		//处理数据
		long start = System.currentTimeMillis();
		int code = 0;
		try {
			if(!CollectionUtils.isEmpty(samples)) {
				while (samples.size() > size) {
					List<LiconicSample> batch = samples.subList(0, size);
					samples = samples.subList(size, samples.size());
					mapper.insetOrUpdateSample(batch);
					System.out.println("第:" + i);
					i++;
				}
				if (!CollectionUtils.isEmpty(samples)) {
					mapper.insetOrUpdateSample(samples);
	            }
			}
		} catch (Exception e) {
			System.out.println("编码同步错误:" + e.getMessage());
			code = -1;
		}
		long end = System.currentTimeMillis();
		System.out.println("共循环处理：" + i + "次;");
		System.out.println("耗时：" + (end - start));
		return code;
	}

	@Override
	public int insertLiconicOutputList(LiconicOutputList liconicOutputList) {
		// TODO Auto-generated method stub
		return mapper.insertLiconicOutputList(liconicOutputList);
	}

	@Override
	public int insertLiconicOutputSample(List<LiconicOutputSample> liconicOutputSample) {
		int code = 0;
		int size = 400;
		if(!CollectionUtils.isEmpty(liconicOutputSample)) {
			while (liconicOutputSample.size() > size) {
				List<LiconicOutputSample> batch = liconicOutputSample.subList(0, size);
				liconicOutputSample = liconicOutputSample.subList(size, liconicOutputSample.size());
				code = code + mapper.insertLiconicOutputSample(batch);
			}
			if(!CollectionUtils.isEmpty(liconicOutputSample)) {
				code = code + mapper.insertLiconicOutputSample(liconicOutputSample);
			}
		}
		return code;
	}

	@Override
	public List<LiconicOutputList> selectOutputList(LiconicOutputList liconicOutputList) {
		
		return mapper.selectOutputList(liconicOutputList);
		
	}

	/**
	 * 出库样本编码
	 */
	@Override
	public List<LiconicSample> selectOutputSample(LiconicOutputList liconicOutputList) {
		
		return mapper.selectOutputSample(liconicOutputList);
		
	}

	@Override
	public LiconicOutputList selectListById(Long id) {
		
		return mapper.selectListById(id);
	}

	@Override
	public int updateOutputList(LiconicOutputList liconicOutputList) {
		
		return mapper.updateOutputList(liconicOutputList);
	}

	@Override
	public LiconicOutputList selectLatestOutputList() {
		// TODO Auto-generated method stub
		return mapper.selectLatestOutputList();
	}

	@Override
	public int updateLiconicSample(LiconicOutputList liconicOutputList) {
		// TODO Auto-generated method stub
		return mapper.updateLiconicSample(liconicOutputList);
	}

	@Override
	public List<LiconicOutputList> selectRunningList() {
		// TODO Auto-generated method stub
		return mapper.selectRunningList();
	}

	@Override
	public List<LiconicSample> selectExportPlate(String jobNo) {
		// TODO Auto-generated method stub
		return mapper.selectExportPlate(jobNo);
	}

}
