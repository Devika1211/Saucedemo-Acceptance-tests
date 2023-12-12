package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;

public class CheckoutPage {
    private WebDriver driver;
    private LocalDateTime lastLoginTime;
    private By userName = By.id("user-name");
    private By password = By.xpath("//input[@id='password']");
    private By login = By.xpath("//input[@id='login-button']");
    private By addToCart = By.xpath("//div[@id='contents_wrapper']/div[2]/div/div/div/div[1]/div[2]/div[2]/button");
    private By basket = By.cssSelector("a[class='shopping_cart_link']");
    private By checkout = By.xpath("//button[@id='checkout']");
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postcode = By.id("postal-code");
    private By continueButton = By.name("continue");
    private By productSelection = By.cssSelector("#item_4_title_link");
    private By finishButton = By.xpath("//button[@id='finish']");
    private By checkoutMessage = By.cssSelector("div[class='complete-text']");
    private By errors = By.cssSelector("h3[data-test='error']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public void setUserName(String user) {
        driver.findElement(userName).sendKeys(user);
    }

    public void setPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
    }

    public void clickOnLogin() {
        driver.findElement(login).click();
    }

    public void clickOnAddToCart() {
        driver.findElement(addToCart).click();
    }

    public void clickOnBasket() {
        driver.findElement(basket).click();
    }

    public void clickOnCheckout() {
        driver.findElement(checkout).click();
    }

    public void setPersonalDetails(String first, String last, String postCode) {
        driver.findElement(firstName).sendKeys(first);
        driver.findElement(lastName).sendKeys(last);
        driver.findElement(postcode).sendKeys(postCode);
    }

    public void clickOnContinue() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scroll(0,300)");
        driver.findElement(continueButton).click();
    }

    public String verifyProduct() {
        return driver.findElement(productSelection).getText();
    }

    public void clickOnFinishButton() {
        driver.findElement(finishButton).click();
    }

    public String verifyCheckoutMessage() {
        return driver.findElement(checkoutMessage).getText();
    }

    public String verifyErrorMessage() {
        return driver.findElement(errors).getText();
    }

    public void verifyFinishButton() {
        WebElement button = driver.findElement(finishButton);
        if (button.isEnabled()) {
            button.click();
        } else {
            System.out.println("Button is not clickable as it is disabled.");
        }
    }

    public int verifyCheckoutButtonOnTopOfThePage() {
        WebElement checkoutButton = driver.findElement(checkout);
        int checkoutButtonYPosition = checkoutButton.getLocation().getX();
        return checkoutButtonYPosition;
    }

    public long verifyLoginTime() {
        long afterLogin = System.currentTimeMillis();
        return afterLogin;
    }

}
