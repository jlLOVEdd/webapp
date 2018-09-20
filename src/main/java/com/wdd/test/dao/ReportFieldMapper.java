package com.wdd.test.dao;

import com.wdd.test.bean.ReportField;
import com.wdd.test.bean.ReportFieldExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportFieldMapper {
    int countByExample(ReportFieldExample example);

    int deleteByExample(ReportFieldExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReportField record);

    int insertSelective(ReportField record);

    List<ReportField> selectByExample(ReportFieldExample example);

    ReportField selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReportField record, @Param("example") ReportFieldExample example);

    int updateByExample(@Param("record") ReportField record, @Param("example") ReportFieldExample example);

    int updateByPrimaryKeySelective(ReportField record);

    int updateByPrimaryKey(ReportField record);
}