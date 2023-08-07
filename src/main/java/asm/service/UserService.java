package asm.service;

import java.util.List;

import asm.entity.User;

public interface UserService {
	User findById(Integer id);
	User findByEmail(String email);
	User findByUsername(String username);
	User login(String username, String password);
	User resetPassword(String email);
	List<User> findAll();
	List<User> findAll(int pageNumber, int pagaSize);
	User register(String username, String password, String email);
	User update(User entity);
	User delete(String username);
	List<User> findUsersLikedVideoByVideoHref(String href);
}
