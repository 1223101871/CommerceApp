package activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import delegates.LatteDelegate;
import example.com.latte_core.R;
import me.yokeyword.fragmentation.SupportActivity;

public abstract class ProxyActivity extends SupportActivity {
    //返回根delegate
    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }
    private void initContainer(@Nullable Bundle savedInstanceState){
        final FrameLayout container = new FrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null){
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    //一个退出，整个应用全部退出，做垃圾回收工作
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
