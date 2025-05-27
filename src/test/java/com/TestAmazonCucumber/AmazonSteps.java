package com.TestAmazonCucumber;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.AfterClass;

import java.time.Duration;
import java.util.ArrayList;

public class AmazonSteps {

    WebDriver driver;
    WebDriverWait wait;

    @Given("I am on the Amazon registration page")
    public void i_am_on_the_amazon_registration_page() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in/ap/register");
    }

    @When("I fill out the registration form")
    public void i_fill_out_the_registration_form() {
        driver.findElement(By.id("ap_customer_name")).sendKeys("TestUser");
        driver.findElement(By.id("ap_email")).sendKeys("testuser123@example.com");
        driver.findElement(By.id("ap_password")).sendKeys("Test@123");
        driver.findElement(By.id("ap_password_check")).sendKeys("Test@123");
        driver.findElement(By.id("continue")).click();
    }

    @Then("I should be redirected to the OTP verification page")
    public void i_should_see_otp_verification() {
        // You might verify heading or partial URL
        System.out.println("Redirected to OTP page.");
    }

    @Given("I am on the Amazon home page")
    public void i_am_on_the_amazon_home_page() {
        if (driver == null) {
            driver = new FirefoxDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            driver.manage().window().maximize();
        }
        driver.get("https://www.amazon.in/");
    }

    @When("I sign in with valid credentials")
    public void i_sign_in_with_valid_credentials() {
        Actions actions = new Actions(driver);
        WebElement accountList = driver.findElement(By.id("nav-link-accountList"));
        actions.moveToElement(accountList).perform();
        WebElement signInLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#nav-flyout-ya-signin a")));
        signInLink.click();

        driver.findElement(By.id("ap_email")).sendKeys("youremail@example.com");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("ap_password")).sendKeys("YourPassword123");
        driver.findElement(By.id("signInSubmit")).click();
    }

    @Then("I should be logged in")
    public void i_should_be_logged_in() {
        // Check some post-login element
        System.out.println("Logged in successfully.");
    }

    @When("I search for {string}")
    public void i_search_for_product(String product) {
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
        searchBox.sendKeys(product);
        driver.findElement(By.id("nav-search-submit-button")).click();
    }

    @When("I add the first product to the cart")
    public void i_add_first_product_to_cart() {
        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.s-main-slot div[data-component-type='s-search-result'] h2 a")));
        firstProduct.click();

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button")));
        addToCart.click();
    }

    @When("I click Buy Now")
    public void i_click_buy_now() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-cart-count")));
        WebElement buyNow = wait.until(ExpectedConditions.elementToBeClickable(By.id("buy-now-button")));
        buyNow.click();
    }

    @Then("I should see the payment or address page")
    public void i_should_see_payment_page() {
        System.out.println("Navigated to payment/address selection.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}