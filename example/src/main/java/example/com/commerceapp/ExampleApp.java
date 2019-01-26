package example.com.commerceapp;

import android.support.multidex.MultiDexApplication;
import android.widget.Toast;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import net.interceptors.DebugInterceptor;

import example.com.latte_core.app.Latte;
import example.com.latte_ec.xcy.database.DatabaseManager;
import example.com.latte_ec.xcy.icon.FontEcModule;
import example.com.latte_ec.xcy.sign.ISignListener;

public class ExampleApp extends MultiDexApplication  {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http:127.0.0.1")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();
        DatabaseManager.getInstance().init(this);
    }


}
