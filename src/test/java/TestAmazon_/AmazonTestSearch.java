package TestAmazon_;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.ArrayList;

public class AmazonTestSearch extends BaseTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void searchAndAddToCartAndBuy() {
        driver.get("https://www.amazon.in/?ref_=nav_signin");

        
        // Search for product
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
        searchBox.sendKeys("Laptop");
        driver.findElement(By.id("nav-search-submit-button")).click();

        // Click first product in results
     // Click first product in results
        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(
            By.id("a-autoid-1-announce")));
        firstProduct.click();

        // Handle single or multiple tab
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        if (tabs.size() > 1) {
            driver.switchTo().window(tabs.get(1));
        }

        // Add to cart
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button")));
        addToCart.click();

        // Optional: Confirm item added
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-cart-count")));

        // Buy Now (if available)
        try {
            WebElement buyNowButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("buy-now-button")));
            buyNowButton.click();
        } catch (Exception e) {
            System.out.println("Buy Now button not available.");
        }

        // Now at the address or payment selection page
        // Optional: check URL or confirmation
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
