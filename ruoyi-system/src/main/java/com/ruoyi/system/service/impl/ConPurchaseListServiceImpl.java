package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ConPurchaseListMapper;
import com.ruoyi.system.domain.ConPurchaseList;
import com.ruoyi.system.service.IConPurchaseListService;
import com.ruoyi.common.core.text.Convert;

/**
 * purchaseService业务层处理
 * 
 * @author zss
 * @date 2020-06-05
 */
@Service
public class ConPurchaseListServiceImpl implements IConPurchaseListService 
{
    @Autowired
    private ConPurchaseListMapper conPurchaseListMapper;

    /**
     * 查询purchase
     * 
     * @param purchaseId purchaseID
     * @return purchase
     */
    @Override
    public ConPurchaseList selectConPurchaseListById(Long purchaseId)
    {
        return conPurchaseListMapper.selectConPurchaseListById(purchaseId);
    }

    /**
     * 查询purchase列表
     * 
     * @param conPurchaseList purchase
     * @return purchase
     */
    @Override
    public List<ConPurchaseList> selectConPurchaseListList(ConPurchaseList conPurchaseList)
    {
        return conPurchaseListMapper.selectConPurchaseListList(conPurchaseList);
    }

    /**
     * 新增purchase
     * 
     * @param conPurchaseList purchase
     * @return 结果
     */
    @Override
    public int insertConPurchaseList(ConPurchaseList conPurchaseList)
    {
        return conPurchaseListMapper.insertConPurchaseList(conPurchaseList);
    }

    /**
     * 修改purchase
     * 
     * @param conPurchaseList purchase
     * @return 结果
     */
    @Override
    public int updateConPurchaseList(ConPurchaseList conPurchaseList)
    {
        return conPurchaseListMapper.updateConPurchaseList(conPurchaseList);
    }

    /**
     * 删除purchase对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConPurchaseListByIds(String ids)
    {
        return conPurchaseListMapper.deleteConPurchaseListByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除purchase信息
     * 
     * @param purchaseId purchaseID
     * @return 结果
     */
    @Override
    public int deleteConPurchaseListById(Long purchaseId)
    {
        return conPurchaseListMapper.deleteConPurchaseListById(purchaseId);
    }
}
