package testcases;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import utilities.DataSet;
import utilities.DriverSetup;

public class TestLogoutFunctionality extends DriverSetup {
    LoginPage loginPage = new LoginPage();
    ProductPage productPage = new ProductPage();

    @BeforeMethod
    public void setup() {
        loginPage.loadAWebPage("https://www.saucedemo.com/");
        loginPage.login(DataSet.STANDARD_USER, DataSet.VALID_PASSWORD);
    }

    @Test(description = "Verify successful logout")
    public void testLogout() {
        Assert.assertTrue(productPage.isOnProductPage(),
                "User is not on product page");

        productPage.logout();

        Assert.assertTrue(loginPage.isLoginFormDisplayed(),
                "Login form is not displayed after logout");
        Assert.assertEquals(loginPage.getLoadedPageUrl(),
                "https://www.saucedemo.com/",
                "URL is incorrect after logout");
    }
}