package testcases;

import utilities.DriverSetup;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import pages.CartPage;
import utilities.DataSet;

public class TestCartPage extends DriverSetup {
    private LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;

    @BeforeMethod
    public void setup() {
        loginPage = new LoginPage();
        productPage = new ProductPage();
        cartPage = new CartPage();

        loginPage.loadAWebPage("https://www.saucedemo.com/");
        loginPage.login(DataSet.STANDARD_USER, DataSet.VALID_PASSWORD);
    }

    @Test
    @Description("Verify adding single product to cart")
    public void testAddSingleProductToCart() {
        productPage.addProductToCart(0);
        Assert.assertEquals(productPage.getCartCount(), 1,
                "Cart count does not match after adding single product");

        productPage.goToCart();
        Assert.assertEquals(cartPage.getNumberOfProducts(), 1,
                "Number of products in cart does not match expected count");
    }

    @Test
    @Description("Verify adding multiple products to cart")
    public void testAddMultipleProductsToCart() {
        productPage.addProductToCart(0);
        productPage.addProductToCart(1);
        Assert.assertEquals(productPage.getCartCount(), 2,
                "Cart count does not match after adding multiple products");

        productPage.goToCart();
        Assert.assertEquals(cartPage.getNumberOfProducts(), 2,
                "Number of products in cart does not match expected count");
    }

    @Test
    @Description("Verify removing products from cart")
    public void testRemoveProductsFromCart() {
        // Add two products
        productPage.addProductToCart(0);
        productPage.addProductToCart(1);
        Assert.assertEquals(productPage.getCartCount(), 2,
                "Initial cart count is incorrect");

        // Remove one product
        productPage.removeProduct(0);
        Assert.assertEquals(productPage.getCartCount(), 1,
                "Cart count incorrect after removing one product");

        // Remove second product
        productPage.removeProduct(1);
        Assert.assertEquals(productPage.getCartCount(), 0,
                "Cart should be empty after removing all products");
    }

    @Test
    @Description("Verify cart badge count updates correctly")
    public void testCartBadgeUpdates() {
        // Add products and verify count increases
        productPage.addProductToCart(0);
        Assert.assertEquals(productPage.getCartCount(), 1,
                "Cart badge count incorrect after adding first product");

        productPage.addProductToCart(1);
        Assert.assertEquals(productPage.getCartCount(), 2,
                "Cart badge count incorrect after adding second product");

        // Remove products and verify count decreases
        productPage.removeProduct(1);
        Assert.assertEquals(productPage.getCartCount(), 1,
                "Cart badge count incorrect after removing product");

        productPage.removeProduct(0);
        Assert.assertEquals(productPage.getCartCount(), 0,
                "Cart badge count should be zero when cart is empty");
    }

    @Test
    @Description("Verify cart persistence after page refresh")
    public void testCartPersistenceAfterRefresh() {
        productPage.addProductToCart(0);
        productPage.addProductToCart(1);
        Assert.assertEquals(productPage.getCartCount(), 2,
                "Initial cart count is incorrect");

        getDriver().navigate().refresh();

        Assert.assertEquals(productPage.getCartCount(), 2,
                "Cart count should persist after page refresh");
    }

    @Test
    @Description("Verify proceeding to checkout from cart")
    public void testProceedToCheckout() {
        productPage.addProductToCart(0);
        productPage.goToCart();
        cartPage.proceedToCheckout();

        Assert.assertTrue(getDriver().getCurrentUrl().contains("/checkout-step-one.html"),
                "User should be redirected to checkout page");
    }
}