package dao;

import java.util.List;

import model.User;

public interface UserDao {
	public int insert(User user);

	public int delete(String id);

	public List<User> queryAll();
}
