package asm.dao.impl;

import java.util.List;
import java.util.Map;

import asm.constant.NamedStored;
import asm.dao.AbstractDao;
import asm.dao.UserDao;
import asm.entity.User;

public class UserDaoImpl extends AbstractDao<User> implements UserDao{

	@Override
	public User findById(Integer id) {
		return super.findById(User.class, id);
	}

	@Override
	public User findByEmail(String email) {
		String sql = "SELECT o FROM User o WHERE o.email = ?0";
		return super.findOne(User.class, sql, email);
	}

	@Override
	public User findByUsername(String username) {
		String sql = "SELECT o FROM User o WHERE o.username = ?0";
		return super.findOne(User.class, sql, username);
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		String sql = "SELECT o FROM User o WHERE o.username = ?0 AND o.password = ?1";
		return super.findOne(User.class, sql, username, password);
	}

	@Override
	public List<User> findAll() {
		return super.findAll(User.class, true);
	}

	@Override
	public List<User> findAll(int pageNumber, int pagaSize) {
		return super.findAll(User.class, true, pageNumber, pagaSize);
	}

	@Override
	public List<User> findUsersLikedVideoByVideoHref(Map<String, Object> param) {
		
		return super.callStored(NamedStored.FIND_USER_LIKED_VIDEO_BY_VIDEO_HREF, param);
	}
}
