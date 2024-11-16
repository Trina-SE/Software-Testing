package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class WebDriverManager {
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            initializeDriver();
        }
        return driverThreadLocal.get();
    }

    private static void initializeDriver() {
        try {
            System.setProperty("webdriver.chrome.driver",
                    "C:\\Users\\sulta\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            // Add additional stable options
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");

            WebDriver driver = new ChromeDriver(options);

            // Configure basic timeouts
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

            driverThreadLocal.set(driver);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage(), e);
        }
    }

    public static void quitDriver() {
        try {
            WebDriver driver = driverThreadLocal.get();
            if (driver != null) {
                driver.quit();
                driverThreadLocal.remove();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error quitting WebDriver: " + e.getMessage(), e);
        }
    }

}