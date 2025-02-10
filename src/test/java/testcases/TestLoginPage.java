package testcases;

import utilities.DriverSetup;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;

    public class TestLoginPage extends DriverSetup {
        LoginPage loginPage = new LoginPage();
        ProductPage productPage = new ProductPage();

        @Test(description = "Verify successful login with valid credentials")
        public void testValidLogin() {
            loginPage.loadAWebPage("https://www.saucedemo.com/");
            loginPage.login("standard_user", "secret_sauce");
            Assert.assertTrue(productPage.isOnProductPage(), "Login was not successful");
        }

        @Test(description = "Verify error message with invalid credentials")
        public void testInvalidLogin() {
            loginPage.loadAWebPage("https://www.saucedemo.com/");
            loginPage.login("wrong_user", "secret_sauce");
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed");
        }

        @Test(description = "Verify locked out user cannot login")
        public void testLockedOutUser() {
            loginPage.loadAWebPage("https://www.saucedemo.com/");
            loginPage.login("locked_out_user", "secret_sauce");
            Assert.assertTrue(loginPage.getErrorMessageText().contains("locked out"),
                    "Incorrect error message for locked out user");
        }
    }
