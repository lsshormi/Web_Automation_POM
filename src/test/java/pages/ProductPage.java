package pages;

import org.openqa.selenium.By;
import java.util.List;
import java.util.ArrayList;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebElement;

import static utilities.DriverSetup.getDriver;

public class ProductPage extends BasePage{
    private final By productTitle = By.className("title");
    private final By addToCartButtons = By.cssSelector("[data-test^='add-to-cart']");
    private final By removeButtons = By.cssSelector("[data-test^='remove']");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");
    private final By sortDropdown = By.className("product_sort_container");
    private final By productNames = By.className("inventory_item_name");
    private final By productPrices = By.className("inventory_item_price");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");

    public boolean isOnProductPage() {
        return getElement(productTitle).getText().equals("Products");
    }

    public void addProductToCart(int index) {
        List<WebElement> buttons = getDriver().findElements(addToCartButtons);
        if (index < buttons.size()) {
            buttons.get(index).click();
        }
    }

    public void removeProduct(int index) {
        List<WebElement> buttons = getDriver().findElements(removeButtons);
        if (index < buttons.size()) {
            buttons.get(index).click();
        }
    }

    public int getCartCount() {
        try {
            return Integer.parseInt(getElement(cartBadge).getText());
        } catch (Exception e) {
            return 0;
        }
    }

    public void goToCart() {
        clickOnElement(cartLink);
    }


    public void sortProducts(String sortOption) {
        waitForElementToBeClickable(sortDropdown);
        selectFromDropdown(sortDropdown, sortOption);
        waitForPageLoad();
    }

    public List<String> getProductNames() {
        List<WebElement> elements = getDriver().findElements(productNames);
        List<String> names = new ArrayList<>();
        for (WebElement element : elements) {
            names.add(element.getText());
        }
        return names;
    }
    public List<Double> getProductPrices() {
        List<WebElement> elements = getElements(productPrices);
        List<Double> prices = new ArrayList<>();
        for (WebElement element : elements) {
            String priceText = element.getText().replace("$", "").trim();
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }


    public void logout() {
        clickOnElement(menuButton);
        waitForElementToBeClickable(logoutLink);
        clickOnElement(logoutLink);
    }
}






