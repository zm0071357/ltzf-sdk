package ltzf.test;

import ltzf.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;

public class ApiTest {
    public static void main(String[] args) {
        String key = "92e72771d4d3e75de9086c2e5e2a27e3";
        long timestamp = System.currentTimeMillis() / 1000;
        System.out.println(timestamp);
        Map<String,String> dataMap = new HashMap<>();
        dataMap.put("mch_id","1698731613");
        dataMap.put("out_trade_no","LTZF2022113023096");
        dataMap.put("total_fee","0.01");
        dataMap.put("body","QQ公仔");
        dataMap.put("timestamp", String.valueOf(timestamp));
        dataMap.put("notify_url","http://xiaoming-programming.top");

        System.out.println(SignUtils.createSign(dataMap, key));
    }
}
