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


	private int getIntFromParameter(String str, int defaultValue) {
		int id;
		
		try {
			id = Integer.parseInt(str);
		} catch (Exception e) {
			id = defaultValue;
		}
		return id;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		String actionUrl = "";
		boolean ret;
		String id = "";
		
		if (op == null && id != "") {
			op = "show";
		}
		
		try {
			if (op==null || op.equals("show")) {
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
			} else if (op.equals("index")){
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
			}else {
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
		String actionUrl;
		String msg="";
		User user = new User();
		request.setCharacterEncoding("utf-8");
		List<String> errorMsgs = new ArrayList<String>();
		String type = request.getParameter("type");
		String log = request.getParameter("log");
		
		if(type.equals("signup")){
		
			String id = "";
			String userid = "";
			String pwd = "";
			String pwd_confirm = "";
			String name = "";
			String email = "";
			String gender = "";
		
			if(request.getAttribute("id") != null)
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
				if(id.equals(null))
				{
					errorMsgs.add("페이스북 로그인을 해주세요.");
					error = true;
				}
				if (pwd.equals(null) || pwd.length() < 6) {
					errorMsgs.add("비밀번호는 6자 이상 입력해주세요.");
					error = true;
				} 
				
				if (!pwd.equals(pwd_confirm)) {
					errorMsgs.add("비밀번호가 일치하지 않습니다.");
					error = true;
				}
				user.setPwd(pwd);
				if (userid.equals(null) || userid.trim().length() == 0) {
					errorMsgs.add("ID를 반드시 입력해주세요.");
					error = true;
				}
				
				if (name.equals(null) || name.trim().length() == 0) {
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
		
		else{
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
					errorMsgs.add("변경에 실패했습니다.");
					actionUrl = "error.jsp";
				}
			} catch(SQLException | NamingException e) {
				errorMsgs.add(e.getMessage());
				actionUrl = "error.jsp";
			}
		}
		request.setAttribute("errorMsgs", errorMsgs);
		RequestDispatcher dispatcher = request.getRequestDispatcher(actionUrl);
		dispatcher.forward(request,  response);
	}

}
