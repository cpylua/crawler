package test;

import model.ProvinceCity;

import org.junit.Assert;
import org.junit.Test;

public class ProvinceCityTest {
	@Test
	public void test() {
		ProvinceCity pc = ProvinceCity.fromId(51, 6);
		Assert.assertEquals("province not equal", "四川", pc.getProvince());
		Assert.assertEquals("city not equal", "德阳", pc.getCity());
		pc = ProvinceCity.fromId(400, 5);
		Assert.assertEquals("province not equal", "海外", pc.getProvince());
		Assert.assertEquals("city not equal", "加拿大", pc.getCity());
		pc = ProvinceCity.fromId(400, 5);
		Assert.assertEquals("province not equal", "海外", pc.getProvince());
		Assert.assertEquals("city not equal", "加拿大", pc.getCity());
		pc = ProvinceCity.fromId(4040, 16);
		Assert.assertEquals("province not equal", "未知", pc.getProvince());
		Assert.assertEquals("city not equal", "未知", pc.getCity());
	}
}
