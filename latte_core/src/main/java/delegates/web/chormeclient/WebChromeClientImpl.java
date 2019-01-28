package delegates.web.chormeclient;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * created by xcy on 2019/1/27
 **/
public class WebChromeClientImpl extends WebChromeClient {

    //拦截Alert并进行自己的处理
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }
}
