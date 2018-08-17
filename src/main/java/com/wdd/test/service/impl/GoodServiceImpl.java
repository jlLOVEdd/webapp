package com.wdd.test.service.impl;

import com.wdd.test.bean.Goods;
import com.wdd.test.dao.GoodsMapper;
import com.wdd.test.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 14:04 2018/8/17
 */
@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void testUpdate(Goods goods) {
        goodsMapper.insert(goods);
    }
}
