package weibo4j.examples.timeline;

import weibo4j.Timeline;
import weibo4j.Weibo;
import weibo4j.examples.Log;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;

public class Destroy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		String access_token = args[0];
		String id = args[1];
		Weibo weibo = new Weibo();
		weibo.setToken(access_token);
		Timeline tm = new Timeline();
		try {
			Status status = tm.Destroy(id);
			Log.logInfo(status.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
