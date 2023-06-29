package com.kypocha.smtp.mailer.config;

import com.kypocha.smtp.mailer.sender.MailSender;
import com.kypocha.smtp.mailer.sender.MockMailSender;
import com.kypocha.smtp.mailer.sender.SmtpMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {
    @Bean
    @ConditionalOnProperty(name = "spring.mail.host", havingValue = "foo", matchIfMissing = true)
    public MailSender mockMailSender(){
        return new MockMailSender();
    }

    @Bean
    @ConditionalOnProperty(name = "spring.mail.host")
    public MailSender smtpMailSender(JavaMailSender javaMailSender){
        return new SmtpMailSender(javaMailSender);
    }
}
