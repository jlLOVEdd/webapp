package com.wdd.test.comm.Interceptor;


import com.wdd.test.comm.log.InnerLog;
import com.wdd.test.comm.page.Dialect;
import com.wdd.test.comm.page.Page;
import com.wdd.test.comm.util.ReflectHelper;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import javax.xml.bind.PropertyException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


/**
 * @Author: weidongdong
 * @Description: 分页拦截器
 * @Date: Created in 15:41 2018/9/28
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PageInterceptor implements Interceptor {

    private Dialect dialectObject = null;//数据库方言

    private String pageSqlId = "";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
        BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler, "delegate");
        MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate, "mappedStatement");
        //查询方法，参数有Page分页
        if (mappedStatement != null && mappedStatement.getSqlCommandType() == SqlCommandType.SELECT) {
//			if (mappedStatement.getId().matches(pageSqlId)) { // 拦截需要分页的SQL
            BoundSql boundSql = delegate.getBoundSql();
            // 分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空
            Object parameterObject = boundSql.getParameterObject();
            if (parameterObject != null) {
                Page page = null;
                // 参数就是Page实体
                if (parameterObject instanceof Page) {
                    page = (Page) parameterObject;
                    //参数为Map，中包含Page
                } else if (parameterObject instanceof Map) {
                    for (Map.Entry entry : (Set<Map.Entry>) ((Map) parameterObject).entrySet()) {
                        if (entry.getValue() instanceof Page) {
                            page = (Page) entry.getValue();
                            break;
                        }
                    }
                    // 参数为某个实体，该实体拥有Pages属性
                } else {
                    page = ReflectHelper.getValueByFieldType(parameterObject, Page.class);
                }
                if (page == null) {
                    return invocation.proceed();
                }
                String sql = boundSql.getSql();
                PreparedStatement countStmt = null;
                ResultSet rs = null;
                try {
                    Connection connection = (Connection) invocation.getArgs()[0];
                    String countSql = "select count(1) from (" + sql + ") tmp_count"; // 记录统计
                    countStmt = connection.prepareStatement(countSql);
                    InnerLog.debug(">>>>>>  countSql = " + countSql);
                    ReflectHelper.setValueByFieldName(boundSql, "sql", countSql);
                    DefaultParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
                    parameterHandler.setParameters(countStmt);
                    rs = countStmt.executeQuery();
                    int count = 0;
                    if (rs.next()) {
                        count = ((Number) rs.getObject(1)).intValue();
                    }
                    page.setTotalCount(count);// setRowCount(count);
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                    } catch (Exception e) {
                    }
                    try {
                        if (countStmt != null) {
                            countStmt.close();
                        }
                    } catch (Exception e) {
                    }
                }
                String pageSql = this.generatePagesSql(sql, page);
                InnerLog.debug(">>>>>>  pageSql = " + pageSql);
                // 将分页sql语句反射回BoundSql.
                ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql);
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String dialect = ""; // 数据库方言
        dialect = properties.getProperty("dialect");
        if (StringUtils.isEmpty(dialect)) {
            try {
                throw new PropertyException("dialect property is not found!");
            } catch (PropertyException e) {
                InnerLog.error("pageSqlId property is not found!", e);
            }
        } else {
            try {
                dialectObject = (Dialect) Class.forName(dialect)
                        .getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(dialect + ", init fail!\n" + e);
            }
        }
        pageSqlId = properties.getProperty("pageSqlId");// 根据id来区分是否需要分页
        if (StringUtils.isEmpty(pageSqlId)) {

            try {
                throw new PropertyException("pageSqlId property is not found!");
            } catch (PropertyException e) {
                InnerLog.error("pageSqlId property is not found!", e);
            }
        }
    }


    /**
     * 根据数据库方言，生成特定的分页sql
     *
     * @param sql
     * @param page
     */
    private String generatePagesSql(String sql, Page page) {
        if (page != null && dialectObject != null) {
            // pageNow默认是从1，而已数据库是从0开始计算的．所以(page.getPageNow()-1)
            int pageNow = page.getPageNow();
            return dialectObject.getLimitString(sql, (pageNow <= 0 ? 0 : pageNow - 1) * page.getPageSize(), page.getPageSize());
        }
        return sql;
    }

}
