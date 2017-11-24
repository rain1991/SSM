package com.rain.ssm.model;

import java.io.Serializable;

/**
 * @author rain
 * @Description com.rain.ssm.model.User
 * @Date 2017/1/13
 */
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L; 

    private Integer userId;

    private String userName;
    
    private String password;
    
    private String phoneNo;

    private Integer age;
    

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhoneNo() {
    	return phoneNo;
    }
    
    public void setPhoneNo(String phoneNo) {
    	this.phoneNo = phoneNo;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
