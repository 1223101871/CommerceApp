package example.com.latte_ui.recycler;

import com.google.auto.value.AutoValue;

/**
 * created by xcy on 2019/1/26
 **/
@AutoValue
public abstract class RgbValue {
    public abstract int red();
    public abstract int green();
    public abstract int blue();

    public static RgbValue create(int red,int green,int blue){
        return new AutoValue_RgbValue(red,green,blue);
    }
}
