package ltzf.payments.nativepay;

import ltzf.factory.Configuration;
import ltzf.payments.nativepay.model.*;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class NativePayService {

    private final INativePayApi nativePayApi;

    private final Configuration configuration;

    public NativePayService(Configuration configuration,INativePayApi nativePayApi) {
        this.configuration = configuration;
        this.nativePayApi = nativePayApi;
    }


    public PrepayResponse prePay(PrepayRequest request) throws IOException {
        // 请求接口 & 签名
        Call<PrepayResponse> call = nativePayApi.prepay(
                request.getMchId(),
                request.getOutTradeNo(),
                request.getTotalFee(),
                request.getBody(),
                request.getTimestamp(),
                request.getNotifyUrl(),
                request.createSign(configuration.getPartnerKey()));

        // 获取数据
        Response<PrepayResponse> execute = call.execute();

        // 返回结果
        PrepayResponse response = execute.body();
        return response;
    }

    /**
     * 查询订单
     * @param request 请求入参
     * @return 订单信息
     * @throws Exception 异常
     */
    public QueryOrderByOutTradeNoResponse queryOrderByOutTradeNo(QueryOrderByOutTradeNoRequest request) throws Exception {
        // 请求接口 & 签名
        Call<QueryOrderByOutTradeNoResponse> call = nativePayApi.getPayOrder(
                request.getMchid(),
                request.getOutTradeNo(),
                request.getTimestamp(),
                request.createSign(configuration.getPartnerKey()));

        // 获取数据
        retrofit2.Response<QueryOrderByOutTradeNoResponse> execute = call.execute();

        // 返回结果
        return execute.body();
    }

    /**
     * 发起退单
     * @param request 退单信息
     * @return 退单结果
     * @throws Exception 异常
     */
    public RefundOrderResponse refundOrder(RefundOrderRequest request) throws Exception {
        // 请求接口 & 签名
        Call<RefundOrderResponse> call = nativePayApi.refundOrder(
                request.getMchid(),
                request.getOutTradeNo(),
                request.getOutRefundNo(),
                request.getTimestamp(),
                request.getRefundFee(),
                request.getRefundDesc(),
                request.getNotifyUrl(),
                request.createSign(configuration.getPartnerKey()));
        // 获取数据
        retrofit2.Response<RefundOrderResponse> execute = call.execute();
        // 返回结果
        return execute.body();
    }

    /**
     * 查询退单结果
     * @param request 请求参数
     * @return 退单信息
     * @throws Exception 异常
     */
    public GetRefundOrderResponse getRefundOrder(GetRefundOrderRequest request) throws Exception {
        // 请求接口 & 签名
        Call<GetRefundOrderResponse> call = nativePayApi.getRefundOrder(
                request.getMchid(),
                request.getOutRefundNo(),
                request.getTimestamp(),
                request.createSign(configuration.getPartnerKey()));
        // 获取数据
        retrofit2.Response<GetRefundOrderResponse> execute = call.execute();
        // 返回结果
        return execute.body();
    }


}
