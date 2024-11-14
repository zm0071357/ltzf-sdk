package ltzf.test;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import ltzf.factory.Configuration;
import ltzf.factory.defaults.DefaultPayFactory;
import ltzf.payments.h5.H5PayService;
import ltzf.payments.h5.model.PrepayRequest;
import ltzf.payments.h5.model.PrepayResponse;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

@Slf4j
public class H5PayServiceTest {

    private H5PayService h5PayService;

    @Before
    public void init() {
        Configuration configuration = new Configuration(
                "1109501", "1698731613", "92e72771d4d3e75de9086c2e5e2a27e3"
        );
        DefaultPayFactory payFactory = new DefaultPayFactory(configuration);
        this.h5PayService = payFactory.h5PayService();
    }

    @Test
    public void test_prepay() throws Exception {
        // 请求参数
        PrepayRequest request = new PrepayRequest();
        request.setMchId("1698731613");
        request.setOutTradeNo("LTZF2024113055096");
        request.setTotalFee("0.01");
        request.setBody("QQ公仔");
        request.setNotifyUrl("http://xiaoming-programming.top");

        // 创建支付订单
        PrepayResponse reponse = h5PayService.prepay(request);
        log.info("请求参数:{}", JSON.toJSONString(request));
        log.info("返回结果:{}", JSON.toJSONString(reponse));
    }


}
