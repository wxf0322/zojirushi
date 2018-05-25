/*
 * Copyright (c) 2005, 2014, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.clover.zojirushi.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述 JavaBean操作工具类
 *
 * @author wangxiaofeng
 * @version 1.1
 */
public class BeanUtil {

    /**
     * 日志管理器
     */
    private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    /**
     * 描述 拷贝MAP
     *
     * @param dest
     * @param orig
     * @author wangxiaofeng
     */
    public static void copyNotNullForMap(Map<String, Object> dest, Map<String, Object> orig) {
        Iterator entries = ((Map) orig).entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) entries.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                dest.put(key, value);
            }
        }
    }

    /**
     * 拷贝一个bean中的非空属性于另一个bean中
     *
     * @param dest :目标bean
     * @param orig :源bean
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void copyNotNullProperties(Object dest, Object orig) {
        BeanUtilsBean beanUtils = BeanUtilsBean.getInstance();
        // Validate existence of the specified beans
        if (dest == null) {
            throw new IllegalArgumentException("No destination bean specified");
        }
        if (orig == null) {
            throw new IllegalArgumentException("No origin bean specified");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("BeanUtils.copyProperties(" + dest + ", " + orig + ")");
        }

        // Copy the properties, converting as necessary
        if (orig instanceof DynaBean) {
            DynaProperty[] origDescriptors = ((DynaBean) orig).getDynaClass()
                    .getDynaProperties();
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                if (beanUtils.getPropertyUtils().isReadable(orig, name)
                        && beanUtils.getPropertyUtils().isWriteable(dest, name)) {
                    Object value = ((DynaBean) orig).get(name);
                    try {
                        beanUtils.copyProperty(dest, name, value);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
        } else if (orig instanceof Map) {
            Iterator entries = ((Map) orig).entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry) entries.next();
                String name = (String) entry.getKey();
                if (beanUtils.getPropertyUtils().isWriteable(dest, name)) {
                    try {
                        beanUtils.copyProperty(dest, name, entry.getValue());
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
        } else {
            PropertyDescriptor[] origDescriptors = beanUtils.getPropertyUtils()
                    .getPropertyDescriptors(orig);
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                if ("class".equals(name)) {
                    continue;
                }
                if (beanUtils.getPropertyUtils().isReadable(orig, name)
                        && beanUtils.getPropertyUtils().isWriteable(dest, name)) {
                    try {
                        Object value = beanUtils.getPropertyUtils()
                                .getSimpleProperty(orig, name);
                        if (value != null) {
                            beanUtils.copyProperty(dest, name, value);
                        }
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
        }
    }

    /**
     * 将值进行转换
     *
     * @param convertUtil
     * @param value
     * @param type
     * @return
     */
    public static Object convertValue(ConvertUtilsBean convertUtil, Object value, Class type) {
        Converter converter = convertUtil.lookup(type);
        if (converter == null)
            return value;
        Object newValue = null;
        if ((value instanceof String))
            newValue = converter.convert(type, (String) value);
        else if ((value instanceof String[]))
            newValue = converter.convert(type, ((String[]) value)[0]);
        else {
            newValue = converter.convert(type, value);
        }
        return newValue;
    }

    /**
     * 获取request请求参数的所有值
     *
     * @param request
     * @return
     */
    public static Map<String, Object> getMapFromRequest(HttpServletRequest request) {
        Map reqMap = request.getParameterMap();
        HashMap<String, Object> datas = new HashMap<>();
        Iterator it = reqMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String key = (String) entry.getKey();
            String[] val = (String[]) entry.getValue();
            if (val.length == 1) {
                if (StringUtils.isNotEmpty(val[0])) {
                    datas.put(key, val[0]);
                } else {
                    datas.put(key, "");
                }
            } else {
                if (val != null) {
                    datas.put(key, val);
                } else {
                    datas.put(key, "");
                }
            }
        }
        return datas;
    }

    /**
     * 判断一个对象是否为空
     *
     * @param o
     * @return
     */
    public static boolean isEmpty(Object o) {
        if (o == null)
            return true;
        if ((o instanceof String)) {
            if (((String) o).trim().length() == 0) {
                return true;
            }
        } else if ((o instanceof Collection)) {
            if (((Collection) o).isEmpty()) {
                return true;
            }
        } else if (o.getClass().isArray()) {
            if (((Object[]) o).length == 0) {
                return true;
            }
        } else if ((o instanceof Map)) {
            if (((Map) o).isEmpty()) {
                return true;
            }
        } else if ((o instanceof Long)) {
            if ((Long) o == null) {
                return true;
            }
        } else if ((o instanceof Short)) {
            if ((Short) o == null) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    /**
     * 判断一个对象不为空
     *
     * @param o
     * @return
     */
    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    /**
     * 获取某个类的绝对路径
     *
     * @param clazz
     * @return
     */
    public static String getClassPath(Class clazz) {
        String className = clazz.getName();
        String classNamePath = className.replace(".", "/") + ".class";
        URL is = clazz.getClassLoader().getResource(classNamePath);
        String path = is.getFile();
        path = StringUtils.replace(path, "%20", " ");
        return StringUtils.removeStart(path, "/");
    }

    /**
     * 获取某个类的绝对路径文件夹路径
     *
     * @param clazz
     * @return
     */
    public static String getClassFoldPath(Class clazz) {
        String path = BeanUtil.getClassPath(clazz);
        path = path.substring(0, path.lastIndexOf("/"));
        return path;
    }

    /**
     * 获取某个实体类的某个字段名称的值
     *
     * @param obj
     * @param fieldName
     * @return
     */
    public static Object getClassFieldValue(Object obj, String fieldName) {
        if (invokeMethod(obj, fieldName, null) != null) {
            return invokeMethod(obj, fieldName, null);
        } else {
            return null;
        }
    }

    /**
     * 利用反射调用方法
     *
     * @param owner
     * @param fieldName
     * @param args
     * @return
     */
    public static Object invokeMethod(Object owner, String fieldName, Object[] args) {
        Class<? extends Object> ownerClass = owner.getClass();
        // fieldName -> FieldName
        String methodName = fieldName.substring(0, 1).toUpperCase()
                + fieldName.substring(1);
        try {
            Method method = ownerClass.getMethod("get" + methodName);
            return method.invoke(owner);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

}
