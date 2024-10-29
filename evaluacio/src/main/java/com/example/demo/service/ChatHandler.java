package com.example.demo.service;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ChatHandler extends TextWebSocketHandler{

private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<WebSocketSession>();
private final ObjectMapper objectMapper = new ObjectMapper();


@Override
public void afterConnectionEstablished(WebSocketSession session) throws Exception {
// TODO Auto-generated method stub
super.afterConnectionEstablished(session);
sessions.add(session);
       
        String welcomeMessage = createWelcomeMessage("pc");
        session.sendMessage(new TextMessage(welcomeMessage));
}

@Override
public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
// TODO Auto-generated method stub
super.afterConnectionClosed(session, status);
sessions.remove(session);
}

@Override
protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
// TODO Auto-generated method stub
super.handleTextMessage(session, message);

for (WebSocketSession webSocketSession : sessions) {
webSocketSession.sendMessage(message);
}
}

private String createWelcomeMessage(String name) throws JsonProcessingException {
   
     
       String lol = "es un canal de comunicacion bidireccional trabaja en el pueerto 80 y 443";
       
       return lol;
   }


}
