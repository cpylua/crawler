package dao;

import java.util.List;

import model.User;

import org.apache.ibatis.session.SqlSession;

public final class UserDaoImpl implements UserDao {
	public UserDaoImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public int insert(User user) {
		int result = session.insert("insertUser", user);
		session.commit();
		return result;
	}

	private final SqlSession session;

	@Override
	public int delete(String id) {
		int result = session.delete("delUser", id);
		session.commit();
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> queryAll() {
		List<User> list = session.selectList("queryAllUser");
		return list;
	}

}
