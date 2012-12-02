package kr.ac.mju.dislab.user2.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;

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
	public UserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
					List<User> users= UserDAO.showAll(logid.getGender());
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
					List<Gheart> gheart = HeartDAO.showgAll(logid.getId());
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
					List<Rheart> rheart = HeartDAO.showrAll(logid.getId());
					request.setAttribute("user",logid);
					request.setAttribute("rheart",rheart);
					actionUrl = "rshow.jsp";
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
		
			if(isEmpty)
			{
				id = request.getParameter("id");
				userid = request.getParameter("userid");
				pwd = request.getParameter("pwd");
				pwd_confirm = request.getParameter("pwd_confirm");
				name = request.getParameter("name");
				email = request.getParameter("email");
				gender = request.getParameter("gender");
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
				
				user.setId(id);
				user.setName(name);
				user.setUserid(userid);
				user.setEmail(email);
				user.setGender(gender);
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
					request.setAttribute("error", errorMsgs);
					errorMsgs.add("가입에 실패했습니다.");
					actionUrl = "error.jsp";
					
				} else {
					request.setAttribute("msg", msg);
					actionUrl = "success.jsp";
					
				}
			} catch (SQLException | NamingException e) {
				errorMsgs.add(e.getMessage());
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
					if(log.equals("login"))
					{	
						request.setAttribute("user", logid);
						request.setAttribute("log", "logout");
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
		else{//하트보내기 요청
			String gId = request.getParameter("gid");//내아이디
			String rId = request.getParameter("rid");//상대 아이디
			String rname =request.getParameter("rname");//보낸 사람 이름
			String gname =request.getParameter("gname");//내 이름 
			try{
				if(!HeartDAO.alreadySend(gId, rId))
				{
					HeartDAO.GiveHaert(gId, rId, rname);
					HeartDAO.ReceiveHeart(gId, rId, gname);
					msg = "<b>" + rname + "</b>님께 하트를 발송하였습니다.";
					request.setAttribute("msg", msg);
					actionUrl = "success.jsp";
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
				actionUrl = "error.jsp";
			}
		}
		request.setAttribute("errorMsgs", errorMsgs);
		RequestDispatcher dispatcher = request.getRequestDispatcher(actionUrl);
		dispatcher.forward(request,  response);
	}

}
