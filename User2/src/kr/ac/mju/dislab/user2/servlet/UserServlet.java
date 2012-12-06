package kr.ac.mju.dislab.user2.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.math.*;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;

import com.sun.xml.internal.ws.org.objectweb.asm.Type;

import kr.ac.mju.dislab.user2.*;

/**
 * Servlet implementation class User
 */
@WebServlet("/user")

public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	User logid,empty;
	boolean logOn = false;
	List<Rheart> rheart;
	List<Gheart> gheart;
	Problem problem;
	public UserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String op = request.getParameter("op");
		String actionUrl = "";
		boolean ret;
		String id = "";
		
		if (op == null) {
			op = "index";
		}
		
		try {
			if (op.equals("show")) {
				if(logOn)
				{
					int birth = Integer.parseInt(logid.getBirth());
					List<User> users= UserDAO.showAll(logid.getGender(),birth);
					request.setAttribute("users", users);
					request.setAttribute("user", logid);
					actionUrl = "show.jsp";
				}
				else
				{
					request.setAttribute("error","로그인  후에 사용 가능합니다");
					request.setAttribute("user",logid);
					actionUrl = "show.jsp";
				}
				
				actionUrl = "show.jsp";
			} else if (op==null ||op.equals("index")){
				//User user = UserDAO.findById(id);
				request.setAttribute("user", logid);
				actionUrl = "index.jsp";
			} else if (op.equals("update")) {
				//User user = UserDAO.findById(id);
				request.setAttribute("user", logid);
				request.setAttribute("method", "PUT");
				actionUrl = "signup.jsp";
			} else if (op.equals("delete")) {
				ret = UserDAO.remove(id);
				request.setAttribute("result", ret);
				
				if (ret) {
					request.setAttribute("msg", "사용자 정보가 삭제되었습니다.");
					actionUrl = "success.jsp";
				} else {
					request.setAttribute("error", "사용자 정보 삭제에 실패했습니다.");
					actionUrl = "error.jsp";
				}
					
			} else if (op.equals("signup")) {
				request.setAttribute("method", "POST");
				request.setAttribute("user", new User());
				actionUrl = "signup.jsp";
			} else if (op.equals("login")){
				if(!logOn)
				{
					request.setAttribute("log","login");
				}
				else
				{
					request.setAttribute("log","logout");
				}	
				request.setAttribute("rheart", rheart);
				request.setAttribute("user",logid);
				actionUrl = "login.jsp";
				
			} else if (op.equals("gshow")){
				if(!logOn)
				{
					request.setAttribute("error","로그인  후에 사용 가능합니다");
					request.setAttribute("user",logid);
					actionUrl = "gshow.jsp";
				}
				else
				{

					gheart = HeartDAO.showgAll(logid.getId());
					request.setAttribute("user",logid);
					request.setAttribute("gheart",gheart);
					actionUrl = "gshow.jsp";
				}
			} else if (op.equals("rshow")){
				if(!logOn)
				{
					request.setAttribute("error","로그인  후에 사용 가능합니다");
					request.setAttribute("user",logid);
					actionUrl = "rshow.jsp";
				}
				else
				{
					rheart = HeartDAO.showrAll(logid.getId());
					HeartDAO.heartCheck(logid.getId());
					request.setAttribute("user",logid);
					request.setAttribute("rheart",rheart);
					actionUrl = "rshow.jsp";
				}
				
			} else if (op.equals("problem")){
				if(!logOn)
				{
					
					request.setAttribute("error","로그인  후에 사용 가능합니다");
					request.setAttribute("user",logid);
					actionUrl = "rshow.jsp";
				}
				else
				{
					String rId = request.getParameter("problem");
					request.setAttribute("rid", rId);
					request.setAttribute("user",logid);
					request.setAttribute("rheart",rheart);
					actionUrl = "problem.jsp";
				} 
			}else if (op.equals("pshow")){
				if(!logOn)
				{
					
					request.setAttribute("error","로그인  후에 사용 가능합니다");
					request.setAttribute("user",logid);
					actionUrl = "rshow.jsp";
				}
				else
				{
					String rId = request.getParameter("problem");
					problem = ProblemDAO.findPromblem(logid.getId(), rId);
					request.setAttribute("problem", problem);
					request.setAttribute("user", logid);
					actionUrl = "pshow.jsp";
				}
			} else if (op.equals("nshow")){
				if(!logOn)
				{
					
					request.setAttribute("error","로그인  후에 사용 가능합니다");
					request.setAttribute("user",logid);
					actionUrl = "rshow.jsp";
				}
				else
				{
					String rId = request.getParameter("problem");
					problem = ProblemDAO.findPromblem(logid.getId(), rId);
					request.setAttribute("problem", problem);
					request.setAttribute("user", logid);
					actionUrl = "nshow.jsp";
				}
			} else {
				request.setAttribute("error", "알 수 없는 명령입니다");
				actionUrl = "error.jsp";
			}
		} catch (SQLException | NamingException e) {
			request.setAttribute("error", e.getMessage());
			e.printStackTrace();
			actionUrl = "error.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(actionUrl);
		dispatcher.forward(request,  response);
		
	}

	private boolean isRegisterMode(HttpServletRequest request) {
		String method = request.getParameter("_method");
		return method == null || method.equals("POST");
	}
	private void logout()
	{
		logid.setId(null);
		logid.setUserid(null);
		logid.setPwd(null);
		logid.setName(null);
		logid.setGender(null);
		logid.setEmail(null);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean ret = false;
		boolean error = false;
		boolean isEmpty = true;
		String actionUrl="";
		String msg="";
		User user = new User();
		request.setCharacterEncoding("utf-8");
		List<String> errorMsgs = new ArrayList<String>();
		String type = request.getParameter("type");
		String log = request.getParameter("log");
		
		if(type.equals("signup")){//회원가입 요청
			if(request.getAttribute("id") != null)
			{
				isEmpty = false;
			}
			String id="";
			String userid = "";
			String pwd = "";
			String pwd_confirm = "";
			String name = "";
			String email = "";
			String gender = "";
			String birth = "";
			String say= "";
		
			if(isEmpty)
			{
				id = request.getParameter("id");
				userid = request.getParameter("userid");
				pwd = request.getParameter("pwd");
				pwd_confirm = request.getParameter("pwd_confirm");
				name = request.getParameter("name");
				email = request.getParameter("email");
				gender = request.getParameter("gender");
				birth = request.getParameter("birth");
				say = request.getParameter("say");
			}
			
		
			if (isRegisterMode(request)) {
				if(id==null)
				{
					errorMsgs.add("페이스북 로그인을 해주세요.");
					error = true;
				}
				if (pwd==null || pwd.length() < 6) {
					errorMsgs.add("비밀번호는 6자 이상 입력해주세요.");
					error = true;
				} 
				
				if (!pwd.equals(pwd_confirm)) {
					errorMsgs.add("비밀번호가 일치하지 않습니다.");
					error = true;
				}
				user.setPwd(pwd);
				if (userid==null || userid.trim().length() == 0) {
					errorMsgs.add("ID를 반드시 입력해주세요.");
					error = true;
				}
				
				if (name==null || name.trim().length() == 0) {
					errorMsgs.add("이름을 반드시 입력해주세요.");
					error = true;
				}
				if(birth==null || birth.trim().length() == 0)
				{
					birth="0";
					errorMsgs.add("태어난 해를 입력해주세요.");
					error = true;
				}
				else 
				{
					if(Integer.parseInt(birth) > 3000 || Integer.parseInt(birth) <1000) {
						errorMsgs.add("태어난 해를 정확히 입력해주세요.");
						error = true;
					}
				}
				user.setId(id);
				user.setName(name);
				user.setUserid(userid);
				user.setEmail(email);
				user.setGender(gender);
				user.setBirth(birth);
				user.setSay(say);
			
			} 
	
			try {
				if (isRegisterMode(request) && error == false) {
					ret = UserDAO.create(user);
					msg = "<b>" + name + "</b>님의 사용자 정보가 등록되었습니다.";
				} else if(!isRegisterMode(request) && error == false){
					ret = UserDAO.update(user);
					actionUrl = "success.jsp";
					msg = "<b>" + name + "</b>님의 사용자 정보가 수정되었습니다.";
				}
				if (error == true) {
					errorMsgs.add("가입에 실패했습니다.");
					request.setAttribute("error", errorMsgs);
					actionUrl = "error.jsp";
					
				} else {
					request.setAttribute("msg", msg);
					actionUrl = "success.jsp";
					
				}
			} catch (SQLException | NamingException e) {
				errorMsgs.add(e.getMessage());
				request.setAttribute("error", errorMsgs);
				actionUrl = "error.jsp";
			}
		}
		
		else if(type.equals("log")){//로그인 요청
			String userid = request.getParameter("userid");
			String pwd = request.getParameter("pwd");
			
			try{
				if(UserDAO.login(userid,pwd)!=null)
				{
					logid= UserDAO.login(userid,pwd);
					gheart = HeartDAO.showgAll(logid.getId());
					rheart = HeartDAO.showrAll(logid.getId());
					if(log.equals("login"))
					{	
						request.setAttribute("user", logid);
						request.setAttribute("log", "logout");
						request.setAttribute("rheart", rheart);
						logOn = true;
					}
					else
					{
						request.setAttribute("log", "login");
						logout();
						logOn = false;
					}
					actionUrl = "login.jsp";
					
				}
				else
				{
					request.setAttribute("error", errorMsgs);
					errorMsgs.add("아이디 또는 비밀번호가 틀렸습니다.");
					actionUrl = "error.jsp";
				}
			} catch(SQLException | NamingException e) {
				errorMsgs.add(e.getMessage());
				actionUrl = "error.jsp";
			}
		}
		else if (type.equals("gheart")){//하트보내기 요청
			String rId = request.getParameter("rid");//상대 아이디
			String rName = request.getParameter("rname");//보낸 사람 이름
			try{
				if(!HeartDAO.alreadyGive(logid.getId(), rId))
				{
					HeartDAO.GiveHaert(logid.getId(), rId, rName);//내db에 상대에게 보냈다고 저장
					HeartDAO.ReceiveHeart(rId, logid.getId(), logid.getName());//상대db에  내가 보냇다고 저장
					msg = "<b>" + rName + "</b>님께 하트를 발송하였습니다.";
					request.setAttribute("msg", msg);
					actionUrl = "success.jsp";
					gheart = HeartDAO.showgAll(logid.getId());//다시 새로고침
					rheart = HeartDAO.showrAll(logid.getId());//다시 새로고침
				}
				else
				{
					errorMsgs.add("이미 보낸적이 있습니다.");
					request.setAttribute("error", errorMsgs);
					actionUrl = "error.jsp";
				}
				// DB rid로 gheart에 생성 gid로 rheart에 생성
			} catch(SQLException | NamingException e){
				errorMsgs.add(e.getMessage());
				request.setAttribute("error", errorMsgs);
				actionUrl = "error.jsp";
			}
		}
		else if (type.equals("problem")){ //문제 제출 요청
			String title = request.getParameter("title");//문제
			String first = request.getParameter("1");//첫지문
			String second = request.getParameter("2");//두번째지문
			String third = request.getParameter("3");//세번째지문
			String fourth = request.getParameter("4");//네번쨰지문
			String hpNumber = request.getParameter("hpnumber");// 핸드폰 번호
			int pNumber = Integer.parseInt(request.getParameter("pnumber"));//문항갯수
			int answer = Integer.parseInt(request.getParameter("answer"));// 정답번호
			String rId = request.getParameter("rid");
			
			if(title==null)
			{
				errorMsgs.add("제목을 입력 해주세요.");
				error = true;
			}
			if (first==null || first.length() < 1) {
				errorMsgs.add("문항을 모두 입력해주세요");
				error = true;
			}
			if (hpNumber.length()>11 ||hpNumber.length()<10)
			{
				errorMsgs.add("핸드폰 번호를 정확히 기입해주세요");
				error = true;
			}
			try{
				if(!error)
				{
					ProblemDAO.deletePromblem(rId, logid.getId());//상대가 보낸문제를 삭제함
					Problem temp = new Problem(logid.getId(),rId,title, pNumber, first, second, third, fourth,answer);
					ProblemDAO.create(temp);
					ProblemDAO.setPhoneNumber(logid.getId(),hpNumber);
					HeartDAO.updatePhrase(rId,logid.getId(),2);//상대가 문제를 풀수있게바꾼다.
					HeartDAO.updatePhrase(logid.getId(),rId,1);//내가 문제를 낼수있게 바꾼다.
					msg = "<b></b>문제를 제출하였습니다.";
					request.setAttribute("msg", msg);
					actionUrl = "success.jsp";
				}
				else
				{
					request.setAttribute("error", errorMsgs);
					actionUrl = "error.jsp";
				}
			} catch(SQLException | NamingException e){
				errorMsgs.add(e.getMessage());
				request.setAttribute("error", errorMsgs);
				actionUrl = "error.jsp";
			}
			
			
		}
		else if (type.equals("answer")){//문제 맞추기 요청
			int answer = Integer.parseInt(request.getParameter("answer"));
			String rId = request.getParameter("problem");
			int secret[] =new int[11];
			int number = 0;
			try{
				
				if(!error)
				{
					Problem problem = ProblemDAO.findPromblem(logid.getId(), rId);
					if(problem.getAnswer()==answer)
					{
						number = problem.getPublicHp()+4;
						if(number>11)
						{
							number = 11;
						}
						double demical;
						String test="";
						for(int i=0; i<number; i++)
						{
							demical = Math.pow(10,i);
							secret[i] = (problem.getHpNumber() / (int)demical) % 10; 
						}
						for(int i=0; i<number; i++)
						{
							test += secret[number-(i+1)];
						}
						ProblemDAO.setPublicHp(rId, logid.getId(), number);
						msg = "정답을 맞추셨습니다.";
						request.setAttribute("answer", "4");
						request.setAttribute("msg", msg);
						request.setAttribute("secret", test);
						request.setAttribute("user", logid);
						actionUrl = "nshow.jsp";
					}
					else
					{
						number = problem.getPublicHp()+3;
						if(number>11)
						{
							number = 11;
						}
						ProblemDAO.setPublicHp(logid.getId(), rId, number);
						int demical;
						String test="";
						for(int i=0; i<number; i++)
						{
							demical = (int)Math.pow(10,i);
							secret[i] = (int)(problem.getHpNumber() / demical) % 10;
						}
						for(int i=0; i<number; i++)
						{
							test += secret[number-(i+1)];
						}
						ProblemDAO.setPublicHp(rId, logid.getId(), number);
						msg = "정답을 틀리셨습니다.";
						request.setAttribute("answer", "3");
						request.setAttribute("msg", msg);
						request.setAttribute("secret", test);
						request.setAttribute("user", logid);
						actionUrl = "nshow.jsp";
					}
					HeartDAO.updatePhrase(rId,logid.getId(),2);//상대가 문제를 낼수있게바꾼다.
					HeartDAO.updatePhrase(logid.getId(),rId,1);//내가 문제를 풀수있게 바꾼다.
					ProblemDAO.deletePromblem(logid.getId(), rId);//상대가 보낸문제를 삭제함
				}
				else
				{
					request.setAttribute("error", errorMsgs);
					actionUrl = "error.jsp";
				}
			}catch(SQLException | NamingException e){
				errorMsgs.add(e.getMessage());
				request.setAttribute("error", errorMsgs);
				actionUrl = "error.jsp";
			}
	
		}
		request.setAttribute("errorMsgs", errorMsgs);
		RequestDispatcher dispatcher = request.getRequestDispatcher(actionUrl);
		dispatcher.forward(request,  response);
	}

}
