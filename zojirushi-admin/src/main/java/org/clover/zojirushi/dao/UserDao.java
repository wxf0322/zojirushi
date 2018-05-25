/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.dao;

import java.util.List;

import org.clover.zojirushi.dao.custom.UserDaoCustom;
import org.clover.zojirushi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 描述
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/6/28 下午6:33
 */
@Repository
public interface UserDao extends JpaRepository<UserEntity, Long>, UserDaoCustom {

    UserEntity findFirstByLoginName(String loginName);

    /**
     * 根据权限角色查询用户列表
     *
     * @param roleName
     * @return
     */
    @Query(value = "select * from users u where u.id in " +
            "(select ur.user_id from user_role ur where ur.role_id in " +
            "(select r.id from roles r where r.name = ?1 and r.enabled = 1) " +
            ")and u.enabled = 1", nativeQuery = true)
    List<UserEntity> listUsersByRoleName(String roleName);

    /**
     * 根据组织机构编码构查询用户列表
     *
     * @param instName
     * @return
     */
    @Query(value = " select * from users u where u.id in ( " +
            " select ui.user_id from user_institution ui " +
            " where ui.institution_id in( " +
            " select i.id from institutions i where i.name =?1))", nativeQuery = true)
    List<UserEntity> listUsersByInstName(String instName);

    /**
     * 根据管辖区域编码查询用户列表
     *
     * @param gridCode
     * @return
     */
    @Query(value = "select * from users u where u.id in " +
            "(select ug.user_id from user_grid ug where ug.grid_code=?1)",
            nativeQuery = true)
    List<UserEntity> listUsersByGridCode(String gridCode);

    /**
     * 根据应用编码查询用户列表
     *
     * @param appName
     * @return
     */
    @Query(value = "select * from users u where u.id in " +
            "(select ur.user_id from user_role ur where ur.role_id in " +
            "(select r.id from roles r " +
            "where r.id in (select pr.role_id from privilege_role pr where pr.priv_id in " +
            "(select p.id from privileges p where p.id in " +
            "(select po.oper_id from privilege_operation po " +
            "where po.oper_id in " +
            "(select o.id from operations o where o.application_id in " +
            "(select a.id from applications a where a.name = ?1) " +
            "and o.enabled = 1)) " +
            "and p.enabled = 1)) and r.enabled = 1)) and u.enabled = 1", nativeQuery = true)
    List<UserEntity> listUsersByAppName(String appName);

    /**
     * 根据角色id查找用户列表
     *
     * @param roleId
     * @return
     */
    @Query(value = "select * from users u where u.id in " +
            "(select ur.user_id from user_role ur " +
            "where ur.role_id = ?1)", nativeQuery = true)
    List<UserEntity> listUsersByRoleId(Long roleId);


    /**
     * 根据权限编码查询用户列表
     *
     * @param privName
     * @return
     */
    @Query(value = "select * from users u " +
            "where u.id in (select ur.user_id from user_role ur " +
            "where ur.role_id in (select r.id from roles r " +
            "where r.id in (select pr.role_id from privilege_role pr " +
            "where pr.priv_id in (select p.id from privileges p " +
            "where p.name = ?1)) and r.enabled = 1)) and u.enabled = 1 ", nativeQuery = true)
    List<UserEntity> listUsersByPrivName(String privName);

    /**
     * 根据操作编码查询用户列表
     *
     * @param operName
     * @return
     */
    @Query(value = "select * from users u where u.id in " +
            "(select ur.user_id from user_role ur " +
            "where ur.role_id in (select r.id from roles r " +
            "where r.id in (select pr.role_id from privilege_role pr " +
            "where pr.priv_id in (select p.id from privileges p " +
            "where p.id in (select po.priv_id from privilege_operation po " +
            "where po.oper_id in (select o.id from operations o " +
            "where o.name = ?1)) and p.enabled = 1)) " +
            "and r.enabled = 1)) and u.enabled = 1"
            , nativeQuery = true)
    List<UserEntity> listUsersByOperName(String operName);


    /**
     * 查询网格员列表
     *
     * @param officalPost
     * @return
     */
    @Query(value = "select * from users u where u.staff_id in " +
            "(select s.id from staffs s where s.offical_post = ?1) " +
            "and u.enabled=1", nativeQuery = true)
    List<UserEntity> listUsersByOfficalPost(String officalPost);


    /**
     * 根据用户姓名查找用户列表
     * @param name
     * @return
     */
    List<UserEntity> findByNameLike(String name);
}
