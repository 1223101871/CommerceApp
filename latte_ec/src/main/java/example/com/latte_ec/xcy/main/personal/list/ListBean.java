package example.com.latte_ec.xcy.main.personal.list;

import android.widget.CompoundButton;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import delegates.LatteDelegate;


/**
 * created by xcy on 2019/4/15
 **/
public class ListBean implements MultiItemEntity {

    private int mItemType = 0;
    private String mImageUrl = null;
    private String mText = null;
    private String mValue = null;
    private int mId = 0;
    private LatteDelegate mDelegate = null;
    private CompoundButton.OnCheckedChangeListener mChangeListener = null;

    public ListBean(int itemType, String imageUrl, String text, String value, int id,
                    LatteDelegate delegate, CompoundButton.OnCheckedChangeListener changeListener) {
        mItemType = itemType;
        mImageUrl = imageUrl;
        mText = text;
        mValue = value;
        mId = id;
        mDelegate = delegate;
        mChangeListener = changeListener;
    }

    public void setItemType(int itemType) {
        mItemType = itemType;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getText() {
        if (mText == null) {
            return "";
        }
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getValue() {
        if (mValue == null) {
            return "";
        }
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public LatteDelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(LatteDelegate delegate) {
        mDelegate = delegate;
    }

    public CompoundButton.OnCheckedChangeListener getChangeListener() {
        return mChangeListener;
    }

    public void setChangeListener(CompoundButton.OnCheckedChangeListener changeListener) {
        mChangeListener = changeListener;
    }

    @Override
    public int getItemType() {
        return mItemType;
    }

    public static final class Builder {
        private int id = 0;
        private int itemType = 0;
        private String imgUrl = null;
        private String text = null;
        private String value = null;
        private LatteDelegate delegate = null;
        private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = null;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setItemType(int itemType) {
            this.itemType = itemType;
            return this;
        }

        public Builder setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder setDelegate(LatteDelegate delegate) {
            this.delegate = delegate;
            return this;
        }

        public Builder setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
            this.onCheckedChangeListener = onCheckedChangeListener;
            return this;
        }

        public ListBean build() {
            return new ListBean(itemType, imgUrl, text, value, id, delegate, onCheckedChangeListener);
        }
    }

}
