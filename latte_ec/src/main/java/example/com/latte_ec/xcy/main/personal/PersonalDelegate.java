package example.com.latte_ec.xcy.main.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import buttom.BottomItemDelegate;
import example.com.latte_ec.R;
import example.com.latte_ec.R2;
import example.com.latte_ec.xcy.main.personal.list.ListAdapter;
import example.com.latte_ec.xcy.main.personal.list.ListBean;
import example.com.latte_ec.xcy.main.personal.list.ListItemType;
import example.com.latte_ec.xcy.main.personal.order.OrderListDelegate;

/**
 * created by xcy on 2019/4/15
 **/
public class PersonalDelegate extends BottomItemDelegate {
    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSettings = null;
    public static final String ORDER_TYPE = "ORDER_TYPE";
    private Bundle mArgs = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @OnClick(R2.id.tv_all_order)
    void onClickAllOrder() {
        mArgs.putString(ORDER_TYPE, "all");
        startOrderListByType();
    }

    @OnClick(R2.id.img_user_avatar)
    void onClickAvatar() {
//        getParentDelegate().getSupportDelegate().start(new UserProfileDelegate());
    }

    private void startOrderListByType() {
        final OrderListDelegate delegate = new OrderListDelegate();
        delegate.setArguments(mArgs);
        getParentDelegate().getSupportDelegate().start(delegate);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArgs = new Bundle();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final ListBean address = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(1)
                .setText("收货地址")
                .build();
        final ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setText("系统设置")
                .build();
        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(system);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvSettings.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRvSettings.setAdapter(adapter);

    }
}
