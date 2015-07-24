package com.prvalue.wechat.dao;

import com.prvalue.wechat.model.Event;
import java.util.List;

/**
 *
 * @author Heisaman
 */
public interface EventDAO {
    public void addEvent(Event e);
    public List<Event> listEvents();
}
