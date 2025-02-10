package pages;

import org.openqa.selenium.By;

import static utilities.DriverSetup.getDriver;

public class CartPage extends BasePage {
    private final By cartItems = By.className("cart_item");
    private final By checkoutButton = By.id("checkout");

    public int getNumberOfProducts() {
        return getDriver().findElements(cartItems).size();
    }

    public void proceedToCheckout() {
        clickOnElement(checkoutButton);
    }
}