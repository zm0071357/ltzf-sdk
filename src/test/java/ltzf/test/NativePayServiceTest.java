package ltzf.test;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import ltzf.factory.Configuration;
import ltzf.factory.defaults.DefaultPayFactory;
import ltzf.payments.nativepay.NativePayService;
import ltzf.payments.nativepay.model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

@Slf4j
public class NativePayServiceTest {

    private NativePayService nativePayService;

    @Before
    public void init() {
        Configuration configuration = new Configuration(
                "1109501", "1698731613", "92e72771d4d3e75de9086c2e5e2a27e3"
        );
        DefaultPayFactory payFactory = new DefaultPayFactory(configuration);
        this.nativePayService = payFactory.nativePayService();
    }

    @Test
    public void test_prepay() throws IOException {
        // 请求参数
        PrepayRequest request = new PrepayRequest();
        request.setMchId("1698731613");
        request.setOutTradeNo("LTZF2022113055096");
        request.setTotalFee("0.01");
        request.setBody("QQ公仔");
        request.setNotifyUrl("http://xiaoming-programming.top");

        // 创建支付订单
        PrepayResponse reponse = nativePayService.prePay(request);
        log.info("请求参数:{}", JSON.toJSONString(request));
        log.info("返回结果:{}", JSON.toJSONString(reponse));
    }

    @Test
    public void test_queryOrderByOutTradeNo() throws Exception {
        QueryOrderByOutTradeNoRequest request = new QueryOrderByOutTradeNoRequest();
        request.setMchid("1698731613");
        request.setOutTradeNo("LTZF2022113055096");
        QueryOrderByOutTradeNoResponse response = nativePayService.queryOrderByOutTradeNo(request);
        log.info("请求参数: {}", JSON.toJSONString(request));
        log.info("返回结果: {}", JSON.toJSONString(response));
    }

    @Test
    public void test_refundOrder() throws Exception {
        RefundOrderRequest request = new RefundOrderRequest();
        request.setMchid("1698731613");
        request.setOutTradeNo("LTZF2022113055096");
        request.setOutRefundNo("LTZFTD2022113055088");
        request.setRefundFee("0.01");
        request.setRefundDesc("测试退款");
        request.setNotifyUrl("http://xiaoming-programming.top ");

        Object response = nativePayService.refundOrder(request);
        log.info("请求参数: {}", JSON.toJSONString(request));
        log.info("返回结果: {}", JSON.toJSONString(response));
    }

    @Test
    public void test_getRefundOrder() throws Exception {
        GetRefundOrderRequest request = new GetRefundOrderRequest();
        request.setMchid("1698731613");
        request.setOutRefundNo("LTZFTD2022113055088");

        GetRefundOrderResponse response = nativePayService.getRefundOrder(request);
        log.info("请求参数: {}", JSON.toJSONString(request));
        log.info("返回结果: {}", JSON.toJSONString(response));
    }




}
