package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.ConReceiveList;

/**
 * 耗材领用Service接口
 * 
 * @author zss
 * @date 2020-05-25
 */
public interface IConReceiveListService 
{
    /**
     * 查询耗材领用
     * 
     * @param conid 耗材领用ID
     * @return 耗材领用
     */
    public ConReceiveList selectConReceiveListById(Long conid);

    /**
     * 查询耗材领用列表
     * 
     * @param conReceiveList 耗材领用
     * @return 耗材领用集合
     */
    public List<ConReceiveList> selectConReceiveListList(ConReceiveList conReceiveList);

    /**
     * 新增耗材领用
     * 
     * @param conReceiveList 耗材领用
     * @return 结果
     */
    public int insertConReceiveList(ConReceiveList conReceiveList);

    /**
     * 修改耗材领用
     * 
     * @param conReceiveList 耗材领用
     * @return 结果
     */
    public int updateConReceiveList(ConReceiveList conReceiveList);

    /**
     * 批量删除耗材领用
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConReceiveListByIds(String ids);

    /**
     * 删除耗材领用信息
     * 
     * @param conid 耗材领用ID
     * @return 结果
     */
    public int deleteConReceiveListById(Long conid);
}
