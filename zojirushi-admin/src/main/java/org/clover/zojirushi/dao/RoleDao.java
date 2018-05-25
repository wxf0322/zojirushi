/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.dao;

import java.util.List;

import org.clover.zojirushi.dao.custom.RoleDaoCustom;
import org.clover.zojirushi.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Pisces Lu
 * @version 1.0
 * @created 2017-5-8 18:24
 */
@Repository
public interface RoleDao extends JpaRepository<RoleEntity, Long>, RoleDaoCustom {

    /**
     * @param enabled
     * @return
     */
    List<RoleEntity> findByEnabled(Long enabled);

    /**
     * 根据用户名查找角色列表
     *
     * @param userId
     * @return
     */
    @Query(value = "select * from roles r where r.id in( " +
            "select ur.role_id from user_role ur " +
            "where ur.user_id = ?1) and r.enabled = 1", nativeQuery = true)
    List<RoleEntity> listRolesByUserId(long userId);

    @Query(value = "select * from roles r where r.id in " +
            "(select ur.role_id from user_role ur where ur.user_id = ?1) " +
            "and r.enabled = 1 ", nativeQuery = true)
    List<RoleEntity> listRolesByUserId(Long userId);

    @Query(value = "select * from roles where id in " +
            "(select role_id from user_role where user_id = ?1) and enabled=1 ", nativeQuery = true)
    List<RoleEntity> listTargetRolesByUserId(Long userId);

    @Query(value = "select * from roles where id not in " +
            "(select role_id from user_role where user_id = ?1) and enabled=1 ", nativeQuery = true)
    List<RoleEntity> listSourceRolesByUserId(Long userId);

    /**
     * 获取该权限已选择的角色
     */
    @Query(value = "select * from roles r where r.id in " +
            "(select role_id from privilege_role " +
            "where priv_id =?1) and enabled=1 ", nativeQuery = true)
    List<RoleEntity> listTargetRolesByPrivId(Long privilegeId);

    /**
     * 获取该权限未选择的角色
     */
    @Query(value = "select * from roles r where r.id not in " +
            "(select role_id from privilege_role " +
            "where priv_id =?1) and enabled=1", nativeQuery = true)
    List<RoleEntity> listSourceRolesByPrivId(Long privilegeId);

    /**
     * 判断是否拥有此角色
     *
     * @param userId
     * @return
     */
    @Query(value = "select * from roles r where r.id in( " +
            "select ur.role_id from user_role ur " +
            "where ur.user_id = ?1) and r.enabled=1 and r.name= ?2", nativeQuery = true)
    List<RoleEntity> listUserRoles(long userId, String roleName);


}
