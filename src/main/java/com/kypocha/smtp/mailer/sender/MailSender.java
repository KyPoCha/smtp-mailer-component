package com.kypocha.smtp.mailer.sender;

import javax.mail.MessagingException;

public interface MailSender {

    void send(String to, String subject, String body) throws MessagingException;
}
