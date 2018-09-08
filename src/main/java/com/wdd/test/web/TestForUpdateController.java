package com.wdd.test.web;

import com.wdd.test.bean.Goods;
import com.wdd.test.bean.dataType.Groal;
import com.wdd.test.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 13:48 2018/8/17
 */
@Controller
@RequestMapping(value = "/testForUpdate")
public class TestForUpdateController {

    @Autowired
    private GoodService goodService;

    @RequestMapping(value = "/testUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Groal testUpdate() {
        Groal groal = new Groal();
        Goods goods = new Goods();
        goods.setPrice(new BigDecimal(36.22));
        goods.setReceivableAmount(new BigDecimal(36.32));
        goods.setSaleAmount(new BigDecimal(32.32));
        goodService.testUpdate(goods);
        groal.setFalg("0");
        groal.setSuccess(true);
        return groal;

    }
}
