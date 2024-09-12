package com.email.send.service;

import com.email.send.dto.MailDTO;
import com.email.send.utils.Template;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class MailSerivce {

    private final JavaMailSender mailSender;

    private final Template template;

    private final String SUBJECT = "Aviso de entrega fuera de horario";

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean smtp_auth;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.protocol}")
    private String protocolo;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private boolean enable;

    public MailSerivce(JavaMailSender javaMailSender) {
        this.mailSender = javaMailSender;
        this.template = new Template();
    }

    public void sendEmail(MailDTO mailDTO) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            message.setSubject(SUBJECT);
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
            mimeMessageHelper.setTo(mailDTO.getTo());

            mimeMessageHelper.setText(template.getTemplateOrderDelay(mailDTO.getOrderId(), mailDTO.getDateStart()
                    , mailDTO.getDateFinish(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toString()), true);
            mimeMessageHelper.setFrom(sender);

            mailSender.send(message);

        } catch (MessagingException e) {
            System.out.println(e);
        } catch (MailException e) {
            System.out.println(e);
        }
    }
}
