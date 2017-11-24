package com.rain.ssm.utils.json;

import java.io.BufferedReader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.alibaba.fastjson.JSONObject;


public class JsonReq implements Serializable {

	private static final long serialVersionUID = 1L;
	private Object param;

	public JsonReq() {
		super();
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}

	public static Map<String, Object> getRequestBody(ServletRequest request, ServletResponse response) {

		String jsonData = "";
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			BufferedReader reader = request.getReader();
			StringBuffer buffer = new StringBuffer("");

			String temp;
			while ((temp = reader.readLine()) != null) {
				buffer.append(temp);
			}

			reader.close();
			jsonData = buffer.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		response.setContentType("text/html; charset=UTF-8");

		JSONObject json = JSONObject.parseObject(jsonData);
		return (Map<String, Object>) json.get("param") ;
	}

}