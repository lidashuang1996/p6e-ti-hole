package club.p6e.ti.hole.follower.action;

import org.springframework.context.ApplicationContext;

/**
 * @author lidashuang
 * @version 1.0
 */
public final class MetadataAction {

    private String id;
    private Object driver;
    private Class<?> driverClass;
    private String account;
    private String password;
    private String baseUrl;
    private ApplicationContext applicationContext;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getDriver() {
        return driver;
    }

    public void setDriver(Object driver) {
        this.driver = driver;
    }

    public Class<?> getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(Class<?> driverClass) {
        this.driverClass = driverClass;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
