package fetch;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import model.Relationship;
import model.Status;
import model.User;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import util.DBUtil;
import weibo4j.Weibo;
import dao.RelationshipDao;
import dao.RelationshipDaoImpl;
import dao.StatusDao;
import dao.StatusDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;

public class Main {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Invalid parameters");
			return;
		}

		// setup access token
		Weibo wb = new Weibo();
		wb.setToken(args[0]);

		Main app = new Main();
		app.start(args[1]);
	}

	private Queue<User> queue;
	private final Crawler crawler = new Crawler();
	private UserDao userDao;
	private RelationshipDao rsDao;
	private StatusDao statusDao;

	private static Logger log = Logger.getLogger(Main.class);
	private static final int MAX_QUEUE_SIZE = 10;

	private void start(String root_uid) {
		int result = setup(root_uid);
		if (result != 0)
			return;

		boolean more = true;
		while (!queue.isEmpty()) {
			if (queue.size() > MAX_QUEUE_SIZE)
				more = false;

			User user = queue.poll();
			if (user != null) {
				// 先保存当前用户
				userDao.insert(user);

				// 取当前用户的关注列表，加到队列并保存到数据库
				List<User> friends = crawler.getFriendsByID(user.getId());
				if (more)
					queue.addAll(friends);
				for (User u : friends) {
					userDao.insert(u);
					Relationship rs = new Relationship(user.getId(), u.getId());
					rsDao.insert(rs);
				}

				// 取微博并保存到数据库
				List<Status> statuses = crawler.getUserTimeline(user.getId());
				for (Status s : statuses)
					statusDao.insertStatus(s);

				log.info(user.getScreenName() + " has " + friends.size() + " friends and " + statuses.size()
						+ " tweets");
			}
		}

		cleanup();
	}

	private int setup(String uid) {
		List<User> users = crawler.getFriendsByID(uid);
		queue = new PriorityQueue<User>(users);
		log.info("Root size: " + queue.size());

		SqlSession session = DBUtil.getSession();
		userDao = new UserDaoImpl(session);
		rsDao = new RelationshipDaoImpl(session);
		statusDao = new StatusDaoImpl(session);

		return queue.size() > 0 ? 0 : -1;
	}

	private void cleanup() {
		DBUtil.closeSession();
	}

}
