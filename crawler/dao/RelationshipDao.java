package dao;

import java.util.List;

import model.Relationship;

public interface RelationshipDao {
	public int insert(Relationship rs);

	public List<Relationship> list();
}
