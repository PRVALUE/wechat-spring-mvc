package com.prvalue.wechat.controller;

import com.prvalue.wechat.encryption.AesException;
import com.prvalue.wechat.encryption.WXBizMsgCrypt;
import com.prvalue.wechat.service.CoreService;
import java.io.IOException;
import java.io.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public @ResponseBody void receiveMsg(@RequestParam("msg_signature") String msgSig,
            @RequestParam("timestamp") String timeStamp,
            @RequestParam("nonce") String nonce,
            @RequestBody String body, Writer writer){
        String result = null;
        try {
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(CoreService.sToken, CoreService.sEncodingAESKey, CoreService.sCorpID);
            result = wxcpt.DecryptMsg(msgSig, timeStamp, nonce, body);
            writer.write(result);
            logger.info(result);
        } catch(AesException | IOException ex) {
            logger.error("AesException or IOException found: ", ex);
        }
    }
}
