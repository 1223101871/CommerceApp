package example.com.latte_ec.xcy.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import buttom.BottomItemDelegate;
import delegates.LatteDelegate;
import delegates.web.WebDelegateImpl;
import example.com.latte_ec.R;

/**
 * created by xcy on 2019/1/27
 **/
public class DiscoverDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebDelegateImpl delegate = WebDelegateImpl.create("index.html");
        delegate.setTopDelegate(this.getParentDelegate());
        loadRootFragment(R.id.web_discovery_container, delegate);
    }
}
