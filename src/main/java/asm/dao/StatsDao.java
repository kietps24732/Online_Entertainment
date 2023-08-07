package asm.dao;

import java.util.List;

import asm.dto.VideoLikedInfo;
import asm.entity.User;

public interface StatsDao {
	List<VideoLikedInfo> findVideoLikedInfo();
}
