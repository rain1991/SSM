package com.rain.ssm.dao;

import java.util.List;

import com.rain.ssm.model.NewUser;

/**
 * @author rain
 * @Description com.rain.ssm.dao.NewUserDao
 * @Date 2017/11/21
 */
public interface NewUserDao {

	void insertSelective(NewUser newUser);

	List<NewUser> findUserInfo();

	NewUser getUserBytelephone(String telephone);

}
