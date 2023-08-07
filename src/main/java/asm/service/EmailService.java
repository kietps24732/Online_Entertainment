package asm.service;

import javax.servlet.ServletContext;

import asm.entity.User;

public interface EmailService {
	void sendMail(ServletContext context, User recipient, String type);
	
}