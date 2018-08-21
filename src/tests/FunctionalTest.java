package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class FunctionalTest {
    protected static WebDriver driver;
    protected static ChromeOptions options;
//    protected static FirefoxDriver firefoxDriver;

    public static void before() {
        /*System.setProperty("webdriver.gecko.driver", "C:\\test\\geckodriver.exe");
        firefoxDriver = new FirefoxDriver();
        driver = new FirefoxDriver();*/
        System.setProperty("webdriver.chrome.driver", "C:/test/chromedriver.exe");
        options = new ChromeOptions();
        options.addArguments("--start-maximized", "--incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public static void clear() {
        driver.manage().deleteAllCookies();
    }

    public static void tearDown() {
        driver.quit();
    }
}