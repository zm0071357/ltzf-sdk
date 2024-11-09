package ltzf.factory;

import ltzf.payments.nativepay.NativePayService;

/**
 * 支付工厂
 */
public interface PayFactory {

    NativePayService nativePayService();

}
