package com.prvalue.wechat.controller;

import com.prvalue.wechat.encryption.AesException;
import com.prvalue.wechat.encryption.WXBizMsgCrypt;
import com.prvalue.wechat.model.Event;
import com.prvalue.wechat.model.Message;
import com.prvalue.wechat.service.CoreService;
import com.prvalue.wechat.service.EventService;
import com.prvalue.wechat.service.MessageService;
import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Heisaman
 */
@Controller
@RequestMapping("/msg")
public class MsgController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
    private MessageService messageService;
    private EventService eventService;

    @Autowired(required=true)
    @Qualifier(value="messageService")
    public void setMessageService(MessageService ms){
        this.messageService = ms;
    }

    @Autowired(required=true)
    @Qualifier(value="eventService")
    public void setEventService(EventService es){
        this.eventService = es;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody void verifyURL(@RequestParam("msg_signature") String msgSig,
            @RequestParam("timestamp") String timeStamp,
            @RequestParam("nonce") String nonce,
            @RequestParam("echostr") String echoStr, Writer writer) {
        String result = null;
        try {
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(CoreService.sToken, CoreService.sEncodingAESKey, CoreService.sCorpID);
            result = wxcpt.VerifyURL(msgSig, timeStamp, nonce, echoStr);
            writer.write(result);
        } catch(AesException | IOException ex) {
            logger.error("AesException or IOException found: ", ex);
        }
    }

    @RequestMapping(method=RequestMethod.POST)
    public void receiveMsg(@RequestParam("msg_signature") String msgSig,
            @RequestParam("timestamp") String timeStamp,
            @RequestParam("nonce") String nonce,
            @RequestBody String body, Writer writer){
        String result = null;
        try {
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(CoreService.sToken, CoreService.sEncodingAESKey, CoreService.sCorpID);
            result = wxcpt.DecryptMsg(msgSig, timeStamp, nonce, body);
            logger.info(result);

            JAXBContext jc = JAXBContext.newInstance(Message.class);

            Unmarshaller unmarshaller = jc.createUnmarshaller();
            Message msg = (Message) unmarshaller.unmarshal(new StringReader(result));
            logger.info("ThumbMediaId of this msg: "+msg.getThumbMediaId());

            if (msg.getMsgType().equals("event")){
                jc = JAXBContext.newInstance(Event.class);
                unmarshaller = jc.createUnmarshaller();
                Event event = (Event) unmarshaller.unmarshal(new StringReader(result));
                eventService.addEvent(event);
            } else {
                messageService.addMessage(msg);
            }
        } catch(AesException | JAXBException ex) {
            logger.error("AesException or IOException found: ", ex);
        }
    }
}
