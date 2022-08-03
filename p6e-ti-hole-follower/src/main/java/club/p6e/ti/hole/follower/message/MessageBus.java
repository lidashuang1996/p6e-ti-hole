package club.p6e.ti.hole.follower.message;

/**
 * 消息总线
 * @author lidashuang
 * @version 1.0
 */
public interface MessageBus {

    /**
     * 推送消息给领导者
     * @param type 消息类型
     * @param content 消息内容
     */
    public void execute(String type, String content);

}
