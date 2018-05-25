/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.service.impl;

import java.util.List;

import org.clover.zojirushi.dao.OperationDao;
import org.clover.zojirushi.entity.OperationEntity;
import org.clover.zojirushi.model.vo.OperationVO;
import org.clover.zojirushi.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Pisces Lu
 * @version 1.0
 * @created 2017-5-8 14:44
 */
@Transactional
@Service
public class OperationServiceImpl extends BaseServiceImpl<OperationEntity, Long>
        implements OperationService {

    /**
     * @see OperationDao
     */
    @Autowired
    private OperationDao operationDao;

    /**
     * 获取应用操作列表
     *
     * @param appName
     * @return
     */
    @Override
    public List<OperationEntity> listOpersByAppName(String appName) {
        return operationDao.listOpersByAppName(appName);
    }

    /**
     * 通过id查找用户权限
     *
     * @param userId
     * @return
     */
    @Override
    public List<OperationEntity> listOpersByUserId(Long userId) {
        return operationDao.listOpersByUserId(userId);
    }

    /**
     * 根据userId，appId查找用户
     *
     * @param userId
     * @param appId
     * @return
     */
    @Override
    public List<OperationEntity> listOpersByUserIdAndAppId(Long userId, Long appId) {
        return operationDao.listOpersByUserIdAndAppId(userId, appId);
    }

    /**
     * 判断是否拥有该操作
     *
     * @param userId
     * @param operName
     * @return
     */
    @Override
    public boolean hasOperation(long userId, String operName) {
        List<OperationEntity> result = operationDao.listUserOperations(userId, operName);
        return result.size() != 0;
    }

    /**
     * 判断该操作节点能否被删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean canBeDeleted(Long id) {
        return operationDao.canBeDeleted(id);
    }

    @Override
    public List<OperationVO> listOpersByPrivId(Long privilegeId) {
        return operationDao.listOpersByPrivId(privilegeId);
    }


}
