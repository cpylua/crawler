package test;

import java.util.List;

import model.Status;
import model.User;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fetch.Crawler;

public class CrawlerTest {
	@BeforeClass
	public static void init() {
		Crawler.init("6673acf0fcf15555b7226bad6603a7af");
	}

	@Test
	public void testGetFriends() {
		Crawler crawler = new Crawler();
		List<User> users = crawler.getFriendsByID(UID);
		Assert.assertTrue("fetch size must <= 50", users.size() <= 50);
		User user = users.get(49);
		log.debug(user.toString());
	}

	@Test
	public void testGetTimeline() {
		Crawler crawler = new Crawler();
		List<Status> status = crawler.getUserTimeline(UID);
		Assert.assertTrue("fetch size must <= 50", status.size() <= 50);
		for (Status s : status)
			log.debug(s.toString());
	}

	private static final Logger log = Logger.getLogger(CrawlerTest.class);
	private static final String UID = "1679264133";
}
