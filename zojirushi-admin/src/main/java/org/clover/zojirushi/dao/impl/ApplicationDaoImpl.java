/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.dao.impl;

import org.clover.zojirushi.dao.custom.ApplicationDaoCustom;
import org.clover.zojirushi.entity.ApplicationEntity;
import org.clover.zojirushi.util.SqlFilter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

/**
 * 描述 拓展类实现
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/6/28 下午5:02
 */
@Repository
public class ApplicationDaoImpl extends BaseDaoImpl<ApplicationEntity> implements ApplicationDaoCustom {
    @Override
    public Page<ApplicationEntity> listAppsByPage(int page, int size, SqlFilter sqlFilter) {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from applications a ")
                .append(sqlFilter.getWhereSql())
                .append(" order by id desc");
        String sql = sb.toString();
        return super.queryForClass(sql, sqlFilter.getParams().toArray(), page, size);
    }
}
