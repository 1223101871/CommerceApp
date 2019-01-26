package net.callback;

import android.os.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ui.LatteLoader;
import ui.LoaderStyle;

/**
 * created by xcy on 2019/1/21
 * 返回信息的回调
 **/
public class RequsetCallbacks implements Callback<String> {
    private final ISuccess SUCCESS;
    private final IRequest REQUEST;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    //Handler尽量声明成static类型，避免内存泄漏
    private static final Handler handler = new Handler();

    public RequsetCallbacks(ISuccess success, IRequest request, IFailure failure, IError error, LoaderStyle style) {
        this.SUCCESS = success;
        this.REQUEST = request;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSucess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }
        stopLoading();

    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.requestEnd();
        }
        stopLoading();
    }


    private void stopLoading(){
        //取消加载，延时1s
        if (LOADER_STYLE != null) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            }, 0);
        }
    }
}
