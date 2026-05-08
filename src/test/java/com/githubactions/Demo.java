package com.githubactions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;

@Epic("Web UI Tests")
@Feature("Search Engine Demonstrations")
public class Demo {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); 
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        
        driver = new ChromeDriver(options);
    }

    @Test(description = "Verify Google Search Page Title")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test checks if the Google home page loads with the correct title.")
    @Story("Search Engine Accessibility")
    public void demoTest1() {
        openUrl("https://www.google.com");
        checkTitle("Google");
    }

    @Test(description = "Verify Example.com Page Title")
    @Severity(SeverityLevel.NORMAL)
    @Story("Domain Verification")
    public void demoTest2() {
        openUrl("https://www.example.com");
        checkTitle("Example Domain");
    }

    @Test(description = "Verify Ebay.com Page Title")
    @Severity(SeverityLevel.NORMAL)
    @Story("Domain Verification")
    public void demoTest3() {
        openUrl("https://www.ebay.com");
        checkTitle("Electronics, Cars, Fashion, Collectibles & More | eBay");

    }

    @Test(description = "Verify Youtube.com Page Title")
    @Severity(SeverityLevel.NORMAL)
    @Story("Domain Verification")
    public void demoTest4() {
        openUrl("https://www.youtube.com");
        checkTitle("YouTube");

    }

    // --- Allure Step Methods ---
    
    @Step("Navigating to URL: {url}")
    public void openUrl(String url) {
        driver.get(url);
    }

    @Step("Verifying page title contains: {expectedTitle}")
    public void checkTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        System.out.println("Actual Title: " + actualTitle);
    }

    // --- Automatic Screenshot on Failure ---

    @Attachment(value = "Screenshot on Failure", type = "image/png")
    public byte[] saveScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            saveScreenshot(); // This attaches the image to the Allure report automatically
        }
        if (driver != null) {
            driver.quit();
        }
    }
}