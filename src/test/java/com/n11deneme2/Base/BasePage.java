package com.n11deneme2.Base;

import com.n11deneme2.Test.BaseTest;
import com.n11deneme2.Util.Methods;
import com.n11deneme2.Util.WaitTool;
import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class BasePage extends BaseTest {

    public Methods met = new Methods();
    public WaitTool waitTool = new WaitTool();

    @Step("Üzerine gel <xpath>")
    public void hoverItem(String xpath) {
        WebElement element = met.getElementBy(By.xpath(xpath));
        met.scrollToElement(element);
        waitTool.waitForElementPresent(driver, By.xpath(xpath), 5);
        try {
            Actions ac = new Actions(driver);
            ac.moveToElement(element)
                    .build()
                    .perform();
            System.out.println("Elementin üzerine geldi" + element);
        }catch (Exception e){
            logger.error("ERROR: " + e.getMessage());
            Assert.fail(e.getMessage());
        }

    }

    @Step("Css i <css> olan elemente tıkla")
    public WebElement cssTiklama(String css) {
        return met.clickElementBy(By.cssSelector(css));
    }

    @Step("İd si <id> olan alana <text> textini yaz")
    public WebElement setTextId(String id, String text) {
        return met.setElementBy(By.id(id), text);
    }

    @Step("İd si <id> olan elemente tıkla")
    public WebElement clickId(String id) {
        return met.clickElementBy(By.id(id));
    }

    @Step("Xpath i <xpath> olan elemente <text> textini yaz")
    public WebElement setTextXpath(String xpath, String text) {
        return met.setElementBy(By.xpath(xpath), text);
    }

    @Step("<css> bulunduğu kontrol edilir")
    public void kontrolEdilir(String css) {
        WebElement element = met.getElementBy(By.cssSelector(css));
        if (element.isDisplayed()) {
            System.out.println("element görüldü : " + element);
        } else {
            logger.error("element görülmedi!");
        }
    }

    @Step("Css i <css> olan elementin <index> indexine tıkla")
    public void cssIndexTikla(String css, int index) {
        try {
            met.clickElementListItemBy(By.cssSelector(css), index - 1);
            logger.info("Elemente tıklandı.");
        } catch (Exception e) {
            logger.error("ERROR : " + e.getMessage());
        }
    }

    @Step("Rastgele ürün seç <css>")
    public void rastgeleSec(String css) {

        int size = met.getElementsBy(By.cssSelector(css)).size();
        int rastgeleSayi = 0;
        rastgeleSayi = met.randomNumber(size);
        met.clickElementListItemBy(By.cssSelector(css), rastgeleSayi);

    }

    @Step("Ürün ile sepeti karşılaştır")
    public void karsilastirma() {

        String ürünXpath = "//div[@class='paymentDetail']//ins";
        String urun = met.getText(ürünXpath);
        hoverItem("//a[@class='myBasket']");
        waitTool.waitSeconds(2);
        hoverItem("(//div[@class='proNumDetail'])[1]");
        waitTool.waitSeconds(2);
        String SepetXpath = "(//div[@class='myBasketList']//ins)[1]";
        String sepet = met.getText(SepetXpath);
        if (urun.contains(sepet)) {
            System.out.println("Ürün fiyatları eşit");
        } else {
            logger.error("Ürün fiyatları eşit değil");

        }

    }

    @Step("<css> elementin değerini <text> içerdiği kontrol edilir.")
    public void checkGettingMessage(String css, String text) {
        waitTool.waitForElementPresent(driver, By.cssSelector(css), 5);
        WebElement element = met.getElementBy(By.cssSelector(css));
        try {
            Assert.assertEquals(element.getText(), text);
            System.out.println("Değeri eşleşmiştir: " + text);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            Assert.fail(e.getMessage());
        }
    }

    @Step("Ürün adetini arttır")
    public void adetArttirma() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('quantity').setAttribute('value', '2')");
    }

    @Step("<title> li sayfanın geldiği kontrol edilir.")
    public void checkGettingPage(String text) {

        try {
            Assert.assertEquals(driver.getTitle(), text);
            System.out.println("Sayfa kontrolü yapılmıştır.");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            Assert.fail(e.getMessage());
        }
    }
}
