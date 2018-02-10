package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class MyTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://newtours.demoaut.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testMy() throws Exception {
    driver.get("http://newtours.demoaut.com/");
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("Welcome: Mercury Tours".equals(driver.getTitle())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.name("userName")).clear();
    driver.findElement(By.name("userName")).sendKeys("mercury");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("mercury");
    driver.findElement(By.name("login")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("Find a Flight: Mercury Tours:".equals(driver.getTitle())) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    try {
      assertTrue(isElementPresent(By.linkText("SIGN-OFF")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.linkText("SIGN-OFF")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("".equals(driver.findElement(By.name("userName")).getAttribute("value"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    try {
      assertEquals(driver.findElement(By.name("userName")).getText(), "");
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
