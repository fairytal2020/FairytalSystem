/*
 *
 *     FairytalSystem
 *     A tool for Fairytal team
 *     Copyright (c) 2020 Fairytal team
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 *     Contact me : fairytal2020@outlook.com
 *
 *     FairytalSystem
 *     Fairytal团队工具
 *     版权所有（C）2020 Fairytal团队
 *     本程序为自由软件，在自由软件联盟发布的GNU通用公共许可协议的约束下，你可以对其进行再发布及修改。协议版本为第三版或（随你）更新的版本。
 *     我们希望发布的这款程序有用，但不保证，甚至不保证它有经济价值和适合特定用途。详情参见GNU通用公共许可协议。
 *     你理当已收到一份GNU通用公共许可协议的副本，如果没有，请查阅<http://www.gnu.org/licenses/>
 *
 *     联系方式： fairytal2020@outlook.com
 */

package io.github.fairytal2020;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.Map.Entry;


import com.sun.org.apache.xalan.internal.xsltc.trax.OutputSettings;
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
     * 发送带附件的mail
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
     * 发送不带附件的mail
     */
    public void send(String subject, String[] to, String[] cc, String bodyText) throws Exception {
        doSend(subject, to, cc, bodyText, null);
    }

    /**
     * 读取邮件
     */
    public HashMap<ArrayList<String> , ArrayList<String>> read() throws Exception {
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

        FindItemsResults<Item> findResults = service.findItems(inbox.getId(), view);
        ArrayList<String> subList = new ArrayList<>();
        ArrayList<String> conList = new ArrayList<>();
        for (Item item : findResults.getItems()) {
            EmailMessage message = EmailMessage.bind(service, item.getId());
            service.loadPropertiesForItems(findResults, PropertySet.FirstClassProperties);
            String sub = message.getSubject();
            MessageBody body = message.getBody();
            body.setBodyType(BodyType.HTML);
            String con = HtmlTool.getContent(body.toString());
            subList.add(sub);
            conList.add(con);
        }
        HashMap<ArrayList<String> , ArrayList<String>> map = new HashMap<>();
        map.put(subList , conList);
        return map;
    }




}

