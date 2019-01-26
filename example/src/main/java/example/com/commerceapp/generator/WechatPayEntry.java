package example.com.commerceapp.generator;

import example.com.latte_annotations.PayEntryGenerator;
import wechat.templete.WXPayEntryTemplete;

/**
 * created by xcy on 2019/1/24
 **/
@PayEntryGenerator(
        packageName = "example.com.commerceapp",
        payEntryTemplete = WXPayEntryTemplete.class
)
public interface WechatPayEntry {
}
