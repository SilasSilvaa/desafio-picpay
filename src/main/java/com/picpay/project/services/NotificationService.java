package com.picpay.project.services;

import com.picpay.project.domain.user.User;
import com.picpay.project.dtos.NotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NotificationService {

    @Value("${api.notify.url}")
    private String url;
    private final RestTemplate restTemplate;


    public void sendNotification(User user, String message) throws Exception{
        String email = user.getEmail();
        NotificationDTO notification = new NotificationDTO(email, message);

        ResponseEntity<String> notificationResponse = restTemplate.postForEntity(url, notification, String.class);

        System.out.println("Notificação enviada");

        if(!(notificationResponse.getStatusCode() == HttpStatus.OK)){
            throw new Exception("Notification service is down");
        }
    }
}
