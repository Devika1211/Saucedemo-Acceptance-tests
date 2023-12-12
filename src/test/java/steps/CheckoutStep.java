package steps;

import driverFactory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.Matchers;
import org.junit.Assert;
import pages.CheckoutPage;

public class CheckoutStep {
    CheckoutPage checkoutPage = new CheckoutPage(DriverFactory.getDriver());

    @Given("User is on the source demo website")
    public void user_is_on_the_source_demo_website() {
        DriverFactory.getDriver().get("https://www.saucedemo.com/");
        String currentUrl = checkoutPage.getUrl();
        Assert.assertThat(currentUrl, Matchers.startsWith("https://www.saucedemo."));
    }

    @When("User enter the username as {string}")
    public void user_enter_the_username_as(String userName) {
        checkoutPage.setUserName(userName);
    }

    @When("User enter the password as {string}")
    public void user_enter_the_password_as(String password) {
        checkoutPage.setPassword(password);
    }

    @When("User click on login button")
    public void user_click_on_login_button() {
        checkoutPage.clickOnLogin();
    }

    @And("User verify land on shopping page")
    public void userVerifyLandOnShoppingPage() {
        String inventoryUrl = checkoutPage.getUrl();
        Assert.assertThat(inventoryUrl, Matchers.endsWith(".com/inventory.html"));
    }

    @When("User select product, click on add to cart")
    public void user_select_product_click_on_add_to_cart() {
        checkoutPage.clickOnAddToCart();
    }

    @When("User click on basket to do checkout")
    public void user_click_on_basket_to_do_checkout() {
        checkoutPage.clickOnBasket();
    }

    @Then("User click on checkout button")
    public void user_click_on_checkout_button() {
        checkoutPage.clickOnCheckout();
    }

    @Then("User enter {string},{string} and {string}")
    public void user_enter_and(String firstName, String lastName, String postCode) {
        checkoutPage.setPersonalDetails(firstName, lastName, postCode);
    }

    @When("User click on continue button, verify selected product")
    public void user_click_on_continue_button_verify() {
        checkoutPage.clickOnContinue();
        String productName = checkoutPage.verifyProduct();
        Assert.assertThat(productName, Matchers.equalTo("Sauce Labs Backpack"));
    }

    @Then("User click on finish button, verify it")
    public void user_click_on_finish_button_verify_it() {
        checkoutPage.clickOnFinishButton();
        String shoppingMessage = checkoutPage.verifyCheckoutMessage();
        Assert.assertThat(shoppingMessage, Matchers.startsWith("Your order has been dispatched"));
    }

    @And("User verify lock out errorMessage when enter invalid username and password")
    public void user_Verify_When_Enter_Invalid_Username_And_Password() {
        String lockoutError = checkoutPage.verifyErrorMessage();
        Assert.assertThat(lockoutError, Matchers.endsWith("user has been locked out."));
    }

    @Then("User verify the login time performance")
    public void userVerifyTheLoginTimePerformance() {
        long loginTime = checkoutPage.verifyLoginTime();
        System.out.println("Login button performance time: " + loginTime + " milliseconds");
        Assert.assertThat(loginTime, Matchers.greaterThan(170233975706L));
    }

    @Then("User verify the error message for last name")
    public void userVerifyTheErrorMessageForLastName() {
        checkoutPage.clickOnContinue();
        String problemUserError = checkoutPage.verifyErrorMessage();
        Assert.assertThat(problemUserError, Matchers.equalTo("Error: Last Name is required"));
    }

    @Then("User verify finish button not work as expected")
    public void userVerifyFinishButtonNotWorkAsExpected() {
        checkoutPage.verifyFinishButton();
    }

    @When("User verify checkout button visual on top page")
    public void userVerifyCheckoutButtonVisualOnTopPage() {
        int checkoutButtonPosition = checkoutPage.verifyCheckoutButtonOnTopOfThePage();
        Assert.assertThat(checkoutButtonPosition, Matchers.greaterThan(500));
    }

}
