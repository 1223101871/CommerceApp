package example.com.commerceapp.generator;

import example.com.latte_annotations.AppRegisterGenerator;
import wechat.templete.AppRegisterTemplete;

/**
 * created by xcy on 2019/1/24
 **/
@AppRegisterGenerator(
        packageName = "example.com.commerceapp",
        registerTemplate = AppRegisterTemplete.class
)
public interface AppRegister {
}
