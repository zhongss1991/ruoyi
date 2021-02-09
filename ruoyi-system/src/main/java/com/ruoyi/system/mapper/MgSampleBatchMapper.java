package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.MgSampleBatch;

/**
 * 样本导入记录Mapper接口
 * 
 * @author zss
 * @date 2020-07-10
 */
public interface MgSampleBatchMapper 
{
    /**
     * 查询样本导入记录
     * 
     * @param id 样本导入记录ID
     * @return 样本导入记录
     */
    public MgSampleBatch selectMgSampleBatchById(Long id);

    /**
     * 查询样本导入记录列表
     * 
     * @param mgSampleBatch 样本导入记录
     * @return 样本导入记录集合
     */
    public List<MgSampleBatch> selectMgSampleBatchList(MgSampleBatch mgSampleBatch);
    
    /**
     * 批量查询样本信息
     */
    public List<MgSampleBatch> selectBatchList(MgSampleBatch mgSampleBatch);
    
    /**
     * 批量插入样本信息
     */
    public int insertBatch(MgSampleBatch sample);
    
    /**
     * 新增样本导入记录
     * 
     * @param mgSampleBatch 样本导入记录
     * @return 结果
     */
    public int insertMgSampleBatch(MgSampleBatch mgSampleBatch);

    /**
     * 修改样本导入记录
     * 
     * @param mgSampleBatch 样本导入记录
     * @return 结果
     */
    public int updateMgSampleBatch(MgSampleBatch mgSampleBatch);

    /**
     * 删除样本导入记录
     * 
     * @param id 样本导入记录ID
     * @return 结果
     */
    public int deleteMgSampleBatchById(Long id);

    /**
     * 批量删除样本导入记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMgSampleBatchByIds(String[] ids);
    
    /**
     * 
     * @return
     */
    public MgSampleBatch selectBySampleNo(String sampleNo);
}
