package com.automationexercise.tests;

import com.automationexercise.utils.BrowserManager;
import com.automationexercise.utils.PropertiesLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class TestBasic {

    protected static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();

    public static synchronized WebDriver getDriver() {
        return tdriver.get();
    }

    @BeforeMethod
    public void setup() throws IOException {
        String url = PropertiesLoader.loadProperty("url");
      
        WebDriver driver = BrowserManager.doBrowserSetup(); //doBrowserSetup contient des options pour le chrome
        tdriver.set(driver);
        driver.manage().window().maximize();
        getDriver().get(url);
    }

    @AfterMethod
    public void tearDown() {
        getDriver().quit();
        tdriver.remove();
    }
}
