package com.hisign.sso.api.entity.sys;

import com.hisign.sso.api.entity.SysBaseEntity;

/**
 * 统一认证运营平台用户信息表
 * @author chailiangzhi
 * @date 2016-6-30
 * 
 */
public class UserInfo extends SysBaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	//用户ID
	private String userId;

    //用户名 
	private String userName;

	//性别
	private int sex;

    //身份证号
	private String cid;

	//是否警员
	private int isPolice;

    //警员ID号
	private String policeId;
	
	//联系方式
	private String contact;
	
    //用户头像
    private String avatar;
    
    //职务
    private String post;
    
    //职务名
    private String postName;
    
	//出生日期
    private long birth;
    
	//政治面貌
    private String poli;
    
    //政治面貌名
    private String poliName;
    
	//联系电话
    private String phone;
    
    //传真
    private String fax;
    
    //通讯地址
    private String address;
    
    //邮编
    private String zipcode;
    
    //省
    private String province;
    
    //省份名
    private String provinceName;
    
    //市
    private String city;
    
    //市名
    private String cityName;
    
    //(区/县)
    private String county;
    
    //(区/县)名
    private String countyName;
    
    //扩展字段1
    private String extStr1;
    
    //扩展字段2
    private String extStr2;
    
    //扩展字段3
    private String extStr3;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public int getIsPolice() {
		return isPolice;
	}

	public void setIsPolice(int isPolice) {
		this.isPolice = isPolice;
	}

	public String getPoliceId() {
		return policeId;
	}

	public void setPoliceId(String policeId) {
		this.policeId = policeId;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getExtStr1() {
		return extStr1;
	}

	public void setExtStr1(String extStr1) {
		this.extStr1 = extStr1;
	}

	public String getExtStr2() {
		return extStr2;
	}

	public void setExtStr2(String extStr2) {
		this.extStr2 = extStr2;
	}

	public String getExtStr3() {
		return extStr3;
	}

	public void setExtStr3(String extStr3) {
		this.extStr3 = extStr3;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public long getBirth() {
		return birth;
	}

	public void setBirth(long birth) {
		this.birth = birth;
	}

	public String getPoli() {
		return poli;
	}

	public void setPoli(String poli) {
		this.poli = poli;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getPoliName() {
		return poliName;
	}

	public void setPoliName(String poliName) {
		this.poliName = poliName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	
}