package TestAmazon_;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.util.ArrayList;

public class AmazonTestSearch extends BaseTest{


    @Test
    public void searchAddToCartAndBuyNow() throws InterruptedException {
    	driver.get("https://www.amazon.in/?ref_=nav_signin");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Laptop");
        driver.findElement(By.id("twotabsearchtextbox")).submit();
        
        driver.findElement(By.id("a-autoid-2-announce")).click();

        
    }

    @AfterClass
    public void tearDown() {
        //driver.quit();
    }
}
