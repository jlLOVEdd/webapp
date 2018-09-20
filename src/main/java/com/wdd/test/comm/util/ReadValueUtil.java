package com.wdd.test.comm.util;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 16:55 2018/9/18
 */
public class ReadValueUtil {
    public static String getValue(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof Double) {
            return Double.toString((Double) obj);
        } else if (obj instanceof Float) {
            return Float.toString((Float) obj);
        } else if (obj instanceof Integer) {
            return Integer.toString((Integer) obj);
        } else if (obj instanceof Long) {
            return Long.toString((Long) obj);
        } else if (obj instanceof BigDecimal) {
            return ((BigDecimal) obj).toString();
        } else if (obj instanceof Timestamp) {
            return new DateTime(((Timestamp) obj).getTime()).toString(DateUtils.Format.DATETIME.toString());
        } else if (obj instanceof Date) {
            return DateUtils.formatDate((Date) obj);
        } else {
            return obj.toString();
        }
    }
}
