package asm.service.impl;

import java.sql.Timestamp;
import java.util.List;

import asm.dao.HistoryDao;
import asm.dao.impl.HistoryDaoImpl;
import asm.entity.History;
import asm.entity.User;
import asm.entity.Video;
import asm.service.HistoryService;
import asm.service.VideoService;

public class HistoryServiceImpl implements HistoryService {

	private HistoryDao dao;
	private VideoService videoService = new VideoServiceImpl();

	public HistoryServiceImpl() {
		dao = new HistoryDaoImpl();
	}

	@Override
	public List<History> findByUser(String username) {
		return dao.findByUser(username);
	}

	@Override
	public List<History> findByUserAndIsLiked(String username) {
		return dao.findByUserAndIsLiked(username);
	}

	@Override
	public History findByUserIdAndVideoId(Integer userId, Integer videoId) {
		return dao.findByUserIdAndVideoId(userId, videoId);
	}

	@Override
	public History create(User user, Video video) {
		History existHistory = findByUserIdAndVideoId(user.getId(), video.getId());
		if (existHistory == null) {
			existHistory = new History();
			existHistory.setUser(user);
			existHistory.setVideo(video);
			existHistory.setViewedDate(new Timestamp(System.currentTimeMillis()));
			existHistory.setIsLiked(Boolean.FALSE);
			return dao.create(existHistory);
		}
		return existHistory;
	}

	@Override
	public Boolean updateLikeOrUnlike(User user, String videoHref) {
		Video video = videoService.findByHref(videoHref);
		History existHistory = findByUserIdAndVideoId(user.getId(), video.getId());
		if (existHistory.getIsLiked() == Boolean.FALSE) {
			existHistory.setIsLiked(Boolean.TRUE);
			existHistory.setLikedDate(new Timestamp(System.currentTimeMillis()));
		} else {
			existHistory.setIsLiked(Boolean.FALSE);
			existHistory.setLikedDate(null);
		}
		History updatedHistory = dao.update(existHistory);
		return updatedHistory != null ? true : false;
	}

}
