package com.ruoyi.system.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * tecan样本表对象 mg_tecan_sample
 * 
 * @author zss
 * @date 2020-07-28
 */
public class MgTecanSample extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;
    
    /** Rack Id */
    @Excel(name = "Rack Id")
    private String rackId;

    /** Cavity Id */
    @Excel(name = "Cavity Id")
    private String cavityId;

    /** Position */
    @Excel(name = "Position")
    private String position;

    /** Sample Id */
    @Excel(name = "Sample Id")
    private String sampleId;

    /** CONCENTRATION */
    @Excel(name = "CONCENTRATION")
    private String concentration;

    /** CONCENTRATIONUNIT */
    @Excel(name = "CONCENTRATIONUNIT")
    private String concentrationUnit;

    /** VOLUME */
    @Excel(name = "VOLUME")
    private String colume;

    /** USERDEFINED1 */
    @Excel(name = "USERDEFINED1")
    private String userdefined1;

    /** USERDEFINED2 */
    @Excel(name = "USERDEFINED2")
    private String userdefined2;

    /** USERDEFINED3 */
    @Excel(name = "USERDEFINED3")
    private String userdefined3;

    /** USERDEFINED4 */
    @Excel(name = "USERDEFINED4")
    private String userdefined4;

    /** USERDEFINED5 */
    @Excel(name = "USERDEFINED5")
    private String userdefined5;

    /** PlateErrors */
    @Excel(name = "PlateErrors")
    private String plateErrors;

    /** SampleErrors */
    @Excel(name = "SampleErrors")
    private String sampleErrors;

    /** SAMPLEINSTANCEID */
    @Excel(name = "SAMPLEINSTANCEID")
    private String sampleInstanceid;

    /** SAMPLEID */
    @Excel(name = "SAMPLEID")
    private String sampleId1;

    /**文件名称**/
    @Excel(name = "文件名")
    private String fileName;
    
    @Excel(name = "创建时间")
    private Date insertTime;
    
    public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRackId(String rackId) 
    {
        this.rackId = rackId;
    }

    public String getRackId() 
    {
        return rackId;
    }
    public void setCavityId(String cavityId) 
    {
        this.cavityId = cavityId;
    }

    public String getCavityId() 
    {
        return cavityId;
    }
    public void setPosition(String position) 
    {
        this.position = position;
    }

    public String getPosition() 
    {
        return position;
    }
    public void setSampleId(String sampleId) 
    {
        this.sampleId = sampleId;
    }

    public String getSampleId() 
    {
        return sampleId;
    }
    public void setConcentration(String concentration) 
    {
        this.concentration = concentration;
    }

    public String getConcentration() 
    {
        return concentration;
    }
    public void setConcentrationUnit(String concentrationUnit) 
    {
        this.concentrationUnit = concentrationUnit;
    }

    public String getConcentrationUnit() 
    {
        return concentrationUnit;
    }
    public void setColume(String colume) 
    {
        this.colume = colume;
    }

    public String getColume() 
    {
        return colume;
    }
    public void setUserdefined1(String userdefined1) 
    {
        this.userdefined1 = userdefined1;
    }

    public String getUserdefined1() 
    {
        return userdefined1;
    }
    public void setUserdefined2(String userdefined2) 
    {
        this.userdefined2 = userdefined2;
    }

    public String getUserdefined2() 
    {
        return userdefined2;
    }
    public void setUserdefined3(String userdefined3) 
    {
        this.userdefined3 = userdefined3;
    }

    public String getUserdefined3() 
    {
        return userdefined3;
    }
    public void setUserdefined4(String userdefined4) 
    {
        this.userdefined4 = userdefined4;
    }

    public String getUserdefined4() 
    {
        return userdefined4;
    }
    public void setUserdefined5(String userdefined5) 
    {
        this.userdefined5 = userdefined5;
    }

    public String getUserdefined5() 
    {
        return userdefined5;
    }
    public void setPlateErrors(String plateErrors) 
    {
        this.plateErrors = plateErrors;
    }

    public String getPlateErrors() 
    {
        return plateErrors;
    }
    public void setSampleErrors(String sampleErrors) 
    {
        this.sampleErrors = sampleErrors;
    }

    public String getSampleErrors() 
    {
        return sampleErrors;
    }
    public void setSampleInstanceid(String sampleInstanceid) 
    {
        this.sampleInstanceid = sampleInstanceid;
    }

    public String getSampleInstanceid() 
    {
        return sampleInstanceid;
    }
    public void setSampleId1(String sampleId1) 
    {
        this.sampleId1 = sampleId1;
    }

    public String getSampleId1() 
    {
        return sampleId1;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fileName", getFileName())
            .append("rackId", getRackId())
            .append("cavityId", getCavityId())
            .append("position", getPosition())
            .append("sampleId", getSampleId())
            .append("concentration", getConcentration())
            .append("concentrationUnit", getConcentrationUnit())
            .append("colume", getColume())
            .append("userdefined1", getUserdefined1())
            .append("userdefined2", getUserdefined2())
            .append("userdefined3", getUserdefined3())
            .append("userdefined4", getUserdefined4())
            .append("userdefined5", getUserdefined5())
            .append("plateErrors", getPlateErrors())
            .append("sampleErrors", getSampleErrors())
            .append("sampleInstanceid", getSampleInstanceid())
            .append("sampleId1", getSampleId1())
            .append("insertTime", getInsertTime())
            .append("fileName", getFileName())
            .toString();
    }
}
