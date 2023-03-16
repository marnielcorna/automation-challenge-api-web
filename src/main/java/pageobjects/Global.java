package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Global {

    private WebDriver driver;


    public Global(WebDriver driver) {
        this.driver = driver;

    }

    public void clickOnButton(String btn) {
        try {
            WebElement button = driver.findElement(By.xpath("//button[contains(text(),'" + btn + "')]"));
            button.isDisplayed();
            button.click();
            waitForSeconds(5);

        } catch (Exception e) {
            System.out.println("Can't click button, retrying again...");
        }
    }

    public void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getTextFromElement(String text) {
        WebElement menu = driver.findElement(By.xpath("//a[contains(text(),'"+ text +"')]"));
        return menu.getText();
    }

    public String textAddToCartButton() {
        WebElement menu = driver.findElement(By.xpath("//a[contains(text(),'Add to cart')]"));
        if (menu.isDisplayed()) {
            return menu.getText();
        } else {
            throw new AssertionError("Add to cart button is not visible");
        }
    }

    public void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
    }

    public void deleteFromCart(String text_menu) {
        try{
            WebElement menu = driver.findElement(By.xpath("//td[contains(text(),'"+text_menu+"')]/../td/a"));
            menu.isDisplayed();
            menu.click();
        }catch (Exception e){
            System.out.println("Cannot permform delete because: " + e);
        }

    }

    public void clickJsExecutor(String nameBtn){
        waitForPageLoad();
        waitForSeconds(3);
        WebElement element = driver.findElement(By.xpath("//button[contains(text(),'" +nameBtn+ "')]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public String captureTotalPrice() {
        WebElement totalPriceFromCart = driver.findElement(By.id("totalp"));
        return totalPriceFromCart.getText();

    }
}
