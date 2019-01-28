package delegates.web.client;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import delegates.web.WebDelegate;
import delegates.web.route.Router;
import util.log.LatteLogger;

/**
 * created by xcy on 2019/1/27
 **/
public class WebViewClientImpl extends WebViewClient {
    private final WebDelegate DELAGATE ;

    public WebViewClientImpl(WebDelegate delagate) {
        DELAGATE = delagate;
    }

//    @Override
//    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//        return super.shouldOverrideUrlLoading(view, request);
//
//    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverrideUrlLoading",url);
        return Router.getInstance().handleWebUrl(DELAGATE,url);
    }
}
