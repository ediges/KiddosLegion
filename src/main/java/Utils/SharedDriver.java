package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

public class SharedDriver {
    private static WebDriver webDriver;

    public enum Browser{
        CHROME,
        FIREFOX,
        IE,
        EDGE
    }
    protected static WebDriver getWebDriver(Browser browser) {
        switch (browser){
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                // options.addArguments("--disable-dev-shm-usage");
                // options.addArguments("--headless");

                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;
            case IE:
                WebDriverManager.iedriver().setup();
                webDriver = new InternetExplorerDriver();
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                break;
        }
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        return webDriver;
    }

    protected static void closeWebDriver() {
        if(webDriver != null) {
            webDriver.close();
        }
    }
}
