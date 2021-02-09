package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.ConReceiveList;
import com.ruoyi.system.domain.ConType;
import com.ruoyi.system.mapper.ConReceiveListMapper;
import com.ruoyi.system.mapper.ConTypeMapper;
import com.ruoyi.system.service.IConReceiveListService;

/**
 * 耗材领用Service业务层处理
 * 
 * @author zss
 * @date 2020-05-25
 */
@Service
public class ConReceiveListServiceImpl implements IConReceiveListService 
{
    @Autowired
    private ConReceiveListMapper conReceiveListMapper;
    
    @Autowired
    private ConTypeMapper conTypeMapper;

    /**
     * 查询耗材领用
     * 
     * @param conid 耗材领用ID
     * @return 耗材领用
     */
    @Override
    public ConReceiveList selectConReceiveListById(Long conid)
    {
        return conReceiveListMapper.selectConReceiveListById(conid);
    }

    /**
     * 查询耗材领用列表
     * 
     * @param conReceiveList 耗材领用
     * @return 耗材领用
     */
    @Override
    public List<ConReceiveList> selectConReceiveListList(ConReceiveList conReceiveList)
    {
        return conReceiveListMapper.selectConReceiveListList(conReceiveList);
    }

    /**
     * 新增耗材领用
     * 减少耗材可用数量
     * 
     * @param conReceiveList 耗材领用
     * @return 结果
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public int insertConReceiveList(ConReceiveList conReceiveList)
    {
    	int code = 0;
    	long opetype = conReceiveList.getOpetype();
    	ConType conType = new ConType();
    	Long id = conReceiveList.getContypeid();
    	//获取总量
    	Long availableNum = conTypeMapper.selectCountNum(id);
    	//使用或采购量
    	Long num = conReceiveList.getReceivenum();
    	if(opetype == 0) {
    		if(num <= availableNum) {
        		conType.setId(id);
        		conType.setAvailablenum(availableNum - num);
        		conTypeMapper.updateConType(conType);
        		conReceiveListMapper.insertConReceiveList(conReceiveList);
        		code = 1;
        	} else if(num > availableNum){
        		//数量不足
        		code = 0;
        	} else {
        		code = -1;
        	}
    	} else {
    		conType.setId(id);
    		conType.setAvailablenum(availableNum + num);
    		conTypeMapper.updateConType(conType);
    		conReceiveListMapper.insertConReceiveList(conReceiveList);
    		code = 2;
    	}
     	
        return code;
    }
    
    /**
     * 修改耗材领用
     * 
     * @param conReceiveList 耗材领用
     * @return 结果
     */
    @Override
    public int updateConReceiveList(ConReceiveList conReceiveList)
    {
        return conReceiveListMapper.updateConReceiveList(conReceiveList);
    }

    /**
     * 删除耗材领用对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConReceiveListByIds(String ids)
    {
        return conReceiveListMapper.deleteConReceiveListByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除耗材领用信息
     * 
     * @param conid 耗材领用ID
     * @return 结果
     */
    @Override
    public int deleteConReceiveListById(Long conid)
    {
        return conReceiveListMapper.deleteConReceiveListById(conid);
    }

}
