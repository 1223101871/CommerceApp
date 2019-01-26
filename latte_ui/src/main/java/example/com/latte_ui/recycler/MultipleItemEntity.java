package example.com.latte_ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * created by xcy on 2019/1/25
 **/
public class MultipleItemEntity implements MultiItemEntity {
    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUENE = new ReferenceQueue<>();
    private final LinkedHashMap<Object, Object> MULTIPLE_FIELDS = new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object, Object>> FILEDS_REFERENCE =
            new SoftReference<>(MULTIPLE_FIELDS, ITEM_QUENE);

    public MultipleItemEntity(LinkedHashMap<Object, Object> fields) {
        FILEDS_REFERENCE.get().putAll(fields);
    }

    public static MultipleEntityBuilder builder(){
        return new MultipleEntityBuilder();
    }

    @Override
    public int getItemType() {
        return (int) FILEDS_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }

    @SuppressWarnings("unchecked")
    public final <T> T getField(Object key) {
        return (T) FILEDS_REFERENCE.get().get(key);
    }

    public final LinkedHashMap<?,?> getFields(){
        return FILEDS_REFERENCE.get();
    }

    public final MultipleItemEntity setField(Object key,Object value){
        FILEDS_REFERENCE.get().put(key,value);
        return this;
    }
}
