package delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import delegates.web.chormeclient.WebChromeClientImpl;
import delegates.web.client.WebViewClientImpl;
import delegates.web.route.RouteKeys;
import delegates.web.route.Router;

/**
 * created by xcy on 2019/1/27
 **/
public class WebDelegateImpl extends WebDelegate{

    private static final String TAG = "WebDelegateImpl";
    public static WebDelegateImpl create(String url){
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(),url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if (getUrl()!=null){
            Log.d(TAG, "onBindView: "+"进行跳转");
            //用原生的方式模拟web跳转并进行页面加载
            Router.getInstance().loadPage(this,getUrl());
        }
        Log.d(TAG, "onBindView: "+"出来了");
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
