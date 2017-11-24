package com.rain.ssm.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rain.ssm.common.PageInfo;
import com.rain.ssm.dao.NewUserDao;
import com.rain.ssm.dao.UserDao;
import com.rain.ssm.model.NewUser;
import com.rain.ssm.model.PageBean;
import com.rain.ssm.model.User;
import com.rain.ssm.service.UserService;
import com.rain.ssm.utils.RedisUtils;
import com.rain.ssm.utils.SerializeUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 
 * @author rain.zong
 *
 */
@Service
public class UserServiceImpl implements UserService {

    Log logger = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private NewUserDao newUserDao;
    
    /**
     *
    * @Title: getUserBytelephone 
    * @Description: TODO(根据电话号码获取new_user用户信息) 
    * @param @param telephone
    * @param @return    设定文件 
    * @return NewUser    返回类型 
    * @throws
     */
    public NewUser getUserBytelephone(String telephone) {
    	NewUser user = newUserDao.getUserBytelephone(telephone);
		return user;
	}
    
    /**
     * 创建new_user用户信息
     */
    public void createNewUser(NewUser newUser) {
    	newUserDao.insertSelective(newUser);
        RedisUtils.del("findNewUserAll");//添加完用户信息以后，清除redis里面用户信息
    }
    
    /**
     * 获得所有new_user用户
     */
	public List<NewUser> findNewUserAll() {
		
	byte[] bytes =(byte[])RedisUtils.get("findNewUserAll");
   	 List<NewUser> userList =null;
   	if(bytes==null) {
   		userList =newUserDao.findUserInfo();
   		byte[] bs = SerializeUtils.serialize(userList);
   	    RedisUtils.save("findAll",bs);
   	}else {
   		logger.info("findAll()，从redis里面获取数据！");
   		userList=(List<NewUser>)SerializeUtils.deserialize(bytes);
   	}
		return userList;
	}
    

    public void findUser(PageInfo pageInfo, User user) {
        logger.info("findUser pageInfo:"+ JSON.toJSONString(pageInfo));
        Page<?> page = PageHelper.startPage(pageInfo.getPageNumber(), pageInfo.getPageSize());
        pageInfo.setRows(userDao.findUserByParams(user));
        pageInfo.setTotal(page.getTotal());
    }

    /**
     * 创建用户信息
     */
    public void createUser(User user) {
        userDao.createUser(user);
        RedisUtils.del("findAll");//添加完用户信息以后，清除redis里面用户信息
    }

    public void deleteUser(User user) {
        userDao.deleteUser(user);
        RedisUtils.del("findAll");//添加完用户信息以后，清除redis里面用户信息
    }

    public void batchDeleteUser(List<Integer> userIds) {
        userDao.batchDeleteUser(userIds);
        RedisUtils.del("findAll");//添加完用户信息以后，清除redis里面用户信息
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
        RedisUtils.del("findAll");//添加完用户信息以后，清除redis里面用户信息
    }

    /**
     * 获得所有用户
     */
	public List<User> findAll() {
		
	byte[] bytes =(byte[])RedisUtils.get("findAll");
   	 List<User> userList =null;
   	if(bytes==null) {
   		userList =userDao.findAll();
   		byte[] bs = SerializeUtils.serialize(userList);
   	    RedisUtils.save("findAll",bs);
   	}else {
   		logger.info("findAll()，从redis里面获取数据！");
   		userList=(List<User>)SerializeUtils.deserialize(bytes);
   	}
		return userList;
	}


	public User getUserByPhoneNo(String phoneNo) {
		User user = userDao.getUserByPhoneNo(phoneNo);
		return user;
	}


	public PageBean getAllByPage(int pageNum, String userName) {
		int pageSize =4;
		int recordCount = userDao.getPageCount(userName);
		int start = (pageNum-1)*pageSize;
		List<User> recordList = userDao.getUserByPage(start,pageSize,userName);
		PageBean pager = new PageBean(pageNum, pageSize, recordList, recordCount);
		return pager;
	}

}
