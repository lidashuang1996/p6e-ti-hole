package club.p6e.ti.hole.follower.a;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * 账号786863666@qq.com
 * 密码 A139239a
 * @author lidashuang
 * @version 1.0
 */
public class Test2 {

    public static void main(String[] args) {
        // 首先要注册系统属性，如果是firefox浏览器，需要设置webdriver.gecko.driver（注意不是webdriver.firefox.driver）
        // 再指定驱动放置的路径。
        System.setProperty("webdriver.chrome.driver","/Users/admin/Documents/2022/ti/chromedriver");
        //加一些设置
        ChromeOptions options = new ChromeOptions();
//        //谷歌的一个限制 要关闭掉
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
//        //控制不弹出浏览器
        options.setHeadless(true);
        //创建WebDriver对象
        WebDriver driver = new ChromeDriver();
        //输入指定的url地址
        //driver.get("http://www.baidu.com/");
        //控制浏览器窗口
        //driver.manage().window().
        //获取一个导航窗口
        var navigation = driver.navigate();
        //加载到指定url
        navigation.to("https://login.ti.com/as/authorization.oauth2?response_type=code&scope=openid%20email%20profile&client_id=DCIT_ALL_WWW-PROD&state=yhLzN4W1bXWyfoIj_nSLiwqtiMY&redirect_uri=https%3A%2F%2Fwww.ti.com.cn%2Foidc%2Fredirect_uri%2F&nonce=WR-Bbejb5IgMYUl92t188Qvt4ne6wmpYNysNxP2bbFQ&response_mode=form_post");

        //id选择器获取指定元素，清空；需要看页面的自定义的id元素
//        driver.findElement(By.id("user_name")).clear();
        //模拟填用户名
        driver.findElement(By.id("username")).sendKeys("786863666@qq.com");
        driver.findElement(By.id("nextbutton")).click();

        //id选择器获取指定元素，清空
//        driver.findElement(By.id("password")).clear();
        //模拟填密码
        driver.findElement(By.id("password")).sendKeys("A139239a");
        //模拟点击登录按钮
        driver.findElement(By.id("loginbutton")).click();

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
