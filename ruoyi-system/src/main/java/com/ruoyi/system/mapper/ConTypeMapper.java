package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ConType;

/**
 * 耗材类型Mapper接口
 * 
 * @author zss
 * @date 2020-05-19
 */
public interface ConTypeMapper 
{
    /**
     * 查询耗材类型
     * 
     * @param id 耗材类型ID
     * @return 耗材类型
     */
    public ConType selectConTypeById(Long id);

    /**
     * 查询耗材类型列表
     * 
     * @param conType 耗材类型
     * @return 耗材类型集合
     */
    public List<ConType> selectConTypeList(ConType conType);

    /**
     * 新增耗材类型
     * 
     * @param conType 耗材类型
     * @return 结果
     */
    public int insertConType(ConType conType);

    /**
     * 修改耗材类型
     * 
     * @param conType 耗材类型
     * @return 结果
     */
    public int updateConType(ConType conType);
    
    /**
     * 修改可用数量
     * @param conid
     * @return
     */
    public int updateConTypeNum(String[] conids);

    /**
     * 删除耗材类型
     * 
     * @param id 耗材类型ID
     * @return 结果
     */
    public int deleteConTypeById(Long id);

    /**
     * 批量删除耗材类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConTypeByIds(String[] ids);
    
    /**
     * 获取总数
     */
    public Long selectCountNum(Long id);
}
