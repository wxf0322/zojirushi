/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.clover.zojirushi.service.TreeService;
import org.clover.zojirushi.util.MapUtil;
import org.clover.zojirushi.util.SqlFilter;
import org.clover.zojirushi.vo.TreeData;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.*;

/**
 * 描述 树形组件基础Service
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/5/23 14:23
 */
@Transactional
@Service
public class TreeServiceImpl implements TreeService {

    /**
     * 注入实体管理器
     */
    @PersistenceContext
    private EntityManager manager;

    /**
     * 描述： 根据语句去获取可执行的query对象
     *
     * @param sqlString the sql string
     * @param values    the values
     * @return the native query
     */
    private Query createNativeQuery(String sqlString, Object[] values) {
        Query query = manager.createNativeQuery(sqlString);
        if (values != null && values.length > 0) {
            for (int i = 0, j = 1; i < values.length; i++, j++) {
                if (values[i] instanceof Date) {
                    query.setParameter(j, (Date) values[i], TemporalType.DATE);
                } else {
                    query.setParameter(j, values[i]);
                }
            }
        }
        return query;
    }

    /**
     * 返回单行数据
     *
     * @param sqlString
     * @param values
     * @return
     */
    private Map<String, Object> queryForMap(String sqlString, Object[] values) {
        Query query = this.createNativeQuery(sqlString, values);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = query.getResultList();
        if (list == null || list.size() == 0) {
            return new HashMap<>();
        } else {
            return (Map<String, Object>) query.getResultList().get(0);
        }
    }

    /**
     * 获取主键值
     *
     * @param tableName
     * @return
     */
    @Override
    public List<String> getPrimaryKeyName(String tableName) {
        StringBuilder sql = new StringBuilder("select cu.column_name from user_cons_columns cu, ")
                .append("user_constraints au where cu.constraint_name = au.constraint_name ")
                .append("and au.constraint_type = 'P' and au.table_name=?");
        Query query = manager.createNativeQuery(sql.toString());
        query.setParameter(1, tableName.toUpperCase());
        List<String> primaryKeyNames = query.getResultList();
        return primaryKeyNames;
    }

    @Override
    public boolean isExisted(Long entityId, String tableName) {
        String primaryKeyName = this.getPrimaryKeyName(tableName).get(0);
        StringBuilder sql = new StringBuilder("select count(*) from ");
        sql.append(tableName).append(" where ").append(primaryKeyName).append("=? ");
        if (entityId == null) {
            entityId = 0L;
        }
        Query query = manager.createNativeQuery(sql.toString());
        query.setParameter(1, entityId);
        long count = Long.valueOf(query.getSingleResult().toString());
        return count != 0;
    }

    /**
     * 根据数据库表名称获取列信息
     *
     * @param tableName
     * @return
     */
    @Override
    public Set<String> getColumnNameByTableName(String tableName) {
        Set<String> set = new HashSet<>();
        StringBuilder sql = new StringBuilder("select t.column_name from ")
                .append("user_tab_columns t where t.table_name=?");
        Query query = manager.createNativeQuery(sql.toString());
        query.setParameter(1, tableName.toUpperCase());
        List<String> list = query.getResultList();
        set.addAll(list);
        return set;
    }

    /**
     * 更新业务表数据
     *
     * @param entityId
     * @param variables
     * @param entityName
     */
    @Override
    public void updateData(Long entityId, Map<String, Object> variables, String entityName) {
        if (entityId != null) {
            // 获取业务表名称
            String tableName = entityName.toUpperCase();
            // 获取私有主键名称
            String primaryKeyName = this.getPrimaryKeyName(tableName).get(0);
            StringBuilder sql = new StringBuilder("update ")
                    .append(tableName).append(" set ");
            // 获取业务表的列名称
            Set<String> columns = this.getColumnNameByTableName(tableName);
            // 设置更新目标列
            List<String> targetCols = new ArrayList<>();
            // 定义更新的目标值
            List<Object> targetVals = new ArrayList<>();
            Iterator it = variables.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String fieldName = (String) entry.getKey();
                Object val = entry.getValue();
                if (columns.contains(fieldName.toUpperCase())
                        && !fieldName.toUpperCase().equals(primaryKeyName)) {
                    if (!targetCols.contains(fieldName.toUpperCase())) {
                        targetCols.add(fieldName.toUpperCase());
                        targetVals.add(val);
                    }
                }
            }
            for (String targetCol : targetCols) {
                sql.append(targetCol).append("=?,");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(" where ").append(primaryKeyName).append("=?");
            targetVals.add(entityId);
            if (targetCols.size() > 0) {
                Query query = this.createNativeQuery(sql.toString(), targetVals.toArray());
                query.executeUpdate();
            }
        }
    }

