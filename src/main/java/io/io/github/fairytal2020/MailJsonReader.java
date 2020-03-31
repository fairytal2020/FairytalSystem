

package io.io.github.fairytal2020;

public class MailJsonReader {
    public String read(String str , String startStr , String endStr){
        int si = str.indexOf(startStr);
        if(si == -1){
            return null;
        }
        si = si + startStr.length();
        int ei = str.indexOf(endStr);
        if(ei == -1){
            return null;
        }
        return str.substring(si , ei);
    }
}
