package com.rain.ssm.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.alibaba.fastjson.JSONObject;


/**
 * @package com.clps.jfh.web.boss.rest.filter
 * @desc 需要包装request，解决无法获取reqeust.getParameter()=null的问题
 * @auto qcd
 * @date 2016年12月5日 下午12:59:27
 * @version V1.0
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private String _body;
	private HttpServletRequest _request;

	public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		_request = request;

		StringBuffer jsonStr = new StringBuffer();
		try (BufferedReader bufferedReader = request.getReader()) {
			String line;
			while ((line = bufferedReader.readLine()) != null)
				jsonStr.append(line);
		}
		JSONObject json = JSONObject.parseObject(jsonStr.toString());
		_body = json.toString();
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(_body.getBytes());
		return new ServletInputStream() {
			public int read() throws IOException {
				return byteArrayInputStream.read();
			}
		};
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(this.getInputStream()));
	}

}
