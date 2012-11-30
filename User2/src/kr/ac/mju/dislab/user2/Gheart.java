package kr.ac.mju.dislab.user2;

import java.util.*;

public class Gheart implements java.io.Serializable {//준사람의 하트
	private Date giveTime;
	private String id;
	private String rId;//하트를 받은사람의 아이디
	private boolean finish;//하트를 서로 주고 받앗는가
	public Gheart(){
	}
	public Gheart(Date giveTime, String id, String rId, boolean finish){
		this.giveTime = giveTime;
		this.id = id;
		this.rId = rId;
		this.finish = finish;
	}
	public Date getGiveTime() {
		return giveTime;
	}
	public void setGiveTime(Date giveTime) {
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
	public boolean isFinish() {
		return finish;
	}
	public void setFinish(boolean finish) {
		this.finish = finish;
	}
}
