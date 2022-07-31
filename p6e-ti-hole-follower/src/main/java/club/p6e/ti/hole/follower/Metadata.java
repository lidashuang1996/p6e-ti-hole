package club.p6e.ti.hole.follower;

/**
 * @author lidashuang
 * @version 1.0
 */
public final class Metadata {

    private Object driver;
    private Class<?> driverClass;

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
}
