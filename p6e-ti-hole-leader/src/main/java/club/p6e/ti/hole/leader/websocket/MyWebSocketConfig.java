package club.p6e.ti.hole.leader.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * MyWebSocketConfig
 * @author lidashuang
 * @version 1.0
 */
@Configuration
@EnableWebSocket
public class MyWebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 客户端的注册
        registry
                .addHandler(new MyWebSocketClientHandler(), "/client/ws")
                .addInterceptors(new MyWebSocketInterceptor());
        // 服务端的注册
        registry
                .addHandler(new MyWebSocketServiceHandler(), "/service/ws")
                .addInterceptors(new MyWebSocketInterceptor());
    }

}
