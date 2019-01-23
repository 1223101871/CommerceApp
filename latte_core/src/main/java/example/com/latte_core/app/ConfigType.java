package example.com.latte_core.app;

/**
 * 线程安全的懒汉模式初始化
 */
public enum ConfigType {
    API_HOST,
    APPLICATION_CONTEXT,
    CONFIG_READY,
    ICON,
    INTERCEPTOR
}
