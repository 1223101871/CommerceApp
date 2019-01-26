package example.com.latte_ec.xcy.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import net.RestClient;
import net.callback.ISuccess;

import java.util.List;

import butterknife.BindView;
import delegates.LatteDelegate;
import example.com.latte_ec.R;
import example.com.latte_ec.R2;

/**
 * created by xcy on 2019/1/26
 **/
public class ContentDelegate extends LatteDelegate {

    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;
    private List<SectionBean> mData = null;


    @BindView(R2.id.rv_list_content)
    RecyclerView mRecyclerView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    public static ContentDelegate newInstance(int contentId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        final ContentDelegate delegate = new ContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    private void initData(){
        RestClient.builder()
                .url("http://192.168.43.46:8080/sort_content_data.json")
                .success(new ISuccess() {
                    @Override
                    public void onSucess(String response) {
                        mData = new SectionDataConverter().convert(response);
                        final SectionAdapter sectionAdapter =
                                new SectionAdapter(R.layout.item_section_content,
                                        R.layout.item_section_head,mData);
                        mRecyclerView.setAdapter(sectionAdapter);

                    }
                })
                .build()
                .get();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        initData();
    }
}
