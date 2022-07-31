package club.p6e.ti.hole.follower.action;

import club.p6e.ti.hole.follower.*;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author lidashuang
 * @version 1.0
 */
public class ChromeBrowserCloseAction implements Action, CloseAction, Order {

    /** 权重 */
    private static final int ORDER = 0;
    /** 名称 */
    private static final String NAME = "CHROME_BROWSER_CLOSE_ACTION";

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public void execute(Metadata metadata) {
        final ChromeDriver chromeDriver = (ChromeDriver) metadata.getDriver();
        chromeDriver.close();
    }

    @Override
    public int order() {
        return ORDER;
    }

}
