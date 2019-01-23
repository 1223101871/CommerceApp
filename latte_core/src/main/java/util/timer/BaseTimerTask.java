package util.timer;

import java.util.TimerTask;

/**
 * created by xcy on 2019/1/22
 **/
public class BaseTimerTask extends TimerTask {
    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener iTimerListener) {
        this.mITimerListener = iTimerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}
