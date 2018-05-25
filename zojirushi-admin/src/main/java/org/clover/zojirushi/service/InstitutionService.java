/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.service;

import org.clover.zojirushi.entity.InstitutionEntity;

import java.util.List;

/**
 * 描述
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/5/8 15:16
 */
public interface InstitutionService extends BaseService<InstitutionEntity, Long> {

    /**
     * 根据登陆名获取机构列表
     *
     * @param loginName
     * @return
     */
    List<InstitutionEntity> listInstsByLoginName(String loginName);

    /**
     * 获取用户相关的组织机构列表
     *
     * @param userId
     * @return
     */
    List<InstitutionEntity> listInstsByUserId(Long userId);

    /**
     * 查询子组织机构
     * @param instName
     * @return
     */
    List<InstitutionEntity> listSubInstsByInstName(String instName);

    /**
     * 根据登陆名获取机构列表
     *
     * @param instName
     * @return
     */
    InstitutionEntity getInstByInstName(String instName);

    /**
     * 检查该组织机构节点是否能被删除
     *
     * @param id
     * @return
     */
    boolean canBeDeleted(Long id);

}
