package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.system.domain.MgTecanBox;
import com.ruoyi.system.domain.MgTecanSample;
import com.ruoyi.system.domain.MgTecanSampleError;

/**
 * tecan样本表Service接口
 * 
 * @author zss
 * @date 2020-07-28
 */
@DataSource(value = DataSourceType.SLAVE)
public interface IMgTecanSampleService 
{
    /**
     * 查询tecan样本表
     * 
     * @param id tecan样本表ID
     * @return tecan样本表
     */
    public MgTecanSample selectMgTecanSampleById(Long id);

    /**
     * 查询tecan样本表列表
     * 
     * @param mgTecanSample tecan样本表
     * @return tecan样本表集合
     */
    public List<MgTecanSample> selectMgTecanSampleList(MgTecanSample mgTecanSample);

    /**
     * 新增tecan样本表
     * 
     * @param mgTecanSample tecan样本表
     * @return 结果
     */
    public int insertMgTecanSample(MgTecanSample mgTecanSample);

    /**
     * 修改tecan样本表
     * 
     * @param mgTecanSample tecan样本表
     * @return 结果
     */
    public int updateMgTecanSample(MgTecanSample mgTecanSample);

    /**
     * 批量删除tecan样本表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMgTecanSampleByIds(String ids);

    /**
     * 删除tecan样本表信息
     * 
     * @param id tecan样本表ID
     * @return 结果
     */
    public int deleteMgTecanSampleById(Long id);

    /**
     * 批量插入
     * @param tecanSamples
     * @return
     */
    public String insertBatch(List<MgTecanSample> tecanSamples);
    
    
   /**
    * 查询盒子
    * @param rackId
    * @return
    */
    public MgTecanBox selectByRackId(String rackId);
    
    /**
     * 
     * @return
     */
    public List<MgTecanSampleError> selectError(MgTecanSampleError sampleError);
}
