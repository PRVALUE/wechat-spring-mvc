package com.prvalue.wechat.service;

import com.prvalue.wechat.dao.MessageDAO;
import com.prvalue.wechat.model.Message;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Heisaman
 */
@Service
public class MessageServiceImpl implements MessageService {
    private MessageDAO messageDAO;

    public void setMessageDAO(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    @Override
    @Transactional
    public void addMessage(Message m) {
        this.messageDAO.addMessage(m);
    }

    @Override
    @Transactional
    public void updateMessage(Message m) {
        this.messageDAO.updateMessage(m);
    }

    @Override
    @Transactional
    public List<Message> listMessages() {
        return this.messageDAO.listMessages();
    }
}
