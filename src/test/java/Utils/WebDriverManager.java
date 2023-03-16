package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManager {

    private static WebDriver driver;

    private WebDriverManager() {}

    public static WebDriver getDriver() {
        if (driver == null) {
            String browserName = System.getProperty("browser", "chrome");
            switch (browserName) {
                case "chrome":
                default:
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", "Put Path to Driver Gecko");
                    driver = new FirefoxDriver();
                    break;
            }
        }
        return driver;
    }


    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
