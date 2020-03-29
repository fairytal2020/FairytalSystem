package io.github.fairytal2020;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class MailJoinInApply extends MailContent {

    public MailJoinInApply(@NotNull MailSubject subject, @NotNull HashMap<String, String> content) throws FairytalSystemException {
        super(subject, content);
    }

    @Override
    public void verifyContent() throws FairytalSystemException {

    }

    @Override
    public String getContent(String key) {
        return null;
    }
}
