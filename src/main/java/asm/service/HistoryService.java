package asm.service;

import java.util.List;

import asm.entity.History;
import asm.entity.User;
import asm.entity.Video;

public interface HistoryService {
	List<History> findByUser(String username);
	List<History> findByUserAndIsLiked(String username);
	History findByUserIdAndVideoId(Integer userId, Integer videoId);
	History create(User user, Video video);
	Boolean updateLikeOrUnlike(User user, String videoHref);
}
