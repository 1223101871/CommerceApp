package example.com.latte_ec.xcy.launcher;

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
import example.com.latte_ec.R;
import example.com.latte_ec.R2;
import util.timer.BaseTimerTask;
import util.timer.ITimerListener;

/**
 * created by xcy on 2019/1/22
 **/
public class LauncherDelegate extends LatteDelegate implements ITimerListener {

    private Timer mTimer = null;
    private int mCount = 5;

    private static final String TAG = "LauncherDelegate";
    @BindView(R2.id.tv_launcher_timer)
     public AppCompatTextView mTvTimer = null;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {

    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
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
                        }
                    }
                }else {
                    Log.d(TAG, "mTvTimer is null");
                }
            }
        });
    }
}
