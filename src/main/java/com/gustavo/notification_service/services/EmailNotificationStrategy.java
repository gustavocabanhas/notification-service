package com.gustavo.notification_service.services;

import com.gustavo.notification_service.models.Notification;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationStrategy implements NotificationStrategy {

    private final JavaMailSender mailSender;

    public EmailNotificationStrategy(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(Notification notification) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("SEU_EMAIL@gmail.com"); // Coloque o mesmo e-mail do properties
        message.setTo(notification.getRecipient());
        message.setSubject("Nova Notificação do Sistema!");
        message.setText(notification.getMessage());

        mailSender.send(message);
        System.out.println(">>> E-mail disparado de verdade para: " + notification.getRecipient());
    }
}