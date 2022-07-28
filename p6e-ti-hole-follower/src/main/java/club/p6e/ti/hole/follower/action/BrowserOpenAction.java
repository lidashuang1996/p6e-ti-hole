package club.p6e.ti.hole.follower.action;

import club.p6e.ti.hole.follower.TiHoleFollowerOpenAction;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public class BrowserOpenAction extends TiHoleFollowerOpenAction {

    /** 权重 */
    private static final int ORDER = 0;

    /** 名称 */
    private static final String NAME = "BROWSER_OPEN_ACTION";


    @Override
    public String name() {
        return NAME;
    }

    @Override
    public void execute(Map<String, Object> global) {
        // 首先要注册系统属性，如果是firefox浏览器，需要设置webdriver.gecko.driver（注意不是webdriver.firefox.driver）
        // 再指定驱动放置的路径。
        System.setProperty("webdriver.chrome.driver","/Users/admin/Documents/2022/ti/chromedriver");
//        System.setProperty("webdriver.chrome.driver","D:\\CodeResources\\club_bar\\ti\\chromedriver.exe");
        //加一些设置
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        //创建WebDriver对象
        ChromeDriver driver = new ChromeDriver(options);
        Map<String, Object> command = new HashMap<>();
        command.put("source", "Object.defineProperty(navigator, 'webdriver', {get:() => false})");
        driver.executeCdpCommand("Page.addScriptToEvaluateOnNewDocument", command);
        var navigation = driver.navigate();
        global.put("CHROME_DRIVER", driver);
    }

    @Override
    public void callback() {

    }

    @Override
    public int order() {
        return 0;
    }

}
