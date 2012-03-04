package model;

import java.sql.Date;

import weibo4j.model.WeiboResponse;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;
import weibo4j.org.json.JSONWriter;

public final class User extends WeiboResponse implements Comparable<User> {
	private static final long serialVersionUID = -702839528214642758L;

	public static User fromJson(JSONObject json) {
		try {
			User user = new User();
			int pid = json.getInt("province");
			int cid = json.getInt("city");
			ProvinceCity pc = ProvinceCity.fromId(pid, cid);
			user.setCity(pc.getCity());
			user.setProvince(pc.getProvince());
			java.util.Date date = parseDate(json.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
			user.setCreateDate(new Date(date.getTime()));
			user.setDescription(json.getString("description"));
			user.setFollowerCnt(json.getInt("followers_count"));
			user.setFriendCnt(json.getInt("friends_count"));
			user.setGender(json.getString("gender"));
			user.setId(json.getString("id"));
			user.setScreenName(json.getString("screen_name"));
			user.setStatusCnt(json.getInt("statuses_count"));
			return user;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public void toJson(JSONWriter writer) {
		try {
			writer.object()
				.key("id")
				.value(getId())
				.key("screenName")
				.value(getScreenName())
				.key("province")
				.value(getProvince())
				.key("city")
				.value(getCity())
				.key("description")
				.value(getDescription())
				.key("gender")
				.value(getGender())
				.key("followersCount")
				.value(getFollowerCnt())
				.key("friendsCount")
				.value(getFriendCnt())
				.key("statusesCount")
				.value(getStatusCnt())
				.key("createdAt")
				.value(getCreateDate())
			.endObject();
		} catch (JSONException ex) {
			ex.printStackTrace();
		}
	}

	public String getId() {
		return fId;
	}

	public void setId(String fId) {
		this.fId = fId;
	}

	public String getScreenName() {
		return fScreenName;
	}

	public void setScreenName(String fScreenName) {
		this.fScreenName = fScreenName;
	}

	public String getProvince() {
		return fProvince;
	}

	public void setProvince(String fProvince) {
		this.fProvince = fProvince;
	}

	public String getCity() {
		return fCity;
	}

	public void setCity(String fCity) {
		this.fCity = fCity;
	}

	public String getDescription() {
		return fDescription;
	}

	public void setDescription(String fDescription) {
		this.fDescription = fDescription;
	}

	public String getGender() {
		return fGender;
	}

	public void setGender(String fGender) {
		this.fGender = fGender;
	}

	public int getFollowerCnt() {
		return fFollowerCnt;
	}

	public void setFollowerCnt(int fFollowerCnt) {
		this.fFollowerCnt = fFollowerCnt;
	}

	public int getFriendCnt() {
		return fFriendCnt;
	}

	public void setFriendCnt(int fFriendCnt) {
		this.fFriendCnt = fFriendCnt;
	}

	public int getStatusCnt() {
		return fStatusCnt;
	}

	public void setStatusCnt(int fStatusCnt) {
		this.fStatusCnt = fStatusCnt;
	}

	public Date getCreateDate() {
		return fCreateDate;
	}

	public void setCreateDate(Date fCreateDate) {
		this.fCreateDate = fCreateDate;
	}

	@Override
	public String toString() {
		return "User {fId=" + fId + ", fScreenName=" + fScreenName + ", fProvince=" + fProvince + ", fCity=" + fCity
		+ ", fDescription=" + fDescription + ", fGender=" + fGender + ", fFollowerCnt=" + fFollowerCnt
		+ ", fFriendCnt=" + fFriendCnt + ", fStatusCnt=" + fStatusCnt + ", fCreateDate=" + fCreateDate + "}";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fCity == null) ? 0 : fCity.hashCode());
		result = prime * result + ((fCreateDate == null) ? 0 : fCreateDate.hashCode());
		result = prime * result + ((fDescription == null) ? 0 : fDescription.hashCode());
		result = prime * result + fFollowerCnt;
		result = prime * result + fFriendCnt;
		result = prime * result + ((fGender == null) ? 0 : fGender.hashCode());
		result = prime * result + ((fId == null) ? 0 : fId.hashCode());
		result = prime * result + ((fProvince == null) ? 0 : fProvince.hashCode());
		result = prime * result + ((fScreenName == null) ? 0 : fScreenName.hashCode());
		result = prime * result + fStatusCnt;
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (fCity == null) {
			if (other.fCity != null)
				return false;
		} else if (!fCity.equals(other.fCity))
			return false;
		if (fCreateDate == null) {
			if (other.fCreateDate != null)
				return false;
		} else if (!fCreateDate.equals(other.fCreateDate))
			return false;
		if (fDescription == null) {
			if (other.fDescription != null)
				return false;
		} else if (!fDescription.equals(other.fDescription))
			return false;
		if (fFollowerCnt != other.fFollowerCnt)
			return false;
		if (fFriendCnt != other.fFriendCnt)
			return false;
		if (fGender == null) {
			if (other.fGender != null)
				return false;
		} else if (!fGender.equals(other.fGender))
			return false;
		if (fId == null) {
			if (other.fId != null)
				return false;
		} else if (!fId.equals(other.fId))
			return false;
		if (fProvince == null) {
			if (other.fProvince != null)
				return false;
		} else if (!fProvince.equals(other.fProvince))
			return false;
		if (fScreenName == null) {
			if (other.fScreenName != null)
				return false;
		} else if (!fScreenName.equals(other.fScreenName))
			return false;
		if (fStatusCnt != other.fStatusCnt)
			return false;
		return true;
	}

	// PRIVATE //
	String fId;
	String fScreenName;
	String fProvince;
	String fCity;
	String fDescription;
	String fGender;
	int fFollowerCnt;
	int fFriendCnt;
	int fStatusCnt;
	Date fCreateDate;

	@Override
	public int compareTo(User o) {
		// 真正的大小关系无所谓
		int i = this.hashCode();
		int j = o.hashCode();
		return i == j ? 0 : (i < j ? -1 : 1);
	}
}
