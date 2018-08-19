package com.example.abhishek.courtcounter;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import org.junit.After;
import org.junit.Before;

public class CourtCounterTest {

 WebDriver driver;

 @Before
 public void setUp() throws MalformedURLException {
  // Created object of DesiredCapabilities class.
  DesiredCapabilities capabilities = new DesiredCapabilities();

  // Set android deviceName desired capability. Set your device name.
  capabilities.setCapability("deviceName", "Android Emulator");

  // Set BROWSER_NAME desired capability. It's Android in our case here.
  capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");

  // Set android VERSION desired capability. Set your mobile device's OS version.
  capabilities.setCapability(CapabilityType.VERSION, "5.1");

  // Set android platformName desired capability. It's Android in our case here.
  capabilities.setCapability("platformName", "Android");

  // Set android appPackage desired capability. It is
  // com.android.calculator2 for calculator application.
  // Set your application's appPackage if you are using any other app.
  capabilities.setCapability("appPackage", "com.example.abhishek.courtcounter");

  // Set android appActivity desired capability. It is
  // com.android.calculator2.Calculator for calculator application.
  // Set your application's appPackage if you are using any other app.
  capabilities.setCapability("appActivity", "com.example.abhishek.courtcounter.MainActivity");

  // Created object of RemoteWebDriver will all set capabilities.
  // Set appium server address and port number in URL string.
  // It will launch calculator app in android device.
  driver = new RemoteWebDriver(new URL("http://http://172.31.22.58:4723/wd/hub"), capabilities);
  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
  
  
 }

 @Test
 public void testFirstCalculator() {
  
 
  // Click on DELETE/CLR button to clear result text box before running test.
  //driver.findElements(By.xpath("//android.widget.Button")).get(0).click();

  // Click on number 2 button.
  driver.findElement(By.name("PlusthreeTeamA")).click();
  
  driver.findElement(By.name("PlustwoTeamA")).click();
  
  driver.findElement(By.name("FreeThrowTeamB")).click();
  
  String result_A = driver.findElement(By.name("team_a_score")).getText();
  
  String result_B = driver.findElement(By.name("team_b_score")).getText();
  
  System.out.println("TEAM A result is : " + result_A);
  
  System.out.println("TEAM B result is : " + result_B);

 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS); 
}

@After
 public void End() {
  driver.quit();
 }
}

 
