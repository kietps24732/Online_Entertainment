package asm.service;

import java.util.List;

import asm.dto.VideoLikedInfo;

public interface StatsService {
	List<VideoLikedInfo> findVideoLikedInfo();
}
