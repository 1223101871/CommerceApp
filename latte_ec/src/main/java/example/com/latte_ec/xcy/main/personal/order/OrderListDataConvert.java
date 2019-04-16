package example.com.latte_ec.xcy.main.personal.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import example.com.latte_ui.recycler.DataConverter;
import example.com.latte_ui.recycler.MultipleFields;
import example.com.latte_ui.recycler.MultipleItemEntity;

/**
 * created by xcy on 2019/4/15
 **/
public class OrderListDataConvert extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = array.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = array.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String title = data.getString("title");
            final int id = data.getInteger("id");
            final double price = data.getDouble("price");
            final String time = data.getString("time");
            MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                    .setItemType(OrderListItemType.ITEM_ORDER_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.IMAGE_URL, thumb)
                    .setField(MultipleFields.TITLE, title)
                    .setField(OrderItemFields.TIME, time)
                    .setField(OrderItemFields.PRICE, price)

                    .build();
            ENTITYS.add(itemEntity);

        }
        return ENTITYS;
    }
}
