package com.epaylater.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorCode {
	private String errName;
	private String errMsg;
	
	
	/**
	 * @param errName
	 * @param errMsg
	 */
	public ErrorCode(String errName, String errMsg) {
		super();
		this.errName = errName;
		this.errMsg = errMsg;
	}
	
	
	/**
	 * 
	 */
	public ErrorCode() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getErrName() {
		return errName;
	}
	public void setErrName(String errName) {
		this.errName = errName;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
