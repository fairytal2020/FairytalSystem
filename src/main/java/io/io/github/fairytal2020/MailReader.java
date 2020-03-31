

package io.io.github.fairytal2020;

import com.google.gson.Gson;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.enumeration.property.BodyType;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.property.complex.MessageBody;

import java.util.*;

public class MailReader<T extends MailContent> {
    private String subject;
    private String id;
    private ArrayList<MailEventListener<T>> listenerList = new ArrayList<>();
    private String mailServer;
    private String user;
    private String password;
    private MailUtils mailUtils;
    private Class<T> tClass;
    private ArrayList<T> mailReadied = new ArrayList<>();
    private ArrayList<String> senders = new ArrayList<>();
    public MailReader(String subject, String id, String mailServer, String user, String password , Class<T> tClass) {
        this.subject = subject;
        this.id = id;
        this.mailServer = mailServer;
        this.user = user;
        this.password = password;
        mailUtils = new MailUtils(mailServer , user , password);
        this.tClass = tClass;
    }

    public String getSubject() {
        return subject;
    }

    public String getId() {
        return id;
    }

    public ArrayList<MailEventListener<T>> getListenerList() {
        return listenerList;
    }

    public String getMailServer() {
        return mailServer;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void addListener(MailEventListener<T> listener){
        listenerList.add(listener);
    }

    public boolean removeListener(MailEventListener<T> listener){
        return listenerList.remove(listener);
    }

    public ArrayList<T> getMailReadied() {
        return mailReadied;
    }

    public ArrayList<String> getSenders() {
        return senders;
    }

    public void startReading(int sleep){
        sleep = sleep * 1000;
        Timer timer = new Timer();
        HashMap<ArrayList<String> , ArrayList<String>> map = this.readMail();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Collection<T> mailList = new ArrayList<T>();


                Set<ArrayList<String>> key_tmp = map.keySet();
                Collection<ArrayList<String>> val_tmp = map.values();
                Iterator<ArrayList<String>> it = key_tmp.iterator();
                ArrayList<String> sub = it.next();
                it = val_tmp.iterator();
                ArrayList<String> con = it.next();
                for(String str : sub){
                    String json = new MailJsonReader().read(str , "startsubject" , "endsubject");
                    if(json != null){
                        Gson g = new Gson();
                        MailSubject subj = g.fromJson(json , MailSubject.class);
                        if(subject.equals(subj.getSubject()) && id.equals(subj.getId())){
                            String strCon = con.get(sub.indexOf(str));
                            json = new MailJsonReader().read(strCon , "startcontent" , "endcontent");
                            if(json != null){
                                T content = g.fromJson(json , tClass);
                                try {
                                    content.verify();
                                } catch (FairytalSystemException e) {
                                    e.printStackTrace();
                                }
                                mailList.add(content);
                            }
                        }
                    }
                }
                mailReadied.addAll(mailList);
                for(MailEventListener listener : listenerList){
                    listener.newListOfEmailArrived(mailList);
                }

            }
        }, 0, sleep);
    }

    public HashMap<ArrayList<String> , ArrayList<String>> readMail(){
        ArrayList<String> subList = new ArrayList<>();
        ArrayList<String> conList = new ArrayList<>();
        ArrayList<EmailMessage> mails = null;
        try {
            mails = mailUtils.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (EmailMessage message : mails) {


            String sub = null;
            try {
                sub = message.getSubject();
            } catch (ServiceLocalException e) {
                e.printStackTrace();
            }
            MessageBody body = null;
            try {
                body = message.getBody();
            } catch (ServiceLocalException e) {
                e.printStackTrace();
            }
            body.setBodyType(BodyType.HTML);
            String con = HtmlTool.getContent(body.toString());
            subList.add(sub);
            conList.add(con);
            try {
                senders.add(message.getSender().toString());
            } catch (ServiceLocalException e) {
                e.printStackTrace();
            }
        }
        HashMap<ArrayList<String> , ArrayList<String>> map = new HashMap<>();
        map.put(subList , conList);
        return map;
    }

}
