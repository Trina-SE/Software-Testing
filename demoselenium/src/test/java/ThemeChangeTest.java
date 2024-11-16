import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.Cookie;
import org.junit.jupiter.api.Assertions;

public class ThemeChangeTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private String osDefaultTheme;

    @BeforeEach
    public void setUp() {
        // Initialize WebDriver
        driver = WebDriverManager.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://developer.mozilla.org/en-US/docs/Web/WebDriver"); // Load the website
        clearBrowserCache();
        osDefaultTheme = determineOsDefaultTheme();
    }

    @AfterEach
    public void tearDown() {
        WebDriverManager.quitDriver();
        driver = null;
        wait = null;
    }


    @Test
    public void OsDefaultToDark() {
        switchToTheme(".icon-theme-os-default");
        pauseForChanges();
        if (!osDefaultTheme.equals("dark")) {
            switchToTheme(".icon-theme-dark");
            pauseForChanges();
            assertThemeApplied("dark");
        } else {
            Assertions.fail("Theme did not change as OS default is already dark.");
        }
    }

    @Test
    public void OsDefaultToLight() {
        switchToTheme(".icon-theme-os-default");
        pauseForChanges();
        if (!osDefaultTheme.equals("light")) {
            switchToTheme(".icon-theme-light");
            pauseForChanges();
            assertThemeApplied("light");
        } if (!osDefaultTheme.equals("light")) {
            switchToTheme(".icon-theme-light");
            pauseForChanges();
            assertThemeApplied("light");
        } else {
            System.out.println("OS default theme is already light, skipping switch.");
        }
    }

    @Test
    public void DarkToLight() {
        switchToTheme(".icon-theme-dark");
        pauseForChanges();
        switchToTheme(".icon-theme-light");
        pauseForChanges();
        assertThemeApplied("light");
    }

    @Test
    public void DarkToOsDefault() {
        switchToTheme(".icon-theme-dark");
        pauseForChanges();
        switchToTheme(".icon-theme-os-default");
        pauseForChanges();
        assertThemeApplied("os-default");
    }

    @Test
    public void LightToDark() {
        switchToTheme(".icon-theme-light");
        pauseForChanges();
        switchToTheme(".icon-theme-dark");
        pauseForChanges();
        assertThemeApplied("dark");
    }
    @Test
    public void LightToOsDefault() {
        switchToTheme(".icon-theme-light");
        pauseForChanges();
        switchToTheme(".icon-theme-os-default");
        pauseForChanges();
        assertThemeApplied("os-default");
    }



    private void switchToTheme(String themeSelector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement themeSwitcher = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".theme-switcher-menu")));
        themeSwitcher.click();
        WebElement themeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(themeSelector)));
        themeButton.click();
    }


    // Helper method to determine OS default theme
    private String determineOsDefaultTheme() {
        // Check the class of the HTML element to infer the OS default theme
        String currentClass = driver.findElement(By.tagName("html")).getAttribute("class");
        if (currentClass.contains("dark")) {
            return "dark";
        } else if (currentClass.contains("light")) {
            return "light";
        } else if (currentClass.contains("os-default")) {
            return "os-default";
        } else {
            return "unknown"; // In case no theme is found
        }
    }


    // Helper method to assert theme
    private void assertThemeApplied(String expectedTheme) {
        String currentClass = driver.findElement(By.tagName("html")).getAttribute("class");
        Assertions.assertTrue(currentClass.contains(expectedTheme), expectedTheme + " theme was not applied successfully.");
    }

    // Helper method to clear browser cache
    private void clearBrowserCache() {
        driver.manage().deleteAllCookies();
    }

    // Helper method to pause for 3 seconds to observe changes
    private void pauseForChanges() {
        try {
            Thread.sleep(3000); // Pause for 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
