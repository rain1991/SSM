package com.rain.ssm.controller;

import com.rain.ssm.model.NewUser;
import com.rain.ssm.model.User;
import com.rain.ssm.service.UserService;
import com.rain.ssm.utils.DateUtil;
import com.rain.ssm.utils.RedisUtils;
import com.rain.ssm.utils.json.JsonReq;
import com.rain.ssm.utils.token.Jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rain
 * @Description com.rain.ssm.controller.NewUserController 用户管理
 * @Date 2017/11/21
 */
@Controller
@RequestMapping(value = "/ssm/newUser")
public class NewUserController {
	private static final Logger log = LoggerFactory.getLogger(NewUserController.class);

    @Autowired
    private UserService userService;
    
    
    /**
     * 用户登录
    * @Title: login 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param request
    * @param @param response
    * @param @return    设定文件 
    * @return Map    返回类型 
    * @throws
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
 	public @ResponseBody Map login(HttpServletRequest request, HttpServletResponse response) {
     	log.info("用户登录开始...");
 		Map<String, Object> resMap = new HashMap<String, Object>();// 返回前端的map
 		Map param = JsonReq.getRequestBody(request, response);
 		try{
 			String phoneNo = (String) param.get("telephone");// 手机号
 			String pwd = (String) param.get("password");// 密码
 			User user =userService.getUserByPhoneNo(phoneNo);
	 		
	 		if(user==null) {
	 			resMap.put("flag", "2");
		 		resMap.put("msg", "该手机号码暂未注册！"); 
		 		return resMap;
	 		}
	 		String password = user.getPassword();
	 		if(!pwd.equals(password)) {
	 			resMap.put("flag", "3");
		 		resMap.put("msg", "密码错误！"); 
		 		return resMap;
	 		}
	 	      // 生成token
			String token = null;
			Map<String, Object> payload = new HashMap<String, Object>();
			Date date = new Date();
			payload.put("uid", user.getUserId());// 用户id
			payload.put("iat", date.getTime());// 生成时间:当前
			payload.put("ext", date.getTime() + 1000 * 60 * 60);// 过期时间1小时
			token = Jwt.createToken(payload);
			log.info("新生成的token是：" + token);
			user.setPassword("");//防止密码回传到前台
	 		// 将token放入redis缓存
	 		RedisUtils.save(token, user.getUserId());
	 		resMap.put("user", user);
	 		resMap.put("token", token);
	 		resMap.put("flag", "1");
	 		resMap.put("msg", "登陆成功！"); 		
	 		return resMap;
 		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("flag", "500");
			resMap.put("msg", "登陆失败");
			return resMap;
		}
     }
    
    
   /**
    *  
   * @Title: registerUser 
   * @Description: TODO(newUser用户注册) 
   * @param @param request
   * @param @param response
   * @param @return    设定文件 
   * @return Map    返回类型 
   * @throws
    */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody Map registerUser(HttpServletRequest request, HttpServletResponse response) {
    	log.info("用户注册开始...");
		Map<String, Object> resMap = new HashMap<String, Object>();// 返回前端的map
		Map param = JsonReq.getRequestBody(request, response);
		NewUser user = new NewUser();
		try {
			String telephone = (String) param.get("telephone");// 手机号
			String userName = (String) param.get("userName");
			String password = (String) param.get("password");// 密码
			String email = (String) param.get("email");//
			String birthday = (String) param.get("birthday");//
			Integer sex = Integer.valueOf(param.get("sex").toString());			
			
			NewUser user1 =userService.getUserBytelephone(telephone);
			if(user1!=null) {
				resMap.put("flag", "2");
				resMap.put("msg", "手机号码已经存在！");
				return resMap;
			}
			user.setTelephone(telephone);
			user.setUsername(userName);
			user.setPassword(password);
			user.setEmail(email);
			user.setSex(sex);			
			user.setBirthday(DateUtil.convertStringToDate(birthday));
			user.setAddtime(DateUtil.getDateTimeNow());
			userService.createNewUser(user);
			resMap.put("flag", "1");
			resMap.put("msg", "恭喜你注册成功！");		
			return resMap;
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("flag", "500");
			resMap.put("msg", "注册用户失败");
			return resMap;
		}
    }
    /**
     * 获得用户列表
    * @Title: findAll 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param request
    * @param @param response
    * @param @return    设定文件 
    * @return Map    返回类型 
    * @throws
     */
    @RequestMapping(value = "/all", method = RequestMethod.POST)
 	public @ResponseBody Map findAll(HttpServletRequest request, HttpServletResponse response) {
     	log.info("获得所有用户信息开始...");
 		Map<String, Object> resMap = new HashMap<String, Object>();// 返回前端的map
 		Map param = JsonReq.getRequestBody(request, response);
 		try{
	 		List<User> userList =userService.findAll();
	 		resMap.put("data", userList);
	 		resMap.put("flag", "1");
	 		resMap.put("msg", "获得数据成功！"); 		
	 		return resMap;
 		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("flag", "500");
			resMap.put("msg", "获得数据失败");
			return resMap;
		}
     }
    
  

}
