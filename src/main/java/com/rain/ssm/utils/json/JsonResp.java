package com.rain.ssm.utils.json;

import java.io.Serializable;
import java.util.HashMap;

public class JsonResp implements Serializable {

	private static final long serialVersionUID = 1L;
	private String errDesc;
	private String errCode;
	private String version;
	private String statusCode;
	private Object data;
	
	public JsonResp() {
		this.version="1.0";
		this.errCode = "0";
		this.statusCode="200";
	}
	public JsonResp(boolean flag) {
		super();
		this.version="1.0";
		this.errCode = "0";
		if(flag){
			this.statusCode="200";
		}else{
			this.errCode = "-1";
			this.statusCode="-1";
		}	
	}
	
	public JsonResp(Object o){
		super();
		this.version="1.0";
		this.errCode = "0";
		this.errDesc = "";
		this.statusCode = "200";
		this.data = o;
	}
	
	public JsonResp(String errCode,String errDesc){
		super();
		this.version="1.0";
		this.errCode = errCode;
		this.errDesc = errDesc;
		this.statusCode = "-1";
		this.data = new HashMap<Object, Object>();
	}

	public String getErrDesc() {
		return errDesc;
	}

	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	
}