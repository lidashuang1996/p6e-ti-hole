package club.p6e.ti.hole.follower.action;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;

/**
 * 谷歌浏览器打开的操作
 * @author lidashuang
 * @version 1.0
 */
public class ChromeBrowserOpenAction implements Action, OpenAction, OrderAction {

    /** 权重 */
    private static final int ORDER = 0;
    /** 类型 */
    private static final String TYPE = "CHROME_BROWSER_OPEN_TYPE";
    /** 名称 */
    private static final String NAME = "CHROME_BROWSER_OPEN_ACTION";
    /** 驱动名称 */
    private static final String WEB_DRIVER_NAME = "webdriver.chrome.driver";

    /** 设备 ID */
    private final String id;
    /** 驱动路径 */
    private final String webDriverPath;

    /**
     * 构造方法初始化
     * @param id 设备 ID
     * @param path 驱动路径
     */
    public ChromeBrowserOpenAction(String id, String path) {
        this.id = id;
        this.webDriverPath = path;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public void execute(MetadataAction metadata) {
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
            metadata.setId(this.id);
            metadata.setDriver(driver);
            metadata.setDriverClass(ChromeDriver.class);
            // 打开默认页面
            driver.navigate().to("http://127.0.0.1:9233");
            // 回调执行脚本
            driver.executeScript(generateXMLHttpRequestScript(metadata.getBaseUrl(),
                    "id", this.id, "type", TYPE, "name", NAME, "result", "SUCCESS", "message", ""));
        } catch (Exception e) {
            e.printStackTrace();
            if (driver != null) {
                // 回调执行脚本
                driver.executeScript(generateXMLHttpRequestScript(metadata.getBaseUrl(),
                        "id", this.id, "type", TYPE, "name", NAME, "result", "SUCCESS", "message", e.getMessage()));
            }
        }
    }

    @Override
    public int order() {
        return ORDER;
    }

}
