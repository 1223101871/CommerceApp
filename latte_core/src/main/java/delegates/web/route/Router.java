package delegates.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import delegates.LatteDelegate;
import delegates.web.WebDelegate;
import delegates.web.WebDelegateImpl;

/**
 * created by xcy on 2019/1/27
 **/
public class Router {
    private Router(){

    }
    private static class Holder{
        private static final Router INSTANCE = new Router();
    }
    public static Router getInstance(){
        return Holder.INSTANCE;
    }

    public final boolean handleWebUrl(WebDelegate delegate,String url){
        //如果是电话协议
        if (url.contains("tel:")){
            callPhone(delegate.getContext(),url);
            return true;
        }

        final LatteDelegate topDelegate = delegate.getTopDelegate();

        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
        topDelegate.getSupportDelegate().start(webDelegate);

        return true;
    }

    private void loadWebPage(WebView webView,String url){
        if (webView!=null){
            webView.loadUrl(url);
        }else {
            throw new NullPointerException("WebView is null");
        }
    }

    private void loadLocalPage(WebView webView,String url){
        loadWebPage(webView,"file:///android_asset/"+url);
    }

    private void loadPage(WebView webView,String url){
       if (URLUtil.isNetworkUrl(url)||URLUtil.isAssetUrl(url)){
           loadWebPage(webView,url);
       }else {
           loadLocalPage(webView,url);
       }
    }

    public final void loadPage(WebDelegate delegate,String url){
        loadPage(delegate.getWebView(),url);
    }


    private void callPhone(Context context,String uri){
        //用户选择是否拨打
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context,intent,null);

    }
}
