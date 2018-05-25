/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.service;

import org.clover.zojirushi.util.SqlFilter;
import org.clover.zojirushi.vo.TreeData;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 描述 复杂组件公用工具类
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/5/23 14:22
 */
public interface TreeService {

    /**
     * 根据数据库表名称获取主键名称
     *
     * @param tableName
     * @return
     */
    List<String> getPrimaryKeyName(String tableName);

    /**
     * 根据id判断某记录是否存在
     *
     * @param entityId
     * @param tableName
     * @return
     */
    boolean isExisted(Long entityId, String tableName);

    /**
     * 获取该表所有的字段名
     *
     * @param tableName
     * @return
     */
    Set<String> getColumnNameByTableName(String tableName);

    /**
     * 根据map更新业务表数据
     *
     * @param entityId
     * @param variables
     * @param entityName
     */
    void updateData(Long entityId,
                    Map<String, Object> variables,
                    String entityName);

    /**
     * 根据map保存或更新数据
     *
     * @param entityId
     * @param variables
     * @param tableName
     * @param seqName
     * @return 主键id
     */
    Long saveOrUpdate(Long entityId,
                      Map<String, Object> variables,
                      String tableName,
                      String seqName);

    /**
     * 根据Map更新树形数据
     *
     * @param entityId
     * @param parentId
     * @param treeData
     * @param tableName
     * @param seqName
     * @return
     */
    Long saveOrUpdateTreeData(Long entityId,
                              Long parentId,
                              Map<String, Object> treeData,
                              String tableName,
                              String seqName);

    /**
     * 获取最大的排序值
     *
     * @return
     */
    Long getMaxManualSortNumber(String tableName, Long parentId);

    /**
     * 获取所有树形数据
     *
     * @return
     */
    List<TreeData> listTreeData(String tableName);

    /**
     * 获取所有树形数据
     *
     * @return
     */
    List<TreeData> listTreeData(String tableName, SqlFilter sqlFilter);

}
