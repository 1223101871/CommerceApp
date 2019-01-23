package example.com.commerceapp;



import activities.ProxyActivity;
import delegates.LatteDelegate;
import example.com.latte_ec.xcy.launcher.LauncherDelegate;

public class ExampleActivity extends ProxyActivity{

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }
}
