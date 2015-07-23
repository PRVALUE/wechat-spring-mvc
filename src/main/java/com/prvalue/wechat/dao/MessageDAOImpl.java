package com.prvalue.wechat.dao;

import com.prvalue.wechat.model.Message;
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
public class MessageDAOImpl implements MessageDAO {
    
    private static final Logger logger = LoggerFactory.getLogger(MessageDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public void addMessage(Message m) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(m);
        logger.info("Message saved successfully, Message Details="+m);
    }

    @Override
    public void updateMessage(Message m) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(m);
        logger.info("Message updated successfully, Message Details="+m);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Message> listMessages() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Message> msgsList = session.createQuery("from Message").list();
        for(Message m : msgsList){
            logger.info("Message List::"+m);
        }
        return msgsList;
    }
}
