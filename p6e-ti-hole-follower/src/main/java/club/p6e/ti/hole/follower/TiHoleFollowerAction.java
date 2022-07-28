package club.p6e.ti.hole.follower;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public abstract class TiHoleFollowerAction {

    /**
     * 缓存的指令-指令方法
     */
    private static final Map<String, TiHoleFollowerAction> MAP = new HashMap<>(16);

    /**
     * 指令执行
     * @param name 指令名称
     */
    public static void instruct(String name) {
        if (MAP.get(name) != null) {
            MAP.get(name).execute();
        }
    }

    /**
     * 构造方法初始化
     */
    public TiHoleFollowerAction() {
        MAP.put(this.name(), this);
    }

    /**
     * 注册的名称
     * @return 注册的名称
     */
    public abstract String name();

    /**
     * 执行方法
     */
    public abstract void execute(Map<String, Object> global);

    /**
     * 回调方法
     */
    public abstract void callback();

}
