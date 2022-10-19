package tests.US_003;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.S8PearlyMarketPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class US003_TC0003 {
    S8PearlyMarketPage pearlyMarketPage = new S8PearlyMarketPage();

    @Test
    public void testCase0003() throws InterruptedException {
        Driver.getDriver().get(ConfigReader.getProperty("pearlyUrl"));
        ReusableMethods.prMrktlogIn();
        ReusableMethods.waitForVisibility(pearlyMarketPage.s8signOut, 5);
        pearlyMarketPage.s8cart.click();
        pearlyMarketPage.s8checkOut.click();
        Assert.assertTrue(pearlyMarketPage.s8checkOutYazisi.isDisplayed());
        Driver.closeDriver();
    }
}
