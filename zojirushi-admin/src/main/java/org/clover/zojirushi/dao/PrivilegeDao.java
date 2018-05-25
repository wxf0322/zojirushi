/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.dao;

import java.util.List;

import org.clover.zojirushi.dao.custom.PrivilegeDaoCustom;
import org.clover.zojirushi.entity.PrivilegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/**
 * @author Pisces Lu
 * @version 1.0
 * @created 2017-5-8 17:26
 */
@Repository
public interface PrivilegeDao extends JpaRepository<PrivilegeEntity, Long>, PrivilegeDaoCustom {

    List<PrivilegeEntity> findByEnabled(Long enabled);

    /**
     * 根据app名字获取权限列表
     *
     * @param appName
     * @return
     */
    @Query(value = "select * from privileges p " +
            "where p.id in (select po.priv_id from privilege_operation po " +
            "where po.oper_id in (select o.id " +
            "from operations o " +
            "where o.application_id in " +
            "(select a.id from applications a " +
            "where a.name= ?1) " +
            "and o.enabled = 1)) ", nativeQuery = true)
    List<PrivilegeEntity> listPrivsByAppName(String appName);

    /**
     * 根据用户名获取权限列表
     *
     * @param userId
     * @return
     */
    @Query(value = "select * from privileges p " +
            "where p.id in( " +
            "select pr.priv_id from privilege_role pr " +
            "where pr.role_id in( " +
            "select ur.role_id from user_role ur " +
            "where ur.user_id = ?1) " +
            ") and p.enabled = 1", nativeQuery = true)
    List<PrivilegeEntity> listPrivsByUserId(long userId);

    /**
     * 判断是否拥有次权限
     *
     * @param userId
     * @param privName
     * @return
     */
    @Query(value = "select * from privileges p " +
            "where p.id in( " +
            "select pr.priv_id from privilege_role pr " +
            "where pr.role_id in( " +
            "select ur.role_id from user_role ur " +
            "where ur.user_id = ?1) " +
            ") and p.enabled = 1 and p.name= ?2", nativeQuery = true)
    List<PrivilegeEntity> listUserPrivileges(long userId, String privName);

    /**
     * 查找角色Id对应的权限列表
     *
     * @param roleId
     * @return
     */
    @Query(value = " select * from privileges where id in( " +
            "select priv_id from privilege_role t " +
            "where role_id=?1) and enabled = 1", nativeQuery = true)
    List<PrivilegeEntity> listTargetPrivileges(Long roleId);

    /**
     * 查找角色Id对应的未选择权限列表
     *
     * @param roleId
     * @return
     */
    @Query(value = "select * from privileges where id not in( " +
            "select priv_id from privilege_role t " +
            "where role_id=?1) and enabled = 1", nativeQuery = true)
    List<PrivilegeEntity> listSourcePrivileges(Long roleId);

}
