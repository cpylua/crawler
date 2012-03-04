package adapter;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import model.Relationship;
import model.Status;
import model.User;

import org.apache.ibatis.session.SqlSession;

import util.DBUtil;
import weibo4j.org.json.JSONWriter;
import dao.RelationshipDao;
import dao.RelationshipDaoImpl;
import dao.StatusDao;
import dao.StatusDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;

public class JsonConverter {
	public static void main(String[] args) {
		SqlSession session = DBUtil.getSession();

		UserDao userDao = new UserDaoImpl(session);
		List<User> users = userDao.queryAll();
		RelationshipDao rlDao = new RelationshipDaoImpl(session);
		List<Relationship> relations = rlDao.list();
		StatusDao statusDao = new StatusDaoImpl(session);
		List<Status> statuses = statusDao.listAll();

		File userJson = new File("user.json");
		File rlJson = new File("relationship.json");
		File statusFile = new File("status.json");
		try {
			FileWriter userWriter = new FileWriter(userJson);
			JSONWriter userJsonwriter = new JSONWriter(userWriter);
			userJsonwriter.array();
			for (User user : users) {
				user.toJson(userJsonwriter);
			}
			userJsonwriter.endArray();
			userWriter.close();

			FileWriter rlWriter = new FileWriter(rlJson);
			JSONWriter rlJsonWriter = new JSONWriter(rlWriter);
			rlJsonWriter.array();
			for (Relationship rl : relations) {
				rl.toJson(rlJsonWriter);
			}
			rlJsonWriter.endArray();
			rlWriter.close();

			FileWriter statusWriter = new FileWriter(statusFile);
			JSONWriter statusJsonWriter = new JSONWriter(statusWriter);
			statusJsonWriter.array();
			for (Status status : statuses) {
				status.toJson(statusJsonWriter);
			}
			statusJsonWriter.endArray();
			statusWriter.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeSession();
		}

	}
}
