package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.system.domain.ConType;
import com.ruoyi.system.domain.SysDept;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author zss
 * @date 2020-05-19
 */
public interface IConTypeService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public ConType selectConTypeById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param conType 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ConType> selectConTypeList(ConType conType);

    /**
     * 新增【请填写功能名称】
     * 
     * @param conType 【请填写功能名称】
     * @return 结果
     */
    public int insertConType(ConType conType);

    /**
     * 修改【请填写功能名称】
     * 
     * @param conType 【请填写功能名称】
     * @return 结果
     */
    public int updateConType(ConType conType);

    /**
     * 修改可用数量
     * @param conids
     * @return
     */
    public int updateConTypeNum(String conids);
    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConTypeByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteConTypeById(Long id);
    
    /**
     * 获取树菜单
     */
    public List<Ztree> selectConTypeTree(ConType conType);
}
