package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel.Type;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 样本导入记录对象 mg_sample_batch
 * 
 * @author zss
 * @date 2020-07-10
 */
public class MgSampleBatch extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 样本编号 */
    @Excel(name = "样本编号", type = Type.IMPORT)
    private String sampleNo;

    /** 父样本编号 */
    @Excel(name = "父样本编号")
    private String sampleParentNo;

    /** 样本类型 */
    @Excel(name = "样本类型")
    private String sampleType;
    
    @Excel(name = "核酸(ng/ul)")
    private String assay1;
    
    @Excel(name = "A260/A280")
    private String assay2;
    
    public String getAssay1() {
		return assay1;
	}

	public void setAssay1(String assay1) {
		this.assay1 = assay1;
	}

	public String getAssay2() {
		return assay2;
	}

	public void setAssay2(String assay2) {
		this.assay2 = assay2;
	}

	/** 生成时间 */
    private Date isrtTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSampleNo(String sampleNo) 
    {
        this.sampleNo = sampleNo;
    }

    public String getSampleNo() 
    {
        return sampleNo;
    }
    public void setSampleParentNo(String sampleParentNo) 
    {
        this.sampleParentNo = sampleParentNo;
    }

    public String getSampleParentNo() 
    {
        return sampleParentNo;
    }
    public void setSampleType(String sampleType) 
    {
        this.sampleType = sampleType;
    }

    public String getSampleType() 
    {
        return sampleType;
    }
    public void setIsrtTime(Date isrtTime) 
    {
        this.isrtTime = isrtTime;
    }

    public Date getIsrtTime() 
    {
        return isrtTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sampleNo", getSampleNo())
            .append("sampleParentNo", getSampleParentNo())
            .append("sampleType", getSampleType())
            .append("isrtTime", getIsrtTime())
            .append("assay1", getAssay1())
            .append("assay2", getAssay2())
            .toString();
    }
}
