package TestAmazon_;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Scanner;

public class AmazonSignin extends BaseTest{

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void signInToAmazon() throws InterruptedException {
        // Navigate to the provided Amazon sign-in URL
        driver.get("https://www.amazon.in/");
        WebElement accountLists = driver.findElement(By.id("nav-link-accountList"));
        Actions actions = new Actions(driver);
        actions.moveToElement(accountLists).perform();

        WebElement signInLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#nav-flyout-ya-signin a")));
        signInLink.click();
        
        //Thread.sleep(5000);
        
        // Step 1: Enter email or phone number
        driver.findElement(By.id("ap_email_login")).sendKeys("punyajain202@gmail.com");
        driver.findElement(By.className("a-button-input")).click();

        // Step 2: Enter password
        driver.findElement(By.id("ap_password")).sendKeys("MYparant@2002");
        driver.findElement(By.id("signInSubmit")).click();

//        Scanner scn =new Scanner(System.in);
//        int code = scn.nextInt();
//        
//        driver.findElement(By.id("input-box-otp")).sendKeys(""+code);
//        driver.findElement(By.id("cvf-submit-otp-button-announce")).click();
    }

    @AfterClass
    public void tearDown() {
       driver.quit();
    }
}
