/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.*;
import java.util.Map.Entry;

/**
 * <P><B>描述:</B> SQL工具类 </P>
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017 /03/01 10:45:52
 */
public class SqlUtil {
    /**
     * The constant LOG.
     *
     * @see Logger
     */
    private static final Logger LOG = LogManager.getLogger(SqlUtil.class);

    /**
     * 根据SQL查询语句获取映射表对象（MODEL 或VMODEL)字段数组
     *
     * @param <M>    the type parameter
     * @param sql    SQL查询语句
     * @param mClazz MODEL 或VMODEL
     * @return 映射表对象 （MODEL 或VMODEL)字段数组
     * @author Wingene Lin
     */
    public static <M> Object[] getParamArray(String sql, Class<M> mClazz) {
        // SQL 语句转大写
        sql = sql.toUpperCase();
        // 取SELECT 和 FROM 之间的语句进行分析
        int indexBegin = sql.indexOf("SELECT ") + 6;
        int indexEnd = sql.indexOf(" FROM ");
        sql = sql.substring(indexBegin, indexEnd);
        // 删除括弧内的字符，目的是处理函数
        int indexBracketBegin = sql.indexOf("(");
        int indexBracketEnd = sql.indexOf(")");
        while (indexBracketBegin > 0) {
            // 处理函数嵌套的情况
            String sqlInner = sql.substring(indexBracketBegin + 1, indexBracketEnd);
            int repeatTimes = sqlInner.split("\\(").length;
            if (repeatTimes > 1) {
                for (int i = 1; i < repeatTimes; i++) {
                    indexBracketEnd = sql.indexOf(")", indexBracketEnd + 1);
                }
            }
            sql = sql.substring(0, indexBracketBegin) + sql.substring(indexBracketEnd + 1);
            indexBracketBegin = sql.indexOf("(");
            indexBracketEnd = sql.indexOf(")");
        }
        // 以逗号为分割转换成字段数组后进行分析
        String[] sqlSubStringArray = sql.split(",");
        List<String> resultList = new ArrayList<String>();
        for (String sqlSubStringElem : sqlSubStringArray) {
            //去头尾空格
            sqlSubStringElem = sqlSubStringElem.trim();
            //处理别名：如存在空格，取空格后面的字符串
            int indexSpaceBegin = sqlSubStringElem.lastIndexOf(" ");
            if (indexSpaceBegin > 0)
                sqlSubStringElem = sqlSubStringElem.substring(indexSpaceBegin + 1);
            //处理.：如存在. ，取.后面的字符串
            int indexDotBegin = sqlSubStringElem.lastIndexOf(".");
            if (indexDotBegin > 0)
                sqlSubStringElem = sqlSubStringElem.substring(indexDotBegin + 1);
            //处理*: 取映射表对象的所有属性,并转换成大写
            if (sqlSubStringElem.equals("*")) {
                Field[] fields = mClazz.getDeclaredFields();
                for (Field field : fields) {
                    String fieldName = field.getName();
                    if (fieldName.equals("serialVersionUID"))
                        continue;
                    else
                        resultList.add(field.getName().toUpperCase());
                }
            } else {
                resultList.add(sqlSubStringElem.replace("_", ""));
            }
        }
        return resultList.toArray();
    }

    /**
     * 功能描述：根据传入的参数回返回SQL条件语句。<br>
     * 注意事项： map.get("value")取到的对象应转为Object[]再处理.如果无数据刚返回null;
     *
     * @param queryMap key为“操作符-表名-列名”或“操作符-列名”，value为查询参数。
     * @param oper     操作符<and,or>
     * @return map 用于存放生成的SQL条件语句和参数值。 <br>         map.get("sql")可以取到SQL条件语句。<br>
     * map.get("value")可以取得参数值数组。<br>
     * @author Wingene Lin
     * @created 2017 /03/01 10:45:52
     */
    private static Map<String, Object> getClause(Map<String, Object> queryMap, String oper) {
        return getClause(queryMap, oper, 0);
    }

