package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

public class LiconicOutputSample extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private long sid;
	
	private String barcode;
	
	private long outputId;

	public long getSid() {
		return sid;
	}

	public void setSid(long sid) {
		this.sid = sid;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public long getOutputId() {
		return outputId;
	}

	public void setOutputId(long outputId) {
		this.outputId = outputId;
	}
	
	

}
