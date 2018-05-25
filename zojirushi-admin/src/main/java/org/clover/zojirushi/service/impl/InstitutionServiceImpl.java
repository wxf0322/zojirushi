/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.clover.zojirushi.dao.InstitutionDao;
import org.clover.zojirushi.entity.InstitutionEntity;
import org.clover.zojirushi.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/5/8 15:16
 */
@Transactional
@Service
public class InstitutionServiceImpl extends BaseServiceImpl<InstitutionEntity, Long>
        implements InstitutionService {

    /**
     * @see InstitutionDao
     */
    @Autowired
    private InstitutionDao institutionDao;

    /**
     * 根据登入名查询组织机构
     *
     * @param loginName
     * @return
     */
    @Override
    public List<InstitutionEntity> listInstsByLoginName(String loginName) {
        return institutionDao.listInstsByLoginName(loginName);
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public List<InstitutionEntity> listInstsByUserId(Long userId) {
        if(userId==null){
            return new ArrayList<>();
        }
        return institutionDao.listInstsByUserId(userId);
    }

    /**
     * 查询子组织机构
     *
     * @param instName
     * @return
     */
    @Override
    public List<InstitutionEntity> listSubInstsByInstName(String instName) {
        return institutionDao.listSubInstsByInstName(instName);
    }


    /**
     * 根据编码查找组织机构
     *
     * @param instName
     * @return
     */
    @Override
    public InstitutionEntity getInstByInstName(String instName) {
        return institutionDao.findFirstByName(instName);
    }

    /**
     * 检查是否能被删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean canBeDeleted(Long id) {
        return institutionDao.canBeDeleted(id);
    }

}
