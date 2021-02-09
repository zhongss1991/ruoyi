package com.ruoyi.system.domain;

import java.util.Date;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class MgTecanSampleError extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Excel(name = "文件名")
	private String fileName;
	
	@Excel(name = "底部二维码")
	private String cavityId;
	
	@Excel(name = "位置")
	private String position;
	
	@Excel(name = "样本类型")
	private String userdefined1;
	
	@Excel(name = "父样本编号")
    private String sampleId;
	
	@Excel(name = "错误问题")
	private String error;

	@Excel(name = "生成日期")
	private Date insertTime;
	
	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCavityId() {
		return cavityId;
	}

	public void setCavityId(String cavityId) {
		this.cavityId = cavityId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getUserdefined1() {
		return userdefined1;
	}

	public void setUserdefined1(String userdefined1) {
		this.userdefined1 = userdefined1;
	}

	public String getSampleId() {
		return sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	
}
