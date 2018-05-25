/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.dao.impl;

import java.util.List;

import org.clover.zojirushi.dao.custom.InstitutionDaoCustom;
import org.clover.zojirushi.entity.InstitutionEntity;
import org.springframework.stereotype.Repository;

/**
 * 描述
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/6/28 下午5:27
 */
@Repository
public class InstitutionDaoImpl extends BaseDaoImpl<InstitutionEntity> implements InstitutionDaoCustom {

    @Override
    public boolean canBeDeleted(Long id) {
        String sql = "select i.id from institutions i where i.parent_id = ?";
        List<Object> children = super.queryForObject(sql, new Object[]{id});
        if (children != null && children.size() > 0) return false;
        sql = "select ui.user_id from user_institution ui where ui.institution_id = ?";
        List<Object> users = super.queryForObject(sql, new Object[]{id});
        if (users != null && users.size() > 0)
            return false;
        else
            return true;
    }

}
