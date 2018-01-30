package com.wdd.test.dao;

import com.wdd.test.bean.Sysuserinfo;
import com.wdd.test.bean.SysuserinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysuserinfoMapper {
    int countByExample(SysuserinfoExample example);

    int deleteByExample(SysuserinfoExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(Sysuserinfo record);

    int insertSelective(Sysuserinfo record);

    List<Sysuserinfo> selectByExample(SysuserinfoExample example);

    Sysuserinfo selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") Sysuserinfo record, @Param("example") SysuserinfoExample example);

    int updateByExample(@Param("record") Sysuserinfo record, @Param("example") SysuserinfoExample example);

    int updateByPrimaryKeySelective(Sysuserinfo record);

    int updateByPrimaryKey(Sysuserinfo record);
}