package com.wdd.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wdd.test.bean.PageParameter;
import com.wdd.test.dao.BaseDao;


@Service
@Transactional(readOnly = true)
public abstract class BaseService<T> {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public abstract BaseDao<T> getDao();

    public PageParameter queryForPage(PageParameter page, T t) {
        return getDao().queryForPage(page, t);
    }

    @Transactional(readOnly = false)
    public void save(T t) {
        this.getDao().save(t);
    }

    @Transactional(readOnly = false)
    public String[] saveOrder(T t) {
        this.getDao().save(t);
        return null;
    }

    @Transactional(readOnly = false)
    public void update(T t) {
        this.getDao().update(t);
    }

    @Transactional(readOnly = false)
    public void delete(Long... ids) {
        this.getDao().delete(ids);
    }

    @Transactional(readOnly = true)
    public T getObjectById(Long id) {
        return this.getDao().selectById(id);
    }

    @Transactional(readOnly = false)
    public void executeSql(String sql) {
        this.getDao().getJdbcTemplate().execute(sql);
    }
}
