package com.kypocha.smtp.mailer.sender;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component("smtp")
@Qualifier("smtpMail")
public class SmtpMailSender implements MailSender{
    private static final Log Log = LogFactory.getLog(MockMailSender.class);

    private final JavaMailSender javaMailSender;

    public SmtpMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void send(String to, String subject, String body) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject(subject);
        helper.setTo(to);
        helper.setText(body, true);

        javaMailSender.send(message);

        Log.info("Sending MOCK mail to " + to);
        Log.info("with subject " + subject);
        Log.info("and body: " + body);
    }
}
