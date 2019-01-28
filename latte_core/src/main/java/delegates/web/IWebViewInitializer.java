package delegates.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * created by xcy on 2019/1/27
 **/
public interface IWebViewInitializer {

    WebView initWebView(WebView webView);

    //针对浏览器本身行为的一个控制
    WebViewClient initWebViewClient();

    //针对内部页面的一个控制
    WebChromeClient initWebChromeClient();
}
