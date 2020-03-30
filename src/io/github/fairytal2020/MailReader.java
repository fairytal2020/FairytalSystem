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

import com.google.gson.Gson;

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

    public void startReading(int sleep){
        sleep = sleep * 1000;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Collection<T> mailList = new ArrayList<T>();
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

}
