package az.turbo.main;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WebPage {
    private static final WebDriver driver;
    private final String url;
    private boolean isConnected;

    static {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setLogLevel(ChromeDriverLogLevel.OFF);
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
    }

    public WebPage(String url) {
        Objects.requireNonNull(url, "Webpage url cannot be null!");
        this.url = url;
    }

    public void connect() {
        try {
            driver.get(url);
            isConnected = true;
        } catch (Exception e) {
            disconnect();
            throw new InternetConnectionException("Failed to connect to webpage, check your internet connection!", e);
        }
    }

    public void disconnect() {
        if (isConnected) {
            driver.quit();
            isConnected = false;
        }
    }

    public List<WebElement> fetchWebElements(String cssSelector) {
        if (!isConnected) {
            connect();
        }

        return new ArrayList<>(driver.findElements(By.cssSelector(cssSelector)));
    }

    public boolean isConnected() {
        return isConnected;
    }
}