    /**
     * 功能描述：根据传入的参数回返回SQL条件语句。<br>
     * 注意事项： map.get("value")取到的对象应转为Object[]再处理.如果无数据刚返回null;
     * Revision Trail: (Date/Author/Description)
     * 2016-7-19 Church Lin CREATE
     *
     * @param queryMap the query map
     * @param oper     the oper
     * @param mark     从此位开始计数
     * @return clause
     * @author Church Lin
     * @created 2017 /03/01 10:45:52
     */
    private static Map<String, Object> getClause(Map<String, Object> queryMap, String oper, int mark) {
        final String BLANK = " ";
        final String MARK = "?";
        final int NOT_TABLE_LETNTH = 2;
        final int HAVE_TABLE_LETNTH = 3;
        StringBuilder sb = new StringBuilder();
        Map<String, Object> resultMap = new HashMap<>();
        ArrayList<Object> valueList = new ArrayList<>();
        boolean flag = false;
        // 对resultMap中的数据进行处理。
        Set<Entry<String, Object>> querySet = queryMap.entrySet();
        int markNum = mark;
        for (Entry<String, Object> entry : querySet) {
            String keyString = entry.getKey();
            String[] operAndColumn = null;
            Operator op;
            try {
                operAndColumn = keyString.split("\\^");
                op = Operator.valueOf(operAndColumn[0]);
                operAndColumn[0] = getOperator(operAndColumn[0]);
            } catch (Exception e) {
                LOG.info("转入的Key不合法！Key为：" + keyString);// log
                continue;
            }
            if (operAndColumn.length != NOT_TABLE_LETNTH && operAndColumn.length != HAVE_TABLE_LETNTH) {
                LOG.info("Key解析出的长度不合法！Key为：" + keyString);// log
                continue;
            }
            if (null == entry.getValue() || "".equals(entry.getValue())) {
                continue;
                // log.info("keyString 的值为空：" + entry.getValue());// log
            }
            if (valueList.size() > 0 || flag) {
                sb.append(oper).append(BLANK);
            }
            markNum++;
            if (operAndColumn.length == NOT_TABLE_LETNTH) {
                //sb.append(operAndColumn[1]).append(BLANK).append(operAndColumn[0]).append(BLANK)
                //        .append(MARK + markNum).append(BLANK);
                sb.append(operAndColumn[1]).append(BLANK);
            } else {
                //sb.append(operAndColumn[2]).append(".").append(operAndColumn[1]).append(BLANK)
                //        .append(operAndColumn[0]).append(BLANK).append(MARK + markNum).append(BLANK);
                sb.append(operAndColumn[2]).append(".").append(operAndColumn[1]).append(BLANK);
            }

            if (Operator.IN.equals(op) || Operator.NOTIN.equals(op)) {
                sb.append(BLANK).append(operAndColumn[0]).append(BLANK).append("(");

                String[] values = entry.getValue().toString().split(",");

                for (int i = 0; i < values.length; i++) {
                    if (i != 0) sb.append(",");
                    sb.append(BLANK).append(MARK).append(markNum);
                    if (i == values.length - 1)
                        sb.append(")").append(BLANK);
                    else
                        markNum++;
                    valueList.add(values[i]);
                }
            } else if (Operator.BETWEEN.equals(op)) {
                operAndColumn[0] = operAndColumn[0].replace("[StartValue]", MARK + markNum);
                markNum++;
                operAndColumn[0] = operAndColumn[0].replace("[EndValue]", MARK + markNum);
                sb.append(BLANK).append(operAndColumn[0]).append(BLANK);
                String[] values = entry.getValue().toString().split(",");
                if (values.length < 2) {
                    valueList.add(values[0]);
                    valueList.add(values[0]);
                } else {
                    valueList.add(values[0]);
                    valueList.add(values[1]);
                }
            } else {
                //null、notnull判断
                String value = entry.getValue().toString().trim(); //FIXED:查询前后空格去除
                if ("NULL".equals(value.toUpperCase())) {
                    sb.append(BLANK).append(" is null ").append(BLANK);
                    flag = true;
                    markNum--;
                } else if ("NOTNULL".equals(value.toUpperCase())) {
                    sb.append(BLANK).append(" is not null ").append(BLANK);
                    flag = true;
                    markNum--;
                } else {
                    sb.append(BLANK).append(operAndColumn[0]).append(BLANK).append(MARK + markNum).append(BLANK);

                    if (Operator.LIKE.equals(op) || Operator.NOTLIKE.equals(op)) {
                        value = "%" + value + "%";
                        valueList.add(value);
                    } else {
                        valueList.add(entry.getValue());
                    }
                }
            }
        }
        if (valueList.size() == 0) {
            if (StringUtils.isNotEmpty(sb.toString())) {
                resultMap.put("sql", sb.toString());
                resultMap.put("value", valueList.toArray());
            } else {
                resultMap.put("sql", "1=1");
                resultMap.put("value", null);
            }
            return resultMap;
        } else {
            resultMap.put("sql", sb.toString());
            resultMap.put("value", valueList.toArray());
        }
        return resultMap;
    }

