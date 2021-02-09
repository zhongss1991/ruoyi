package com.ruoyi.system.domain;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.BaseEntity;

public class LiconicOutputList extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private Date outputTime;
	
	private String createUser;
	
	private String partition;

	private String status;
	
	private String jobNo;
	
	private long outputNu;
	
	private Date updateTime;
	
	/* 1 任务生成  2 挑管中 3 出库中 4 整理中*/
	private int step;

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public long getOutputNu() {
		return outputNu;
	}

	public void setOutputNu(long outputNu) {
		this.outputNu = outputNu;
	}

	public String getPartition() {
		return partition;
	}

	public void setPartition(String partition) {
		this.partition = partition;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getOutputTime() {
		return outputTime;
	}

	public void setOutputTime(Date outputTime) {
		this.outputTime = outputTime;
	}
}
