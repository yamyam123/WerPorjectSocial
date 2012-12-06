package kr.ac.mju.dislab.user2;

import java.util.*;

public class Rheart implements java.io.Serializable {//받은사람 하트
	private String id;
	private String receiveTime;//받은시간
	private String gId;//하트를 보낸사람 아이디
	private String gName;//보낸사람 이름
	int finish;
	int phrase;//문제의 단계수
	public Rheart(){
	}
	public Rheart(String id, String receiveTime, String gId, String gName, int finish, int phrase)
	{
		this.id = id;
		this.receiveTime = receiveTime;
		this.gId = gId;
		this.gName = gName;
		this.finish = finish;
		this.phrase = phrase;
	}
	public int getPhrase() {
		return phrase;
	}
	public void setPhrase(int phrase) {
		this.phrase = phrase;
	}
	public String getId() {
		return id;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public int getFinish() {
		return finish;
	}
	public void setFinish(int finish) {
		this.finish = finish;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getgId() {
		return gId;
	}
	public void setgId(String gId) {
		this.gId = gId;
	}
}
