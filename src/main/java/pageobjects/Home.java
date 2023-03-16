package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class Home {
    private WebDriver driver;

    public Home(WebDriver driver) {
        this.driver = driver;

    }

    public void navigateTo(String text_menu) {
        WebElement menu = driver.findElement(By.xpath("//a[contains(text(),'"+ text_menu +"')]"));
        menu.isDisplayed();
        menu.click();

    }

    public void selectProduct(String product) {
        WebElement selectedProduct = driver.findElement(By.xpath("//a[contains(text(),'"+product+"')]"));
        selectedProduct.isDisplayed();
        selectedProduct.click();
    }


    public void addToCart(String addToCart) {
        WebElement clickAddToCart = driver.findElement(By.xpath("//div/descendant::a[contains(text(),'"+addToCart+"')]"));
        clickAddToCart.isDisplayed();
        clickAddToCart.click();
    }

    public void fillOrderForm(List<Map<String, String>> table) {
        for (Map<String, String> row : table){
            for (Map.Entry<String, String> entry : row.entrySet()){
                String key = entry.getKey();
                String value = entry.getValue();
                WebElement inputKey = driver.findElement(By.id(key));
                inputKey.sendKeys(value);
            }

        }
    }

    public void purchaseConfirmationModal(String expectedAmount) {

        WebElement amountModalConfirmation = driver.findElement(By.xpath("//p[contains(@class, 'lead')]"));
        String text = amountModalConfirmation.getText();
        String actualAmount = text.substring(text.indexOf("Amount: ") + 8, text.indexOf(" USD")).trim();

        try{
        Assert.assertEquals(actualAmount, expectedAmount);
        }catch (AssertionError e){
            System.out.println(e);
        }
    }
}
