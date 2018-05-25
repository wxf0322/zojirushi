/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.clover.zojirushi.dao.custom.RoleDaoCustom;
import org.clover.zojirushi.entity.RoleEntity;
import org.clover.zojirushi.util.SqlFilter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

/**
 * 描述
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/6/28 下午6:32
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl<RoleEntity> implements RoleDaoCustom {

    @Override
    public void updatePrivileges(Long roleId, String[] privilegeIds) {
        String sql = "delete from privilege_role where role_id =:roleId";
        Query query = manager.createNativeQuery(sql);
        query.setParameter("roleId", roleId);
        query.executeUpdate();
        if (privilegeIds != null) {
            for (String id : privilegeIds) {
                Long privilegeId = Long.valueOf(id);
                sql = "insert into privilege_role values(:privilegeId,:roleId)";
                query = manager.createNativeQuery(sql);
                query.setParameter("roleId", roleId);
                query.setParameter("privilegeId", privilegeId);
                query.executeUpdate();
            }
        }
    }

    @Override
    public Page<RoleEntity> listRolesByPage(int page, int size, SqlFilter sqlFilter) {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from roles r  ").append(sqlFilter.getWhereSql())
                .append(" order by r.id  desc");
        String sql = sb.toString();
        return queryForClass(sql, sqlFilter.getParams().toArray(), page, size);
    }

    @Override
    public List<Map<String, Object>> listTargetUsers(Long roleId, SqlFilter sqlFilter) {
        if (roleId == null) {
            return new ArrayList<>();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("select id, login_name, name from users where id in(")
                    .append(" select user_id from user_role t")
                    .append(" where role_id=?) and enabled=1");
            String sql = sb.toString();
            return super.queryForMap(sql, new Object[]{roleId});
        }
    }

    @Override
    public List<Map<String, Object>> listSourceUsers(Long roleId, Long institutionId, String key) {
        StringBuilder sb = new StringBuilder();
        List<Object> params = new ArrayList<>();
        String searchWord = "%" + key + "%";
        String sql;
        if (roleId == null) {
            // 新增
            if (institutionId == null) {
                sb.append("select * from users u where u.name like ?");
            } else {
                sb.append("select * from (select distinct u.id, u.login_name, u.name ")
                        .append(" from users u where u.id in ")
                        .append(" (select ui.user_id from user_institution ui where ui.institution_id = ?)) u ")
                        .append(" where u.name like ?");
                params.add(institutionId);
            }
            sql = sb.toString();
        } else {
            // 修改
            if (institutionId == null) {
                sb.append("select * from users u where u.id not in ( ")
                        .append("select user_id from user_role r where r.role_id = ? ")
                        .append(") and u.name like ?");
                params.add(roleId);
            } else {
                sb.append("select * from users u where u.id in ( ")
                        .append("select ui.user_id from user_institution ui where ui.user_id not in ")
                        .append("(select user_id from user_role r where r.role_id = ?) ")
                        .append("and ui.institution_id = ? ) and u.name like ?");
                params.add(roleId);
                params.add(institutionId);
            }
            sql = sb.toString();
        }
        params.add(searchWord);
        return super.queryForMap(sql, params.toArray());
    }

    @Override
    public void updateUsers(Long roleId, String[] userIds) {
        String sql = "delete from user_role where role_id =:roleId";
        Query query = manager.createNativeQuery(sql);
        query.setParameter("roleId", roleId);
        query.executeUpdate();
        if (userIds != null) {
            for (String id : userIds) {
                Long userId = Long.valueOf(id);
                sql = "insert into user_role values(:roleId,:userId)";
                query = manager.createNativeQuery(sql);
                query.setParameter("roleId", roleId);
                query.setParameter("userId", userId);
                query.executeUpdate();
            }
        }
    }

}
