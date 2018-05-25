/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.dao;

import java.util.List;

import org.clover.zojirushi.dao.custom.ApplicationDaoCustom;
import org.clover.zojirushi.entity.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 描述 应用Dao层
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/4/24 15:33
 */
@Repository
public interface ApplicationDao extends JpaRepository<ApplicationEntity, Long>, ApplicationDaoCustom {

    /**
     * 根据ClientId查询应用
     *
     * @param clientId
     * @return
     */
    ApplicationEntity findFirstByClientId(String clientId);

    /**
     * 根据ClientSecret查询应用
     *
     * @param clientSecret
     * @return
     */
    ApplicationEntity findFirstByClientSecret(String clientSecret);

    /**
     * 根据ClientId与ClientSecret查询应用
     * @param clientId
     * @param clientSecret
     * @return
     */
    ApplicationEntity findFirstByClientIdAndClientSecret(String clientId, String clientSecret);

    @Query(value = "select * from applications a where a.id in ( " +
            "select distinct o.application_id from operations o " +
            "where o.id in (select po.oper_id " +
            "from privilege_operation po " +
            "where po.priv_id in " +
            "(select p.id from privileges p " +
            "where p.id in (select pr.priv_id " +
            "from privilege_role pr, roles r " +
            "where pr.role_id in " +
            "(select ur.role_id from user_role ur where ur.user_id = ?1) " +
            "and pr.role_id = r.id and r.enabled = 1) " +
            " and p.enabled = 1)) and o.enabled = 1 and o.application_id is not null) ", nativeQuery = true)
    List<ApplicationEntity> listAppsByUserId(Long userId);

}
