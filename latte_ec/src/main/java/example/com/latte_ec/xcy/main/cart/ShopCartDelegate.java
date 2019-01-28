package example.com.latte_ec.xcy.main.cart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;

import net.RestClient;
import net.callback.ISuccess;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import buttom.BottomItemDelegate;
import example.com.latte_ec.R;
import example.com.latte_ec.R2;
import example.com.latte_ui.recycler.MultipleItemEntity;

/**
 * created by xcy on 2019/1/27
 **/
public class ShopCartDelegate extends BottomItemDelegate implements ISuccess {
    private ShopCartAdapter mAdapter = null;
    //购物车数量标记
    private int mCurrentCount = 0;
    private int mTotalCount = 0;
    private double mTotalPrice = 0.00;

    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView = null;

    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectAll = null;

    @OnClick(R2.id.icon_shop_cart_select_all)
    void onClickSelectAll(){
        final int tag = (int) mIconSelectAll.getTag();
        if (tag == 0){
            mIconSelectAll.setTextColor
                    (ContextCompat.getColor(getContext(), R.color.app_main));
            mIconSelectAll.setTag(1);
            mAdapter.setIsSelectAll(true);
            //更新RecyclerView的显示状态
            //更新RecyclerView的显示状态
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        }else {
            mIconSelectAll.setTextColor(Color.GRAY);
            mIconSelectAll.setTag(0);
            mAdapter.setIsSelectAll(false);
            //更新RecyclerView的显示状态
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        }

    }

    @OnClick(R2.id.tv_top_shop_cart_remove)
    void onClickItemRemove(){
        final List<MultipleItemEntity> data = mAdapter.getData();
        //要删除的数据
        final List<MultipleItemEntity> deleteEntities = new ArrayList<>();
        for (MultipleItemEntity entity : data){
            final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
            if (isSelected){
                deleteEntities.add(entity);
            }
        }
        for (MultipleItemEntity entity : deleteEntities) {
            int removePosition;
            final int entityPosition = entity.getField(ShopCartItemFields.POSITION);
            if (entityPosition > mCurrentCount - 1) {
                removePosition = entityPosition - (mTotalCount - mCurrentCount);
            } else {
                removePosition = entityPosition;
            }
            if (removePosition <= mAdapter.getItemCount()) {
                mAdapter.remove(removePosition);
                mCurrentCount = mAdapter.getItemCount();
                //更新数据
                mAdapter.notifyItemRangeChanged(removePosition, mAdapter.getItemCount());
            }
        }
    }

    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickClear() {
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
//        checkItemCount();
    }

    @Override
    public Object setLayout() {
        return R.layout.deleagte_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mIconSelectAll.setTag(0);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("http://192.168.43.46:8080/shop_cart_data.json")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSucess(String response) {
        final ArrayList<MultipleItemEntity> data = new ShopCartDataConverter()
                .setJsonData(response)
                .convert();
        mAdapter = new ShopCartAdapter(data);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

    }
}
