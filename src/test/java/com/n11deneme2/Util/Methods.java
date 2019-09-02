package com.n11deneme2.Util;

import com.n11deneme2.Base.BasePage;
import com.n11deneme2.Test.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class Methods extends BaseTest {

    private WaitTool waitTool = new WaitTool();

    public WebElement getElementBy(By by) {
        return driver.findElement(by);
    }
    public List<WebElement> getElementsBy(By by) {
        return driver.findElements(by);
    }
    public WebElement setElementBy(By by, String value) {
        WebElement element = null;
        try {
            element = getElementBy(by);
        } catch (Exception e) {
            logger.error("ERROR :", e);
            Assert.fail("Element Not Found :" + e.getMessage());
        }
        System.out.println("Element Send Keys : " + value + "-" + element);
        waitTool.waitForElementPresent(driver, by,5);
        element.clear();
        element.sendKeys(value);
        return element;
    }

    public WebElement clickElementBy(By by) {
        WebElement element = null;
        try {
            element = getElementBy(by);
        } catch (Exception e) {
            logger.error("ERROR :", e);
            Assert.fail("Element Not Found :" + e.getMessage());
        }
        waitTool.waitForElementClickable(driver, by, 10);
        System.out.println("Element Clicked :" + element);
        scrollToElement(element);
        element.click();
        return element;
    }
    public WebElement clickElementListItemBy(By by, int index) {
        List<WebElement> elements = null;
        WebElement element = null;
        try {
            elements= getElementsBy(by);
        } catch (Exception e) {
            logger.error("ERROR :", e);
            Assert.fail("Element Not Found :" + e.getMessage());
        }
        waitTool.waitForElementClickable(driver, by, 10);
        element = elements.get(index);
        scrollToElement(element);
        element.click();
        System.out.println("Element Clicked :" + element);
        return element;
    }

    public int randomNumber(int size) {
        Random random = new Random();
        int a =0;
        a = random.nextInt(size-1);
        return a;
    }

    public String getText(String text) {
        WebElement element = getElementBy(By.xpath(text));
        return element.getText();
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                element);
        waitTool.waitSeconds(5);
    }

}
