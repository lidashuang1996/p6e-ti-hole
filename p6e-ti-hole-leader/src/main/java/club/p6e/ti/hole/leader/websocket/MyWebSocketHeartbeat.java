package club.p6e.ti.hole.leader.websocket;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lidashuang
 * @version 1.0
 */
public class MyWebSocketHeartbeat {

    private static ThreadPoolExecutor THREAD_POOL_EXECUTOR;

    static {
        init();
    }

    @SuppressWarnings("all")
    private static void init() {
        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(1, 1, 0L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new DefaultThreadFactory("MWB_HEARTBEAT"));
        THREAD_POOL_EXECUTOR.execute(() -> {
            while (true) {
                try {
                    final long currentTime = System.currentTimeMillis() / 1000L;
                } catch (Exception var7) {
                    var7.printStackTrace();
                }
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException var5) {
                    var5.printStackTrace();
                }
            }
        });
    }

    protected static ThreadPoolExecutor getThreadPoolExecutor() {
        return THREAD_POOL_EXECUTOR;
    }
}