    /**
     * 功能描述：根据传入的参数回返回以and为操作符的SQL条件语句。<br>
     * 注意事项： map.get("value")取到的对象应转为Object[]再处理.
     *
     * @param queryMap key为“操作符-表名-列名”或“操作符-列名”，value为查询参数。
     * @return map 用于存放生成的SQL条件语句和参数值。 <br>         map.get("sql")可以取到SQL条件语句。<br>
     * map.get("value")可以取得参数值数组。<br>
     * @author Wingene Lin
     * @created 2017 /03/01 10:45:52
     */
    public static Map<String, Object> getAndClause(Map<String, Object> queryMap) {
        return getClause(queryMap, "and");
    }

    /**
     * 功能描述：根据传入的参数回返回以and为操作符的SQL条件语句。<br>
     * 注意事项： map.get("value")取到的对象应转为Object[]再处理.
     * Revision Trail: (Date/Author/Description)
     * 2016-7-19 Church Lin CREATE
     *
     * @param queryMap the query map
     * @param markNum  从此位开始计数
     * @return and clause
     * @author Church Lin
     * @created 2017 /03/01 10:45:52
     */
    public static Map<String, Object> getAndClause(Map<String, Object> queryMap, int markNum) {
        return getClause(queryMap, "and", markNum);
    }

    /**
     * 功能描述：根据传入的参数回返回以or为操作符的SQL条件语句。<br>
     * 注意事项： map.get("value")取到的对象应转为Object[]再处理.
     *
     * @param queryMap key为“操作符-表名-列名”或“操作符-列名”，value为查询参数。
     * @return map 用于存放生成的SQL条件语句和参数值。 <br>         map.get("sql")可以取到SQL条件语句。<br>
     * map.get("value")可以取得参数值数组。<br>
     * @author Wingene Lin
     * @created 2017 /03/01 10:45:53
     */
    public static Map<String, Object> getOrClause(Map<String, Object> queryMap) {
        return getClause(queryMap, "or");
    }

    /**
     * 功能描述：根据传入的参数回返回以or为操作符的SQL条件语句。<br>
     * 注意事项： map.get("value")取到的对象应转为Object[]再处理.
     * Revision Trail: (Date/Author/Description)
     * 2016-7-19 Church Lin CREATE
     *
     * @param queryMap the query map
     * @param markNum  从此位开始计数
     * @return or clause
     * @author Church Lin
     * @created 2017 /03/01 10:45:53
     */
    public static Map<String, Object> getOrClause(Map<String, Object> queryMap, int markNum) {
        return getClause(queryMap, "or", markNum);
    }

