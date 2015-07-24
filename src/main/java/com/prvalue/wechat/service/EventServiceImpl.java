package com.prvalue.wechat.service;

import com.prvalue.wechat.dao.EventDAO;
import com.prvalue.wechat.model.Event;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Heisaman
 */
@Service
public class EventServiceImpl implements EventService {
    private EventDAO eventDAO;

    public void setEventDAO(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Override
    @Transactional
    public void addEvent(Event e) {
        this.eventDAO.addEvent(e);
    }

    @Override
    @Transactional
    public List<Event> listEvents() {
        return this.eventDAO.listEvents();
    }
}
