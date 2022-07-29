package club.p6e.ti.hole.follower.a;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public class Test3 {

    public static void main(String[] args) {

        // 首先要注册系统属性，如果是firefox浏览器，需要设置webdriver.gecko.driver（注意不是webdriver.firefox.driver）
        // 再指定驱动放置的路径。
        System.setProperty("webdriver.chrome.driver","/Users/admin/Documents/2022/ti/chromedriver");
//        System.setProperty("webdriver.chrome.driver","D:\\CodeResources\\club_bar\\ti\\chromedriver.exe");
        //加一些设置
        ChromeOptions options = new ChromeOptions();
//        //谷歌的一个限制 要关闭掉
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
//        options.setPageLoadStrategy(PageLoadStrategy.NONE);
//        options.setPageLoadTimeout(Duration.ofMillis(10));

//        //控制不弹出浏览器
//        options.setHeadless(true);
        //创建WebDriver对象
        ChromeDriver driver = new ChromeDriver(options);
        Map<String, Object> command = new HashMap<>();
        command.put("source", "Object.defineProperty(navigator, 'webdriver', {get:() => false})");
        driver.executeCdpCommand("Page.addScriptToEvaluateOnNewDocument", command);


        //输入指定的url地址
        //driver.get("http://www.baidu.com/");
        //控制浏览器窗口
        //driver.manage().window().
        //获取一个导航窗口
        var navigation = driver.navigate();

        //加载到指定url
        navigation.to("https://www.ti.com/secure-link-forward/?gotoUrl=https%3A%2F%2Fwww.ti.com%2F");


        driver.findElement(By.id("username")).sendKeys("786863666@qq.com");
        driver.findElement(By.id("nextbutton")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("password")).findElement(By.name("password")).sendKeys("A139239a");
        driver.findElement(By.id("loginbutton")).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        navigation.to("https://www.ti.com/product/cn/LM48560/part-details/LM48560TL/NOPB");


        for (int i = 0; i < 100; i++) {
            try {
                System.out.println(
                        driver.findElements(By.className("ti_ocb-atc"))
                );

                System.out.println("------------");
                for (WebElement element : driver.findElements(By.className("ti_ocb-atc"))) {
                    System.out.println(
                            element.findElement(By.className("ti_p-layout-space-small")).getAttribute("style")
                    );
                }
                System.out.println("------------");
//                System.out.println(
//                        driver.findElement(By.className("ti_ocb-main")).findElement(By.className("u-hide-only-on-phone"))
//                );
//                System.out.println(
//                        driver.findElement(By.cssSelector("div#atc-in-stock-LM48560TL/NOPB .ti-button-compact"))
//                );
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
            Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


//        driver.findElement(By.id("atc-in-stock-LM48560TL/NOPB")).findElement(By.className("ti-form-element")).findElement(By.tagName("input")).sendKeys("50");
//        driver.findElement(By.id("atc-in-stock-LM48560TL/NOPB")).findElement(By.className("ti-button-compact")).click();

    }

    // https://www.ti.com/product/cn/LM48560/part-details/LM48560TL/NOPB

}
