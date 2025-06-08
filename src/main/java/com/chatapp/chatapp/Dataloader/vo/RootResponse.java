package com.chatapp.chatapp.Dataloader.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RootResponse implements Serializable {


	private String appId;
	private String retStatus;
	private String sysErrorCode;
	private String sysErrorMessage;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getRetStatus() {
		return retStatus;
	}

	public void setRetStatus(String retStatus) {
		this.retStatus = retStatus;
	}

	public String getSysErrorCode() {
		return sysErrorCode;
	}

	public void setSysErrorCode(String sysErrorCode) {
		this.sysErrorCode = sysErrorCode;
	}

	public String getSysErrorMessage() {
		return sysErrorMessage;
	}

	public void setSysErrorMessage(String sysErrorMessage) {
		this.sysErrorMessage = sysErrorMessage;
	}
}