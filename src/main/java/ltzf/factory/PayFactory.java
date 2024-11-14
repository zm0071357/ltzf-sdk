package ltzf.factory;

import ltzf.payments.h5.H5PayService;
import ltzf.payments.nativepay.NativePayService;

/**
 * 支付工厂
 */
public interface PayFactory {

    NativePayService nativePayService();

    H5PayService h5PayService();

}
