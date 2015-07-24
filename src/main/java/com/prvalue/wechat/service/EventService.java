package com.prvalue.wechat.service;

import com.prvalue.wechat.model.Event;
import java.util.List;

/**
 *
 * @author Heisaman
 */
public interface EventService {
    public void addEvent(Event e);
    public List<Event> listEvents();
}
