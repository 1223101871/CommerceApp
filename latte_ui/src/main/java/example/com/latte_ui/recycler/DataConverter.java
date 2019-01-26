package example.com.latte_ui.recycler;

import java.util.ArrayList;

/**
 * created by xcy on 2019/1/25
 **/
public abstract class DataConverter {
    protected final ArrayList<MultipleItemEntity> ENTITYS = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String json) {
        mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null && mJsonData.isEmpty()) {
            throw new NullPointerException("data is null");
        }
        return mJsonData;
    }
}
