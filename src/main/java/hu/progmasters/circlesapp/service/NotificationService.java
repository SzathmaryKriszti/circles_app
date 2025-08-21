package hu.progmasters.circlesapp.service;

import hu.progmasters.circlesapp.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificationService {
    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void receiveMessage(Map<String, String> message) {
        System.out.println("Received notification for group " + message.get("groupId"));
        System.out.println("New post by " + message.get("author") + ": " + message.get("content"));
        // Itt lehetne email, push notification, stb.
    }
}
