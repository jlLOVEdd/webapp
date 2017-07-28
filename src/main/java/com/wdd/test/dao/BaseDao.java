package com.wdd.test.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;



import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.wdd.test.bean.PageParameter;
import com.wdd.test.bean.PageRowBounds;

@SuppressWarnings("all")
public class BaseDao<T> extends SqlSessionDaoSupport {
	protected String namespace;
	
	@Resource(name="sqlSessionFactory")
	 protected SqlSessionFactory sqlSessionFactory;
	
    @Autowired
	protected JdbcTemplate		jdbcTemplate;

	protected Integer			BATCH_LIMIT	= 50;
	
	public BaseDao(){
		namespace = this.getClass().getName();
	}


	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
	@PostConstruct
	public void sqlSessionFactory()
	{
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Integer getBATCH_LIMIT() {
		return BATCH_LIMIT;
	}

	public void setBATCH_LIMIT(Integer bATCH_LIMIT) {
		BATCH_LIMIT = bATCH_LIMIT;
	}
	
	
	/**
	 * 查询列表
	 * 
	 * @param t
	 * @return
	 */
	public PageParameter queryForPage(PageParameter page, T t)
	{
		List<T> list = this.getSqlSession().selectList(namespace + ".queryForPage", t, new PageRowBounds(page));
		page.setData(list);
		return page;
	}

	/**
	 * 根据ID获取实体
	 * 
	 * @param id
	 * @return
	 */
	public T selectById(Long id)
	{
		return (T) this.getSqlSession().selectOne(namespace + ".selectById", id);
	}

	/**
	 * 保存
	 * 
	 * @param t
	 * @return
	 */
	public void save(T t)
	{
		this.getSqlSession().insert(namespace + ".save", t);
	}

	/**
	 * 更新
	 * 
	 * @param t
	 * @return
	 */
	public void update(T t)
	{
		this.getSqlSession().update(namespace + ".update", t);
	}

	/**
	 * 根据ID删除
	 * 
	 * @param id
	 */
	public void delete(Long... ids)
	{
		this.getSqlSession().update(namespace + ".delete", ids);
	}


	
}
