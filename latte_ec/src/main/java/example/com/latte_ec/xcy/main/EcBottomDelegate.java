package example.com.latte_ec.xcy.main;

import android.graphics.Color;

import java.util.LinkedHashMap;

import buttom.BaseButtomDelegate;
import buttom.BottomItemDelegate;
import buttom.BottomTabBean;
import buttom.ItemBuilder;
import example.com.latte_ec.xcy.main.cart.ShopCartDelegate;
import example.com.latte_ec.xcy.main.discover.DiscoverDelegate;
import example.com.latte_ec.xcy.main.index.IndexDelegate;
import example.com.latte_ec.xcy.main.sort.SortDelegate;

/**
 * created by xcy on 2019/1/24
 **/
public class EcBottomDelegate extends BaseButtomDelegate {



    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
