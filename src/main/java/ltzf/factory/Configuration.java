package ltzf.factory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 配置类
 */
@Data
@Getter
public class Configuration {

    private String apiHost = "https://api.ltzf.cn/";

    /**
     * 开发者ID
     */
    private final String appId;

    /**
     * 商户号ID
     */
    private final String merchantId;

    /**
     * 商户密钥
     */
    private final String partnerKey;

    private OkHttpClient okHttpClient;

    private HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.HEADERS;

    private long connectTimeOut = 60;

    private long writeTimeOut = 60;

    private long readTimeOut = 60;

}