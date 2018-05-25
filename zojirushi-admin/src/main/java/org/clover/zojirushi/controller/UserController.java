/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.clover.zojirushi.entity.InstitutionEntity;
import org.clover.zojirushi.entity.RoleEntity;
import org.clover.zojirushi.entity.UserEntity;
import org.clover.zojirushi.model.vo.UserVO;
import org.clover.zojirushi.service.InstitutionService;
import org.clover.zojirushi.service.UserService;
import org.clover.zojirushi.util.SqlFilter;
import org.clover.zojirushi.vo.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述 user控制器
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/4/25 8:50
 */
@Controller
@RequestMapping("/user")
@MultipartConfig
public class UserController {

    /**
     * @see UserService
     */
    @Autowired
    private UserService userService;

//    /**
//     * @see GridService
//     */
//    @Autowired
//    private GridService gridService;

    /**
     * @see InstitutionService
     */
    @Autowired
    private InstitutionService institutionService;

    @ResponseBody
    @RequestMapping(value = "list")
    public Page<UserVO> listUsersByPage(Integer page, Integer size, Long institutionId,
                                        HttpServletRequest request) {
        SqlFilter sqlFilter = new SqlFilter();
        String key = request.getParameter("key");
        if (!StringUtils.isEmpty(key)) {
            sqlFilter.addOrFilter("QUERY_u#login_name_S_LK", key);
            sqlFilter.addOrFilter("QUERY_u#name_S_LK", key);
        }
        return userService.listUsersByPage(page, size, institutionId, sqlFilter);
    }

    @ResponseBody
    @RequestMapping(value = "delete")
    public ResultStatus delete(String columns) {
        if (StringUtils.isNotEmpty(columns)) {
            String[] ids = columns.split(",");
            long[] entityIds = Arrays.stream(ids).mapToLong(Long::valueOf).toArray();
            userService.delete(ArrayUtils.toObject(entityIds));
        }
        return new ResultStatus(true, "");
    }

    @ResponseBody
    @RequestMapping(value = "find")
    public UserVO findOne(Long id) {
        UserEntity user = userService.findOne(id);
        UserVO userVO = new UserVO(user);
        return userVO;
    }

    @ResponseBody
    @RequestMapping(value = "saveOrUpdate")
    public ResultStatus saveOrUpdate(@RequestBody UserVO userVO) {
        String[] roleIds = StringUtils.isEmpty(userVO.getRoleIds()) ?
                null : userVO.getRoleIds().split(",");

        String[] institutionIds = StringUtils.isEmpty(userVO.getInstitutionIds()) ?
                null : userVO.getInstitutionIds().split(",");

        if (userVO.getId() == null) {
            // 新增
            UserEntity userEntity = userService.createUser(userVO);
            userService.updateRoles(userEntity.getId(), roleIds);
            userService.updateInstitutions(userEntity.getId(), institutionIds);
        } else {
            // 修改
            userService.updateUser(userVO);
            userService.updateRoles(userVO.getId(), roleIds);
            userService.updateInstitutions(userVO.getId(), institutionIds);
        }
        return new ResultStatus(true, "");
    }

    @ResponseBody
    @RequestMapping(value = "password/reset", method = RequestMethod.POST)
    public ResultStatus resetPassword(String userIds) {
        String[] ids = userIds.split(",");
        String newPassword = "123456";
        for (String id : ids) {
            userService.changePassword(Long.valueOf(id), newPassword);
        }
        return new ResultStatus(true, "");
    }

    @ResponseBody
    @RequestMapping(value = "institutions/update")
    public ResultStatus updateInstitutions(Long userId, String institutionIds) {
        String[] ids;
        if (StringUtils.isEmpty(institutionIds)) {
            ids = null;
        } else {
            ids = institutionIds.split(",");
        }
        userService.updateInstitutions(userId, ids);
        return new ResultStatus(true, "");
    }

    @ResponseBody
    @RequestMapping(value = "institutions")
    public List<InstitutionEntity> listInstsByUserId(Long userId) {
        return institutionService.listInstsByUserId(userId);
    }

//    @ResponseBody
//    @RequestMapping(value = "grids")
//    public List<GridVO> listGridsByUserId(Long userId) {
//        UserEntity user = userService.findOne(userId);
//        String loginName = user.getLoginName();
//        List<GridEntity> grids = gridService.listGridsByLoginName(loginName);
//        List<GridVO> result = new ArrayList<>();
//        for (GridEntity grid : grids) {
//            GridVO gridVO = new GridVO(grid);
//            result.add(gridVO);
//        }
//        return result;
//    }

    @ResponseBody
    @RequestMapping(value = "roles/target")
    public List<RoleEntity> listTargetRoles(Long userId) {
        return userService.listTargetRoles(userId);
    }

    @ResponseBody
    @RequestMapping(value = "roles/source")
    public List<RoleEntity> listSourceRoles(Long userId) {
        return userService.listSourceRoles(userId);
    }

}
