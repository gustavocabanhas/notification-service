package com.gustavo.notification_service.repositories;

import com.gustavo.notification_service.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // Isso vai buscar notificações pendentes que já deveriam ter sido enviadas
    List<Notification> findByStatusAndScheduleDateBefore(String status, LocalDateTime dateTime);
}