package example.com.commerceapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import net.RestClient;
import net.callback.IError;
import net.callback.IFailure;
import net.callback.ISuccess;

import delegates.LatteDelegate;

/**
 * created by xcy on 2019/1/20
 **/
public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://127.0.0.1/index")
//                .params("", "")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSucess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
