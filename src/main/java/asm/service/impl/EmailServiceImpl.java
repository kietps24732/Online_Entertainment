package asm.service.impl;

import javax.servlet.ServletContext;

import asm.entity.User;
import asm.service.EmailService;
import asm.util.SendEmail;

public class EmailServiceImpl implements EmailService {
	private static final String EMAIL_WELCOME_SUBJECT = "Welcome to Online Entertainment";
	private static final String EMAIL_FORGOT_PASSWORD = "New password";

	@Override
	public void sendMail(ServletContext context, User recipient, String type) {
		String host = context.getInitParameter("host");
		String port = context.getInitParameter("port");
		String user = context.getInitParameter("user");
		String pass = context.getInitParameter("pass");
		
		try {
			String content = null;
			String subject = null;
			switch (type) {
			case "welcome":
				subject = EMAIL_WELCOME_SUBJECT;
				content = "Dear " + recipient.getUsername() + ", hope luck!";
				break;
			case "forgot":
				subject = EMAIL_WELCOME_SUBJECT;
				content = "Dear " + recipient.getUsername() + "new password here: " + recipient.getPassword();
				break;
			default:
				subject = "Online Entertainment";
				content = "Email is wrong!";
			}
		SendEmail.sendEmail(host, port, user, pass, recipient.getEmail(), subject, content);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