    @Override
    public Long saveOrUpdate(Long entityId,
                             Map<String, Object> variables,
                             String tableName,
                             String seqName) {
        if (this.isExisted(entityId, tableName)) {
            this.updateData(entityId, variables, tableName);
            return entityId;
        } else {
            Set<String> columns = this.getColumnNameByTableName(tableName);
            // 获取主键名称
            String primaryKeyName = getPrimaryKeyName(tableName).get(0);
            Long nextSeq = -1L;
            if (StringUtils.isNotEmpty(seqName)) {
                String getNextSeq = "select " + seqName + ".nextval from dual";
                Query query = manager.createNativeQuery(getNextSeq);
                nextSeq = Long.valueOf(query.getSingleResult().toString());
            }
            Map<String, Object> insertMap = new HashMap<>();
            insertMap.put(primaryKeyName, nextSeq);
            Iterator it = variables.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String fieldName = (String) entry.getKey();
                Object val = entry.getValue();
                if (columns.contains(fieldName.toUpperCase()) && val != null) {
                    if (!insertMap.containsKey(fieldName.toUpperCase())) {
                        insertMap.put(fieldName.toUpperCase(), val);
                    }
                }
            }
            StringBuilder sql = new StringBuilder("insert into ")
                    .append(tableName.toUpperCase())
                    .append("(");
            // 定义目标columns
            List<String> targetCols = new ArrayList<>();
            // 定义目标值
            List<Object> targetVals = new ArrayList<>();
            Iterator insertIter = insertMap.entrySet().iterator();
            while (insertIter.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry<String, Object>) insertIter.next();
                String fieldName = entry.getKey();
                Object val = entry.getValue();
                if (val != null) {
                    targetCols.add(fieldName);
                    targetVals.add(val);
                }
            }
            for (String targetCol : targetCols) {
                sql.append(targetCol).append(",");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(") values (");
            for (String targetCol : targetCols) {
                sql.append("?").append(",");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(")");

            Query query = this.createNativeQuery(sql.toString(), targetVals.toArray());
            int result = query.executeUpdate();
            if (result == 1) {
                return (entityId != null) ? entityId : nextSeq;
            } else {
                return -1L;
            }
        }
    }

    @Override
    public Long saveOrUpdateTreeData(Long entityId,
                                     Long parentId,
                                     Map<String, Object> treeData,
                                     String tableName,
                                     String seqName) {
        String path;
        int level = 0;
        //获取私有主键名称
        String primaryKeyName = this.getPrimaryKeyName(tableName).get(0);
        if (parentId != null && parentId != 0L) {
            StringBuilder sql = new StringBuilder("select * from ")
                    .append(tableName).append(" where ")
                    .append(primaryKeyName).append(" =?");
            Map<String, Object> parentData = this.queryForMap(sql.toString(), new Object[]{parentId});
            path = (String) parentData.get("PATH");
            level = Integer.parseInt(parentData.get("TREE_LEVEL").toString());
        } else {
            path = "0";
            parentId = 0L;
        }
        if (level < 1) level = 1;
        treeData.put("tree_level", level + 1);
        treeData.put("parent_id", parentId);
        Long maxSn = getMaxManualSortNumber(tableName, parentId);
        if (maxSn < 0 || maxSn == null) maxSn = 0L;

        // 更新操作
        if (entityId == null) { // 新增
            treeData.put("manual_sn", maxSn + 1);
            entityId = saveOrUpdate(null, treeData, tableName, seqName);
        } else { // 更新
            entityId = saveOrUpdate(entityId, treeData, tableName, null);
        }
        // 再次更新 path
        if (entityId != -1L) {
            path = path + "," + entityId;
            treeData.put("PATH", path);
            saveOrUpdate(entityId, treeData, tableName, null);
        }
        return entityId;
    }

    @Override
    public Long getMaxManualSortNumber(String tableName, Long parentId) {
        StringBuilder sb = new StringBuilder();
        sb.append("select max(manual_sn) from ").append(tableName).append(" where parent_id = ?");
        String sql = sb.toString();
        Query query = manager.createNativeQuery(sql);
        query.setParameter(1, parentId);
        if (query.getSingleResult() == null) {
            return 0L;
        } else {
            return Long.valueOf(query.getSingleResult().toString());
        }
    }

    @Override
    public List<TreeData> listTreeData(String tableName) {
        SqlFilter sqlFilter = new SqlFilter();
        return this.listTreeData(tableName, sqlFilter);
    }

    @Override
    public List<TreeData> listTreeData(String tableName, SqlFilter sqlFilter) {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from ").append(tableName).append(sqlFilter.getWhereSql());
        Query query = this.createNativeQuery(sb.toString(), sqlFilter.getParams().toArray());
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String,Object>> rows = query.getResultList();

        List<TreeData> trees = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            TreeData treeData = new TreeData();
            treeData.setId(((BigDecimal) row.get("ID")).longValue());
            treeData.setParentId(((BigDecimal) row.get("PARENT_ID")).longValue());
            treeData.setLabel((String) row.get("LABEL"));
            // 设置树形节点上的数据
            Map<String, Object> data = MapUtil.toCamelCaseMap(row);
            treeData.setData(data);
            trees.add(treeData);
        }
        return trees;
    }

}
