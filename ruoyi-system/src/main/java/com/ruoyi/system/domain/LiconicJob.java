package com.ruoyi.system.domain;

public class LiconicJob {

	private static final long serialVersionUID = 1L;
	
	private String taskId;
	
	private String jobNm;
	
	private String cstat;
	
	private String errinfo;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getJobNm() {
		return jobNm;
	}

	public void setJobNm(String jobNm) {
		this.jobNm = jobNm;
	}

	public String getCstat() {
		return cstat;
	}

	public void setCstat(String cstat) {
		this.cstat = cstat;
	}

	public String getErrinfo() {
		return errinfo;
	}

	public void setErrinfo(String errinfo) {
		this.errinfo = errinfo;
	}
}
