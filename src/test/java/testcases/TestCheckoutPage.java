package testcases;

import utilities.DriverSetup;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import pages.CartPage;
import pages.CheckoutPage;
import utilities.DataSet;

public class TestCheckoutPage extends DriverSetup {
    LoginPage loginPage = new LoginPage();
    ProductPage productPage = new ProductPage();
    CartPage cartPage = new CartPage();
    CheckoutPage checkoutPage = new CheckoutPage();

    @BeforeMethod
    public void setup() {
        loginPage.loadAWebPage("https://www.saucedemo.com/");
        loginPage.login(DataSet.STANDARD_USER, DataSet.VALID_PASSWORD);
    }

    @Test(description = "Verify successful checkout process")
    public void testCheckoutProcess() {
        // Add product to cart
        productPage.addProductToCart(0);
        productPage.goToCart();

        // Verify product in cart
        Assert.assertEquals(cartPage.getNumberOfProducts(), 1,
                "Product count in cart is incorrect");

        // Proceed to checkout
        cartPage.proceedToCheckout();

        // Fill checkout information
        checkoutPage.fillCheckoutInformation(
                DataSet.CHECKOUT_FIRST_NAME,
                DataSet.CHECKOUT_LAST_NAME,
                DataSet.CHECKOUT_POSTAL_CODE
        );

        // Complete order
        checkoutPage.completeOrder();

        // Verify success message
        Assert.assertEquals(checkoutPage.getSuccessMessage(),
                "Thank you for your order!",
                "Order completion message is incorrect");
    }
}
