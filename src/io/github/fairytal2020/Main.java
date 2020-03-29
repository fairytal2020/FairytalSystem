package io.github.fairytal2020;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Main implements MailEventListener{
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
        new Main().go();
    }

    @Override
    public void newListOfEmailArrived(MailContent mail) {
        System.out.println(mail.getContent("name"));
        System.out.println(mail.getContent("id"));
        System.out.println(mail.getContent("contact"));
        System.out.println(mail.getContent("skill"));
        System.out.println(mail.getContent("other"));
    }

    public void go(){
        MailReader<MailJoinInApply> reader = new MailReader<>("join in apply" , "7cc50110-e4ed-4c8c-b08c-4cd045a062f8" , "outlook.live.com" , "fairytal2020@outlook.com" , "fairytalbzfx2020" , MailJoinInApply.class);
        reader.addListener(this);
        reader.startReading(5);
    }
}
