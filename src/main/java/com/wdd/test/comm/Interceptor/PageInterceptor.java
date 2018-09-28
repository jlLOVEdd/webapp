package com.wdd.test.comm.Interceptor;


import com.wdd.test.comm.log.InnerLog;
import com.wdd.test.comm.util.ReflectHelper;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.data.domain.Page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


/**
 * @Author: weidongdong
 * @Description: 分页拦截器
 * @Date: Created in 15:41 2018/9/28
 */
@Intercepts({@Signature(type = StatementHandler.class,method = "prepare",args = {Connection.class, Integer.class})})
public class PageInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof RoutingStatementHandler) {
            RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
            BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler, "delegate");
            MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate, "mappedStatement");
            //查询方法，参数有Page分页
            if(mappedStatement != null && mappedStatement.getSqlCommandType() == SqlCommandType.SELECT){
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
                        InnerLog.debug(">>>>>>  countSql = "+countSql);
                        ReflectHelper.setValueByFieldName(boundSql, "sql", countSql);
                        DefaultParameterHandler parameterHandler = new DefaultParameterHandler(	mappedStatement, parameterObject, boundSql);
                        parameterHandler.setParameters(countStmt);
                        rs = countStmt.executeQuery();
                        int count = 0;
                        if (rs.next()) {
                            count = ((Number) rs.getObject(1)).intValue();
                        }
                       // page.setTotalCount(count);// setRowCount(count);
                    } finally {
                        try {
                            if (rs != null){
                                rs.close();
                            }
                        } catch (Exception e) {
                        }
                        try {
                            if(countStmt != null){
                                countStmt.close();
                            }
                        } catch (Exception e) {
                        }
                    }
                    //String pageSql = this.generatePagesSql(sql, page);
                    //InnerLog.debug(">>>>>>  pageSql = "+pageSql);
                    // 将分页sql语句反射回BoundSql.
                   // ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql);
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
