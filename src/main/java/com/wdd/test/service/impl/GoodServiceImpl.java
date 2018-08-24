package com.wdd.test.service.impl;

import com.wdd.test.bean.Goods;
import com.wdd.test.dao.GoodsMapper;
import com.wdd.test.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
        Goods goods1 = goodsMapper.selectByPrimaryKey(1);
        goods1.setSaleAmount(goods1.getSaleAmount().add(new BigDecimal(10)));
        goods1.setPrice(goods1.getPrice().add(new BigDecimal(1)));
        goods1.setReceivableAmount(goods1.getReceivableAmount().add(new BigDecimal(2)));
        goodsMapper.updateByPrimaryKey(goods1);
    }
}
