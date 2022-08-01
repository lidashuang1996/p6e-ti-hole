package club.p6e.ti.hole.follower.action;

import club.p6e.ti.hole.follower.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public class ChromeBrowserOpenAction implements Action, OpenAction, Order {

    /** 权重 */
    private static final int ORDER = 0;
    /** 名称 */
    private static final String NAME = "CHROME_BROWSER_OPEN_ACTION";
    /** 驱动名称 */
    private static final String WEB_DRIVER_NAME = "webdriver.chrome.driver";


    /** 消息总线 */
    @Resource
    private MessageBus messageBus;

    /** 驱动路径 */
    private final String webDriverPath;

    /**
     * 构造方法初始化
     * @param path 驱动路径
     */
    public ChromeBrowserOpenAction(String path) {
        this.webDriverPath = path;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public void execute(Metadata metadata) {
        Lds8295722
        ChromeDriver driver = null;
        try {
            System.setProperty(WEB_DRIVER_NAME, webDriverPath);
            final ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("excludeSwitches", new String[]{ "enable-automation" });
            options.setExperimentalOption("useAutomationExtension", false);
            final Map<String, Object> command = new HashMap<>(1);
            command.put("source", "Object.defineProperty(navigator, 'webdriver', { get:() => false })");
            driver = new ChromeDriver(options);
            driver.executeCdpCommand("Page.addScriptToEvaluateOnNewDocument", command);
            metadata.setDriver(driver);
            metadata.setDriverClass(ChromeDriver.class);
        } catch (Exception e) {
            e.printStackTrace();
            if (driver != null) {
                driver.executeScript();
            }
        }
    }

    @Override
    public int order() {
        return ORDER;
    }

}
