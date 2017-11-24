package com.rain.ssm.controller;
import com.rain.ssm.common.Constans;
import com.rain.ssm.common.PageInfo;
import com.rain.ssm.common.ResultBean;
import com.rain.ssm.model.PageBean;
import com.rain.ssm.model.User;
import com.rain.ssm.service.UserService;
import com.rain.ssm.utils.RedisUtils;
import com.rain.ssm.utils.SerializeUtils;
import com.rain.ssm.utils.json.JsonReq;
import com.rain.ssm.utils.token.Jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rain
 * @Description com.rain.ssm.controller.UserCtr
 * @Date 2017/1/13
 */
@Controller
@RequestMapping(value = "/ssm2/user2")
public class UserCtr2 {
	private static final Logger log = LoggerFactory.getLogger(UserCtr2.class);

    @Autowired
    private UserService userService;

    
    
    /**
	 * 分页查询对象列表
	 * @param request
	 * @return
	 * @throws Exception
	 * @throws IOException
	 */
	@RequestMapping(value = "/getPageUser", method = RequestMethod.POST)
	public @ResponseBody Map getAllByPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("分页查询开始...");
 		Map<String, Object> resMap = new HashMap<String, Object>();// 返回前端的map
 		Map param = JsonReq.getRequestBody(request, response);
 		String pageSize =param.get("pageSize").toString();
 		String userName = param.get("userName").toString();
		if(pageSize==null) pageSize="1";
		int pageNum = Integer.parseInt(pageSize.trim());
		PageBean pager = userService.getAllByPage(pageNum,userName);
		resMap.put("flag", "1");
		resMap.put("msg", "分页查询成功！");
		resMap.put("pager", pager);
		return resMap;
		
	}
       
    
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
 			String phoneNo = (String) param.get("phoneNo");// 手机号
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

	 		// 将token放入redis缓存
	 		RedisUtils.save(token, user.getUserId());
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
    *  用户注册
   * @Title: registerUser 
   * @Description: TODO(这里用一句话描述这个方法的作用) 
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
		User user = new User();
		try {
			String phoneNo = (String) param.get("phoneNo");// 手机号
			String userName = (String) param.get("userName");
			String password = (String) param.get("password");// 密码
			Integer age = (Integer) param.get("age");
			
			user.setUserName(userName);
			user.setPassword(password);
			user.setAge(age);
			user.setPhoneNo(phoneNo);
			userService.createUser(user);
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
    
    

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageInfo findUser(PageInfo pageInfo, User user) {
        userService.findUser(pageInfo, user);
        return pageInfo;
    }
    
    
    
    @RequestMapping(value = "/all1", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultBean findAll1() {
    	log.info("findAll开始查询所有的用户......");
    	ResultBean resultBean = new ResultBean();
    	List<User> userList =userService.findAll();
        resultBean.setData(userList);
        resultBean.setFlag(1);
        resultBean.setMsg("所有用户查询成功！");
        return resultBean;
    }



    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultBean delete(User user) {
        ResultBean rb = new ResultBean();
        userService.deleteUser(user);
        rb.setFlag(Constans.SUCCESS);
        return rb;
    }

    @RequestMapping(value = "/batchDeleteUser", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultBean batchDeleteUser(@RequestParam(value = "userIds") List<Integer> userIds) {
        ResultBean rb = new ResultBean();
        userService.batchDeleteUser(userIds);
        rb.setFlag(Constans.SUCCESS);
        return rb;
    }

    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultBean update(User user) {
        ResultBean rb = new ResultBean();
        userService.updateUser(user);
        rb.setFlag(Constans.SUCCESS);
        return rb;
    }

}
