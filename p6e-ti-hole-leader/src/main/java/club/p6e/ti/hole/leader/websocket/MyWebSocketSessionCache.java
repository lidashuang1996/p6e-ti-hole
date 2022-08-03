package club.p6e.ti.hole.leader.websocket;

import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public class MyWebSocketSessionCache {

    private static class Model {
        private volatile long date;
        private volatile WebSocketSession webSocketSession;

        public Model(WebSocketSession webSocketSession) {
            this.date = 0L;
            this.webSocketSession = webSocketSession;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public WebSocketSession getWebSocketSession() {
            return webSocketSession;
        }

        public void setWebSocketSession(WebSocketSession webSocketSession) {
            this.webSocketSession = webSocketSession;
        }
    }

    private final Map<String, Model> cache = new HashMap<>();

    public Model get(String key) {
        synchronized (cache) {
            return cache.get(key);
        }
    }

    public Model get(WebSocketSession session) {
        return get(session.getId());
    }

    public void put(WebSocketSession session) {
        synchronized (cache) {
            cache.put(session.getId(), new Model(session));
        }
    }

    public void del(String key) {
        synchronized (cache) {
            cache.remove(key);
        }
    }

    public void del(WebSocketSession session) {
        del(session.getId());
    }

}
