/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.dao;

import org.clover.zojirushi.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 描述 员工管理Dao
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/4/26 9:59
 */
@Repository
public interface StaffDao extends JpaRepository<StaffEntity, Long> {

}
