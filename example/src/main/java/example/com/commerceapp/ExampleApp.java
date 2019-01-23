package example.com.commerceapp;

import android.support.multidex.MultiDexApplication;

import net.interceptors.DebugInterceptor;

import example.com.latte_core.app.Latte;

public class ExampleApp extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http:127.0.0.1")
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
    }
}
