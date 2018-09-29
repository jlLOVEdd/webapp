package com.wdd.test.comm.page;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 10:59 2018/9/29
 */
public class MysqlDialect extends Dialect {

    @Override
    public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
        if (offset > 0) {
            return sql + " limit " + offsetPlaceholder + "," + limitPlaceholder;
        } else {
            return sql + " limit " + limitPlaceholder;
        }
    }
}
