/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.service.impl;

import org.clover.zojirushi.dao.StaffDao;
import org.clover.zojirushi.entity.StaffEntity;
import org.clover.zojirushi.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述员工相关Service
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/6/29 下午1:05
 */
@Service
public class StaffServiceImpl extends BaseServiceImpl<StaffEntity, Long>
        implements StaffService {

    /**
     * 该类被上层Controller类调用，勿删
     * @see StaffDao
     */
    @Autowired
    private StaffDao staffDao;

}
