package ltzf.payments.nativepay.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ltzf.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;

@Data
public class PrepayRequest {

    @JsonProperty("mch_id")
    private String mchId;
    @JsonProperty("out_trade_no")
    private String outTradeNo;
    @JsonProperty("total_fee")
    private String totalFee;
    @JsonProperty("body")
    private String body;
    @JsonProperty("time_expire")
    private String timeExpire;
    @JsonProperty("notify_url")
    private String notifyUrl;
    @JsonProperty("sign")
    private String sign;

    private String timestamp = String.valueOf(System.currentTimeMillis() / 1000);

    public String getTimestamp() {return timestamp;}

    public String createSign(String partnerKey) {
        String key = "92e72771d4d3e75de9086c2e5e2a27e3";
        long timestamp = System.currentTimeMillis() / 1000;
        System.out.println(timestamp);
        Map<String,String> dataMap = new HashMap<>();
        dataMap.put("mch_id", getMchId());
        dataMap.put("out_trade_no", getOutTradeNo());
        dataMap.put("total_fee", getTotalFee());
        dataMap.put("body", getBody());
        dataMap.put("timestamp", getTimestamp());
        dataMap.put("notify_url", getNotifyUrl());
        return SignUtils.createSign(dataMap, partnerKey);
    }

}
