package club.p6e.ti.hole.follower;

/**
 * @author lidashuang
 * @version 1.0
 */
public final class TiHoleFollowerCore {

    /**
     * 打开时候的执行方法
     */
    public static void open() {
        TiHoleFollowerOpenAction.allExecution();
    }

    /**
     * 关闭时候的执行方法
     */
    public static void close() {
        TiHoleFollowerCloseAction.allExecution();
    }

    /**
     * 指令方法
     * @param name 指令名称
     */
    public static void instruct(String name) {
        TiHoleFollowerAction.instruct(name);
    }

}
