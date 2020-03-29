package io.github.fairytal2020;

public interface MailEventListener<T extends MailContent> {
    void newListOfEmailArrived(T mail);
}
