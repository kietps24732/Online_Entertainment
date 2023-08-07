package asm.service.impl;

import java.util.List;

import asm.dao.StatsDao;
import asm.dao.impl.StatsDaoImpl;
import asm.dto.VideoLikedInfo;
import asm.service.StatsService;

public class StatsServiceImpl implements StatsService{
	
	private StatsDao statsDao;
	public StatsServiceImpl() {
		statsDao = new StatsDaoImpl();
	}

	@Override
	public List<VideoLikedInfo> findVideoLikedInfo() {
		return statsDao.findVideoLikedInfo();
	}

}
