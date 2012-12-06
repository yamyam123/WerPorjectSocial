package kr.ac.mju.dislab.user2;

import java.util.*;
public class Problem implements java.io.Serializable{
	private String id;//내아이디
	private String rId;//상대아이디
	private String title;//문제
	private int pNumber;//문항갯수
	/*문제 1번 ~4번*/
	private String first;
	private String second;
	private String third;
	private String fourth;
	private int hpNumber;//핸드폰 번호
	private int answer;//정답 번호
	private int publicHp;//번호 공개 갯수
	
	public Problem(String id, String rId, String title, int pNumber,
			String first, String second, String third, String fourth,
			int hpNumber, int answer) {
		super();
		this.id = id;
		this.rId = rId;
		this.title = title;
		this.pNumber = pNumber;
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
		this.hpNumber = hpNumber;
		this.answer = answer;
	}

	public Problem(String id, String rId, String title, int pNumber,
			String first, String second, String third, String fourth, int answer) {
		super();
		this.id = id;
		this.rId = rId;
		this.title = title;
		this.pNumber = pNumber;
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
		this.answer = answer;
	}
	public Problem(String id, String rId, String title, int pNumber,
			String first, String second, String third, String fourth,
			int hpNumber, int answer, int publicHp) {
		super();
		this.id = id;
		this.rId = rId;
		this.title = title;
		this.pNumber = pNumber;
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
		this.hpNumber = hpNumber;
		this.answer = answer;
		this.publicHp = publicHp;
	}

	
	public Problem()
	{
		
	}
	
	public int getPublicHp() {
		return publicHp;
	}

	public void setPublicHp(int publicHp) {
		this.publicHp = publicHp;
	}

	public int getHpNumber() {
		return hpNumber;
	}

	public void setHpNumber(int hpNumber) {
		this.hpNumber = hpNumber;
	}

	public Problem(String title, int pNumber, String first, String second,
			String third, String fourth,int answer) {
		super();
		this.title = title;
		this.pNumber = pNumber;
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
		this.answer = answer;
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

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getpNumber() {
		return pNumber;
	}
	public void setpNumber(int pNumber) {
		this.pNumber = pNumber;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}
	public String getThird() {
		return third;
	}
	public void setThird(String third) {
		this.third = third;
	}
	public String getFourth() {
		return fourth;
	}
	public void setFourth(String fourth) {
		this.fourth = fourth;
	}
	
	
}
