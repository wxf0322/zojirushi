/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.dao.custom;

import java.util.List;

import org.clover.zojirushi.dao.BaseDao;
import org.clover.zojirushi.entity.UserEntity;
import org.clover.zojirushi.model.vo.UserVO;
import org.clover.zojirushi.util.SqlFilter;
import org.springframework.data.domain.Page;

/**
 * 描述
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/6/28 下午6:33
 */
public interface UserDaoCustom extends BaseDao<UserEntity> {

    /**
     * @param page          当前页码
     * @param size          页面数据量
     * @param institutionId 组织机构id
     * @return
     */
    Page<UserVO> listUsersByPage(int page, int size, Long institutionId, SqlFilter sqlFilter);

    /**
     * 根据组织机构集合查询用户列表
     *
     * @param instNames
     * @return
     */
    List<UserEntity> listUsersByInstNames(String[] instNames);

    /**
     * 根据登入名集合查询用户列表
     *
     * @param loginNames
     * @return
     */
    List<UserEntity> listUsersByLoginNames(String[] loginNames);

    /**
     * 根据权限角色查询列表用户列表
     *
     * @param roleNames
     * @return
     */
    List<UserEntity> listUsersByRoleNames(String[] roleNames);

    /**
     * 更新用户与角色之间的关系
     *
     * @param userId
     * @param roleIds
     */
    void updateRoles(Long userId, String[] roleIds);

    /**
     * 更新用户与组织机构之间的关系
     *
     * @param userId
     * @param institutionIds
     */
    void updateInstitutions(Long userId, String[] institutionIds);
}
