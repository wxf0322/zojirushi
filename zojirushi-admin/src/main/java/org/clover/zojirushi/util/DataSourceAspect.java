package org.clover.zojirushi.util;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

public class DataSourceAspect implements MethodBeforeAdvice, AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method,
                               Object[] args, Object target) throws Throwable {
        // TODO Auto-generated method stub
        DataSourceContextHolder.clearDataSourceType();
    }

    @Override
    public void before(Method method, Object[] args, Object target)
            throws Throwable {

        if (method.isAnnotationPresent(DataSource.class)) {
            DataSource datasource = method.getAnnotation(DataSource.class);
            DataSourceContextHolder.setDataSourceType(datasource.name());
        } else {
            DataSourceContextHolder.setDataSourceType(DataSource.dataSource);
        }

    }
}
