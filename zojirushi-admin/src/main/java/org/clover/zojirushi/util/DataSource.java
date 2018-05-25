package org.clover.zojirushi.util;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String name() default DataSource.dataSource;

    public static String dataSource = "dataSource";

    public static String dataSource2 = "dataSource2";


}
