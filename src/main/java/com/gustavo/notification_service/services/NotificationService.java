package com.gustavo.notification_service.services;

import com.gustavo.notification_service.models.Notification;
import com.gustavo.notification_service.repositories.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository repository;
    private final NotificationStrategy notificationStrategy; // Injetamos a estratégia genérica!

    public NotificationService(NotificationRepository repository, NotificationStrategy notificationStrategy) {
        this.repository = repository;
        this.notificationStrategy = notificationStrategy;
    }

    @Scheduled(fixedDelay = 10000)
    public void checkAndSendNotifications() {
        List<Notification> pending = repository.findByStatusAndScheduleDateBefore("PENDENTE", LocalDateTime.now());

        for (Notification n : pending) {
            try {
                // O robô manda enviar, não importa se é E-mail, SMS ou pombo correio!
                notificationStrategy.send(n);

                n.setStatus("ENVIADO");
                repository.save(n);
            } catch (Exception e) {
                System.out.println("Erro ao tentar enviar a notificação " + n.getId() + ": " + e.getMessage());
                // Se der erro de conexão com o Gmail, mudamos o status para ERRO
                n.setStatus("ERRO");
                repository.save(n);
            }
        }
    }

    public Notification saveNotification(Notification notification) {
        return repository.save(notification);
    }

    public List<Notification> findAllNotifications() {
        return repository.findAll();
    }
}