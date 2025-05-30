package com.TestAmazonCucumber;



import com.base.BaseCucumberClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SignInSteps {

    @Before
    public void setUp() {
    	BaseCucumberClass.initializeDriver();
    }

    @Given("I navigate to Amazon sign-in page")
    public void iNavigateToAmazonSignInPage() {
    	BaseCucumberClass.driver.get("https://www.amazon.in/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2F%3Fref_%3Dnav_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");
    	BaseCucumberClass.takeScreenshot("NavigateToSignInPage");
    }

    @When("I sign in with email and password")
    public void iSignInWithEmailAndPassword() throws InterruptedException {
    	BaseCucumberClass.driver.findElement(By.id("ap_email")).sendKeys("punyajain202@gmail.com");
    	BaseCucumberClass.driver.findElement(By.id("continue")).click();
    	BaseCucumberClass.driver.findElement(By.id("ap_password")).sendKeys("MYparant@2002");
    	BaseCucumberClass.driver.findElement(By.id("signInSubmit")).click();
    	
    	BaseCucumberClass.takeScreenshot("AfterSignIn");

        Thread.sleep(20000); // handle OTP manually
    }

    @And("I search for Laptop and add first item to cart")
    public void iSearchForLaptopAndAddFirstItemToCart() {
        WebDriver driver = BaseCucumberClass.driver;
        WebDriverWait wait = BaseCucumberClass.wait;
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Laptop");
        driver.findElement(By.id("nav-search-submit-button")).click();
        BaseCucumberClass.takeScreenshot("SearchResults");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".widgetId\\=search-results_2 > div:nth-child(1) > div:nth-child(1) > span:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(2)")));
        List<WebElement> products = driver.findElements(By.cssSelector(".widgetId\\=search-results_2 > div:nth-child(1) > div:nth-child(1) > span:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(2)"));
        if (!products.isEmpty()) {
            WebElement firstProduct = products.get(0);
            js.executeScript("arguments[0].removeAttribute('target')", firstProduct);
            firstProduct.click();
        }
        
        BaseCucumberClass.takeScreenshot("ProductPage");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/div[15]/div/div/div/div[1]/div[2]/div/div[2]/div/form/div[5]/span/span/input"))).click();
        BaseCucumberClass.takeScreenshot("AfterAddingToCart");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-cart"))).click();
    }

    @Then("The product should be added to the cart")
    public void theProductShouldBeAddedToTheCart() {
        assert BaseCucumberClass.driver.getTitle().contains("Cart");
        BaseCucumberClass.takeScreenshot("CartPage");
    }

    @After
    public void tearDown() {
    	BaseCucumberClass.quitDriver();
    }
}
