/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.clover.zojirushi.dao.InstitutionDao;
import org.clover.zojirushi.dao.custom.UserDaoCustom;
import org.clover.zojirushi.entity.InstitutionEntity;
import org.clover.zojirushi.entity.UserEntity;
import org.clover.zojirushi.model.vo.UserVO;
import org.clover.zojirushi.util.JpaUtil;
import org.clover.zojirushi.util.SqlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

/**
 * 描述
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/6/28 下午6:33
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<UserEntity> implements UserDaoCustom {

    /**
     * @see InstitutionDao
     */
    @Autowired
    private InstitutionDao institutionDao;

//    /**
//     * @see GridDao
//     */
//    @Autowired
//    private GridDao gridDao;

    /**
     * 根据分页查询用户Model
     *
     * @param page          当前页码
     * @param size          页面数据量
     * @param institutionId
     * @param sqlFilter
     * @return
     */
    @Override
    public Page<UserVO> listUsersByPage(int page, int size, Long institutionId, SqlFilter sqlFilter) {
        List params = new ArrayList();
        StringBuilder sb = new StringBuilder();
        if (institutionId == null) {
            sb.append("select u.id, u.login_name, u.name, u.enabled, s.mobile from users u ")
                    .append("left join staffs s on u.staff_id = s.id ")
                    .append(sqlFilter.getWhereSql())
                    .append(" order by u.id desc");
        } else {
            sb.append("select u.id, u.login_name, u.name, u.enabled, s.mobile from ( ")
                    .append("select distinct u.id, u.login_name, u.name, u.staff_id, ")
                    .append("u.enabled, ui.institution_id ")
                    .append("from users u left join user_institution ui on u.id = ui.user_id ")
                    .append("where ui.institution_id = ?) u ")
                    .append("left join staffs s ")
                    .append("on u.staff_id = s.id ")
                    .append(sqlFilter.getWhereSql())
                    .append( " order by u.id  desc");
            params.add(institutionId);
        }
        params.addAll(sqlFilter.getParams());
        String sql = sb.toString();
        Page<Map<String, Object>> pageBean = queryForMap(sql, params.toArray(), page, size);
        List<UserVO> results = new ArrayList<>();

        // 遍历map
        for (Map<String, Object> var : pageBean.getContent()) {
            UserVO userVO = new UserVO();

            // 获得用户id
            Long userId = ((BigDecimal) var.get("ID")).longValue();

            // 获得登入名
            String loginName = (String) var.get("LOGIN_NAME");
            // 获得组织机构列表
            List<InstitutionEntity> institutions = institutionDao.listInstsByUserId(userId);

            // 获得组织机构名
            List<String> instNames = new ArrayList<>();
            if (institutions != null) {
                Long minLevel = Long.MAX_VALUE;
                for (InstitutionEntity institution : institutions) {
                    minLevel = Math.min(minLevel, institution.getTreeLevel());
                }
                for (InstitutionEntity institution : institutions) {
                    if (institution.getTreeLevel().equals(minLevel)) {
                        instNames.add(institution.getName());
                    }
                }
            }

            // 获得网格列表
//            List<GridEntity> grids = gridDao.listGridsByLoginName(loginName);
//            // 获得网格名称
//            List<String> gridNames = new ArrayList<>();
//            if (grids != null) {
//                Long minLevel = Long.MAX_VALUE;
//                for (GridEntity grid : grids) {
//                    minLevel = Math.min(minLevel, grid.getLvl());
//                }
//                for (GridEntity grid : grids) {
//                    if (grid.getLvl().equals(minLevel)) {
//                        gridNames.add(grid.getName());
//                    }
//                }
//            }
            // 设置变量
            userVO.setId(userId);
            userVO.setLoginName(loginName);
            userVO.setName((String) var.get("NAME"));
            userVO.setMobile((String) var.get("MOBILE"));
            userVO.setEnabled(((BigDecimal) var.get("ENABLED")).longValue());
            userVO.setInstitutionNames(instNames);
            //userVO.setGridNames(gridNames);
            results.add(userVO);
        }
        return new PageImpl<>(results, new PageRequest(page, size), pageBean.getTotalElements());
    }

    @Override
    public List<UserEntity> listUsersByLoginNames(String[] loginNames) {
        String queryParams = JpaUtil.getQuestionMarks(loginNames);
        StringBuilder sb = new StringBuilder();
        sb.append("select * from users u where u.login_name in (")
                .append(queryParams).append(")");
        String sql = sb.toString();
        return super.queryForClass(sql, loginNames);
    }

    @Override
    public void updateRoles(Long userId, String[] roleIds) {
        String sql = "delete from user_role where user_id =:userId";
        Query query = manager.createNativeQuery(sql);
        query.setParameter("userId", userId);
        query.executeUpdate();
        if (roleIds != null) {
            for (String id : roleIds) {
                Long roleId = Long.valueOf(id);
                sql = "insert into user_role values(:roleId,:userId)";
                query = manager.createNativeQuery(sql);
                query.setParameter("roleId", roleId);
                query.setParameter("userId", userId);
                query.executeUpdate();
            }
        }

    }

    @Override
    public void updateInstitutions(Long userId, String[] institutionIds) {
        String sql = "delete from user_institution ui where user_id=:userId";
        Query query = manager.createNativeQuery(sql);
        query.setParameter("userId", userId);
        query.executeUpdate();
        if (institutionIds != null) {
            for (String id : institutionIds) {
                Long institutionId = Long.valueOf(id);
                sql = "insert into user_institution values(:userId, :institutionId)";
                query = manager.createNativeQuery(sql);
                query.setParameter("userId", userId);
                query.setParameter("institutionId", institutionId);
                query.executeUpdate();
            }
        }
    }

    @Override
    public List<UserEntity> listUsersByInstNames(String[] instNames) {
        String queryParams = JpaUtil.getQuestionMarks(instNames);
        StringBuilder sb = new StringBuilder();
        sb.append("select * from users u where u.id in ( \n")
                .append("select distinct ui.user_id from user_institution ui where ui.institution_id in (\n")
                .append("select i.id from institutions i where i.name in (")
                .append(queryParams).append(") and i.enabled = 1 ))");
        String sql = sb.toString();
        return super.queryForClass(UserEntity.class, sql, instNames);
    }

    @Override
    public List<UserEntity> listUsersByRoleNames(String[] roleNames) {
        String queryParams = JpaUtil.getQuestionMarks(roleNames);
        StringBuilder sb = new StringBuilder();
        sb.append("select * from users u where u.id in")
                .append(" (select ur.user_id from user_role ur")
                .append(" where ur.role_id in")
                .append(" (select r.id from roles r where r.name in (")
                .append(queryParams).append(") and r.enabled = 1 ))")
                .append(" and u.enabled = 1");
        String sql = sb.toString();
        return super.queryForClass(UserEntity.class, sql, roleNames);
    }

}
