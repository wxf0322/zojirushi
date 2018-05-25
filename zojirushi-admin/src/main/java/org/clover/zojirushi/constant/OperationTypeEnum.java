/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.constant;

/**
 * 描述
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/6/29 下午2:55
 */
public enum OperationTypeEnum {
    /**
     * 1:菜单 2:按钮 3:系统
     */
    MENU(1L), BUTTON(2L), SYSTEM(3L);

    /**
     * 常量值
     */
    private long value;

    OperationTypeEnum(long value) {
        this.value = value;
    }

    public long getValue() {
        return this.value;
    }
}
