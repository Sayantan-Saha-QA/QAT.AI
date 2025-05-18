import java.sql.DriverManager;

public interface DriverManger{
    WebDriver createDriver();
}

public class DriverManagerFactory{

    Map <String, Class <? extends Drivermanager >> registry = new Hashmap<>();

    public Webdriver getRegistry(String browser, Class <? extends drivermanager> managerClass){
        registry.put(browser.toLowerCase(), managerClass);
    }

    public DriverManager getmanager(String broswer){
        Class <? extends DriverManager> clazz = registry.get(rowser.toLowerCase());
        return clazz.getConstructor(null).newInstance();
    }

}