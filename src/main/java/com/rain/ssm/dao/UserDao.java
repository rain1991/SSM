package com.rain.ssm.dao;

import com.rain.ssm.model.User;

import java.util.List;

/**
 * @author rain
 * @Description com.rain.ssm.dao.UserDao
 * @Date 2017/1/13
 */
public interface UserDao {

    List<User> findUserByParams(User user);

    void createUser(User user);

    void deleteUser(User user);

    void batchDeleteUser(List<Integer> userIds);

    void updateUser(User user);

	List<User> findAll();

	User getUserByPhoneNo(String phoneNo);

	int getPageCount(String userName);

	List<User> getUserByPage(int start, int pageSize, String userName);

}
