package tests.US_016;

import org.apache.poi.sl.draw.geom.Path;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.M7PearlyMarketPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.nio.file.Files;
import java.nio.file.Paths;

public class US016_TC0002 {

    M7PearlyMarketPage pearlyMarketPage;
    Actions action;

    @Test
    public void TC0002() throws InterruptedException {
        pearlyMarketPage = new M7PearlyMarketPage();
        action = new Actions(Driver.getDriver());
        //vendor URL adresine gider
        Driver.getDriver().get(ConfigReader.getProperty("pearlyUrl"));

        //signin butonuna tiklar vendor hesabiyla giris yapar
        ReusableMethods.prMrktlogIn();
        Thread.sleep(3000);

        //MY ACCOUNT yazisini gorur ve My Account'a tiklar
        WebElement myAccount = pearlyMarketPage.myAccountYazisi;
        ReusableMethods.waitForVisibility(myAccount, 5);
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript("arguments[0].scrollIntoView(true);", myAccount);
        Thread.sleep(1000);
        Assert.assertTrue(myAccount.isDisplayed());
        pearlyMarketPage.m7MyAccount.click();

        //Store Manager'a tiklar
        pearlyMarketPage.m7storeManager.click();

        //Customers'a tiklar
        WebElement customers = pearlyMarketPage.m7Customers;
        ReusableMethods.waitForVisibility(customers, 5);
        JavascriptExecutor jse2 = (JavascriptExecutor) Driver.getDriver();
        jse2.executeScript("arguments[0].scrollIntoView(true);", customers);
        pearlyMarketPage.m7Customers.click();
        action.sendKeys(Keys.PAGE_DOWN).perform();

        //pdf butonuna tıklar dosyanın indiğini doğrular
        pearlyMarketPage.m7PdfButton.click();
        String dosyaYolu = "C:\\Users\\w10\\Downloads";
        Assert.assertTrue(Files.exists(Paths.get(dosyaYolu)));

        Thread.sleep(2000);
        Driver.closeDriver();






    }
}
