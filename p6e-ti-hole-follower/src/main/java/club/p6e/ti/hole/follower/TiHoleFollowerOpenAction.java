package club.p6e.ti.hole.follower;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public abstract class TiHoleFollowerOpenAction extends TiHoleFollowerAction {

    /**
     * 缓存的操作对象
     */
    private static final List<TiHoleFollowerOpenAction> LIST = new ArrayList<>();

    /**
     * 执行全部的需要打开的方法指令
     */
    public static void allExecution() {
        // 1. 根据 ORDER 进行排序
        // 2. 依次执行排序后的对象
        LIST.stream()
                .sorted(Comparator.comparing(TiHoleFollowerOpenAction::order))
                .forEach(TiHoleFollowerAction::execute);
    }

    /**
     * 构造方法初始化
     */
    public TiHoleFollowerOpenAction() {
        LIST.add(this);
    }

    /**
     * 排序
     * @return 排序的序号
     */
    public abstract int order();
}
