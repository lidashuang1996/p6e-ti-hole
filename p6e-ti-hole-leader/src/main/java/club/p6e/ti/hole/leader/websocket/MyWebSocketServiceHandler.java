package club.p6e.ti.hole.leader.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;

import java.nio.ByteBuffer;

/**
 * MyWebSocketServiceHandler
 * @author lidashuang
 * @version 1.0
 */
public class MyWebSocketServiceHandler implements WebSocketHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyWebSocketServiceHandler.class);

    private final MyWebSocketSessionCache cache = new MyWebSocketSessionCache();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        LOGGER.info("[ SERVER: " + session.getId() + " ] connect.");
        cache.put(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
        LOGGER.info("[ SERVER: " + session.getId() + " ] message. ==> len: " + message.getPayloadLength());
        if (message instanceof BinaryMessage) {
            final BinaryMessage binaryMessage = (BinaryMessage) message;
            final ByteBuffer byteBuffer = binaryMessage.getPayload();
            byteBuffer.clear();
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        LOGGER.info("[ SERVER: " + session.getId() + " ] error. ==> " + exception.getMessage());
        exception.printStackTrace();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        LOGGER.info("[ SERVER: " + session.getId() + " ] close.");
        cache.del(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}
