/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.dao.impl;

import javax.persistence.Query;

import org.clover.zojirushi.dao.custom.PrivilegeDaoCustom;
import org.clover.zojirushi.entity.PrivilegeEntity;
import org.clover.zojirushi.util.SqlFilter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

/**
 * 描述
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/6/28 下午6:10
 */
@Repository
public class PrivilegeDaoImpl extends BaseDaoImpl<PrivilegeEntity> implements PrivilegeDaoCustom {

    /**
     * 查找权限列表
     *
     * @param page
     * @param size
     * @param sqlFilter
     * @return
     */
    @Override
    public Page<PrivilegeEntity> listPrivsByPage(int page, int size, SqlFilter sqlFilter) {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from privileges p ")
                .append(sqlFilter.getWhereSql())
                .append(" order by p.id desc");
        String sql = sb.toString();
        return super.queryForClass(sql, sqlFilter.getParams().toArray(), page, size);
    }

    @Override
    public void updateOperations(Long privilegeId, String[] operationIds) {
        String sql ="delete from privilege_operation where priv_id =:privilegeId";
        Query query = manager.createNativeQuery(sql);
        query.setParameter("privilegeId", privilegeId);
        query.executeUpdate();
        if (operationIds != null) {
            for (String id : operationIds) {
                Long operationId = Long.valueOf(id);
                sql = "insert into privilege_operation values(:privilegeId,:operationId)";
                query = manager.createNativeQuery(sql);
                query.setParameter("privilegeId", privilegeId);
                query.setParameter("operationId", operationId);
                query.executeUpdate();
            }
        }
    }

    @Override
    public void updateRoles(Long privilegeId, String[] roleIds) {
        String sql = "delete from privilege_role where priv_id =:privilegeId";
        Query query = manager.createNativeQuery(sql);
        query.setParameter("privilegeId", privilegeId);
        query.executeUpdate();
        if (roleIds != null) {
            for (String id : roleIds) {
                Long roleId = Long.valueOf(id);
                sql = "insert into privilege_role values(:privilegeId,:roleId)";
                query = manager.createNativeQuery(sql);
                query.setParameter("roleId", roleId);
                query.setParameter("privilegeId", privilegeId);
                query.executeUpdate();
            }
        }
    }

}
