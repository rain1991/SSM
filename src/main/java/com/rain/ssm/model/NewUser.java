package com.rain.ssm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
*
*表 new_user 对应的实体类 
*/
public class NewUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	    *new_user表中id
	     */
	    private Long id;

	    /**
	    *new_user表中addTime
	     */
	    private Date addtime;

	    /**
	    *new_user表中deleteStatus
	     */
	    private Boolean deletestatus;

	    /**
	    *new_user表中MSN
	     */
	    private String msn;

	    /**
	    *new_user表中QQ
	     */
	    private String qq;

	    /**
	    *new_user表中WW
	     */
	    private String ww;

	    /**
	     *   地址
	    *new_user表中address
	     */
	    private String address;

	    /**
	    *new_user表中availableBalance
	     */
	    private BigDecimal availablebalance;

	    /**
	     *   生日
	    *new_user表中birthday
	     */
	    private Date birthday;
	    
	    /**
	     *   微信open_Id
	    *new_user表中open_Id
	     */
	    private String  wxOpenid;

		/**
	    *new_user表中email
	     */
	    private String email;

	    /**
	    *new_user表中freezeBlance
	     */
	    private BigDecimal freezeblance;

	    /**
	    *new_user表中gold
	     */
	    private Integer gold;

	    /**
	    *new_user表中integral
	     */
	    private Integer integral;

	    /**
	     *   最后登录时间
	    *new_user表中lastLoginDate
	     */
	    private Date lastlogindate;

	    /**
	     *   最后登录IP
	    *new_user表中lastLoginIp
	     */
	    private String lastloginip;

	    /**
	     *   登录次数
	    *new_user表中loginCount
	     */
	    private Integer logincount;

	    /**
	     *   登录时间
	    *new_user表中loginDate
	     */
	    private Date logindate;

	    /**
	     *   登录IP
	    *new_user表中loginIp
	     */
	    private String loginip;

	    /**
	    *new_user表中mobile
	     */
	    private String mobile;

	    /**
	    *new_user表中password
	     */
	    private String password;

	    /**
	    *new_user表中report
	     */
	    private Integer report;

	    /**
	    *new_user表中sex
	     */
	    private Integer sex;

	    /**
	     *   状态
	    *new_user表中status
	     */
	    private String status;

	    /**
	    *new_user表中telephone
	     */
	    private String telephone;

	    /**
	    *new_user表中trueName
	     */
	    private String truename;

	    /**
	    *new_user表中userName
	     */
	    private String username;

	    /**
	    *new_user表中userRole
	     */
	    private String userrole;

	    /**
	     *   身份证
	    *new_user表中user_credit
	     */
	    private Integer userCredit;

	    /**
	    *new_user表中photo_url
	     */
	    private String photoUrl;

	    /**
	     *   商户id
	    *new_user表中store_id
	     */
	    private Long storeId;

	    /**
	    *new_user表中qq_openid
	     */
	    private String qqOpenid;

	    /**
	    *new_user表中sina_openid
	     */
	    private String sinaOpenid;

	    /**
	    *new_user表中parent_id
	     */
	    private Long parentId;

	    /**
	    *new_user表中years
	     */
	    private Integer years;

	    /**
	    *new_user表中area_id
	     */
	    private Long areaId;

	    /**
	    *new_user表中invitation_code
	     */
	    private String invitationCode;

	    /**
	    *new_user表中store_quick_menu
	     */
	    private String storeQuickMenu;
	    
	    /**
	     * 提现密码
	     */
	    private String cashing;
	    
	    
	    

		public NewUser() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return super.toString();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Date getAddtime() {
			return addtime;
		}

		public void setAddtime(Date addtime) {
			this.addtime = addtime;
		}

		public Boolean getDeletestatus() {
			return deletestatus;
		}

		public void setDeletestatus(Boolean deletestatus) {
			this.deletestatus = deletestatus;
		}

		public String getMsn() {
			return msn;
		}

		public void setMsn(String msn) {
			this.msn = msn;
		}

		public String getQq() {
			return qq;
		}

		public void setQq(String qq) {
			this.qq = qq;
		}

		public String getWw() {
			return ww;
		}

		public void setWw(String ww) {
			this.ww = ww;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public BigDecimal getAvailablebalance() {
			return availablebalance;
		}

		public void setAvailablebalance(BigDecimal availablebalance) {
			this.availablebalance = availablebalance;
		}

		public Date getBirthday() {
			return birthday;
		}

		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}

		public String getWxOpenid() {
			return wxOpenid;
		}

		public void setWxOpenid(String wxOpenid) {
			this.wxOpenid = wxOpenid;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public BigDecimal getFreezeblance() {
			return freezeblance;
		}

		public void setFreezeblance(BigDecimal freezeblance) {
			this.freezeblance = freezeblance;
		}

		public Integer getGold() {
			return gold;
		}

		public void setGold(Integer gold) {
			this.gold = gold;
		}

		public Integer getIntegral() {
			return integral;
		}

		public void setIntegral(Integer integral) {
			this.integral = integral;
		}

		public Date getLastlogindate() {
			return lastlogindate;
		}

		public void setLastlogindate(Date lastlogindate) {
			this.lastlogindate = lastlogindate;
		}

		public String getLastloginip() {
			return lastloginip;
		}

		public void setLastloginip(String lastloginip) {
			this.lastloginip = lastloginip;
		}

		public Integer getLogincount() {
			return logincount;
		}

		public void setLogincount(Integer logincount) {
			this.logincount = logincount;
		}

		public Date getLogindate() {
			return logindate;
		}

		public void setLogindate(Date logindate) {
			this.logindate = logindate;
		}

		public String getLoginip() {
			return loginip;
		}

		public void setLoginip(String loginip) {
			this.loginip = loginip;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Integer getReport() {
			return report;
		}

		public void setReport(Integer report) {
			this.report = report;
		}

		public Integer getSex() {
			return sex;
		}

		public void setSex(Integer sex) {
			this.sex = sex;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getTelephone() {
			return telephone;
		}

		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}

		public String getTruename() {
			return truename;
		}

		public void setTruename(String truename) {
			this.truename = truename;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getUserrole() {
			return userrole;
		}

		public void setUserrole(String userrole) {
			this.userrole = userrole;
		}

		public Integer getUserCredit() {
			return userCredit;
		}

		public void setUserCredit(Integer userCredit) {
			this.userCredit = userCredit;
		}

		public String getPhotoUrl() {
			return photoUrl;
		}

		public void setPhotoUrl(String photoUrl) {
			this.photoUrl = photoUrl;
		}

		public Long getStoreId() {
			return storeId;
		}

		public void setStoreId(Long storeId) {
			this.storeId = storeId;
		}

		public String getQqOpenid() {
			return qqOpenid;
		}

		public void setQqOpenid(String qqOpenid) {
			this.qqOpenid = qqOpenid;
		}

		public String getSinaOpenid() {
			return sinaOpenid;
		}

		public void setSinaOpenid(String sinaOpenid) {
			this.sinaOpenid = sinaOpenid;
		}

		public Long getParentId() {
			return parentId;
		}

		public void setParentId(Long parentId) {
			this.parentId = parentId;
		}

		public Integer getYears() {
			return years;
		}

		public void setYears(Integer years) {
			this.years = years;
		}

		public Long getAreaId() {
			return areaId;
		}

		public void setAreaId(Long areaId) {
			this.areaId = areaId;
		}

		public String getInvitationCode() {
			return invitationCode;
		}

		public void setInvitationCode(String invitationCode) {
			this.invitationCode = invitationCode;
		}

		public String getStoreQuickMenu() {
			return storeQuickMenu;
		}

		public void setStoreQuickMenu(String storeQuickMenu) {
			this.storeQuickMenu = storeQuickMenu;
		}

		public String getCashing() {
			return cashing;
		}

		public void setCashing(String cashing) {
			this.cashing = cashing;
		}
	
	

}
