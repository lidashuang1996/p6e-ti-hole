package club.p6e.ti.hole.follower.message;

import club.p6e.websocket.client.*;
import io.netty.buffer.ByteBuf;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import javax.websocket.WebSocketContainer;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 消息总线 WebSocket 的实现
 * @author lidashuang
 * @version 1.0
 */
@Component
@ConditionalOnMissingBean(
        value = Message.class,
        ignored = MessageWebsocket.class
)
public class MessageWebsocket implements Message {

    private static volatile boolean IS_INIT = false;

    private static synchronized void init() {
        if (!IS_INIT) {
            P6eWebSocketClientApplication.initThreadPool();
            initScheduledExecutorService();
            final Connector connector = P6eWebSocketClientApplication.connector();
            connector.connect(new Config("ws://127.0.0.1:9600/ws"), new P6eWebSocketCallback() {
                @Override
                public void onOpen(P6eWebSocketClient p6eWebSocketClient) {

                }

                @Override
                public void onClose(P6eWebSocketClient p6eWebSocketClient) {

                }

                @Override
                public void onError(P6eWebSocketClient p6eWebSocketClient, Throwable throwable) {

                }

                @Override
                public void onMessageText(P6eWebSocketClient p6eWebSocketClient, ByteBuf byteBuf) {

                }

                @Override
                public void onMessageBinary(P6eWebSocketClient p6eWebSocketClient, ByteBuf byteBuf) {

                }

                @Override
                public void onMessagePong(P6eWebSocketClient p6eWebSocketClient, ByteBuf byteBuf) {

                }

                @Override
                public void onMessagePing(P6eWebSocketClient p6eWebSocketClient, ByteBuf byteBuf) {

                }

                @Override
                public void onMessageContinuation(P6eWebSocketClient p6eWebSocketClient, ByteBuf byteBuf) {

                }
            }, true);
            IS_INIT = true;
        }
    }

    private static void initScheduledExecutorService() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new DefaultThreadFactory("PANT TASK"));
        threadPoolExecutor.execute(() -> {
//            while(true) {
//                try {
//                    long currentTime = System.currentTimeMillis() / 1000L;
//                    Iterator var2 = TASK_LIST.iterator();
//
//
//                    }
//                } catch (Exception var7) {
//                    var7.printStackTrace();
//                }
//
//                try {
//                    Thread.sleep(200L);
//                } catch (InterruptedException var5) {
//                    var5.printStackTrace();
//                }
//            }
        });
    }

    public MessageWebsocket() {
        if (!IS_INIT) {
            init();
        }
    }

    @Override
    public void execute(String type, String content) {

    }

}
