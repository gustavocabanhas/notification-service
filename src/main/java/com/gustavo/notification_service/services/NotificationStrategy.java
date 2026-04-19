package com.gustavo.notification_service.services;

import com.gustavo.notification_service.models.Notification;

public interface NotificationStrategy {
    void send(Notification notification);
}