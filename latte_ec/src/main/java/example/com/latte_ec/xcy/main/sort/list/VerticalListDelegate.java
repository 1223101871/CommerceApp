package example.com.latte_ec.xcy.main.sort.list;

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
import example.com.latte_ec.xcy.main.sort.SortDelegate;
import example.com.latte_ui.recycler.MultipleItemEntity;

/**
 * created by xcy on 2019/1/26
 **/
public class VerticalListDelegate extends LatteDelegate {

    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    private void initRecyclerView(){
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽动画效果
        mRecyclerView.setItemAnimator(null);


    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("http://192.168.43.46:8080/sort_list.json")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSucess(String response) {
                        final List<MultipleItemEntity> data =
                                new VerticalListDataConverter().setJsonData(response).convert();
                        final SortDelegate delegate = getParentDelegate();
                        final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data,delegate);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }
}
