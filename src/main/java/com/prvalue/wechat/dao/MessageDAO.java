package com.prvalue.wechat.dao;

import com.prvalue.wechat.model.Message;
import java.util.List;

/**
 *
 * @author Heisaman
 */
public interface MessageDAO {
    public void addMessage(Message m);
    public void updateMessage(Message m);
    public List<Message> listMessages();
}
