package io.github.fairytal2020;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map.Entry;


import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.BodyType;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.MessageBody;

public class MailUtils {

    private String mailServer = "outlook.live.com";
    private String user = "fairytal2020@outlook.com";
    private String password = "fairytalbzfx2020";

    public MailUtils(String mailServer, String user, String password){
        this.mailServer = mailServer;
        this.user = user;
        this.password = password;
    }

    /**
     * 发送带附件的mail
     */
    public void doSend(String subject, String[] to, String[] cc, String bodyText, String[] attachmentPath) throws Exception {
        ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2007_SP1);
        ExchangeCredentials credentials = new WebCredentials(user, password);
        service.setCredentials(credentials);
        try {
            service.setUrl(new URI(mailServer));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        EmailMessage msg = new EmailMessage(service);
        msg.setSubject(subject);
        MessageBody body = MessageBody.getMessageBodyFromText(bodyText);
        body.setBodyType(BodyType.HTML);
        msg.setBody(body);
        for (String s : to) {
            msg.getToRecipients().add(s);
        }
        if (cc != null) {
            for (String s : cc) {
                msg.getCcRecipients().add(s);
            }
        }
        if (attachmentPath != null && attachmentPath.length > 0) {
            for (int a = 0; a < attachmentPath.length; a++) {
                msg.getAttachments().addFileAttachment(attachmentPath[a]);
            }

        }
        msg.send();
    }

    /**
     * 发送不带附件的mail
     */
    public void send(String subject, String[] to, String[] cc, String bodyText) throws Exception {
        doSend(subject, to, cc, bodyText, null);
    }
}

