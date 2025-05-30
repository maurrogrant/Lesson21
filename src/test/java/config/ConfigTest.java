package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class ConfigTest {
    protected static WebDriver driver;

    public static void initConfig() {
        ConfigTest.initializeProfileDirectory();
        ChromeOptions options = ConfigTest.createChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static void initializeProfileDirectory() {
        Path profileDir = Paths.get(System.getProperty("user.dir"), "target", "profile");
        try {
            if (!Files.exists(profileDir)) {
                Files.createDirectories(profileDir);
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось создать директорию профиля", e);
        }
    }

    public static ChromeOptions createChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--remote-debugging-port=9222",
                "--disable-gpu",
                "--window-size=1920,1080",
                "--user-data-dir=" + System.getProperty("user.dir") + "/target/profile"
        );

        return options;
    }

    // Закрытие драйвера
    public static void quit() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
