package example.com.latte_core.app;

import android.content.Context;
import android.os.Handler;

import java.util.WeakHashMap;

public final class Latte {
    //context：推荐传入applicationContext
    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getLatteConfigs()
                .put(ConfigType.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigType.HANDLER);
    }


    public static Context getApplicationContext() {
        return getConfiguration(ConfigType.APPLICATION_CONTEXT);
    }
}
