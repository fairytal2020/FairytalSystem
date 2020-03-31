

package io.io.github.fairytal2020;

import java.util.Collection;

public interface MailEventListener<T extends MailContent> {
    void newListOfEmailArrived(Collection<T> mailList);
}
