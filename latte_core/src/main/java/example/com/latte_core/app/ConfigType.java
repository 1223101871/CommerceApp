package example.com.latte_core.app;

/**
 * 线程安全的懒汉模式初始化
 */
public enum ConfigType {
    //网络请求域名
    API_HOST,
    //全局上下文
    APPLICATION_CONTEXT,
    //控制配置是否完成
    CONFIG_READY,
    //字体初始化
    ICON,
    INTERCEPTOR,
    WE_CHAT_APP_ID,
    WE_CHAT_APP_SECRET,
    ACTIVITY,
    HANDLER
}
