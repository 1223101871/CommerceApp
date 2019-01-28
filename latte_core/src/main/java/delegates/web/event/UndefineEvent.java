package delegates.web.event;

import util.log.LatteLogger;

/**
 * created by xcy on 2019/1/27
 **/
public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefindEvent",params);
        return null;
    }
}
