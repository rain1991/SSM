package com.rain.ssm.service;

import com.rain.ssm.common.PageInfo;
import com.rain.ssm.model.NewUser;
import com.rain.ssm.model.PageBean;
import com.rain.ssm.model.User;

import java.util.List;

/**
 * @author rain
 * @Description com.rain.ssm.service.UserService
 * @Date 2017/1/13
 */
public interface UserService {

    void findUser(PageInfo pageInfo, User user);

    void createUser(User user);

    void deleteUser(User user);

    void batchDeleteUser(List<Integer> userIds);

    void updateUser(User user);
    /**
     * 获得所有员工信息
     * @return List<User>
     */
	List<User> findAll();

	/**
	 * 根据手机号获得用户信息
	* @Title: getUserByPhoneNo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param phoneNo    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	User getUserByPhoneNo(String phoneNo);

	/**
	 * 分页查询用户列表
	* @Title: getAllByPage 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pageNum
	* @param @param userName
	* @param @return    设定文件 
	* @return PageBean    返回类型 
	* @throws
	 */
	PageBean getAllByPage(int pageNum, String userName);

	NewUser getUserBytelephone(String phoneNo);

	void createNewUser(NewUser user);

	

}
