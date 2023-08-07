package asm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import asm.constant.SessionAttr;
import asm.entity.User;
import asm.service.EmailService;
import asm.service.UserService;
import asm.service.impl.EmailServiceImpl;
import asm.service.impl.UserServiceImpl;

@WebServlet({ "/login", "/logout", "/register", "/forgotPass"})
public class user extends HttpServlet {

	private static final long serialVersionUID = 7304914117702978722L;
	private UserService userService = new UserServiceImpl();
	private EmailService emailService = new EmailServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		switch (path) {
		case "/login":
			doGetLogin(req, resp);
			break;
		case "/register":
			doGetRegister(req, resp);
			break;
		case "/logout":
			doGetLogout(session, req, resp);
			break;
		case "/forgotPass":
			doGetForgotPass(req, resp);
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		switch (path) {
		case "/login":
			doPostLogin(session, req, resp);
			break;
		case "/register":
			doPostRegister(session, req, resp);
			break;
		case "/forgotPass":
			doPostForgotPass(req, resp);
			break;
		}
	}

	
	private void doGetLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/user/login.jsp").forward(req, resp);
	}
	private void doPostLogin(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		User user = userService.login(username, password);
		if(user != null) {
			session.setAttribute(SessionAttr.CURRENT_USER, user);
			resp.sendRedirect("index");
		} else {
			resp.sendRedirect("login");
		}
	}
	
	
	private void doGetRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/user/register.jsp").forward(req, resp);
	}
	private void doPostRegister(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		
		User user = userService.register(username, password, email);
		if(user != null) {
			emailService.sendMail(getServletContext(), user, "welcome");
			session.setAttribute(SessionAttr.CURRENT_USER, user);
			resp.sendRedirect("index");
		} else {
			resp.sendRedirect("register");
		}
	}
	
	
	private void doGetLogout(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		session.removeAttribute(SessionAttr.CURRENT_USER);
		resp.sendRedirect("index");
	}
	
	
	private void doGetForgotPass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/user/forgot-pass.jsp").forward(req, resp);
	}
	private void doPostForgotPass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		String email = req.getParameter("email");
		User userWithNewPass = userService.resetPassword(email);
		if(userWithNewPass != null) {
			emailService.sendMail(getServletContext(), userWithNewPass, "fotgot");
			resp.setStatus(204);
		} else {
			resp.setStatus(400);
		}
	}
}
