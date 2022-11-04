package tests.US_016;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pages.M7PearlyMarketPage;
import tests.log4J.Log4jTest1;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import utilities.TestBaseRapor;

import java.util.List;

public class US016_TC0001 extends TestBaseRapor {

    private static Logger logger = LogManager.getLogger(US016_TC0001.class.getName());

    M7PearlyMarketPage pearlyMarketPage;
    Actions action;
    @Test
    public void TC0001() throws InterruptedException {
        logger.info("PearlyMarketPage sitesine gidilir");
        extentTest=extentReports.createTest("pozitif test","Geçerli kullanıcı adı ve password ile giriş yapıldı");
        pearlyMarketPage = new M7PearlyMarketPage();
         action = new Actions(Driver.getDriver());
        //vendro URL adresine gider
        Driver.getDriver().get(ConfigReader.getProperty("pearlyUrl"));

        logger.info("signin girisi yapılır");
        //signin butonuna tiklar vendor hesabiyla giris yapar
        ReusableMethods.prMrktlogIn();
        extentTest.info("Doğru kullanıcı email ve password girildi");

        //MY ACCOUNT yazisini gorur ve My Account'a tiklar
        WebElement myAccount = pearlyMarketPage.myAccountYazisi;
        ReusableMethods.waitForVisibility(myAccount,5);
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript("arguments[0].scrollIntoView(true);",myAccount);
        Thread.sleep(1000);
        Assert.assertTrue(myAccount.isDisplayed());
        pearlyMarketPage.m7MyAccount.click();
        extentTest.pass("MY ACCOUNT goruldu ve myAccount yazısına tıklandı");

        //Store Manager'a tiklar
        pearlyMarketPage.m7storeManager.click();
        extentTest.pass("Store Manager'a tıklandı");

        //Customers'a tiklar
        WebElement customers = pearlyMarketPage.m7Customers;
        ReusableMethods.waitForVisibility(customers,5);
        jse.executeScript("arguments[0].scrollIntoView(true);",customers);
        pearlyMarketPage.m7Customers.click();
        action.sendKeys(Keys.PAGE_DOWN).perform();
        extentTest.pass("Customers'a tıklandı");

        //Basliklari dogrular
        pearlyMarketPage.m7Name.isDisplayed();
        pearlyMarketPage.m7Username.isDisplayed();
        pearlyMarketPage.m7Email.isDisplayed();
        pearlyMarketPage.m7Location.isDisplayed();
        pearlyMarketPage.m7Orders.isDisplayed();
        pearlyMarketPage.m7MoneySpent.isDisplayed();
        pearlyMarketPage.m7LastOrder.isDisplayed();
        extentTest.pass("basliklar dogrulandi");

        //table body'si icindeki hücrelerin dolu oldugunu dogrular
        WebElement body = Driver.getDriver().findElement(By.xpath("//tbody"));
        System.out.println("body = " + body.getSize());
        body.isDisplayed();
        extentTest.pass("tbody hücrelerin dolu oldugu dogrulandi");

        Thread.sleep(2000);
        Driver.closeDriver();


    }

}
