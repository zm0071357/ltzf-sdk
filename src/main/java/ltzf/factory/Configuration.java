package ltzf.factory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 配置类
 */
@Data
@Getter
public class Configuration {

    @Setter
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

    @Setter
    private OkHttpClient okHttpClient;
    @Setter
    private HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.HEADERS;
    @Setter
    private long connectTimeOut = 60;
    @Setter
    private long writeTimeOut = 60;
    @Setter
    private long readTimeOut = 60;

}
