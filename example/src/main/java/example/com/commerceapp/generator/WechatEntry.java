package example.com.commerceapp.generator;

import example.com.latte_annotations.EntryGenerator;
import wechat.templete.WXEntryTemplete;

/**
 * created by xcy on 2019/1/24
 **/
@EntryGenerator(
        packageName = "example.com.commerceapp",
        entryTemplete = WXEntryTemplete.class
)
public interface WechatEntry {
}
