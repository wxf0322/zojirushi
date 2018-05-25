/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.clover.zojirushi.dao.RoleDao;
import org.clover.zojirushi.dao.UserDao;
import org.clover.zojirushi.entity.RoleEntity;
import org.clover.zojirushi.entity.UserEntity;
import org.clover.zojirushi.model.vo.UserVO;
import org.clover.zojirushi.util.PasswordHelper;
import org.clover.zojirushi.service.UserService;
import org.clover.zojirushi.util.SqlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述 用户Service实现
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/4/25 8:52
 */
@Transactional
@Service
public class UserServiceImpl extends BaseServiceImpl<UserEntity, Long>
        implements UserService {

    /**
     * @see RoleDao
     */
    @Autowired
    private RoleDao roleDao;

    /**
     * @see UserDao
     */
    @Autowired
    private UserDao userDao;

    /**
     * @see PasswordHelper
     */
    @Autowired
    private PasswordHelper passwordHelper;

    /**
     * @param userVO
     * @return
     */
    @Override
    public UserEntity createUser(UserVO userVO) {
        UserEntity userEntity = new UserEntity();
        userEntity = userVO.mergeUserEntity(userEntity);
        userEntity.setPassword("123456");
        userEntity.setTimeCreated(new Date());
        //加密密码
        passwordHelper.encryptPassword(userEntity);
        return userDao.save(userEntity);
    }

    /**
     * @param userVO
     * @return
     */
    @Override
    public UserEntity updateUser(UserVO userVO) {
        UserEntity found = userDao.getOne(userVO.getId());
        found = userVO.mergeUserEntity(found);
        return userDao.save(found);
    }

    /**
     * 修改密码
     *
     * @param id
     * @param newPassword
     */
    @Override
    public void changePassword(Long id, String newPassword) {
        UserEntity user = userDao.getOne(id);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userDao.save(user);
    }

    /**
     * 根据用户名查找用户
     *
     * @param loginName
     * @return
     */
    @Override
    public UserEntity getUserByLoginName(String loginName) {
        return userDao.findFirstByLoginName(loginName);
    }

    /**
     * @param page
     * @param size
     * @param sqlFilter
     * @return
     */
    @Override
    public Page<UserVO> listUsersByPage(int page, int size, Long institutionId, SqlFilter sqlFilter) {
        return userDao.listUsersByPage(page, size, institutionId, sqlFilter);
    }

    @Override
    public List<UserEntity> listUsersByLoginNames(String[] loginNames) {
        return userDao.listUsersByLoginNames(loginNames);
    }

    /**
     * 根据角色编码查询用户列表
     *
     * @param roleName
     * @return
     */
    @Override
    public List<UserEntity> listUsersByRoleName(String roleName) {
        return userDao.listUsersByRoleName(roleName);
    }

    /**
     * 根据角色id查找用户列表
     *
     * @param roleId
     * @return
     */
    public List<UserEntity> listUsersByRoleId(Long roleId) {
        return userDao.listUsersByRoleId(roleId);
    }


    /**
     * 根据角色编码列表查询用户列表
     *
     * @param roleNames
     * @return
     */
    @Override
    public List<UserEntity> listUsersByRoleNames(String[] roleNames) {
        return userDao.listUsersByRoleNames(roleNames);
    }

    /**
     * 根据组织机构编码构查询用户列表
     *
     * @param instName
     * @return
     */
    @Override
    public List<UserEntity> listUsersByInstName(String instName) {
        return userDao.listUsersByInstName(instName);
    }

    /**
     * 根据组织机构集合查找员工
     *
     * @param instNames
     * @return
     */
    @Override
    public List<UserEntity> listUsersByInstNames(String[] instNames) {
        return userDao.listUsersByInstNames(instNames);
    }

    /**
     * 验证登录
     *
     * @param loginName  用户名
     * @param password   密码
     * @param salt       盐
     * @param encryptpwd 加密后的密码
     * @return
     */
    @Override
    public boolean checkUser(String loginName, String password, String salt, String encryptpwd) {
        String pwd = passwordHelper.encryptPassword(loginName, password, salt);
        return pwd.equals(encryptpwd);
    }


    /**
     * @param userId
     * @param institutionIds
     */
    @Override
    public void updateInstitutions(Long userId, String[] institutionIds) {
        userDao.updateInstitutions(userId, institutionIds);
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public List<RoleEntity> listTargetRoles(Long userId) {
        if (userId == null) {
            return new ArrayList<>();
        } else {
            return roleDao.listTargetRolesByUserId(userId);
        }
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public List<RoleEntity> listSourceRoles(Long userId) {
        if (userId != null) {
            return roleDao.listSourceRolesByUserId(userId);
        } else {
            return roleDao.findByEnabled(1L);
        }
    }

    /**
     * @param userId
     * @param roleIds
     */
    @Override
    public void updateRoles(Long userId, String[] roleIds) {
        userDao.updateRoles(userId, roleIds);
    }



    /**
     * 描述
     * 查询网格员列表
     *
     * @param officalPost
     * @return
     */
    @Override
    public List<UserEntity> listUsersByOfficalPost(String officalPost) {
        return userDao.listUsersByOfficalPost(officalPost);
    }

    /**
     * 根据应用编码查询用户列表
     *
     * @param appName
     * @return
     */
    @Override
    public List<UserEntity> listUsersByAppName(String appName) {
        return userDao.listUsersByAppName(appName);
    }

    /**
     * 根据管辖区域编码查询用户列表
     *
     * @param gridCode
     * @return
     */
    @Override
    public List<UserEntity> listUsersByGridCode(String gridCode) {
        return userDao.listUsersByGridCode(gridCode);
    }

    /**
     * 根据操作编码查询用户列表
     *
     * @param operName
     * @return
     */
    @Override
    public List<UserEntity> listUsersByOperName(String operName) {
        return userDao.listUsersByOperName(operName);
    }

    /**
     * 根据权限编码查询用户列表
     *
     * @param privName
     * @return
     */
    @Override
    public List<UserEntity> listUsersByPrivName(String privName) {
        return userDao.listUsersByPrivName(privName);
    }


    @Override
    public List<UserEntity> listUsersByNameLike(String name) {
        return userDao.findByNameLike(name);
    }

}
