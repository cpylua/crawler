package fetch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Status;
import model.User;
import weibo4j.Weibo;
import weibo4j.http.Response;
import weibo4j.model.PostParameter;
import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONObject;
import weibo4j.util.WeiboConfig;

public final class Crawler {
	public static void init(String token) {
		Weibo weibo = new Weibo();
		weibo.setToken(token);
	}

	public List<User> getFriendsByID(String uid) {
		final List<User> EMPTY = Collections.emptyList();

		try {
			Response res = Weibo.client.get(WeiboConfig.getValue("baseURL") + "friendships/friends.json",
					new PostParameter[] { new PostParameter("uid", uid), new PostParameter("count", 50) });
			JSONObject json = res.asJSONObject();
			JSONArray users = null;
			if (!json.isNull("users")) {
				users = json.getJSONArray("users");
			} else {
				users = res.asJSONArray();
			}
			int size = users.length();
			List<User> result = new ArrayList<User>(size);
			for (int i = 0; i < size; i++) {
				User u = User.fromJson(users.getJSONObject(i));
				if (u != null)
					result.add(u);
			}
			return Collections.unmodifiableList(result);
		} catch (Exception ex) {
			ex.printStackTrace();
			return EMPTY;
		}
	}

	public List<Status> getUserTimeline(String uid) {
		final List<Status> EMPTY = Collections.emptyList();

		try {
			Response res = Weibo.client.get(
					WeiboConfig.getValue("baseURL") + "statuses/user_timeline.json",
					new PostParameter[] { new PostParameter("uid", uid), new PostParameter("count", 50),
						new PostParameter("trim_user", 0)});
			JSONObject json = res.asJSONObject();
			JSONArray list = null;
			if(!json.isNull("reposts")){
				list = res.asJSONObject().getJSONArray("reposts");
			}
			else if(!json.isNull("statuses")){
				list = res.asJSONObject().getJSONArray("statuses");
			}else{
				list = res.asJSONArray();
			}

			int size = list.length();
			List<Status> statuses = new ArrayList<Status>(size);
			for (int i = 0; i < size; i++) {
				Status s = Status.fromJson(list.getJSONObject(i));
				if (s != null)
					statuses.add(s);
			}
			return Collections.unmodifiableList(statuses);
		} catch (Exception ex) {
			ex.printStackTrace();
			return EMPTY;
		}
	}
}
