package example.com.latte_ec.xcy.pay;

/**
 * created by xcy on 2019/4/10
 **/
public interface IAlPayResultListener {
    void onPaySuccess();
    void onPaying();
    void onPayFail();
    void onPayCancel();
    void onPayConnectError();
}
