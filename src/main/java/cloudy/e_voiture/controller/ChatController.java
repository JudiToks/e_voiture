package cloudy.e_voiture.controller;

import cloudy.e_voiture.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController
{
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    private Message receivePublicMessage(@Payload Message message)
    {
        return message;
    }

//    eto no asina @RequestBody Message message, d iny no inserena anaty base mongo, tsy aiko manao anzany
//    d aveo mila miselect anlee rehetra any fotsn normalement d azo ny historique message
    @MessageMapping("/private-message")
    private Message receivePrivateMessage(@Payload Message message)
    {
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
        return message;
    }
}
