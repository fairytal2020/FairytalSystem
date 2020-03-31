/*
 *
 *     FairytalSystem
 *     A tool for Fairytal team
 *     Copyright (c) 2020 wsk
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
 *     版权所有（C）2020 wsk
 *     本程序为自由软件，在自由软件联盟发布的GNU通用公共许可协议的约束下，你可以对其进行再发布及修改。协议版本为第三版或（随你）更新的版本。
 *     我们希望发布的这款程序有用，但不保证，甚至不保证它有经济价值和适合特定用途。详情参见GNU通用公共许可协议。
 *     你理当已收到一份GNU通用公共许可协议的副本，如果没有，请查阅<http://www.gnu.org/licenses/>
 *
 *     联系方式： fairytal2020@outlook.com
 */

package io.github.fairytal2020;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

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
        Runnable run = new Runnable() {

            @Override
            public void run() {
                from = new MainFrom();
                from.setVisible(true);
            }
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
