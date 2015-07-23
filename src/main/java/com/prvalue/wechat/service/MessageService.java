package com.prvalue.wechat.service;

import com.prvalue.wechat.model.Message;
import java.util.List;

/**
 *
 * @author Heisaman
 */
public interface MessageService {
    public void addMessage(Message m);
    public void updateMessage(Message m);
    public List<Message> listMessages();
}
