package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 15:56 2018/4/17
 */
public class MapTest {

    public static void main(String[] args) {
        JSONObject object =JSONObject.parseObject("{'info':[{'goodsId':'1234','goodsq':'10'},{'goodsId':'5678','goodsq':'20'}]}");
        JSONArray array = object.getJSONArray("info");
        for (int i=0; i<array.size();i++) {
            JSONObject object1 = (JSONObject) array.get(i);
            System.out.println(object1.get("goodsId")+"------------------"+object1.get("goodsq"));
        }

    }
}
