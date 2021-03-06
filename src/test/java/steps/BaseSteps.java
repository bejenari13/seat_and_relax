package steps;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class BaseSteps extends PageObject {

    public final int SHORT_TIMEOUT = 10;
    public final int MEDIUM_TIMEOUT = 15;
    public final int LONG_TIMEOUT = 15;

    public WebDriver driver;
    private WebDriverWait wait;



    public void initDriver(String baseURL) {
        driver = Serenity.getDriver();
        driver.manage().window().maximize();
        driver.get(baseURL);
        wait = new WebDriverWait(driver, LONG_TIMEOUT);
    }

    public void click(String xpath) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
        } catch (Exception e) {
            locatorNotFound(xpath);
        }
    }

    public void placeClic(String xpath){
        WebElement element = driver.findElement(By.xpath(xpath));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }

    public void input(String text, String xpath) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).sendKeys(text);
        } catch (Exception e) {
            locatorNotFound(xpath);
        }
    }

    public void hover(String xpath) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            Actions actions = new Actions(driver);
            actions.moveToElement(element);
            actions.perform();
        } catch (Exception e) {
            locatorNotFound(xpath);
        }
    }

    public void scroll(String xpath) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(500);
        } catch (Exception e) {
            locatorNotFound(xpath);
        }
    }

    private void locatorNotFound(String xpath) {
        failWithMessage(String.format("Locator was not found: [%s]", xpath));
    }

    public void failWithMessage(String message) {
        Assert.fail(message);
    }


//read file with user and pass





    public String getClipboard() {
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable t = c.getContents(this);
        String a;
        try {
            a = (String) t.getTransferData(DataFlavor.stringFlavor);
            return a;
        } catch (UnsupportedFlavorException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

