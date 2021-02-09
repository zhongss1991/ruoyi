package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.system.domain.MgSampleBatch;

/**
 * 样本导入记录Service接口
 * 
 * @author zss
 * @date 2020-07-10
 */
@DataSource(value = DataSourceType.SLAVE)
public interface IMgSampleBatchService 
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
     * 批量导入样本
     * 
     * @param mgSampleBatch 样本导入记录
     * @return 结果
     */
    public int insertBatch(List<MgSampleBatch> samples);
    
    /**
     * 新增样本导入记录
     * @param mgSampleBatch样本导入记录
     * @return
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
     * 批量删除样本导入记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMgSampleBatchByIds(String ids);

    /**
     * 删除样本导入记录信息
     * 
     * @param id 样本导入记录ID
     * @return 结果
     */
    public int deleteMgSampleBatchById(Long id);
    
    /**
     * 批量导入样本
     * @param samples
     * @param isUpdateSupport
     * @param operName
     * @return
     */
    public String importBatch(List<MgSampleBatch> samples, Boolean isUpdateSupport, String operName);
}
