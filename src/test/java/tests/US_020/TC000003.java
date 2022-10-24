package tests.US_020;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.M7PearlyMarketPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class TC000003 {
    M7PearlyMarketPage pearlyMarketPage = new M7PearlyMarketPage();

    @Test
    public void test1(){
        Driver.getDriver().get(ConfigReader.getProperty("pearlyUrl"));
        ReusableMethods.prMrktlogIn();
        WebElement myAccountButon = Driver.getDriver().findElement(By.xpath("//*[@id='menu-item-1079']"));
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript("argument[0].click();",myAccountButon);
        try {
            myAccountButon.click();
        }catch (Exception e){
            JavascriptExecutor executor = (JavascriptExecutor) Driver.getDriver();
            executor.executeScript("argument[0].click();",myAccountButon);

        }

        jse.executeScript("arguments[0].scrollIntoView(true);",myAccountButon);

    }
}
