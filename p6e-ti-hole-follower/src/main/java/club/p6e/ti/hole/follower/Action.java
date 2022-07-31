package club.p6e.ti.hole.follower;

/**
 * 动作执行接口
 * @author lidashuang
 * @version 1.0
 */
public interface Action {

    /**
     * 注册的名称
     * @return 注册的名称
     */
    public String name();

    /**
     * 执行方法
     * @param metadata 执行的元数据
     */
    public void execute(Metadata metadata);

}
