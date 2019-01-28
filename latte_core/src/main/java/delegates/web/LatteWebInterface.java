package delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;


import delegates.web.event.Event;
import delegates.web.event.EventManager;

/**
 * created by xcy on 2019/1/27
 **/
public class LatteWebInterface {
    private final WebDelegate DELEGATE;

    private LatteWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static LatteWebInterface create(WebDelegate delegate){
        return new LatteWebInterface(delegate);
    }

    //安卓4.4之后加入注解web事件才能响应
    @JavascriptInterface
    public String event(String params){
        final String action = JSON.parseObject("action").getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        if (event!=null){
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
