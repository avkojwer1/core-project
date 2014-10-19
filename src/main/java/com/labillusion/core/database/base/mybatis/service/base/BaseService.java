package com.labillusion.core.database.base.mybatis.service.base;

import java.util.List;
import java.util.Map;

/**
 * Created by greg.chen on 14-9-17.
 */
public interface BaseService<T> {
    public List<T> selectList(Map<String, Object> query);

    public T selectOne(Map<String, Object> query);

    public T selectById(String id);

    public int insert(T entity);

    public int delete(Map<String, Object> query);

    public int deleteById(String id);

    public int update(T entity);
}
