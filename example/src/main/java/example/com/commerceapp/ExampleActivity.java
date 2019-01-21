package example.com.commerceapp;



import activities.ProxyActivity;
import delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity{

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
