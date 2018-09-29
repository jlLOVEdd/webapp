package com.wdd.test.dao.bus;

import com.wdd.test.bean.Sysuserinfo;
import com.wdd.test.bean.SysuserinfoVo;
import com.wdd.test.comm.page.Page;
import com.wdd.test.dao.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysuserinfoBusMapper extends BaseMapper<SysuserinfoVo, Integer> {
    SysuserinfoVo selectByName(@Param("name") String name, @Param("page") Page page);
}
