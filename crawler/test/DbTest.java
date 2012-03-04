package test;

import java.io.StringWriter;
import java.sql.Date;
import java.util.List;

import model.ProvinceCity;
import model.Relationship;
import model.Status;
import model.User;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import util.DBUtil;
import weibo4j.org.json.JSONWriter;
import dao.RelationshipDao;
import dao.RelationshipDaoImpl;
import dao.StatusDao;
import dao.StatusDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;

public class DbTest {
	@Before
	public void init() {
		SqlSession session = DBUtil.getSession();
		UserDao ud = new UserDaoImpl(session);
		ud.delete("12");
		ud.delete("123333");
		DBUtil.closeSession();
	}

	@Test
	public void testInsert() {
		SqlSession session = DBUtil.getSession();

		UserDao userDao = new UserDaoImpl(session);
		User user = new User();
		user.setId("123333");
		user.setCreateDate(new Date(System.currentTimeMillis()));
		user.setDescription("test");
		user.setFollowerCnt(12);
		user.setFriendCnt(23);
		user.setGender("m");
		user.setScreenName("你好");
		user.setStatusCnt(766);
		ProvinceCity pc = ProvinceCity.fromId(51, 3);
		user.setProvince(pc.getProvince());
		user.setCity(pc.getCity());
		int rows = userDao.insert(user);
		Assert.assertEquals(1, rows);
		user.setId("12");
		rows = userDao.insert(user);
		Assert.assertEquals(1, rows);

		RelationshipDao rsDao = new RelationshipDaoImpl(session);
		Relationship rs = new Relationship();
		rs.setUid("12");
		rs.setFid("123333");
		rows = rsDao.insert(rs);
		Assert.assertEquals(1, rows);

		StatusDao statusDaoImpl = new StatusDaoImpl(session);
		Status status = new Status();
		status.setId("2");
		status.setUid("12");
		status.setText("a");
		status.setCreatedAt(new Date(System.currentTimeMillis()));
		status.setCommentsCount(1);
		status.setRepostsCount(1);
		rows = statusDaoImpl.insertStatus(status);
		Assert.assertEquals(1, rows);

		DBUtil.closeSession();
	}

	@Test
	public void testQueryAll() {
		SqlSession session = DBUtil.getSession();

		UserDao userDao = new UserDaoImpl(session);
		List<User> users = userDao.queryAll();
		Assert.assertTrue(users.size() > 0);

		StringWriter sw = new StringWriter();
		JSONWriter writer = new JSONWriter(sw);
		users.get(0).toJson(writer);
		System.out.println(sw.toString());

		sw = new StringWriter();
		writer = new JSONWriter(sw);
		RelationshipDao rlDao = new RelationshipDaoImpl(session);
		List<Relationship> relations = rlDao.list();
		Assert.assertTrue(relations.size() > 0);
		relations.get(0).toJson(writer);
		System.out.println(sw.toString());

		sw = new StringWriter();
		writer = new JSONWriter(sw);
		StatusDao statusDao = new StatusDaoImpl(session);
		List<Status> statuses = statusDao.listAll();
		Assert.assertTrue(statuses.size() > 0);
		statuses.get(100).toJson(writer);
		System.out.println(sw.toString());

		DBUtil.closeSession();
	}
}
