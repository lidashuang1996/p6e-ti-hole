package club.p6e.ti.hole.leader.websocket;

import club.p6e.ti.hole.leader.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * MyWebSocketClientHandler
 * @author lidashuang
 * @version 1.0
 */
public class MyWebSocketClientHandler implements WebSocketHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyWebSocketClientHandler.class);

    private final MyWebSocketSessionCache cache = new MyWebSocketSessionCache();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        LOGGER.info("[ CLIENT: " + session.getId() + " ] connect.");
        cache.put(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
        LOGGER.info("[ CLIENT: " + session.getId() + " ] message. ==> len: " + message.getPayloadLength());
        if (message instanceof TextMessage) {
            try {
                final TextMessage textMessage = (TextMessage) message;
                final String messageContent = textMessage.getPayload();
                final Map<String, String> messageMap = JsonUtil.fromJsonToMap(messageContent, String.class, String.class);
                System.out.println(messageMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        LOGGER.info("[ CLIENT: " + session.getId() + " ] error. ==> " + exception.getMessage());
        exception.printStackTrace();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        LOGGER.info("[ CLIENT: " + session.getId() + " ] close.");
        cache.del(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
