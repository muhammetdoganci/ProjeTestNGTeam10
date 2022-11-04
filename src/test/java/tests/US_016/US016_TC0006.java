package tests.US_016;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
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

public class US016_TC0006 {

    M7PearlyMarketPage pearlyMarketPage;
    Actions action;
    Faker faker;
    @Test
    public void TC0006() throws InterruptedException {
        pearlyMarketPage = new M7PearlyMarketPage();
        action = new Actions(Driver.getDriver());
        faker = new Faker();
         //vendro URL adresine gider
        Driver.getDriver().get(ConfigReader.getProperty("pearlyUrl"));

        //signin butonuna tiklar vendor hesabiyla giris yapar
        ReusableMethods.prMrktlogIn();

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

        //Customers uzerine gelir acilan Add New butonuna tiklar
        action.sendKeys(Keys.PAGE_DOWN).perform();
        Thread.sleep(3000);
        action.moveToElement(pearlyMarketPage.m7Customers).perform();
        pearlyMarketPage.m7AddNewButonu.click();

        //username inputbox'a uniq isim, emaile uniq mail, diğerlerini boş bırakır
        pearlyMarketPage.m7userName.sendKeys(faker.name().username());
        action.sendKeys(Keys.TAB).sendKeys(faker.internet().emailAddress()).perform();

        //Submit'e tiklar Customer Successfully Saved.' yazısının göründüğünü test eder
        WebElement submit = pearlyMarketPage.m7SubmitButonu;
        ReusableMethods.waitForVisibility(submit,5);
        jse.executeScript("arguments[0].scrollIntoView(true);",submit);
        Thread.sleep(2000);
        pearlyMarketPage.m7SubmitButonu.click();

        String expextedYazı = "'Customer succesfully saved'";
        String actualYazı = Driver.getDriver().findElement(By.xpath("//*[@class='wcfm-message']")).getText();
        Assert.assertTrue(expextedYazı.contains(actualYazı),"Müşteri başarıyla kaydedildi");

        Thread.sleep(2000);
        Driver.closeDriver();










    }
}
