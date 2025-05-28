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
        driver.get("https://www.amazon.in/ap/register?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2Fgp%2Fcss%2Fhomepage.html%2F%3Fie%3DUTF8%26ref_%3Dnav_newcust&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");
    }

    @When("I fill out the registration form")
    public void i_fill_out_the_registration_form() throws InterruptedException {
        driver.findElement(By.id("ap_customer_name")).sendKeys("TestUser");
        driver.findElement(By.id("ap_phone_number")).sendKeys("8770533132");
        driver.findElement(By.id("ap_password")).sendKeys("Test@123");
        //driver.findElement(By.id("ap_password_check")).sendKeys("Test@123");
        driver.findElement(By.id("continue")).click();
        Thread.sleep(30000);
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
    public void i_sign_in_with_valid_credentials() throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement accountList = driver.findElement(By.id("nav-link-accountList"));
        actions.moveToElement(accountList).perform();
        WebElement signInLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#nav-flyout-ya-signin a")));
        signInLink.click();

        driver.findElement(By.xpath("//*[@id=\"ap_email_login\"]")).sendKeys("punyajain202@gmail.com");
        driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div/div/div/span/form/span/span/input")).click();
        driver.findElement(By.id("ap_password")).sendKeys("MYparant@2002");
        driver.findElement(By.id("signInSubmit")).click();
        Thread.sleep(10000);
    }

    @Then("I should be logged in")
    public void i_should_be_logged_in() {
        // Check some post-login element
        System.out.println("Logged in successfully.");
        driver = null;
    }

    @When("I search for {string}")
    public void i_search_for_product(String product) {
    	driver.findElement(By.id("twotabsearchtextbox")).sendKeys(product);
        driver.findElement(By.id("nav-search-submit-button")).click();
    }

    @When("I add the first product to the cart")
    public void i_add_first_product_to_cart() {
        // Wait for the first product link
        WebElement firstProductLink = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("div.s-main-slot div[data-component-type='s-search-result'] h2 a")));

        // Remove target="_blank" using JavaScript so it opens in the same tab
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('target');", firstProductLink);

        // Click the link
        firstProductLink.click();

        // Now add to cart (in same tab)
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