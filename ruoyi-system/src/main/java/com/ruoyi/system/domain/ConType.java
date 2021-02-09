package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.annotation.Excel.Type;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 con_type
 * 
 * @author zss
 * @date 2020-05-19
 */
public class ConType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 耗材类型 0 前处理耗材 */
    @Excel(name = "耗材类型", readConverterExp = "0=前处理耗材,1=qiugen")
    private String type;

    /** $column.columnComment */
    @Excel(name = "耗材名称", readConverterExp = "$column.readConverterExp()")
    private String name;

    /** $column.columnComment */
    @Excel(name = "品牌", readConverterExp = "$column.readConverterExp()")
    private String brand;

    /** $column.columnComment */
    @Excel(name = "货号", readConverterExp = "$column.readConverterExp()")
    private String itemnumber;

    /** $column.columnComment */
    @Excel(name = "规格", readConverterExp = "$column.readConverterExp()")
    private String spec;

    /** $column.columnComment */
    @Excel(name = "单位", readConverterExp = "$column.readConverterExp()")
    private String unit;

    /** $column.columnComment */
    @Excel(name = "预警值", readConverterExp = "$column.readConverterExp()")
    private Long warnnum;

    /** $column.columnComment */
    @Excel(name = "可用数量", readConverterExp = "$column.readConverterExp()")
    private Long availablenum;

    /** $column.columnComment */
    private Long userid;
    
    @Excel(name = "负责人", targetAttr = "userName", type = Type.EXPORT)
    private SysUser user;
    
	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setBrand(String brand) 
    {
        this.brand = brand;
    }

    public String getBrand() 
    {
        return brand;
    }
    public void setItemnumber(String itemnumber) 
    {
        this.itemnumber = itemnumber;
    }

    public String getItemnumber() 
    {
        return itemnumber;
    }
    public void setSpec(String spec) 
    {
        this.spec = spec;
    }

    public String getSpec() 
    {
        return spec;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setWarnnum(Long warnnum) 
    {
        this.warnnum = warnnum;
    }

    public Long getWarnnum() 
    {
        return warnnum;
    }
    public void setAvailablenum(Long availablenum) 
    {
        this.availablenum = availablenum;
    }

    public Long getAvailablenum() 
    {
        return availablenum;
    }
    public void setUserid(Long userid) 
    {
        this.userid = userid;
    }

    public Long getUserid() 
    {
        return userid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("name", getName())
            .append("brand", getBrand())
            .append("itemnumber", getItemnumber())
            .append("spec", getSpec())
            .append("unit", getUnit())
            .append("warnnum", getWarnnum())
            .append("availablenum", getAvailablenum())
//            .append("userid", getUserid())
            .toString();
    }
}
