/*
 * Copyright (c) 2005, 2014, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * SQL过滤器，用于添加where条件和排序，过滤结果集
 * <p>
 * 添加规则使用addFilter方法
 * <p>
 * 举例：QUERY_t#id_S_EQ = 0 //最终连接出的SQL是 and t.id = ? id的值是0通过参数传递
 * <p>
 * 格式说明QUERY前缀就说明要添加过滤条件
 * <p>
 * t#id 就是t.id
 * <p>
 * S:String L:Long I:Integer D:Date ST:Short BD:BigDecimal FT:Float
 * <p>
 * EQ 是操作符
 * <p>
 * // EQ 相等 // NE 不等 // LT 小于 // GT 大于 // LE 小于等于 // GE 大于等于 // LK 模糊 // RLK 右模糊
 * // LLK 左模糊 TLK 树本级和下级 TCLK 树下级
 * // QUERY_t#id_EQNULL 表示 is null
 *
 * @author wangxiaofeng
 * @created 2017/6/30 12:00
 */
public class SqlFilter {

    /**
     * 为了获取request里面传过来的动态参数
     */
    private HttpServletRequest request;

    /**
     * 为了获取request里面传过来的动态参数
     */
    private List<Object> params = new ArrayList<>(); // 条件参数

    /**
     * 为了获取request里面传过来的动态参数
     */
    private StringBuilder sql = new StringBuilder();

    /**
     * 为了获取request里面传过来的动态参数
     */
    private String sort; // 排序字段

    /**
     * 为了获取request里面传过来的动态参数
     */
    private String order = "asc"; // asc/desc

    /**
     * 默认构造
     */
    public SqlFilter() {
    }

    /**
     * 带参构造
     *
     * @param request
     */
    public SqlFilter(HttpServletRequest request) {
        this.request = request;
        addFilter(request);
    }

    /**
     * 添加排序字段
     *
     * @param sort
     */
    public void addSort(String sort) {
        this.sort = sort;
    }

    /**
     * 添加排序方法，默认asc升序
     *
     * @param order
     */
    public void addOrder(String order) {
        this.order = order;
    }

    /**
     * 转换SQL操作符
     *
     * @param operator
     * @return
     */
    private String getSqlOperator(String operator) {
        if (StringUtils.equalsIgnoreCase(operator, "EQ")) {
            return " = ";
        }
        if (StringUtils.equalsIgnoreCase(operator, "NE")) {
            return " != ";
        }
        if (StringUtils.equalsIgnoreCase(operator, "LT")) {
            return " < ";
        }
        if (StringUtils.equalsIgnoreCase(operator, "GT")) {
            return " > ";
        }
        if (StringUtils.equalsIgnoreCase(operator, "LE")) {
            return " <= ";
        }
        if (StringUtils.equalsIgnoreCase(operator, "GE")) {
            return " >= ";
        }
        if (StringUtils.equalsIgnoreCase(operator, "LK") || StringUtils.equalsIgnoreCase(operator, "RLK")
                || StringUtils.equalsIgnoreCase(operator, "LLK")) {
            return " like ";
        }
        return "";
    }

    /**
     * 获得添加过滤字段后的SQL
     *
     * @return
     */
    public String getWhereSql() {
        return sql.toString();
    }

