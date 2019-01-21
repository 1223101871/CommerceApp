package util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import example.com.latte_core.app.Latte;

/**
 * created by xcy on 2019/1/21
 **/
public class DimenUtil {
    public static int getScreenWidth(){
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
