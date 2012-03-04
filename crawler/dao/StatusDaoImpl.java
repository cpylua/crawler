package dao;

import java.util.List;

import model.Status;

import org.apache.ibatis.session.SqlSession;

public class StatusDaoImpl implements StatusDao
{
	private final SqlSession sqlSession;

	public StatusDaoImpl(SqlSession sqlSession)
	{
		this.sqlSession = sqlSession;
	}

	@Override
	public int insertStatus(Status status)
	{
		// TODO Auto-generated method stub
		int i = sqlSession.insert("insertStatus", status);
		sqlSession.commit();
		return i;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Status> listAll() {
		return sqlSession.selectList("listAllStatus");
	}

}
