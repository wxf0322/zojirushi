/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.service.impl;

import org.clover.zojirushi.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 描述 Service抽象层
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/5/26 8:53
 */
@Transactional
@Service("baseService")
public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

    /**
     * 由业务类实现
     *
     * @return
     */
    @Autowired
    public JpaRepository<T, ID> jpaRepository;

    /**
     * 保存或者更新
     *
     * @param entity
     * @return
     */
    public T saveOrUpdate(T entity) {
        return jpaRepository.save(entity);
    }

    /**
     * 查询单个
     *
     * @param entityId
     * @return
     */
    public T findOne(ID entityId) {
        if (entityId == null) return null;
        return jpaRepository.getOne(entityId);
    }

    /**
     * 查询全部
     *
     * @return
     */
    public List<T> findAll() {
        return jpaRepository.findAll();
    }

    /**
     * 单行删除
     *
     * @param entityId
     */
    public void delete(ID entityId) {
        jpaRepository.deleteById(entityId);
    }

    /**
     * 多行删除
     *
     * @param entityIds
     */
    public void delete(ID[] entityIds) {
        if (entityIds != null) {
            for (ID id : entityIds) {
                jpaRepository.deleteById(id);
            }
        }
    }

}
