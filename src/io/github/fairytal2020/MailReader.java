package io.github.fairytal2020;

import com.google.gson.Gson;
import microsoft.exchange.webservices.data.notification.GetEventsResults;

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

    public void startReading(int sleep){
        sleep = sleep * 1000;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                HashMap<ArrayList<String> , ArrayList<String>> map = null;
                try {
                    map = mailUtils.read();
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
                                for(MailEventListener<T> li : listenerList){
                                    li.newListOfEmailArrived(content);
                                }
                            }
                        }
                    }
                }
            }
        }, 0, sleep);
    }

}
