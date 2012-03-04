package model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;

public final class ProvinceCity {
	static {
		try {
			// 读入省份城市表（JSON格式）
			InputStream in = ProvinceCity.class.getResourceAsStream("provinces.json");
			InputStreamReader reader = new InputStreamReader(in, "UTF-8");
			BufferedReader br = new BufferedReader(reader);
			StringBuilder builder = new StringBuilder();

			char[] buf = new char[64];
			int len = 0;
			while ((len = br.read(buf)) != -1)
				builder.append(buf, 0, len);

			String json = builder.toString();
			fJson = new JSONObject(json).getJSONArray("provinces");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 根据省份id和城市id构造ProvinceCity
	 * 
	 * @param pid 省份id
	 * @param cid 城市id
	 * @return ProvinceCity对象
	 */
	public static ProvinceCity fromId(int pid, int cid) {
		String key = Integer.toString(pid) + ":" + Integer.toString(cid);
		if (fCache.containsKey(key)) {
			log.debug("Cache hit: " + key);
			return fCache.get(key);
		}

		String p = UNKNOWN;
		String c = UNKNOWN;
		for (int i = 0; i < fJson.length(); i++) {
			try {
				JSONObject province = fJson.getJSONObject(i);
				if (province.getInt("id") == pid) {
					p = province.getString("name");

					JSONArray cities = province.getJSONArray("citys");
					String id = Integer.toString(cid);
					for (int j = 0; j < cities.length(); j++) {
						JSONObject city = cities.getJSONObject(j);
						if (city.has(id)) {
							c = city.getString(id);
							break;
						}
					}
					break;
				}
			} catch (JSONException ex) {
				ex.printStackTrace();
			}
		}

		ProvinceCity pc = new ProvinceCity(p, c);
		log.debug("Add new element to cache: " + key + "[" + p + "-" + c + "]");
		fCache.put(key, pc);
		return pc;
	}

	public String getProvince() {
		return fProvince;
	}

	public String getCity() {
		return fCity;
	}

	// auto generated methods
	@Override
	public String toString() {
		return "ProvinceCity {fProvince=" + fProvince + ", fCity=" + fCity + "}";
	}

	// auto generated methods
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fCity == null) ? 0 : fCity.hashCode());
		result = prime * result + ((fProvince == null) ? 0 : fProvince.hashCode());
		return result;
	}

	// auto generated methods
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ProvinceCity))
			return false;
		ProvinceCity other = (ProvinceCity) obj;
		if (fCity == null) {
			if (other.fCity != null)
				return false;
		} else if (!fCity.equals(other.fCity))
			return false;
		if (fProvince == null) {
			if (other.fProvince != null)
				return false;
		} else if (!fProvince.equals(other.fProvince))
			return false;
		return true;
	}

	// PRIVATE //
	private ProvinceCity(String province, String city) {
		fProvince = province;
		fCity = city;
	}

	private static JSONArray fJson;

	private final String fProvince;
	private final String fCity;

	private static final String UNKNOWN = "未知";
	private static final Map<String, ProvinceCity> fCache = new ConcurrentHashMap<String, ProvinceCity>();
	private static final Logger log = Logger.getLogger(ProvinceCity.class);
}
