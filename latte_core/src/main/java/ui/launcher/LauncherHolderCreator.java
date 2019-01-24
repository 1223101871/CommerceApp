package ui.launcher;

import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;

/**
 * created by xcy on 2019/1/23
 **/
public class LauncherHolderCreator implements CBViewHolderCreator {

    @Override
    public Object createHolder() {
        return  new LauncherHolder();
    }
}
