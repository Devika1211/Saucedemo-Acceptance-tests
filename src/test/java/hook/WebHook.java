package hook;

import driverFactory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

import java.io.IOException;
import java.util.Properties;

public class WebHook {
    private DriverFactory driverFactory;
    private ConfigReader configReader;
    private WebDriver driver;
    private Properties prop;

    @Before(order = 0)
    public void readFile() throws IOException {
        configReader = new ConfigReader();
        prop = configReader.reader();
    }

    @Before(order = 1)
    public void openBrowser() {
        String name = prop.getProperty("browser");
        driverFactory = new DriverFactory();
        driver = driverFactory.browser(name);
    }

    @After(order = 0)
    public void tearDown() {
        driver.quit();
    }

    @After(order = 1)
    public void screenShot(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenshotName);
        }
    }
}
