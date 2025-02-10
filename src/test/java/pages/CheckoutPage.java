package pages;

import org.openqa.selenium.By;

public class CheckoutPage extends BasePage {
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By finishButton = By.id("finish");
    private final By successMessage = By.className("complete-header");

    public void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        writeOnElement(firstNameField, firstName);
        writeOnElement(lastNameField, lastName);
        writeOnElement(postalCodeField, postalCode);
        clickOnElement(continueButton);
    }

    public void completeOrder() {
        clickOnElement(finishButton);
    }

    public String getSuccessMessage() {
        return getElement(successMessage).getText();
    }
}