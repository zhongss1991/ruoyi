package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * purchase对象 con_purchase_list
 * 
 * @author zss
 * @date 2020-06-05
 */
public class ConPurchaseList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 采购id */
    private Long purchaseId;

    /** 耗材类型id */
    @Excel(name = "耗材类型id")
    private Long typeId;

    /** 采购时间 */
    @Excel(name = "采购时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date purchaseTime;

    /** 采购数量 */
    @Excel(name = "采购数量")
    private Long purchaseNumber;

    /** 采购人 */
    @Excel(name = "采购人")
    private Long userId;

    public void setPurchaseId(Long purchaseId) 
    {
        this.purchaseId = purchaseId;
    }

    public Long getPurchaseId() 
    {
        return purchaseId;
    }
    public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }
    public void setPurchaseTime(Date purchaseTime) 
    {
        this.purchaseTime = purchaseTime;
    }

    public Date getPurchaseTime() 
    {
        return purchaseTime;
    }
    public void setPurchaseNumber(Long purchaseNumber) 
    {
        this.purchaseNumber = purchaseNumber;
    }

    public Long getPurchaseNumber() 
    {
        return purchaseNumber;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("purchaseId", getPurchaseId())
            .append("typeId", getTypeId())
            .append("purchaseTime", getPurchaseTime())
            .append("purchaseNumber", getPurchaseNumber())
            .append("userId", getUserId())
            .toString();
    }
}
