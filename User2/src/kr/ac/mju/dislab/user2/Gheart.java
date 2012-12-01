package kr.ac.mju.dislab.user2;

import java.util.*;

public class Gheart implements java.io.Serializable {//준사람의 하트
	private String giveTime;
	private String id;
	private String rId;//하트를 받은사람의 아이디
	private String rName;// 받은사람 이름
	private int finish;//하트를 서로 주고 받앗는가
	
	public Gheart(){
	}
	public Gheart(String giveTime, String id, String rId,String rName, int finish){
		this.giveTime = giveTime;
		this.id = id;
		this.rId = rId;
		this.rName = rName;
		this.finish = finish;
	}
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public String getGiveTime() {
		return giveTime;
	}
	public void setGiveTime(String giveTime) {
		this.giveTime = giveTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getrId() {
		return rId;
	}
	public void setrId(String rId) {
		this.rId = rId;
	}
	public int getFinish() {
		return finish;
	}
	public void setFinish(int finish) {
		this.finish = finish;
	}
}
