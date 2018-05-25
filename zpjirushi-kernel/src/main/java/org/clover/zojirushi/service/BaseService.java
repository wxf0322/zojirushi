/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.service;

import java.io.Serializable;
import java.util.List;

/**
 * 描述
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/5/26 8:52
 */
public interface BaseService<T, ID extends Serializable> {
    /**
     * 保存或者更新
     *
     * @param entity
     * @return
     */
    T saveOrUpdate(T entity);

    /**
     * 查询单个
     *
     * @param entityId
     * @return
     */
    T findOne(ID entityId);

    /**
     * 查询全部对象
     *
     * @return
     */
    List<T> findAll();

    /**
     * 单行删除
     *
     * @param entityId
     */
    void delete(ID entityId);

    /**
     * 多行删除
     *
     * @param entityIds
     */
    void delete(ID[] entityIds);

}
