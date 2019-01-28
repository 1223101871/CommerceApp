package delegates.web.event;

import java.util.HashMap;

import io.reactivex.annotations.NonNull;

/**
 * created by xcy on 2019/1/27
 **/
public class EventManager {
    private static final HashMap<String,Event> EVENTS = new HashMap<>();

    private EventManager(){

    }

    private static class Holder{
        private static final EventManager MANAGER = new EventManager();
    }

    public static EventManager getInstance(){
        return Holder.MANAGER;
    }

    public EventManager addEvent(@NonNull String name,@NonNull Event event){
        EVENTS.put(name,event);
        return this;
    }

    public Event createEvent(@NonNull String action){
        final Event event = EVENTS.get(action);
        if (event == null){
            return new UndefineEvent();
        }
        return event;
    }



}
