package tests.US_005;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BPearlyMarketPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class US005_TC0006 {
    BPearlyMarketPage pearlyMarketPage = new BPearlyMarketPage();
    Select select;

    @Test
    public void US005_TC0004() {
        Driver.getDriver().get(ConfigReader.getProperty("pearlyUrl"));
        ReusableMethods.prMrktlogIn();
        ReusableMethods.waitFor(5);

        //1-Store Manager butonuna basar
        ReusableMethods.prMrktstoreManager();

        //2-Products butonuna basar
        pearlyMarketPage.b2productsButton.click();
        ReusableMethods.waitFor(3);

        //3-Add New butonuna basar
        pearlyMarketPage.b2addNewButton.click();

        //4-Product Title'ı doldurur
        pearlyMarketPage.b2productTitleBox.sendKeys("Elmas Kupe");

        //5-Featured Images alanına fotoğraf yükler !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        //6-Gallery Images alanına fotoğraf yükler  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

      // 7-Categories'den ürünün kategorisini seçer
        ReusableMethods.scrollIntoView(pearlyMarketPage.b2categoriesTable);
        ReusableMethods.click(pearlyMarketPage.b2accessoriesBox);

      // 8-Submit butonuna basar
        ReusableMethods.click(pearlyMarketPage.b2submitButton);

      // 9-Ürünün başarıyla eklendiğini görür
        ReusableMethods.waitForVisibility(pearlyMarketPage.b2UrunEklendiText,6);
        Assert.assertTrue(pearlyMarketPage.b2UrunEklendiText.isDisplayed());



    }
}
