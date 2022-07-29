package club.p6e.ti.hole.follower.a;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * https://chromedriver.storage.googleapis.com/index.html
 * @author lidashuang
 * @version 1.0
 */
public class Test {

    public static void main(String[] args) {
        // 首先要注册系统属性，如果是firefox浏览器，需要设置webdriver.gecko.driver（注意不是webdriver.firefox.driver）
        // 再指定驱动放置的路径。
//        System.setProperty("webdriver.chrome.driver","/Users/admin/Documents/2022/ti/chromedriver");
        System.setProperty("webdriver.chrome.driver","D:\\CodeResources\\club_bar\\ti\\chromedriver.exe");
        //加一些设置
        ChromeOptions options = new ChromeOptions();
//        //谷歌的一个限制 要关闭掉
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

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

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("--------STOP");
                System.out.println("[[[[[[[ STOP");
                driver.executeScript("window.stop()");
                System.out.println("--------STOP");
            }
        }.start();

        //加载到指定url
        navigation.to("https://www.ti.com.cn/zh-cn/info/contact-us.html");

        //id选择器获取指定元素，清空；需要看页面的自定义的id元素
//        driver.findElement(By.id("user_name")).clear();
        //模拟填用户名
//        driver.findElement(By.className("ti-login-link")).sendKeys("user");
        //id选择器获取指定元素，清空
//        driver.findElement(By.id("password")).clear();
        //模拟填密码
        // https://www.ti.com.cn/productmodel/gpn/TPS25852-Q1/tistoresegmented

        // https://training.ti.com/apiservice/getvideos/training.json?fm=TPS25852-Q1&result=3&foldertype=product_tree&language=ZH-CN,EN&type=curriculum
//        driver.findElement(By.id("password")).sendKeys("pass");
        //模拟点击登录按钮
        driver.findElement(By.className("ti_p-responsiveHeader-top-llc")).findElement(By.className("mod-login")).click();


        driver.findElement(By.id("username")).sendKeys("786863666@qq.com");
        driver.findElement(By.id("nextbutton")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //id选择器获取指定元素，清空
//        driver.findElement(By.id("password")).clear();
        //模拟填密码
        driver.findElement(By.id("password")).findElement(By.name("password")).sendKeys("A139239a");
        //模拟点击登录按钮
        driver.findElement(By.id("loginbutton")).click();
        // 不停的发请求查询库存

        System.out.println("=========");
        for (int i = 0; i < 20; i++) {
            System.out.println(
                    driver.executeScript("fetch('https://www.ti.com.cn/productmodel/gpn/TPL5010-Q1/tistoresegmented', {\n" +
                            "            method: \"GET\"\n" +
                            "        }).then(response => response.json())\n" +
                            "\t.then(function(myJson) {\n" +
                            "\t\tconsole.log(myJson);\n" +
                            "\t}).catch(t=>console.warn(t.message));")
            );

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("=========");


        // 借助 WS 实现消息的回调 OK ！
        // 现在剩下 加入购物车 以及购买了


//        Thread.sleep(2000);
//        WebElement page_container = driver.findElement(By.id("app_page_container"));
//        //以此判断是否登录成功
//        if (ObjectUtil.isNotNull(page_container)) {
//            navigation.to("http://xxx");
//            System.out.println("success");
//        }
        //测试完成关闭浏览器
        //driver.close();
    }

}
