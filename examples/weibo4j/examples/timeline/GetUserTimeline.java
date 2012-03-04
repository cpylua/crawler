package weibo4j.examples.timeline;

import java.util.List;

import weibo4j.Timeline;
import weibo4j.Weibo;
import weibo4j.examples.Log;
import weibo4j.model.Paging;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;

public class GetUserTimeline {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String access_token = args[0];
		Weibo weibo = new Weibo();
		weibo.setToken(access_token);
		Timeline tm = new Timeline();
		try {
			Paging paging = new Paging(1);
			List<Status> status = tm.getUserTimeline("2259861624", "", 50, paging, 0, 0);
			for(Status s : status){
				Log.logInfo(s.toString());
			}
			paging.setPage(2);
			status = tm.getUserTimeline("2259861624", "", 50, paging, 0, 0);
			for(Status s : status){
				Log.logInfo(s.toString());
			}
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
