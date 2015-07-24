package com.prvalue.wechat.dao;

import com.prvalue.wechat.model.Event;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Heisaman
 */
@Repository
public class EventDAOImpl implements EventDAO {
    private static final Logger logger = LoggerFactory.getLogger(EventDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public void addEvent(Event e) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(e);
        logger.info("Event saved successfully, Event Details="+e);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Event> listEvents() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Event> eventsList = session.createQuery("from EVENT").list();
        for(Event e : eventsList){
            logger.info("Event List::"+e);
        }
        return eventsList;
    }
}
