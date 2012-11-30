package kr.ac.mju.dislab.user2;

import java.util.*;

public class Rheart implements java.io.Serializable {//받은사람 하트
	private String id;
	private Date receiveTime;//받은시간
	private String gId;//하트를 받은사람 아이디
	public Rheart(){
	}
	public Rheart(String id, Date receiveTime, String gId)
	{
		this.id = id;
		this.receiveTime = receiveTime;
		this.gId = gId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getgId() {
		return gId;
	}
	public void setgId(String gId) {
		this.gId = gId;
	}
}
