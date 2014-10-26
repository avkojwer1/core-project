package com.labillusion.core.database.base.mybatis.service.base.impl;


import com.labillusion.core.database.base.mybatis.dao.BaseDao;
import com.labillusion.core.database.base.mybatis.service.base.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by greg.chen on 14-9-17.
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    /**
     * 获取基础数据库操作类
     *
     * @return
     */
    protected abstract BaseDao<T> getBaseDao();

    @Override
    public List<T> selectList(Map<String, Object> query) {
        return getBaseDao().selectList(query);
    }

    @Override
    public List<T> selectList() {
        return getBaseDao().selectList(null);
    }

    @Override
    public T selectOne(Map<String, Object> query) {
        return getBaseDao().selectOne(query);
    }

    @Override
    public T selectById(String id) {
        return getBaseDao().selectById(id);
    }

    @Override
    @Transactional
    public int insert(T entity) {
        return getBaseDao().insert(entity);
    }

    @Override
    @Transactional
    public int delete(Map<String, Object> query) {
        return getBaseDao().delete(query);
    }

    @Override
    @Transactional
    public int deleteById(String id) {
        return getBaseDao().deleteById(id);
    }

    @Override
    @Transactional
    public int update(T entity) {
        return getBaseDao().update(entity);
    }
}
