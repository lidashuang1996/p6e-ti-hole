package club.p6e.ti.hole.follower.action;

import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class LoginAction implements Action {

    /** 权重 */
    private static final int ORDER = 100;
    /** 类型 */
    private static final String TYPE = "LOGIN_TYPE";
    /** 名称 */
    private static final String NAME = "LOGIN_ACTION";

    private static final String TI_LOGIN_URL =
            "https://login.ti.com/as/authorization.oauth2?response_type=code&scope=openid%20email%20profile&client_id=DCIT_ALL_WWW-PROD&state=yhLzN4W1bXWyfoIj_nSLiwqtiMY&redirect_uri=https%3A%2F%2Fwww.ti.com.cn%2Foidc%2Fredirect_uri%2F&nonce=WR-Bbejb5IgMYUl92t188Qvt4ne6wmpYNysNxP2bbFQ&response_mode=form_post";
    private static final String TI_LOGIN_SCRIPT = "LOGIN_ACTION";

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public void execute(MetadataAction metadata) {
        ChromeDriver driver = null;
        try {
            driver = (ChromeDriver) metadata.getDriver();
            // 打开登录页面
            driver.navigate().to(TI_LOGIN_URL);
            // 执行登录的脚本
            // driver.executeScript(metadata.getAccount(), metadata.getPassword());
            // 回调执行脚本
            driver.executeScript(generateXMLHttpRequestScript(
                    "id", metadata.getId(),
                    "type", TYPE, "name",
                    NAME, "result", "SUCCESS",
                    "message", "{\"account\": \"" + metadata.getAccount() + "\", \"password\": \"" + metadata.getPassword() + "\"}"));
        } catch (Exception e) {
            if (driver != null) {
                // 回调执行脚本
                driver.executeScript(generateXMLHttpRequestScript(
                        "id", metadata.getId(),
                        "type", TYPE,
                        "name", NAME,
                        "result", "SUCCESS", "message", e.getMessage()));
            }
        }
    }

    @Override
    public int order() {
        return ORDER;
    }
}
