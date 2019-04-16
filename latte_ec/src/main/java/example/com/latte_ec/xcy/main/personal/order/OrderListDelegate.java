package example.com.latte_ec.xcy.main.personal.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.RestClient;
import net.callback.ISuccess;

import java.util.List;

import butterknife.BindView;
import delegates.LatteDelegate;
import example.com.latte_ec.R;
import example.com.latte_ec.R2;
import example.com.latte_ec.xcy.main.personal.PersonalDelegate;
import example.com.latte_ui.recycler.MultipleItemEntity;

/**
 * created by xcy on 2019/4/15
 **/
public class OrderListDelegate extends LatteDelegate {
    private String mType = null;
    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mType = args.getString(PersonalDelegate.ORDER_TYPE);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .loader(getContext())
                .url("http://192.168.43.47:8080/order_list.json")
                .params("type",mType)
                .success(new ISuccess() {
                    @Override
                    public void onSucess(String response) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data =
                                new OrderListDataConvert().setJsonData(response).convert();
                        final OrderListAdapter adapter = new OrderListAdapter(data);
                        mRecyclerView.setAdapter(adapter);
//                        mRecyclerView.addOnItemTouchListener(new OrderListClickListener(OrderListDelegate.this));
                    }
                })
                .build()
                .get();
    }
}
