

package io.io.github.fairytal2020;





import java.util.HashMap;

public abstract class MailContent {
    private String id;
    private MailSubject subject;
    private HashMap<String , String> content;

    public MailContent( MailSubject subject , HashMap<String , String> content) throws FairytalSystemException{
        this.subject = subject;
        this.id = content.get("id");
        if(!this.id.equals(subject.getId())){
            throw new FairytalSystemException("Mail ID verify failed");
        }
        this.verifyContent();
        this.content = content;
    }

    public MailContent(){

    }

    public String getId() {
        return id;
    }

    public MailSubject getSubject() {
        return subject;
    }

    public HashMap<String, String> getContent() {
        return content;
    }

    public void verify() throws FairytalSystemException {
        if(!this.id.equals(subject.getId())){
            throw new FairytalSystemException("Mail ID verify failed");
        }
        this.verifyContent();
    }

    public abstract void verifyContent() throws FairytalSystemException;

    public abstract String getContent( String key);

}
