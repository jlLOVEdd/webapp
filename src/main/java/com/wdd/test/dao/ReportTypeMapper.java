package com.wdd.test.dao;

import com.wdd.test.bean.ReportType;
import com.wdd.test.bean.ReportTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportTypeMapper {
    int countByExample(ReportTypeExample example);

    int deleteByExample(ReportTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReportType record);

    int insertSelective(ReportType record);

    List<ReportType> selectByExample(ReportTypeExample example);

    ReportType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReportType record, @Param("example") ReportTypeExample example);

    int updateByExample(@Param("record") ReportType record, @Param("example") ReportTypeExample example);

    int updateByPrimaryKeySelective(ReportType record);

    int updateByPrimaryKey(ReportType record);
}