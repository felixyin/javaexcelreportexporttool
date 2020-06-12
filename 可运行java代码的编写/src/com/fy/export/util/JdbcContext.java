package com.fy.export.util;

import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yinbin
 */
public class JdbcContext implements Serializable {

    private static final String JDBC_KEY = "JDBC_CONNECTION";
    private static ThreadLocal threadLocal = new ThreadLocal();
    private Map<String, Object> map;

    public static Connection getJdbcConnection() throws Exception {
        Map<String, Object> map = (Map<String, Object>) threadLocal.get();
        if (null != map) {
            Connection con = (Connection) map.get(JDBC_KEY);
            if (null != con) {
                return con;
            }
        }
        return null;
    }

    public static void setJdbcConnection(Connection connection) {
        Map<String, Object> map = (Map<String, Object>) threadLocal.get();
        if (null == map) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put(JDBC_KEY, connection);
    }
}
