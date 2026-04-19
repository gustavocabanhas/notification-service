package com.gustavo.notification_service.controllers;

import com.gustavo.notification_service.models.Notification;
import com.gustavo.notification_service.services.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    // O Spring injeta o Service automaticamente aqui
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Endpoint para criar uma notificação e jogar na fila (banco)
    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        notification.setStatus("PENDENTE");

        // Se a data vier vazia, definimos para o momento atual
        if (notification.getScheduleDate() == null) {
            notification.setScheduleDate(java.time.LocalDateTime.now());
        }

        Notification savedNotification = notificationService.saveNotification(notification);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNotification);
    }

    // Endpoint para listar as notificações (bom para testarmos se salvou no banco)
    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.findAllNotifications();
        return ResponseEntity.ok(notifications);
    }
}