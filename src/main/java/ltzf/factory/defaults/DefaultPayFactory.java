package ltzf.factory.defaults;

import ltzf.factory.Configuration;
import ltzf.factory.PayFactory;
import ltzf.payments.nativepay.INativePayApi;
import ltzf.payments.nativepay.NativePayService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

public class DefaultPayFactory implements PayFactory {

    private final Configuration configuration;

    private final OkHttpClient httpClient;

    public DefaultPayFactory(Configuration configuration) {
        this.configuration = configuration;
        // 日志配置
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(configuration.getLevel());
        // 开启HTTP客户端
        this.httpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(configuration.getConnectTimeOut(), TimeUnit.SECONDS)
                .writeTimeout(configuration.getWriteTimeOut(), TimeUnit.SECONDS)
                .readTimeout(configuration.getReadTimeOut(), TimeUnit.SECONDS)
                .build();
    }

    @Override
    public NativePayService nativePayService() {
        // 构建API
        INativePayApi nativePayApi = new Retrofit.Builder()
                .baseUrl(configuration.getApiHost())
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(INativePayApi.class);

        // 创建Native支付服务
        return new NativePayService(configuration, nativePayApi);
    }
}
