package ltzf.payments.nativepay;

import ltzf.factory.Configuration;
import ltzf.payments.nativepay.model.PrepayRequest;
import ltzf.payments.nativepay.model.PrepayResponse;
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
        Call<PrepayResponse> call = nativePayApi.prepay(request.getMchId(),
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

}
