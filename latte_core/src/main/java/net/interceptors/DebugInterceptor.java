package net.interceptors;

import android.support.annotation.RawRes;
import android.widget.RemoteViews;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;
import util.file.FileUtil;

/**
 * created by xcy on 2019/1/22
 **/
public class DebugInterceptor extends BaseInterceptor {
    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String debugUrl, int rawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_ID = rawId;
    }

    private Response getResponse(Chain chain, String json) {
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response debugResponse(Chain chain, @RawRes int rawId) {
        final String json = FileUtil.getRawFile(rawId);
        return getResponse(chain, json);
    }

    @Override
    public Response intercept(Chain chain) {
        final String url = chain.request().url().toString();
        Response response = null;
        if (url.contains(DEBUG_URL)) {
            return debugResponse(chain, DEBUG_RAW_ID);
        }
        try {
            response = chain.proceed(chain.request());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
