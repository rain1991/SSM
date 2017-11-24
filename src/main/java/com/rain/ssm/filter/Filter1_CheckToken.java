package com.rain.ssm.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.rain.ssm.utils.RedisUtils;
import com.rain.ssm.utils.json.JsonReq;
import com.rain.ssm.utils.token.Jwt;
import com.rain.ssm.utils.token.TokenState;

import net.minidev.json.JSONObject;

/**
 * toekn校验过滤器，所有的API接口请求都要经过该过滤器(除了登陆接口)
 * @author running@vip.163.com
 *
 */

@WebFilter(urlPatterns="/ssm/*")
public class Filter1_CheckToken implements Filter {

	Logger logger = Logger.getLogger(Filter1_CheckToken.class);
	
	@Override
	public void doFilter(ServletRequest argo, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) argo;
		HttpServletResponse response=(HttpServletResponse) arg1;
		//允许跨域访问  
		ServletRequest requestWrapper = null;  
        if(request instanceof HttpServletRequest) {  
            requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request);  
        }  
        
        Map<String, Object> map = JsonReq.getRequestBody(requestWrapper, response);
        
		
		String uri = request.getRequestURI();
		//下列定义需要放行的接口
		String test = "/ssm/user/all";//测试
		String login = "/ssm/newUser/login";//登录接口
		String login1 = "/ssm/user/login";//登录接口
		String register = "/ssm/user/register";//注册接口
		String register1 = "/ssm/newUser/register";//注册接口

		if(uri.endsWith(login)||uri.endsWith(register) ||uri.endsWith(register1) ||uri.endsWith(login1)) {
			// 登陆、注册、获取验证码接口不校验token，直接放行
			if(null == requestWrapper) {  
	            chain.doFilter(request, response);  
	        } else {  
	            chain.doFilter(requestWrapper, response);
	        }
			return;
		}
		// 其他API接口一律校验token
		logger.info("开始校验token");
		String token = (String) map.get("token");
		
		//从请求中获取token
		boolean isExist = RedisUtils.exists(token);
		if(isExist) {//该token在缓存中存在
			Map<String, Object> resultMap=Jwt.validToken(token);
			TokenState state=TokenState.getTokenState((String)resultMap.get("state"));
			switch (state) {
			case VALID:
				// 取出payload中数据,放入到request作用域中
				 request.setAttribute("data", resultMap.get("data"));
				// 放行
				if(null == requestWrapper) {  
		            chain.doFilter(request, response);  
		        } else {  
		            chain.doFilter(requestWrapper, response);  
		        }
				break;
			case EXPIRED:
			case INVALID:
				logger.info("无效token");
				// token过期或者无效，则输出错误信息返回给ajax
				JSONObject outputMSg=new JSONObject();
				outputMSg.put("flag", "999");
				outputMSg.put("msg", "您的token不合法或者过期了，请重新登录！");
				output(outputMSg.toJSONString(), response);
				break;
			}
		} else {// 缓存中不存在该token
			logger.info("无效token");
			// token过期或者无效，则输出错误信息返回给ajax
			JSONObject outputMSg=new JSONObject();
			outputMSg.put("flag", "999");
			outputMSg.put("msg", "您的token不合法或者过期了，请重新登录！");
			output(outputMSg.toJSONString(), response);
		}
	}
	
	
	public void output(String jsonStr,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8;");
		PrintWriter out = response.getWriter();
//		out.println();
		out.write(jsonStr);
		out.flush();
		out.close();
		
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("token过滤器初始化了");
	}

	@Override
	public void destroy() {
		
	}
	
}
