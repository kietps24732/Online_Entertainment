package asm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import asm.constant.SessionAttr;
import asm.entity.History;
import asm.entity.User;
import asm.entity.Video;
import asm.service.HistoryService;
import asm.service.VideoService;
import asm.service.impl.HistoryServiceImpl;
import asm.service.impl.VideoServiceImpl;

@WebServlet(urlPatterns = {"/index", "/favorites", "/history"})
public class home extends HttpServlet{

	private static final long serialVersionUID = -129649736640452566L;
	private static final int VIDEO_MAX_PAGE_SIZE = 6;
	private VideoService videoService = new VideoServiceImpl();
	private HistoryService historyService = new HistoryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		switch (path) {
		case "/index":
			doGetIndex(req, resp);
			break;
		case "/favorites":
			doGetFavorites(session, req, resp);
			break;
		case "/history":
			doGetHistory(session, req, resp);
			break;
		}
		
	}
	private void doGetIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Video> countVideo = videoService.findAll();
		int maxPage = (int) Math.ceil(countVideo.size() / (double) VIDEO_MAX_PAGE_SIZE);
		req.setAttribute("maxPage", maxPage);
		
		List<Video> videos;	
		String pageNumber = req.getParameter("page");	
		
		if(pageNumber == null || maxPage < Integer.valueOf(pageNumber)) {
			videos = videoService.findAll(1, VIDEO_MAX_PAGE_SIZE);
			req.setAttribute("currentPage", 1);
		} else {
			videos = videoService.findAll(Integer.valueOf(pageNumber), VIDEO_MAX_PAGE_SIZE);
			req.setAttribute("currentPage", Integer.valueOf(pageNumber));
		}
		
		req.setAttribute("videos", videos);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/user/index.jsp");
		requestDispatcher.forward(req, resp);
	}
	
	private void doGetFavorites(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		List<History> histories = historyService.findByUserAndIsLiked(user.getUsername());
		List<Video> videos = new ArrayList<>();
		for(int i = 0; i < histories.size(); i++) {
			videos.add(histories.get(i).getVideo()); // histories.forEach(item -> videos.add(item.getVideo()));
		}
		req.setAttribute("videos", videos);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/user/favorites.jsp");
		requestDispatcher.forward(req, resp);
	}
	
	private void doGetHistory(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		List<History> histories = historyService.findByUser(user.getUsername());
		List<Video> videos = new ArrayList<>();
		for(int i = 0; i < histories.size(); i++) {
			videos.add(histories.get(i).getVideo()); // histories.forEach(item -> videos.add(item.getVideo()));
		}
		req.setAttribute("videos", videos);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/user/history.jsp");
		requestDispatcher.forward(req, resp);
	}
}
