package dao;

import java.util.List;

import model.Relationship;

import org.apache.ibatis.session.SqlSession;

public final class RelationshipDaoImpl implements RelationshipDao {
	public RelationshipDaoImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public int insert(Relationship rs) {
		int result = session.insert("insertRelationship", rs);
		session.commit();
		return result;
	}

	private final SqlSession session;

	@Override
	@SuppressWarnings("unchecked")
	public List<Relationship> list() {
		return session.selectList("listAllRelationship");
	}
}
