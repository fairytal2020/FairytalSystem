package io.github.fairytal2020;

public class Main {
    public static void main(String[] args) throws Exception {
        MailUtils mail = new MailUtils("outlook.live.com" , "fairytal2020@outlook.com" , "fairytalbzfx2020");
        mail.send("Hello World" , new String[]{"wangshengkai2007@163.com" } , new String[]{""} , "content");
    }
}
