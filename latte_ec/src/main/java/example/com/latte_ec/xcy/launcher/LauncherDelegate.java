package example.com.latte_ec.xcy.launcher;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;
import delegates.LatteDelegate;
import example.com.latte_core.app.AccountManager;
import example.com.latte_core.app.IUserChecker;
import example.com.latte_ec.R;
import example.com.latte_ec.R2;
import ui.launcher.ILauncherListener;
import ui.launcher.OnLauncherFinishTag;
import ui.launcher.ScrollLauncherTag;
import util.storage.LattePreference;
import util.timer.BaseTimerTask;
import util.timer.ITimerListener;

/**
 * created by xcy on 2019/1/22
 **/
public class LauncherDelegate extends LatteDelegate implements ITimerListener {
    //倒计时的计时器
    private Timer mTimer = null;
    private int mCount = 5;

    private static final String TAG = "LauncherDelegate";
    @BindView(R2.id.tv_launcher_timer)
     public AppCompatTextView mTvTimer = null;

    private ILauncherListener mILauncherListener = null;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener){
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    //判断是否启动滑动启动页
   private void checkIsShowScroll(){
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            start(new LauncherScrollDelegate(),SINGLETASK);
        }else {
            //检查用户是否登录了APP
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener!=null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener!=null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
   }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }else {
                    Log.d(TAG, "mTvTimer is null");
                }
            }
        });
    }
}
