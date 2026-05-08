package com.githubactions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        
        // The magic "headless" flag
        options.addArguments("--headless=new"); 
        
        // Critical flags for Linux/Docker/CI environments
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }
    @Test
    public void demoTest1() {
       driver.get("https://www.google.com");
       System.out.println("Title: " + driver.getTitle());
       System.out.println("Current Directory: " + System.getProperty("user.dir"));
    }
    @Test
    public void demoTest2() {
        driver.get("https://www.example.com");
        System.out.println("Title: " + driver.getTitle());
    }
    @Test
    public void demoTest3() {
        driver.get("https://www.youtube.com");
        System.out.println("Title: " + driver.getTitle());
    }
    @Test
    public void demoTest4() {
        driver.get("https://www.bing.com");
        System.out.println("Title: " + driver.getTitle());
    }
    @Test
    public void demoTest5() {
        driver.get("https://www.yahoo.com");
        System.out.println("Title: " + driver.getTitle());
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
