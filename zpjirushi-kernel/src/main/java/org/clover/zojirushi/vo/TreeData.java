/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * 树形组件
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/5/22 11:27
 */
public class TreeData {
    /**
     * 序号
     */
    private Long id;
    /**
     * 父节点
     */
    private Long parentId;
    /**
     * 名称
     */
    private String label;
    /**
     * 树形节点上保存的数据
     */
    private Map<String, Object> data = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TreeData{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", label='" + label + '\'' +
                ", data=" + data +
                '}';
    }
}

