package com.ruoyi.system.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.system.mapper.MgTecanSampleMapper;
import com.ruoyi.system.domain.MgTecanBox;
import com.ruoyi.system.domain.MgTecanSample;
import com.ruoyi.system.domain.MgTecanSampleError;
import com.ruoyi.system.service.IMgTecanSampleService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;

/**
 * tecan样本表Service业务层处理
 * 
 * @author zss
 * @date 2020-07-28
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class MgTecanSampleServiceImpl implements IMgTecanSampleService 
{
	
	private static final Logger log = LoggerFactory.getLogger(MgTecanSampleServiceImpl.class);
	
    @Autowired
    private MgTecanSampleMapper mgTecanSampleMapper;

    /**
     * 查询tecan样本表
     * 
     * @param id tecan样本表ID
     * @return tecan样本表
     */
    @Override
    public MgTecanSample selectMgTecanSampleById(Long id)
    {
        return mgTecanSampleMapper.selectMgTecanSampleById(id);
    }

    /**
     * 查询tecan样本表列表
     * 
     * @param mgTecanSample tecan样本表
     * @return tecan样本表
     */
    @Override
    public List<MgTecanSample> selectMgTecanSampleList(MgTecanSample mgTecanSample)
    {
        return mgTecanSampleMapper.selectMgTecanSampleList(mgTecanSample);
    }

    /**
     * 新增tecan样本表
     * 
     * @param mgTecanSample tecan样本表
     * @return 结果
     */
    @Override
    public int insertMgTecanSample(MgTecanSample mgTecanSample)
    {
        return mgTecanSampleMapper.insertMgTecanSample(mgTecanSample);
    }

    /**
     * 修改tecan样本表
     * 
     * @param mgTecanSample tecan样本表
     * @return 结果
     */
    @Override
    public int updateMgTecanSample(MgTecanSample mgTecanSample)
    {
        return mgTecanSampleMapper.updateMgTecanSample(mgTecanSample);
    }

    /**
     * 删除tecan样本表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMgTecanSampleByIds(String ids)
    {
        return mgTecanSampleMapper.deleteMgTecanSampleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除tecan样本表信息
     * 
     * @param id tecan样本表ID
     * @return 结果
     */
    @Override
    public int deleteMgTecanSampleById(Long id)
    {
        return mgTecanSampleMapper.deleteMgTecanSampleById(id);
    }

    /**
     * 批量插入信息
     */
	@Override
	@Transactional
	public String insertBatch(List<MgTecanSample> tecanSamples) {
		
		int size = 400;
		if (StringUtils.isNull(tecanSamples) || tecanSamples.size() == 0)
        {
            throw new BusinessException("暂无数据需要同步！");
        }
        int successNum = 0;
        int failureNum = 0;
        long start = System.currentTimeMillis();
        StringBuilder successMsg = new StringBuilder();
        
		 while(tecanSamples.size() > size) {
	      	List<MgTecanSample> batch = tecanSamples.subList(0, size);
	      	tecanSamples = tecanSamples.subList(size, tecanSamples.size());
	//              	mgTecanSampleMa
	      	mgTecanSampleMapper.insertBatch(batch);
	      	successNum++;
	      }
	 	 if(!CollectionUtils.isEmpty(tecanSamples)) {
	   		mgTecanSampleMapper.insertBatch(tecanSamples);
	     }
        
        long end = System.currentTimeMillis();
		System.out.println("共循环处理：" + successNum + "次;");
		System.out.println("耗时：" + (end - start));
		return successMsg.toString();
	}

	@Override
	public MgTecanBox selectByRackId(String rackId) {
		
		return mgTecanSampleMapper.selectByRackId(rackId);
	}

	@Override
	public List<MgTecanSampleError> selectError(MgTecanSampleError sampleError) {
		
		return mgTecanSampleMapper.selectError(sampleError);
	}
}
