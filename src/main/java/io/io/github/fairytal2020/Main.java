

package io.io.github.fairytal2020;

import com.google.gson.Gson;


import javax.swing.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

public class Main implements MailEventListener<MailJoinInApply>{
    MainFrom from;
    public static MailReader<MailJoinInApply> reader;
    public static void main(String[] args) throws Exception {
        MailUtils mail = new MailUtils("outlook.live.com" , "fairytal2020@outlook.com" , "fairytalbzfx2020");
//        mail.send("Hello World" , new String[]{"wangshengkai2007@163.com" } , new String[]{} , "content");
//        mail.read();
        /*MailSubject sub = new MailSubject("join in apply" , "7cc50110-e4ed-4c8c-b08c-4cd045a062f8");
        HashMap<String , String> map = new HashMap<>();
        map.put("id" , "7cc50110-e4ed-4c8c-b08c-4cd045a062f8");
        map.put("name" , "wsk");
        map.put("skill" , "pr ps au");
        map.put("contact" , "my phone:12312312313 my email:fakeemail@123.com");
        map.put("other" , "no");
        MailJoinInApply con = new MailJoinInApply(sub , map);
        Gson g = new Gson();
        String json = g.toJson(con);
        System.out.println(json);
        json = g.toJson(sub);
        System.out.println(json);*/
        /*String conJson = "{\"id\":\"7cc50110-e4ed-4c8c-b08c-4cd045a062f8\",\"subject\":{\"subject\":\"join in apply\",\"id\":\"7cc50110-e4ed-4c8c-b08c-4cd045a062f8\"},\"content\":{\"other\":\"no\",\"skill\":\"pr ps au\",\"contact\":\"my phone:12312312313 my email:fakeemail@123.com\",\"name\":\"wsk\",\"id\":\"7cc50110-e4ed-4c8c-b08c-4cd045a062f8\"}}";
        String subJson = "{\"subject\":\"join in apply\",\"id\":\"7cc50110-e4ed-4c8c-b08c-4cd045a062f8\"}";
        Gson gson = new Gson();
        MailJoinInApply join = gson.fromJson(conJson , MailJoinInApply.class);
        String j = gson.toJson(join);
        System.out.println(j);*/
        //from = new MainFrom();
        //new Main().foo();
        new Main().go();
    }



    public void go(){
        Runnable run = () -> {
            from = new MainFrom();
            from.setVisible(true);
        };
        Thread t = new Thread(run);
        t.start();
        reader = new MailReader<>("join in apply" , "7cc50110-e4ed-4c8c-b08c-4cd045a062f8" , "outlook.live.com" , "fairytal2020@outlook.com" , "fairytalbzfx2020" , MailJoinInApply.class);
        reader.addListener(this);
        reader.startReading(5);

    }

    public void foo(){
        from = new MainFrom();
        from.setVisible(true);
    }

    @Override
    public void newListOfEmailArrived(Collection<MailJoinInApply> mailList) {
        JList list = from.applyList;
        Vector<String> ve = new Vector<>();
        for(MailJoinInApply mail : mailList){
            StringBuilder sb = new StringBuilder();
            sb.append(mail.getContent("name"));
            ve.add(sb.toString());
        }
        list.setListData(ve);
        from.applyList = list;
    }
}
