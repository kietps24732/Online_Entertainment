package asm.dao;

import java.util.List;
import java.util.Map;

import asm.entity.User;

public interface UserDao {
	User findById(Integer id);
	User findByEmail(String email);
	User findByUsername(String username);
	User findByUsernameAndPassword(String username, String password);
	List<User> findAll();
	List<User> findAll(int pageNumber, int pagaSize);
	User create(User entity);
	User update(User entity);
	User delete(User entity);
	List<User> findUsersLikedVideoByVideoHref(Map<String, Object> param);
}
