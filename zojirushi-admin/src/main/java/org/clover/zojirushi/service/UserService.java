/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.service;

import java.util.List;

import org.clover.zojirushi.entity.RoleEntity;
import org.clover.zojirushi.entity.UserEntity;
import org.clover.zojirushi.model.vo.UserVO;
import org.clover.zojirushi.util.SqlFilter;
import org.springframework.data.domain.Page;

/**
 * 描述 用户Service层
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/4/25 8:51
 */
public interface UserService extends BaseService<UserEntity, Long> {
    /**
     * 创建用户
     *
     * @param userVO
     */
    UserEntity createUser(UserVO userVO);

    /**
     * 更新用户
     *
     * @param userVO
     * @return
     */
    UserEntity updateUser(UserVO userVO);

    /**
     * 修改密码
     *
     * @param id
     * @param newPassword
     */
    void changePassword(Long id, String newPassword);

    /**
     * 根据登入名查找用户
     *
     * @param loginName
     * @return
     */
    UserEntity getUserByLoginName(String loginName);

    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @param institutionId
     * @return
     */
    Page<UserVO> listUsersByPage(int page, int size, Long institutionId, SqlFilter sqlFilter);

    /**
     * 根据登入名列表查找用户
     *
     * @param loginNames
     * @return
     */
    List<UserEntity> listUsersByLoginNames(String[] loginNames);


    /**
     * 根据角色编码查询用户列表
     *
     * @param roleName
     * @return
     */
    List<UserEntity> listUsersByRoleName(String roleName);

    /**
     * 根据角色id查找用户列表
     *
     * @param roleId
     * @return
     */
    List<UserEntity> listUsersByRoleId(Long roleId);

    /**
     * 根据权限角色查询列表用户列表
     *
     * @param roleNames
     * @return
     */
    List<UserEntity> listUsersByRoleNames(String[] roleNames);

    /**
     * 根据组织机构编码构查询用户列表
     *
     * @param instName
     * @return
     */
    List<UserEntity> listUsersByInstName(String instName);

    /**
     * 根据组织机构编码列表查询
     *
     * @param instNames
     * @return
     */
    List<UserEntity> listUsersByInstNames(String[] instNames);

    /**
     * 查询网格员列表
     *
     * @param officalPost
     * @return
     */
    List<UserEntity> listUsersByOfficalPost(String officalPost);

    /**
     * 根据应用编码查询用户列表
     *
     * @param appName
     * @return
     */
    List<UserEntity> listUsersByAppName(String appName);

    /**
     * 根据管辖区域编码查询用户列表
     *
     * @param gridCode
     * @return
     */
    List<UserEntity> listUsersByGridCode(String gridCode);

    /**
     * 根据操作编码查询用户列表
     *
     * @param operName
     * @return
     */
    List<UserEntity> listUsersByOperName(String operName);

    /**
     * 根据权限编码查询用户列表
     *
     * @param privName
     * @return
     */
    List<UserEntity> listUsersByPrivName(String privName);

    /**
     * 验证登录
     *
     * @param loginName  用户名
     * @param password   密码
     * @param salt       盐
     * @param encryptpwd 加密后的密码
     * @return
     */
    boolean checkUser(String loginName, String password, String salt, String encryptpwd);


    /**
     * 更新用户与组织机构的关系
     *
     * @param userId
     * @param institutionIds
     */
    void updateInstitutions(Long userId, String[] institutionIds);


    /**
     * 获得当前用户已选中的用户
     *
     * @param userId
     * @return
     */
    List<RoleEntity> listTargetRoles(Long userId);

    /**
     * 获得当前用户未选中的用户
     *
     * @param userId
     * @return
     */
    List<RoleEntity> listSourceRoles(Long userId);

    /**
     * 更新用户与角色之间的关系
     *
     * @param userId
     * @param roleIds
     */
    void updateRoles(Long userId, String[] roleIds);


    /**
     * 描述 根据用户姓名模糊查询，返回用户列表
     *
     * @param name
     * @return
     */
    List<UserEntity> listUsersByNameLike(String name);
}
