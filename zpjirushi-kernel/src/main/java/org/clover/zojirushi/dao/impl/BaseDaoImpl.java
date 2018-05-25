/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.clover.zojirushi.dao.BaseDao;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

/**
 * <P><B>描述: </B> 数据层基础类  </P>
 *
 * @param <T> 实体类
 * @author wangxiaofeng
 * @version 4.0
 * @created 2017/06/24 12:00:00
 */
@Repository("baseDao")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

    /**
     * @see Logger
     */
    private static Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

    /**
     * @see EntityManager
     */
    @PersistenceContext
    protected EntityManager manager;

    /**
     * 泛型类
     */
    private Class<T> clazz;

    /**
     * 构造方法
     */
    protected BaseDaoImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
    }

    /**
     * 根据语句去获取可执行的query对象
     *
     * @param sqlString
     * @param values
     * @return
     */
    private Query createNativeQuery(String sqlString, Object[] values) {
        Query query = this.createNativeQuery(null, sqlString, values);
        return query;
    }

    /**
     * 设置SQL语句参数
     *
     * @param query
     * @param values
     * @return
     */
    private Query setParameters(Query query, Object[] values) {
        if (values != null && values.length > 0) {
            for (int i = 0, j = 1; i < values.length; i++, j++) {
                if (values[i] instanceof Date) {
                    query.setParameter(j, (Date) values[i], TemporalType.DATE);
                } else {
                    query.setParameter(j, values[i]);
                }
            }
        }
        return query;
    }

    /**
     * 根据语句去获取可执行的query对象
     *
     * @param sqlString
     * @param values
     * @return
     */
    private Query createNativeQuery(Class entityClass, String sqlString, Object[] values) {
        Query query;
        if (entityClass == null) {
            query = manager.createNativeQuery(sqlString);
        } else {
            query = manager.createNativeQuery(sqlString, entityClass);
        }
        return setParameters(query, values);
    }

    /**
     * 描述： 根据名称去获取可执行的query对象
     *
     * @param name
     * @param values
     * @return
     */
    private Query createNamedQuery(String name, Object[] values) {
        Query query = manager.createNamedQuery(name);
        return setParameters(query, values);
    }

    /**
     * 查询数据总量
     *
     * @param sqlString
     * @param values
     * @return
     */
    @Override
    public long getCountBySql(String sqlString, Object[] values) {
        String allSql = "select count(*) from (" + sqlString + ")";
        Query query = this.createNativeQuery(allSql, values);
        return Long.valueOf(query.getSingleResult().toString());
    }

    /**
     * 别名查询
     *
     * @param name
     * @param values
     * @param <M>
     * @return
     */
    @Override
    public <M> List<M> namedQueryForClass(String name, Object[] values) {
        Query query = this.createNamedQuery(name, values);
        return query.getResultList();
    }

    /**
     * 分页别名查询
     *
     * @param entityClass
     * @param name
     * @param values
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public <M> Page<M> namedQueryForClass(Class<M> entityClass,
                                          String name, Object[] values,
                                          int page, int pageSize) {
        Query query = this.createNamedQuery(name, values);
        String queryString = query.unwrap(org.hibernate.Query.class).getQueryString();
        return this.queryForClass(entityClass, queryString, values, page, pageSize);
    }

    /**
     * 直接查询
     *
     * @param sqlString
     * @param values
     * @return
     */
    @Override
    public List<Object> queryForObject(String sqlString, Object[] values) {
        Query query = this.createNativeQuery(sqlString, values);
        return query.getResultList();
    }

    /**
     * 分页查询
     *
     * @param sqlString
     * @param values
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public Page<Object> queryForObject(String sqlString,
                                       Object[] values,
                                       int page, int pageSize) {
        Query query = this.createNativeQuery(sqlString, values);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return new PageImpl<Object>(query.getResultList(),
                new PageRequest(page, pageSize),
                getCountBySql(sqlString, values));
    }

    /**
     * 返回查询到的ListMap
     *
     * @param sqlString
     * @param values
     * @return
     */
    @Override
    public List<Map<String, Object>> queryForMap(String sqlString, Object[] values) {
        Query query = this.createNativeQuery(sqlString, values);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    /**
     * 返回分页过的Map
     *
     * @param sqlString
     * @param values
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public Page<Map<String, Object>> queryForMap(String sqlString,
                                                 Object[] values,
                                                 int page, int pageSize) {
        Query query = this.createNativeQuery(sqlString, values);

        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return new PageImpl<Map<String, Object>>(query.getResultList(),
                new PageRequest(page, pageSize),
                getCountBySql(sqlString, values));
    }

    /**
     * 根据sql语句查询。
     *
     * @param entityClass
     * @param sqlString   the sql string
     * @param values      the values
     * @param <M>
     * @return
     */
    @Override
    public <M> List<M> queryForClass(Class<M> entityClass, String sqlString, Object[] values) {
        Query query = this.createNativeQuery(entityClass, sqlString, values);
        return query.getResultList();
    }

    /**
     * 根据sql语句查询。
     *
     * @param sqlString the sql string
     * @param values    the values
     * @return
     */
    @Override
    public List<T> queryForClass(String sqlString, Object[] values) {
        return queryForClass(this.clazz, sqlString, values);
    }

    /**
     * 根据sql语句查询。
     *
     * @param entityClass
     * @param sqlString   the sql string
     * @param values      the values
     * @param page        the start
     * @param pageSize    the page size
     * @param <M>
     * @return
     */
    @Override
    public <M> Page<M> queryForClass(Class<M> entityClass,
                                     String sqlString,
                                     Object[] values,
                                     int page, int pageSize) {
        Query query = this.createNativeQuery(entityClass, sqlString, values);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return new PageImpl<M>(query.getResultList(),
                new PageRequest(page, pageSize),
                getCountBySql(sqlString, values));
    }

    /**
     * 根据sql语句查询。
     *
     * @param sqlString
     * @param values
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public Page<T> queryForClass(String sqlString, Object[] values, int page, int pageSize) {
        return queryForClass(this.clazz, sqlString, values, page, pageSize);
    }

    /**
     * 根据sql语句查询。
     *
     * @param entityClass
     * @param sqlString
     * @param values
     * @param <M>
     * @return
     */
    @Override
    public <M> M querySingle(Class<M> entityClass, String sqlString, Object[] values) {
        Query query = this.createNativeQuery(entityClass, sqlString, values);
        query.setMaxResults(1);
        List<M> list = query.getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 根据sql语句查询，并返回一条记录, 适用于VModel
     *
     * @param sqlString
     * @param values
     * @return
     */
    @Override
    public T querySingle(String sqlString, Object[] values) {
        return this.querySingle(this.clazz, sqlString, values);
    }

    /**
     * 事务执行sql语句，也可执行无返回结果的存储过程
     *
     * @param sqlString
     * @param values
     * @return 受影响的记录数
     * @throws Exception
     */
    @Override
    public int executeSql(String sqlString, Object[] values) throws Exception {
        Query query = this.createNativeQuery(sqlString, values);
        return query.executeUpdate();
    }

}
