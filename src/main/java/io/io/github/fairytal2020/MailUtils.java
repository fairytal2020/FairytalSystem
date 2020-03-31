

package io.io.github.fairytal2020;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.Map.Entry;



import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.notification.EventType;
import microsoft.exchange.webservices.data.core.enumeration.property.BasePropertySet;
import microsoft.exchange.webservices.data.core.enumeration.property.BodyType;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.schema.ItemSchema;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.notification.GetEventsResults;
import microsoft.exchange.webservices.data.notification.ItemEvent;
import microsoft.exchange.webservices.data.notification.PullSubscription;
import microsoft.exchange.webservices.data.property.complex.FolderId;
import microsoft.exchange.webservices.data.property.complex.MessageBody;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.ItemView;
import org.w3c.dom.Document;


public class MailUtils {

    private String mailServer;
    private String user;
    private String password;

    public MailUtils(String mailServer, String user, String password){
        this.mailServer = mailServer;
        this.user = user;
        this.password = password;
    }

    /**
     * mail
     */
    public void doSend(String subject, String[] to, String[] cc, String bodyText, String[] attachmentPath) throws Exception {
        ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
        ExchangeCredentials credentials = new WebCredentials(user, password);
        service.setCredentials(credentials);
        try {
            service.setUrl(new URI("https://" + mailServer + "/ews/exchange.asmx"));
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
     * mail
     */
    public void send(String subject, String[] to, String[] cc, String bodyText) throws Exception {
        doSend(subject, to, cc, bodyText, null);
    }

    /**
     *
     */
    public ArrayList<EmailMessage> read() throws Exception {
        /*ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
        ExchangeCredentials credentials = new WebCredentials(user, password);
        service.setCredentials(credentials);
        try {
            service.setUrl(new URI("https://" + mailServer + "/ews/exchange.asmx"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        List<FolderId> folder = new ArrayList<FolderId>();
        FolderId folder1 = new FolderId(WellKnownFolderName.Inbox);
        folder.add(folder1);
        final PullSubscription subscription = service.subscribeToPullNotifications(folder, 5, null,
                EventType.NewMail, EventType.Created, EventType.Deleted);

        Integer cacheTime = 1000 * 10;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    GetEventsResults events = subscription.getEvents();

                    for (ItemEvent itemEvent : events.getItemEvents()) {

                        EmailMessage message = EmailMessage.bind(service, itemEvent.getItemId());
                        System.out.println("######## NEW EMAIL SUBJECT IS: " + message.getSubject());
                        System.out.println("######## NEW EMAIL CONTENT IS: " + message.getBody());

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, cacheTime);*/
        ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
        ExchangeCredentials credentials = new WebCredentials(user, password , mailServer);
        service.setCredentials(credentials);
        service.setUrl(new URI("https://" + mailServer + "/ews/exchange.asmx"));
        // Bind to the Inbox.
        Folder inbox = Folder.bind(service , WellKnownFolderName.Inbox);
        System.out.println(inbox.getDisplayName());
        ItemView view = new ItemView(32767);
        ArrayList<EmailMessage> mails = new ArrayList<>();
        FindItemsResults<Item> findResults = service.findItems(inbox.getId(), view);
        ArrayList<String> subList = new ArrayList<>();
        ArrayList<String> conList = new ArrayList<>();
        for (Item item : findResults.getItems()) {
            EmailMessage message = EmailMessage.bind(service, item.getId());
            service.loadPropertiesForItems(findResults, PropertySet.FirstClassProperties);
            /*String sub = message.getSubject();
            MessageBody body = message.getBody();
            body.setBodyType(BodyType.HTML);
            String con = HtmlTool.getContent(body.toString());
            subList.add(sub);
            conList.add(con);*/
            mails.add(message);
        }
        /*HashMap<ArrayList<String> , ArrayList<String>> map = new HashMap<>();
        map.put(subList , conList);*/
        return mails;
    }




}

