package example.com.latte_ec.xcy.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import buttom.BottomItemDelegate;
import example.com.latte_ec.R;

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
}