    /**
     * 获得添加过滤字段后加上排序字段的SQL
     *
     * @return
     */
    public String getWhereAndOrderSql() {
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            if (sort.indexOf(".") < 1) {
                sort = "t." + sort;
            }
            sql.append(" order by " + sort + " " + order + " "); // 添加排序信息
        } else {
            if (request != null) {
                String s = request.getParameter("sort");
                String o = request.getParameter("order");
                if (!StringUtils.isBlank(s)) {
                    sort = s;
                }
                if (!StringUtils.isBlank(o)) {
                    order = o;
                }
                if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
                    if (sort.indexOf(".") < 1) {
                        sort = "t." + sort;
                    }
                    sql.append(" order by " + sort + " " + order + " ");// 添加排序信息
                }
            }
        }
        return sql.toString();
    }

    /**
     * 获得过滤字段参数和值
     *
     * @return
     */
    public List<Object> getParams() {
        return params;
    }

    /**
     * 添加过滤
     *
     * @param request
     * @author Fandy Liu
     * @created 2014年10月5日 下午11:36:54
     */
    public void addFilter(HttpServletRequest request) {
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = request.getParameter(name);
            if (StringUtils.isNotBlank(value)) {
                addFilter(name, value.trim());
            }
        }
    }


    /**
     * 添加过滤
     * <p>
     * 举例，name传递：QUERY_t#id_S_EQ
     * <p>
     * 举例，value传递：0
     *
     * @param name
     * @param value
     */
    public void addFilter(String name, String value) {
        if (name != null && value != null) {
            if (name.startsWith("QUERY_")) {// 如果有需要过滤的字段
                String[] filterParams = StringUtils.split(name, "_");
                if (filterParams.length >= 4) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(filterParams[1]);
                    for (int i = 2; i < filterParams.length - 2; i++) {
                        sb.append("_").append(filterParams[i]);
                    }
                    String columnName = sb.toString().replaceAll("#", ".");// 要过滤的字段名称

                    String columnType = filterParams[filterParams.length - 2];// 字段类型
                    String operator = filterParams[filterParams.length - 1];// SQL操作符

                    if (sql.toString().indexOf("where 1=1") < 0) {
                        sql.append("  where 1=1 ");
                    }

                    // TLK 树本级和下级
                    if (StringUtils.equalsIgnoreCase(operator, "TLK")) {
                        sql.append(" and (" + columnName + " = ? or " + columnName + " like ?) ");
                        params.add(value);
                        params.add(value + ".%");
                    }

                    // TCLK 树下级
                    else if (StringUtils.equalsIgnoreCase(operator, "TCLK")) {
                        sql.append(" and " + columnName + " like ? ");
                        params.add(value + ".%");
                    } else if (StringUtils.equalsIgnoreCase(operator, "EQNULL")) {
                        sql.append(" and " + columnName + " is null ");
                    } else if (StringUtils.equalsIgnoreCase(operator, "IN") && StringUtils.isNotBlank(value)) {
                        sql.append(" and  " + columnName + " in (" + toSql(value) + ")  ");
                    } else {
                        sql.append(" and " + columnName + " " + getSqlOperator(operator) + "? ");// 拼HQL
                        params.add(getObjValue(columnType, operator, value));
                    }
                }
            }
        }
    }

    public void addOrFilter(String name, String value) {
        if (name != null && value != null) {
            if (name.startsWith("QUERY_")) {// 如果有需要过滤的字段
                String[] filterParams = StringUtils.split(name, "_");
                if (filterParams.length >= 4) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(filterParams[1]);
                    for (int i = 2; i < filterParams.length - 2; i++) {
                        sb.append("_").append(filterParams[i]);
                    }
                    String columnName = sb.toString().replaceAll("#", ".");// 要过滤的字段名称

                    String columnType = filterParams[filterParams.length - 2];// 字段类型
                    String operator = filterParams[filterParams.length - 1];// SQL操作符
                    if (sql.toString().indexOf("where") < 0) {
                        sql.append(" where 1=2 ");
                    }

                    // TLK 树本级和下级
                    if (StringUtils.equalsIgnoreCase(operator, "TLK")) {
                        sql.append(" or (" + columnName + " = ? or " + columnName + " like ?) ");
                        params.add(value);
                        params.add(value + ".%");
                    }
                    // TCLK 树下级
                    else if (StringUtils.equalsIgnoreCase(operator, "TCLK")) {
                        sql.append(" or " + columnName + " like ? ");
                        params.add(value + ".%");
                    } else if (StringUtils.equalsIgnoreCase(operator, "EQNULL")) {
                        sql.append(" or " + columnName + " is null ");
                    } else if (StringUtils.equalsIgnoreCase(operator, "IN") && StringUtils.isNotBlank(value)) {
                        sql.append(" or  " + columnName + " in (" + toSql(value) + ")  ");
                    } else {
                        sql.append(" or " + columnName + " " + getSqlOperator(operator) + "? ");// 拼HQL
                        params.add(getObjValue(columnType, operator, value));
                    }
                }
            }
        }
    }


    /**
     * @param ids
     * @return
     * @discription 把11, 22, 33...转成'11','22','33'...
     */
    private String toSql(String ids) {
        if (null == ids || ids.isEmpty()) {
            return "";
        }
        String[] idsArr = ids.split(",");
        StringBuilder sqlSb = new StringBuilder();
        int length = idsArr.length;
        for (int i = 0, size = length - 1; i < size; i++) {
            sqlSb.append(" '").append(idsArr[i]).append("', ");
        }
        if (length != 0) {
            sqlSb.append(" '").append(idsArr[length - 1]).append("' ");
        }
        return sqlSb.toString();
    }

    /**
     * 将String值转换成Object，用于拼写HQL，替换操作符和值
     * <p>
     * S:String L:Long I:Integer D:Date ST:Short BD:BigDecimal FT:Float
     *
     * @param columnType
     * @param operator
     * @param value
     * @return
     */
    private Object getObjValue(String columnType, String operator, String value) {
        if (StringUtils.equalsIgnoreCase(columnType, "S")) {
            if (StringUtils.equalsIgnoreCase(operator, "LK")) {
                value = "%" + value + "%";
            } else if (StringUtils.equalsIgnoreCase(operator, "RLK")) {
                value = value + "%";
            } else if (StringUtils.equalsIgnoreCase(operator, "LLK")) {
                value = "%" + value;
            }
            return value;
        }
        if (StringUtils.equalsIgnoreCase(columnType, "L")) {
            return Long.parseLong(value);
        }
        if (StringUtils.equalsIgnoreCase(columnType, "I")) {
            return Integer.parseInt(value);
        }
        if (StringUtils.equalsIgnoreCase(columnType, "D")) {
            try {
                return new Timestamp(DateUtils.parseDate(value,
                        new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd", "yyyy/MM/dd"})
                        .getTime());
            } catch (ParseException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        if (StringUtils.equalsIgnoreCase(columnType, "ST")) {
            return Short.parseShort(value);
        }
        if (StringUtils.equalsIgnoreCase(columnType, "BD")) {
            return new BigDecimal(value);
        }
        if (StringUtils.equalsIgnoreCase(columnType, "FT")) {
            return Float.parseFloat(value);
        }
        return null;
    }
}
