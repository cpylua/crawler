package util;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBUtil
{
	private static SqlSession sqlSession;

	public static SqlSession getSession()
	{
		try
		{
			if (sqlSession == null)
			{
				String resource = "mybatis_cfg.xml";
				Reader reader = Resources.getResourceAsReader(resource);
				Properties props = new Properties();
				props.load(Resources.getResourceAsReader("jdbc.properties"));
				SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader, props);
				sqlSession = sessionFactory.openSession();
			}
			return sqlSession;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static void closeSession()
	{
		if (sqlSession != null)
		{
			sqlSession.close();
			sqlSession = null;
		}
	}
}
