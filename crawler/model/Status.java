package model;

//import java.util.Date;
import java.sql.Date;

import weibo4j.model.WeiboResponse;
import weibo4j.org.json.JSONObject;
import weibo4j.org.json.JSONWriter;


public class Status extends WeiboResponse {
	private static final long serialVersionUID = -4351587256695867457L;

	private String id;
	private String uid;
	private String text;
	private Date createdAt;
	private int repostsCount;
	private int commentsCount;

	public static Status fromJson(JSONObject json) {
		try {
			Status s = new Status();
			s.setCommentsCount(json.getInt("comments_count"));
			java.util.Date date = parseDate(json.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
			s.setCreatedAt(new Date(date.getTime()));
			s.setId(json.getString("id"));
			s.setRepostsCount(json.getInt("reposts_count"));
			s.setText(json.getString("text"));
			s.setUid(json.getJSONObject("user").getString("id"));
			return s;
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
				.key("uid")
				.value(getUid())
				.key("text")
				.value(getText())
				.key("createdAt")
				.value(getCreatedAt())
				.key("repostsCount")
				.value(getRepostsCount())
				.key("commentsCount")
				.value(getRepostsCount())
			.endObject();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getText()
	{
		return this.text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public Date getCreatedAt()
	{
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}

	public int getRepostsCount()
	{
		return this.repostsCount;
	}

	public void setRepostsCount(int repostsCount)
	{
		this.repostsCount = repostsCount;
	}

	public int getCommentsCount()
	{
		return this.commentsCount;
	}

	public void setCommentsCount(int commentsCount)
	{
		this.commentsCount = commentsCount;
	}

	@Override
	public String toString() {
		return "Status {id=" + id + ", uid=" + uid + ", text=" + text + ", createdAt=" + createdAt + ", repostsCount="
		+ repostsCount + ", commentsCount=" + commentsCount + "}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commentsCount;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + repostsCount;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Status))
			return false;
		Status other = (Status) obj;
		if (commentsCount != other.commentsCount)
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (repostsCount != other.repostsCount)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}

}