    /**
     * <P><B>描述:</B> 操作符 </P>
     *
     * @author Wingene Lin
     * @version 1.0
     * @created 2017 /03/01 10:45:52
     * @copyright 2013 EVECOM Technology Co.,Ltd. All rights reserved.
     */
    public enum Operator {
        /**
         * EQ:"="
         */
        EQ,
        /**
         * LIKE:"like"
         */
        LIKE,
        /**
         * GT:">"
         */
        GT,
        /**
         * LT:"<"
         */
        LT,
        /**
         * GTE:">="
         */
        GTE,
        /**
         * LTE:"<="
         */
        LTE,

        /**
         * BETWEEN:"between ... and ..."
         */
        BETWEEN,
        /**
         * NOTLIKE:"not like"
         */
        NOTLIKE,
        /**
         * NOT:"<>"
         */
        NOT,
        /**
         * IN:"in"
         */
        IN,
        /**
         * NOTIN:"not in"
         */
        NOTIN
    }

    /**
     * 将操作符转为SQL中对应的符号。
     *
     * @param str EQ:"=", LIKE:"like", NOTLIKE:"not like", GT:">", LT:"<",            GTE:">=",
     *            LTE:"<=", NOT:"<>", BETWEEN:"between ... and ...",
     *            IN:"in", NOTIN:"not in"
     * @return operator
     * @author Wingene Lin
     * @created 2017 /03/01 10:45:53
     */
    public static String getOperator(String str) {
        Operator op = Operator.valueOf(str.toUpperCase());
        switch (op) {
            case EQ:
                return "=";
            case LIKE:
                return "like";
            case GT:
                return ">";
            case LT:
                return "<";
            case GTE:
                return ">=";
            case LTE:
                return "<=";

            case BETWEEN:
                return "between [StartValue] and [EndValue]";
            case NOTLIKE:
                return "not like";
            case NOT:
                return "<>";
            case IN:
                return "in";
            case NOTIN:
                return "not in";
            default:
                throw new IllegalArgumentException(str + "is not valid Operator");
        }
    }

    /**
     * 拼查询语句并返回。
     *
     * @param searchParams 条 件
     * @param sql          前部分语句如：select * from tabel_name
     * @return sql and value
     * @author Wingene Lin
     * @created 2017 /03/01 10:45:53
     */
    public static Map<String, Object> getSqlAndValue(Map<String, Object> searchParams, String sql) {
        Map<String, Object> sqlandValue = SqlUtil.getAndClause(searchParams);
        String whereString = sqlandValue.get("sql").toString();
        // String sql = SELECT + columnOfVModel() + fromAndJoinOfVModel();
        if (null != sqlandValue.get("value")) {
            sql = sql + " WHERE " + whereString;
        }
        Map<String, Object> newSqlAndValue = new HashMap<String, Object>();
        newSqlAndValue.put("sql", sql);
        newSqlAndValue.put("value", sqlandValue.get("value"));
        return newSqlAndValue;
    }

    /**
     * 拼查询语句并返回。
     * Revision Trail: (Date/Author/Description)
     * 2016-7-19 Church Lin CREATE
     *
     * @param searchParams the search params
     * @param sql          the sql
     * @param markNum      从此位开始计数
     * @return sql and value
     * @author Church Lin
     * @created 2017 /03/01 10:45:53
     */
    public static Map<String, Object> getSqlAndValue(Map<String, Object> searchParams, String sql, int markNum) {
        Map<String, Object> sqlandValue = SqlUtil.getAndClause(searchParams, markNum);
        String whereString = sqlandValue.get("sql").toString();
        // String sql = SELECT + columnOfVModel() + fromAndJoinOfVModel();
        if (null != sqlandValue.get("value")) {
            sql = sql + " WHERE " + whereString;
        }
        Map<String, Object> newSqlAndValue = new HashMap<String, Object>();
        newSqlAndValue.put("sql", sql);
        newSqlAndValue.put("value", sqlandValue.get("value"));
        return newSqlAndValue;
    }

}
