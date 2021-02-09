package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.MgTecanBox;
import com.ruoyi.system.domain.MgTecanSample;
import com.ruoyi.system.domain.MgTecanSampleError;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.exception.BusinessException;
/**
 * tecan样本表Mapper接口
 * 
 * @author zss
 * @date 2020-07-28
 */
public interface MgTecanSampleMapper 
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
     * 删除tecan样本表
     * 
     * @param id tecan样本表ID
     * @return 结果
     */
    public int deleteMgTecanSampleById(Long id);

    /**
     * 批量删除tecan样本表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMgTecanSampleByIds(String[] ids);
   
    /**
     * 
     * @param rackId
     * @return
     */
    public MgTecanBox selectByRackId(String rackId);
    
    /**
     * 
     * @param cavityId
     * @return
     */
    public MgTecanSample selectByCavityId(String cavityId);
    
    /**
     * 插入盒子表
     * @param tecanBox
     * @return
     */
    public int insertTecanBox(String rackId);
    
    
    /**
     * 批量插入样本
     * @param tecanSamples
     * @return
     */
    public int insertBatch(List<MgTecanSample> tecanSamples);
    
    /**
     * 未分装出子样本，子样本管数为0
     * @return
     */
    public List<MgTecanSampleError> selectNoSonError();
    
    /**
     * 当前采血管样本编号不存在
     * @return
     */
    public List<MgTecanSampleError> selectNoSampleError();
    
    
    /**
     * 缺失数据
     * @return
     */
    public List<MgTecanSampleError> selectDefectDataError();
    
    /**
     * 采血管样本编码重复
     * @return
     */
    public List<MgTecanSampleError> selectRepeatError();
    
    public List<MgTecanSampleError> selectError(MgTecanSampleError sampleError);
}
