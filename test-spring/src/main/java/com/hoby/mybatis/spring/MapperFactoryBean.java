package com.hoby.mybatis.spring;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author hoby
 * @since 2024-01-09
 */
public class MapperFactoryBean implements FactoryBean {

	private Class mapperInterface;

	private SqlSession sqlSession;

	public MapperFactoryBean(Class mapperInterface) {
		this.mapperInterface = mapperInterface;
	}

	public void setSqlSession(SqlSessionFactory sqlSessionFactory) {
		sqlSessionFactory.getConfiguration().addMapper(mapperInterface);
		this.sqlSession = sqlSessionFactory.openSession();
	}

	@Override
	public Object getObject() throws Exception {
		return sqlSession.getMapper(mapperInterface);
	}

	@Override
	public Class<?> getObjectType() {
		return mapperInterface;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
