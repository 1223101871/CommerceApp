package example.com.latte_core.app;

import android.content.Context;

import java.util.WeakHashMap;

public final class Latte {
    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getLatteConfigs()
                .put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }


    public static Context getApplicationContext() {
        return getConfiguration(ConfigType.APPLICATION_CONTEXT.name());
    }
}
