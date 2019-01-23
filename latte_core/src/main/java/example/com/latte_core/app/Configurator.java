package example.com.latte_core.app;

import java.util.ArrayList;
import java.util.WeakHashMap;

import okhttp3.Interceptor;

/**
 * 能final就final
 * 配置基本的东西
 */
public class Configurator {
    private static final WeakHashMap<Object, Object> LATTE_CONFIGS = new WeakHashMap<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    final WeakHashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    public final void configure() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY, true);
    }

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST, host);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR,INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR,INTERCEPTORS);
        return this;
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }
}
