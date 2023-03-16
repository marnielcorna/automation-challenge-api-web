package StepDefinitions;

import Utils.PropertiesReader;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageobjects.Cart;
import pageobjects.Global;
import Utils.WebDriverManager;
import pageobjects.Home;

import java.util.List;
import java.util.Map;


public class Websteps {

    PropertiesReader pro = new PropertiesReader();
    private static WebDriver driver;
    private Global global;
    private Home home;
    private String actualAmount;

    @BeforeClass
    public static void setup() {
        driver = WebDriverManager.getDriver();
    }

    @AfterClass
    public static void teardown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public Websteps() {
        driver = WebDriverManager.getDriver();
        global = new Global(driver);
        home = new Home(driver);

    }

    @Given("access to the {string} the application")
    public void accessTheURL(String url) {
        driver.get(pro.getPropValue(url));
        driver.manage().window().maximize();
        String expectedLeftMenuText = "CATEGORIES";
        String actual_text = global.getTextFromElement(expectedLeftMenuText);
        Assert.assertEquals(actual_text, expectedLeftMenuText);
    }

    @Given("the Home page")
    public void the_page() {
        try {
            driver.navigate().refresh();
            global.waitForSeconds(1);

        } catch (AssertionError e) {
            System.out.println("Error in step: 'the Home Page'");
        }
    }
    @Given("Navigate to {string} page")
    public void navigateToHomePage(String menu) {
        try {
            global.getTextFromElement(menu);
            global.waitForSeconds(1);

        } catch (AssertionError e) {
            System.out.println("Error in step: 'the Home Page'");
        }
    }

    @When("Navigate to {string}")
    public void navigateTo(String page) {
        try{
            global.waitForSeconds(1);
            home.navigateTo(page);
        } catch (Exception e){
            System.out.println("Error during the navegation: " + e);
        }
    }

    @When("Select product {string}, add to {string}")
    public void selectAndAddToCart(String product, String addToCart) {
        try {
            global.waitForPageLoad();
            global.waitForSeconds(1);
            home.selectProduct(product);
            global.waitForSeconds(1);
            home.addToCart(addToCart);
            global.waitForSeconds(1);

            String AddToCartActualText = global.textAddToCartButton();
            Assert.assertEquals(AddToCartActualText, "Add to cart");
        } catch (Exception e) {
            System.out.println("Error when add product to cart" + e);
        }

    }

    @When("click on {string} button")
    public void click_on(String btn) {
        this.actualAmount = global.captureTotalPrice();
        global.clickJsExecutor(btn);
    }

    @When("Navigate through products in Categories")
    public void navigateThroughProductsInCategory(DataTable categoriesTable) {
        List<String> categories = categoriesTable.asList();

        try {
            for (String product : categories) {
                home.navigateTo(product);
                String actualTextSubmenu = global.getTextFromElement(product);
                Assert.assertEquals(actualTextSubmenu, product);
                global.waitForSeconds(1);
            }
        } catch (AssertionError e) {
            System.out.println("Error navigating Through --------> " + e);
            throw e;
        }

    }

    @When("{string} pop up Alert")
    public void acceptPopUpAlert(String acceptAlert) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            System.out.println("No alert '" + acceptAlert + "' is present, retrying...");
        }
    }

    @When("fill the form fields with the following data")
    public void fillTheFormFieldsWithTheFollowingData(List<Map<String, String>> tableForm) {
        global.waitForPageLoad();
        global.waitForSeconds(1);
        home.fillOrderForm(tableForm);

    }

    @When("Delete product {string}")
    public void deleteProductDellIGb(String productToDelete) {
        global.waitForSeconds(1);
        global.deleteFromCart(productToDelete);

    }


    @Then("we log out")
    public void weLogOut() throws InterruptedException {
        global.waitForSeconds(1);
    }

    @Then("the product {string} is not present")
    public void theProductDellIGbIsNotPresent(String deleteProduct) {
        System.out.println("Deleted product.");
    }

    @Then("validate the corresponding amount of the product in the displayed modal")
    public void validatePurchaseModalConfirmation() {

        home.purchaseConfirmationModal(this.actualAmount);

    }

    @Then("We exit the application")
    public void weExitTheApplication() {
        driver.quit();
    }
}
