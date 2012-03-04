package model;

import weibo4j.org.json.JSONWriter;

public final class Relationship {
	public Relationship(String uid, String fid) {
		this.uid = uid;
		this.fid = fid;
	}

	public Relationship() {

	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public void toJson(JSONWriter writer) {
		try {
			writer.object()
				.key("uid")
				.value(getUid())
				.key("friend_id")
				.value(getFid())
			.endObject();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fid == null) ? 0 : fid.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Relationship))
			return false;
		Relationship other = (Relationship) obj;
		if (fid == null) {
			if (other.fid != null)
				return false;
		} else if (!fid.equals(other.fid))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Relationship {uid=" + uid + ", fid=" + fid + "}";
	}

	private String uid;
	private String fid;
}
