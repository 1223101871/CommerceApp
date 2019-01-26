package example.com.latte_ec.xcy.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import buttom.BottomItemDelegate;
import example.com.latte_ec.R;
import example.com.latte_ec.xcy.main.sort.content.ContentDelegate;
import example.com.latte_ec.xcy.main.sort.list.VerticalListDelegate;

/**
 * created by xcy on 2019/1/24
 **/
public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container, listDelegate);
        //设置右侧第一个分类显示，默认显示分类一
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));

    }
}
