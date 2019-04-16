package example.com.latte_ec.xcy.main.personal.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import example.com.latte_ec.R;

/**
 * created by xcy on 2019/4/15
 **/
public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {
    public ListAdapter(List data) {
        super(data);
        addItemType(ListItemType.ITEM_NORMAL, R.layout.arrow_item_layout);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ListBean listBean) {
        switch (baseViewHolder.getItemViewType()) {
            case 20:
                baseViewHolder.setText(R.id.tv_arrow_text, listBean.getText());
                baseViewHolder.setText(R.id.tv_arrow_value, listBean.getValue());

                break;
            default:
                break;
        }
    }
}
