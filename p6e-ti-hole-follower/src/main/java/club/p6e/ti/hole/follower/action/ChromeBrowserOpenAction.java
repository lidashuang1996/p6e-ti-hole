package club.p6e.ti.hole.follower.action;

import club.p6e.ti.hole.follower.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.ApplicationContext;

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

    /** 驱动路径 */
    private final String webDriverPath;
    /** Spring 上下文对象 */
    private final ApplicationContext applicationContext;

    /**
     * 构造方法初始化
     * @param path 驱动路径
     */
    public ChromeBrowserOpenAction(String path, ApplicationContext applicationContext) {
        this.webDriverPath = path;
        this.applicationContext = applicationContext;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public void execute(Metadata metadata) {
        ChromeDriver driver = null;
        try {
            // 设置参数数据
            System.setProperty(WEB_DRIVER_NAME, webDriverPath);
            // 谷歌浏览器的配置参数
            final ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("excludeSwitches", new String[]{ "enable-automation" });
            options.setExperimentalOption("useAutomationExtension", false);
            // 浏览器打开的时候执行的 CMD 命令
            final Map<String, Object> command = new HashMap<>(1);
            command.put("source", "Object.defineProperty(navigator, 'webdriver', { get:() => false })");
            // 构建浏览器对象
            driver = new ChromeDriver(options);
            // CDP -> 执行 CMD 命令
            driver.executeCdpCommand("Page.addScriptToEvaluateOnNewDocument", command);
            // 数据缓存到元数据对象中
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
