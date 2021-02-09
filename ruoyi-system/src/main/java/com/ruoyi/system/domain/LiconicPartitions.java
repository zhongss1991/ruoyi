package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

public class LiconicPartitions extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private long pid;
	
	private String partitions;

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public String getPartitions() {
		return partitions;
	}

	public void setPartitions(String partitions) {
		this.partitions = partitions;
	}
	
	
}
