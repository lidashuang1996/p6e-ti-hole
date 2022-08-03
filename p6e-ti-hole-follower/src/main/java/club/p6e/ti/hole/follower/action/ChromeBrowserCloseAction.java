package club.p6e.ti.hole.follower.action;

import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 谷歌浏览器关闭的操作
 * @author lidashuang
 * @version 1.0
 */
public class ChromeBrowserCloseAction implements Action, CloseAction, OrderAction {

    /** 权重 */
    private static final int ORDER = 0;
    /** 类型 */
    private static final String TYPE = "CHROME_BROWSER_CLOSE_TYPE";
    /** 名称 */
    private static final String NAME = "CHROME_BROWSER_CLOSE_ACTION";

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public void execute(MetadataAction metadata) {
        ChromeDriver driver = null;
        try {
            driver = (ChromeDriver) metadata.getDriver();
            driver.close();
            // 回调执行脚本
            driver.executeScript(generateXMLHttpRequestScript(metadata.getBaseUrl(),
                    "id", metadata.getId(), "type", TYPE, "name", NAME, "result", "SUCCESS", "message", ""));
        } catch (Exception e) {
            if (driver != null) {
                // 回调执行脚本
                driver.executeScript(generateXMLHttpRequestScript(metadata.getBaseUrl(),
                        "id", metadata.getId(), "type", TYPE, "name", NAME, "result", "SUCCESS", "message", e.getMessage()));
            }
        }
    }

    @Override
    public int order() {
        return ORDER;
    }

}
