package driverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {
    public static ThreadLocal<WebDriver> tsLocal = new ThreadLocal<>();

    public WebDriver browser(String browserName) {
        System.out.println("The browser name is " + browserName);
        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--start-maximized");
            options.addArguments("--remote-allow-origins=*");
            tsLocal.set(new ChromeDriver(options));
        } else if (browserName.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("browser.download.folderList", 2);
            tsLocal.set(new FirefoxDriver());
        } else if (browserName.equals("ie")) {
            WebDriverManager.iedriver().setup();
            InternetExplorerOptions options = new InternetExplorerOptions();
            options.setCapability("ignoreZoomSettings", true);
            options.setCapability("nativeEvents", false);
            options.setCapability("unexpectedAlertBehaviour", "ignore");
            options.setCapability("ignoreProtectedModeSettings", true);
            options.setCapability("disable-popup-blocking", true);
            options.setCapability("enablePersistentHover", true);
            options.setCapability("requireWindowFocus", true);
            tsLocal.set(new InternetExplorerDriver());
        } else if (browserName.equals("opera")) {
            WebDriverManager.operadriver().setup();
            tsLocal.set(new OperaDriver());
        } else if (browserName.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.setCapability("ignoreZoomSetting", true);
            options.setCapability("nativeEvents", false);
            options.setCapability("unexpectedAlertBehaviours", "ignore");
            options.setCapability("disable-popup-blocking", true);
            options.setCapability("requireWindowFocus", true);
            options.setCapability("enablePersistentHover", true);
            options.setCapability("ignoreProtectedModeSettings", true);
            tsLocal.set(new EdgeDriver());
        } else {
            System.out.println("The correct browser is " + browserName);
        }

        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return getDriver();
    }

    public static WebDriver getDriver() {
        return tsLocal.get();
    }
}
