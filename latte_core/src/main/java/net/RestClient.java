package net;

import android.content.Context;

import net.callback.IError;
import net.callback.IFailure;
import net.callback.IRequest;
import net.callback.ISuccess;
import net.callback.RequsetCallbacks;

import java.util.Map;
import java.util.WeakHashMap;


import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import ui.LatteLoader;
import ui.LoaderStyle;

/**
 * created by xcy on 2019/1/20
 **/
public class RestClient {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final ISuccess SUCCESS;
    private final IRequest REQUEST;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;


    public RestClient(String url, Map<String, Object> params,
                      ISuccess success, IRequest request,
                      IFailure failure, IError error,
                      RequestBody body,Context context,LoaderStyle loaderStyle) {
        this.URL = url;
        this.PARAMS.putAll(params);
        this.SUCCESS = success;
        this.REQUEST = request;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.requestStart();
        }
        if (LOADER_STYLE != null){
            LatteLoader.showLoading(CONTEXT,LOADER_STYLE);
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequsetCallbacks(
                SUCCESS, REQUEST, FAILURE, ERROR,LOADER_STYLE);
    }

    public final void get(){
        request(HttpMethod.GET);
    }
    public final void post(){
        request(HttpMethod.POST);
    }
    public final void put(){
        request(HttpMethod.PUT);
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }
}
