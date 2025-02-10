package testcases;

import utilities.DriverSetup;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import utilities.DataSet;
import java.util.List;
import java.util.ArrayList;

public class TestProductPage extends DriverSetup {
    private LoginPage loginPage;
    private ProductPage productPage;

    @BeforeMethod
    public void setup() {
        loginPage = new LoginPage();
        productPage = new ProductPage();
        loginPage.loadAWebPage("https://www.saucedemo.com/");
        loginPage.login(DataSet.STANDARD_USER, DataSet.VALID_PASSWORD);
    }

    @Test(description = "Verify products can be sorted by name ascending")
    public void testProductSortingByNameAscending() {
        productPage.sortProducts(DataSet.SORT_NAME_AZ);
        List<String> names = productPage.getProductNames();

        for (int i = 0; i < names.size() - 1; i++) {
            Assert.assertTrue(names.get(i).compareTo(names.get(i + 1)) <= 0,
                    "Products are not sorted correctly by name in ascending order");
        }
    }

    @Test(description = "Verify products can be sorted by name descending")
    public void testProductSortingByNameDescending() {
        productPage.sortProducts(DataSet.SORT_NAME_ZA);
        List<String> names = productPage.getProductNames();

        for (int i = 0; i < names.size() - 1; i++) {
            Assert.assertTrue(names.get(i).compareTo(names.get(i + 1)) >= 0,
                    "Products are not sorted correctly by name in descending order");
        }
    }

    @Test(description = "Verify products can be sorted by price ascending")
    public void testProductSortingByPriceLowToHigh() {
        productPage.sortProducts(DataSet.SORT_PRICE_LOW_HIGH);
        List<Double> prices = productPage.getProductPrices();

        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(prices.get(i) <= prices.get(i + 1),
                    "Products are not sorted correctly by price in ascending order");
        }
    }

    @Test(description = "Verify products can be sorted by price descending")
    public void testProductSortingByPriceHighToLow() {
        productPage.sortProducts(DataSet.SORT_PRICE_HIGH_LOW);
        List<Double> prices = productPage.getProductPrices();

        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(prices.get(i) >= prices.get(i + 1),
                    "Products are not sorted correctly by price in descending order");
        }
    }

    @Test(description = "Verify cart badge count")
    public void testCartBadgeCount() {
        productPage.addProductToCart(0);
        Assert.assertEquals(productPage.getCartCount(), 1,
                "Cart count incorrect after adding item");

        productPage.addProductToCart(1);
        Assert.assertEquals(productPage.getCartCount(), 2,
                "Cart count incorrect after adding second item");

        productPage.removeProduct(0);
        Assert.assertEquals(productPage.getCartCount(), 1,
                "Cart count incorrect after removing item");
    }
}