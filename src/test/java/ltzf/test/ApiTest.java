package ltzf.test;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import ltzf.payments.nativepay.INativePayApi;
import ltzf.payments.nativepay.model.PrepayResponse;
import ltzf.utils.SignUtils;
import okhttp3.OkHttpClient;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
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

    @Test
    public void test_retrofit() throws IOException {
        OkHttpClient httpClient = new OkHttpClient();

        INativePayApi nativePayApi = new Retrofit.Builder()
                .baseUrl("https://api.ltzf.cn/")
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(INativePayApi.class);

        String key = "92e72771d4d3e75de9086c2e5e2a27e3";
        long timestamp = System.currentTimeMillis() / 1000;
        System.out.println(timestamp);
        Map<String,String> dataMap = new HashMap<>();
        dataMap.put("mch_id","1698731613");
        dataMap.put("out_trade_no","LTZF2022113023097");
        dataMap.put("total_fee","0.01");
        dataMap.put("body","QQ公仔");
        dataMap.put("timestamp", String.valueOf(timestamp));
        dataMap.put("notify_url","http://xiaoming-programming.top");

        Call<PrepayResponse> call = nativePayApi.prepay(
                dataMap.get("mch_id"),
                dataMap.get("out_trade_no"),
                dataMap.get("total_fee"),
                dataMap.get("body"),
                dataMap.get("timestamp"),
                dataMap.get("notify_url"),
                SignUtils.createSign(dataMap, key));

        Response<PrepayResponse> response = call.execute();
        PrepayResponse prepayResponse = response.body();

        log.info("测试结果:{}", JSON.toJSONString(prepayResponse));

    }
}
