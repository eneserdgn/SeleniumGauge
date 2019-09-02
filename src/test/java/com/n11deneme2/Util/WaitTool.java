package com.n11deneme2.Util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WaitTool {
    public static final int DEFAULT_WAIT_4_ELEMENT = 7;
    public static final int DEFAULT_WAIT_4_PAGE = 12;


    public static WebElement waitForElementClickable(WebDriver driver,
                                                     final By by, int timeOutInSeconds) {
        WebElement element;
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); // nullify
            // implicitlyWait()

            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            element = wait.until(ExpectedConditions.elementToBeClickable(by));

            driver.manage().timeouts()
                    .implicitlyWait(DEFAULT_WAIT_4_PAGE, TimeUnit.SECONDS); // reset
            // implicitlyWait
            return element; // return the element
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static WebElement waitForElementPresent(WebDriver driver,
                                                   final By by, int timeOutInSeconds) {
        WebElement element;
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); // nullify
            // implicitlyWait()

            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            element = wait.until(ExpectedConditions
                    .presenceOfElementLocated(by));

            driver.manage().timeouts()
                    .implicitlyWait(DEFAULT_WAIT_4_PAGE, TimeUnit.SECONDS); // reset
            // implicitlyWait
            return element; // return the element
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
        }
    }


    public void waitSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static boolean waitElementDisappearr(WebDriver driver, By by, int second) {
        boolean validate = false;
        int counter = 0;
        while (validate == false && counter < second) {
            validate = !driver.findElement(by).isDisplayed();
            sleep(1000);
            counter += 1;
        }
        return validate;
    }

}
