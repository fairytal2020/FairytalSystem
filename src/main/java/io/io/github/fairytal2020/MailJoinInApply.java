

package io.io.github.fairytal2020;




import java.util.ArrayList;
import java.util.HashMap;

public class MailJoinInApply extends MailContent {

    public MailJoinInApply( MailSubject subject,  HashMap<String, String> content) throws FairytalSystemException {
        super(subject, content);
        if(!this.getId().equals("7cc50110-e4ed-4c8c-b08c-4cd045a062f8")){
            throw new FairytalSystemException("This is not a join in apply");
        }
    }

    @Override
    public void verifyContent() throws FairytalSystemException {
        ArrayList<String> list = new ArrayList<>();
        list.add(getContent().get("id"));
        list.add(getContent().get("name"));
        list.add(getContent().get("skill"));
        list.add(getContent().get("contact"));
        list.add(getContent().get("other"));
        for (String s : list) {
            if(s == null){
                throw new FairytalSystemException("Content verify failed");
            }
        }
    }

    @Override
    public String getContent(String key) {
        return this.getContent().get(key);
    }


}
