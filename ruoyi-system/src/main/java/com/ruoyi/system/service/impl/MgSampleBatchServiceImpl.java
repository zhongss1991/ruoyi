package com.ruoyi.system.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.system.mapper.MgSampleBatchMapper;
import com.ruoyi.system.domain.MgSampleBatch;
import com.ruoyi.system.service.IMgSampleBatchService;

import ch.qos.logback.core.db.dialect.MsSQLDialect;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;

/**
 * 样本导入记录Service业务层处理
 * 
 * @author zss
 * @date 2020-07-10
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class MgSampleBatchServiceImpl implements IMgSampleBatchService 
{
    
	private static final Logger log = LoggerFactory.getLogger(MgSampleBatchServiceImpl.class);
	
	@Autowired
    private MgSampleBatchMapper mgSampleBatchMapper;

    /**
     * 查询样本导入记录
     * 
     * @param id 样本导入记录ID
     * @return 样本导入记录
     */
    @Override
    public MgSampleBatch selectMgSampleBatchById(Long id)
    {
        return mgSampleBatchMapper.selectMgSampleBatchById(id);
    }

    /**
     * 查询样本导入记录列表
     * 
     * @param mgSampleBatch 样本导入记录
     * @return 样本导入记录
     */
    @Override
    public List<MgSampleBatch> selectMgSampleBatchList(MgSampleBatch mgSampleBatch)
    {
        return mgSampleBatchMapper.selectMgSampleBatchList(mgSampleBatch);
    }

    /**
     * 新增样本导入记录
     * 
     * @param mgSampleBatch 样本导入记录
     * @return 结果
     */
    @Override
    public int insertMgSampleBatch(MgSampleBatch mgSampleBatch)
    {
        return mgSampleBatchMapper.insertMgSampleBatch(mgSampleBatch);
    }

    /**
     * 修改样本导入记录
     * 
     * @param mgSampleBatch 样本导入记录
     * @return 结果
     */
    @Override
    public int updateMgSampleBatch(MgSampleBatch mgSampleBatch)
    {
        return mgSampleBatchMapper.updateMgSampleBatch(mgSampleBatch);
    }

    /**
     * 删除样本导入记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMgSampleBatchByIds(String ids)
    {
        return mgSampleBatchMapper.deleteMgSampleBatchByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除样本导入记录信息
     * 
     * @param id 样本导入记录ID
     * @return 结果
     */
    @Override
    public int deleteMgSampleBatchById(Long id)
    {
        return mgSampleBatchMapper.deleteMgSampleBatchById(id);
    }

	@Override
	public int insertBatch(List<MgSampleBatch> samples) {
		
		return 0;
//		return mgSampleBatchMapper.insertBatch(samples);
	}

	/**
	 * 
	 */
	@Override	
	@Transactional
	public String importBatch(List<MgSampleBatch> samples, Boolean isUpdateSupport, String operName) {

		if (StringUtils.isNull(samples) || samples.size() == 0)
        {
            throw new BusinessException("导入样本数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (MgSampleBatch sample : samples) {
			try {
				//验证样本编码是否存在
				MgSampleBatch mgSampleBatch = mgSampleBatchMapper.selectBySampleNo(sample.getSampleNo());
				if(StringUtils.isNull(mgSampleBatch)) {
					//插入批量表
					mgSampleBatchMapper.insertMgSampleBatch(sample);
					//插入样本表
//					mgSampleBatchMapper.insertBatch(sample);
					successNum ++;
					successMsg.append("<br/>" + successNum + "、样本编号 " + sample.getSampleNo() + " 导入成功");
				}
				else if(isUpdateSupport) {
					mgSampleBatchMapper.updateMgSampleBatch(sample);
					successNum ++;
					successMsg.append("<br/>" + successNum + "、样本编号 " + sample.getSampleNo() + " 更新成功");
				}
				else {
					failureNum ++;
					failureMsg.append("<br/>" + failureNum + "、样本编号 " + sample.getSampleNo() + " 已存在");
				}
			} catch (Exception e) {
				failureNum++;
                String msg = "<br/>" + failureNum + "、样本编号 " + sample.getSampleNo() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
			}
		}
        if(failureNum > 0) {
        	failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
		return successMsg.toString();
	}
}
