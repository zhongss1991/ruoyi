package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

public class LiconicSample extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private int sid;
	
	private String barcode;
	
	private String partitions;
	
	private String plateId;
	
	private String plateCode;
	
	private int status;

	public String getPlateId() {
		return plateId;
	}

	public void setPlateId(String plateId) {
		this.plateId = plateId;
	}

	public String getPlateCode() {
		return plateCode;
	}

	public void setPlateCode(String plateCode) {
		this.plateCode = plateCode;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getPartitions() {
		return partitions;
	}

	public void setPartitions(String partitions) {
		this.partitions = partitions;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
