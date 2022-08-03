package club.p6e.ti.hole.follower.message;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

/**
 * 消息总线 WebSocket 的实现
 * @author lidashuang
 * @version 1.0
 */
@Component
@ConditionalOnMissingBean(
        value = MessageBus.class,
        ignored = MessageBusWebsocket.class
)
public class MessageBusWebsocket implements MessageBus {

    static {
        // CHUSHIHUALIANJIE
    }

    public MessageBusWebsocket() {

    }

    @Override
    public void execute(String type, String content) {

    }

}
