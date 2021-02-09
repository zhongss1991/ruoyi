package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ConPurchaseList;

/**
 * purchaseMapper接口
 * 
 * @author zss
 * @date 2020-06-05
 */
public interface ConPurchaseListMapper 
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
     * 删除purchase
     * 
     * @param purchaseId purchaseID
     * @return 结果
     */
    public int deleteConPurchaseListById(Long purchaseId);

    /**
     * 批量删除purchase
     * 
     * @param purchaseIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteConPurchaseListByIds(String[] purchaseIds);
}
