/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

/**
 * <P><B>描述: </B> 数据层基础类接口  </P>
 *
 * @param <T>  实体类
 * @author wangxiaofeng
 * @version 4.0
 * @created 2017/06/24 12:00:00
 */
public interface BaseDao<T> {

    /**
     * 查询数据总量
     *
     * @param sqlString sql语句
     * @param values    参数值
     * @return count by sql
     */
    long getCountBySql(String sqlString, Object[] values);

    /**
     * 别名查询
     *
     * @param name
     * @param values
     * @param <M>
     * @return
     */
    <M> List<M> namedQueryForClass(String name, Object[] values);

    /**
     * 分页别名查询
     *
     * @param entityClass
     * @param name
     * @param values
     * @param page
     * @param pageSize
     * @param <M>
     * @return
     */
     <M> Page<M> namedQueryForClass(Class<M> entityClass, String name, Object[] values, int page, int pageSize);

    /**
     * 根据sql语句查询，返回List
     *
     * @param sqlString the sql string
     * @param values    the values
     * @return list
     */
    List<Object> queryForObject(String sqlString, Object[] values);

    /**
     * 根据sql语句分页查询，返回List
     *
     * @param sqlString sql语句
     * @param values    参数值
     * @param page      the page
     * @param pageSize  the page size
     * @return 查询结果 list
     */
    Page<Object> queryForObject(String sqlString, Object[] values, int page, int pageSize);

    /**
     * 查询返回ListMap
     *
     * @param sqlString
     * @param values
     * @return
     */
    List<Map<String, Object>> queryForMap(String sqlString, Object[] values);

    /**
     * 返回分页过的List Map
     *
     * @param sqlString
     * @param values
     * @param page
     * @param pageSize
     * @return
     */
    Page<Map<String, Object>> queryForMap(String sqlString, Object[] values, int page, int pageSize);

    /**
     * 根据sql语句查询。也可以执行返回数据集的存储过程
     *
     * @param <M>         the type parameter
     * @param entityClass the clazz
     * @param sqlString   the sql string
     * @param values      the values
     * @return list
     */
    <M> List<M> queryForClass(Class<M> entityClass, String sqlString, Object[] values);

    /**
     * 根据sql语句查询。也可以执行返回数据集的存储过程
     *
     * @param sqlString the sql string
     * @param values    the values
     * @return list
     */
    List<T> queryForClass(String sqlString, Object[] values);

    /**
     * 根据sql语句查询（分页）, 适用于VModel
     *
     * @param <M>         the type parameter
     * @param entityClass the entityClass
     * @param sqlString   the sql string
     * @param values      the values
     * @param page        the page
     * @param pageSize    the page size
     * @return list
     */
    <M> Page<M> queryForClass(Class<M> entityClass, String sqlString, Object[] values, int page, int pageSize);

    /**
     * 根据sql语句查询（分页）
     *
     * @param sqlString the sql string
     * @param values    the values
     * @param page      the page
     * @param pageSize  the page size
     * @return list
     */
    Page<T> queryForClass(String sqlString, Object[] values, int page, int pageSize);

    /**
     * 根据sql语句查询，并返回一条记录
     *
     * @param <M>         the type parameter
     * @param entityClass the entityClass
     * @param sqlString   the sql string
     * @param values      the values
     * @return m
     */
    <M> M querySingle(Class<M> entityClass, String sqlString, Object[] values);

    /**
     * 根据sql语句查询，并返回一条记录
     *
     * @param sqlString the sql string
     * @param values    the values
     * @return t
     */
    T querySingle(String sqlString, Object[] values);

    /**
     * 事务执行sql语句，也可执行无返回结果的存储过程
     *
     * @param sqlString the sql string
     * @param values    the values
     * @return 受影响的记录数 int
     * @throws Exception the exception
     */
    int executeSql(String sqlString, Object[] values) throws Exception;

}
