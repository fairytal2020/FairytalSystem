package io.github.fairytal2020;

public class MailSubject {
    private String subject;

    public MailSubject(String subject, String id) {
        this.subject = subject;
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

}
