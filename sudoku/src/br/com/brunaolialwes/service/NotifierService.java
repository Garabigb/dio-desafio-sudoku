package br.com.brunaolialwes.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.brunaolialwes.service.EventEnum.CLEAR_SPACE;

public class NotifierService {

    private Map<EventEnum, List<EventListener>> listener = new HashMap<>(){{
        put(CLEAR_SPACE, new ArrayList<>());
    }};

    public void subscriber(final EventEnum eventType, EventListener listeners){
        var selectedListeners = listener.get(eventType);
        selectedListeners.add(listeners);
    }

    public void notify(final EventEnum eventType){
        listener.get(eventType).forEach(l->l.update(eventType));
    }
}
