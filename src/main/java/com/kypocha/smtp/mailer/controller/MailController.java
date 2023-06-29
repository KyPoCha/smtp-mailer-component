package com.kypocha.smtp.mailer.controller;

import com.kypocha.smtp.mailer.model.MailRequest;
import com.kypocha.smtp.mailer.sender.MailSender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class MailController {

    private final MailSender mailSender;

    public MailController(@Qualifier("smtpMail") MailSender smtp) {
        this.mailSender = smtp;
    }

    @RequestMapping("/mail")
    public String mail(@RequestBody MailRequest mailRequest) throws MessagingException {
        mailSender.send(mailRequest.getRecipient(), mailRequest.getSubject(), mailRequest.getBodyText());
        return "Mail Sent";
    }
}
