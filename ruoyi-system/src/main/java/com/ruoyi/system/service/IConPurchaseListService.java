package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ConPurchaseList;

/**
 * purchaseService接口
 * 
 * @author zss
 * @date 2020-06-05
 */
public interface IConPurchaseListService 
{
    /**
     * 查询purchase
     * 
     * @param purchaseId purchaseID
     * @return purchase
     */
    public ConPurchaseList selectConPurchaseListById(Long purchaseId);

    /**
     * 查询purchase列表
     * 
     * @param conPurchaseList purchase
     * @return purchase集合
     */
    public List<ConPurchaseList> selectConPurchaseListList(ConPurchaseList conPurchaseList);

    /**
     * 新增purchase
     * 
     * @param conPurchaseList purchase
     * @return 结果
     */
    public int insertConPurchaseList(ConPurchaseList conPurchaseList);

    /**
     * 修改purchase
     * 
     * @param conPurchaseList purchase
     * @return 结果
     */
    public int updateConPurchaseList(ConPurchaseList conPurchaseList);

    /**
     * 批量删除purchase
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConPurchaseListByIds(String ids);

    /**
     * 删除purchase信息
     * 
     * @param purchaseId purchaseID
     * @return 结果
     */
    public int deleteConPurchaseListById(Long purchaseId);
}
