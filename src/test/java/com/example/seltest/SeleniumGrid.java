package com.example.seltest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SeleniumGrid {

    WebDriver driver;
    String baseURL, nodeURL;

    @Before
    public void setUp() throws MalformedURLException {
        nodeURL = "http://localhost:4444/wd/hub";
        baseURL = "http://demo.guru99.com/test/login.html";
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setBrowserName("chrome");
        driver = new RemoteWebDriver(new URL(nodeURL), capability);
    }

    @After
    public void afterTest() {
        driver.quit();
    }

    @Test
    public void sampleTest() {
        driver.get(baseURL);


        driver.findElement(By.id("email")).sendKeys("abcd@gmail.com");
        driver.findElement(By.name("passwd")).sendKeys("abcdefghlkjl");
        driver.findElement(By.id("SubmitLogin")).click();

        Cookie cookie = new Cookie("zaleniumTestPassed", "false");
        driver.manage().addCookie(cookie);

//        if (!driver.getPageSource().contains("Successfully Logged in...")) {
//            fail();
//        }
    }

}