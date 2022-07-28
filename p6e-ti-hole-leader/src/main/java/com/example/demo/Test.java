package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * @author lidashuang
 * @version 1.0
 */
public class Test {

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
        ChromeDriver driver = new ChromeDriver();
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
        navigation.to("https://www.ti.com.cn/zh-cn/power-management/battery-management/products.html?login-check=true");

        //id选择器获取指定元素，清空；需要看页面的自定义的id元素
//        driver.findElement(By.id("user_name")).clear();
        //模拟填用户名
//        driver.findElement(By.className("ti-login-link")).sendKeys("user");
        //id选择器获取指定元素，清空
//        driver.findElement(By.id("password")).clear();
        //模拟填密码
//        driver.findElement(By.id("password")).sendKeys("pass");
        //模拟点击登录按钮

        // response_type: code
        //scope: openid email profile
        //client_id: DCIT_ALL_WWW-PROD
        //state: VBsXZbqsf26mE5QxcVT588iNO-0
        //redirect_uri: https://www.tij.co.jp/oidc/redirect_uri/
        //nonce: bckb8Mb-4NSya9Y5xiqryWUoBVFP1-4eWfZxeroxJBM
        //response_mode: form_post


        System.out.println("xxxxx xxx " + By.className("ti_p-responsiveHeader-top-llc"));
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


        System.out.println("-cookie----");
        for (Cookie cookie : driver.manage().getCookies()) {
            System.out.println(cookie.getName() + "  " + cookie.getValue());
        }
        System.out.println("-cookie----");


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
