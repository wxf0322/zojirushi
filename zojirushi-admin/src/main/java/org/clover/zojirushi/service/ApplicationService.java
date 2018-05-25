/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.service;

import java.util.List;

import org.clover.zojirushi.entity.ApplicationEntity;
import org.clover.zojirushi.util.SqlFilter;
import org.springframework.data.domain.Page;

/**
 * 描述 应用Service层
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/4/24 15:37
 */
public interface ApplicationService extends BaseService<ApplicationEntity, Long> {

    /**
     * 查询应用列表
     */
    Page<ApplicationEntity> listAppsByPage(int page, int size, SqlFilter sqlFilter);

    /**
     * 创建应用
     *
     * @param application
     * @return
     */
    ApplicationEntity createApplication(ApplicationEntity application);

    /**
     * 更新应用
     *
     * @param application
     * @return
     */
    ApplicationEntity updateApplication(ApplicationEntity application);

    /**
     * 根据ClientId查询单个
     *
     * @param clientId
     * @return
     */
    ApplicationEntity getAppByClientId(String clientId);

    /**
     * 根据ClientSecret查询单个
     *
     * @param clientSecret
     * @return
     */
    ApplicationEntity getAppByClientSecret(String clientSecret);

    /**
     * 根据ClientId与ClientSecret查询
     *
     * @param clientId
     * @param clientSecret
     * @return
     */
    ApplicationEntity getApplication(String clientId, String clientSecret);

    /**
     * 通过 Id 查找应用
     *
     * @param userId
     * @return
     */
    List<ApplicationEntity> listAppsByUserId(Long userId);

}
