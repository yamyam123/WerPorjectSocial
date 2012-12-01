package kr.ac.mju.dislab.user2;

import java.util.*;

import org.apache.commons.lang3.StringUtils;

public class User implements java.io.Serializable {
	private static final long serialVersionUID = 2193897931951340673L;
	
	
	private String id;
	private String userid;
	private String name;
	private String pwd;
	private String email;
	private String gender;
	private Date birth;
	private int g;//하트 준갯수
	private int r;//하트 받은갯수
	private List Gheart;
	private List Rheart;
		
	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	// No-arg constructor 가 있어야 한다.
	public User() {
	}

	public User(String id, String userid, String name, String pwd, String email, String gender, Date birth) {
		super();
		this.id = id;
		this.userid = userid;
		this.name = name;
		this.pwd = pwd;
		this.email = email;
		this.gender = gender;
		this.birth = birth;
	}

	// getter & setter 가 있어야 한다. (Eclipse 에서 자동 생성 가능)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGenderStr() {
		if (gender.equals("Male")) {
			return "남성";
		} else {
			return "여성";
		}
	}
}
	
