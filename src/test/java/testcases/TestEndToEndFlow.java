package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import pages.CartPage;
import pages.CheckoutPage;
import utilities.DataSet;
import utilities.DriverSetup;

public class TestEndToEndFlow extends DriverSetup {
    LoginPage loginPage = new LoginPage();
    ProductPage productPage = new ProductPage();
    CartPage cartPage = new CartPage();
    CheckoutPage checkoutPage = new CheckoutPage();

    @Test(description = "Verify complete end-to-end order flow")
    public void testEndToEndOrderFlow() {
        // Login
        loginPage.loadAWebPage("https://www.saucedemo.com/");
        loginPage.login(DataSet.STANDARD_USER, DataSet.VALID_PASSWORD);
        Assert.assertTrue(productPage.isOnProductPage(),
                "Login was not successful");

        // Add multiple products
        productPage.addProductToCart(0);
        productPage.addProductToCart(1);
        Assert.assertEquals(productPage.getCartCount(), 2,
                "Cart count is incorrect");

        // Verify cart
        productPage.goToCart();
        Assert.assertEquals(cartPage.getNumberOfProducts(), 2,
                "Product count in cart is incorrect");

        // Complete checkout
        cartPage.proceedToCheckout();
        checkoutPage.fillCheckoutInformation(
                DataSet.CHECKOUT_FIRST_NAME,
                DataSet.CHECKOUT_LAST_NAME,
                DataSet.CHECKOUT_POSTAL_CODE
        );
        checkoutPage.completeOrder();

        // Verify order completion
        Assert.assertEquals(checkoutPage.getSuccessMessage(),
                "Thank you for your order!",
                "Order completion message is incorrect");

        // Logout
        productPage.logout();
        Assert.assertTrue(loginPage.isLoginFormDisplayed(),
                "Logout was not successful");
    }
}