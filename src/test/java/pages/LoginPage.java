package pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private final By loginForm = By.id("login_button_container");
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.xpath("//h3[@data-test='error']");

    public boolean isLoginFormDisplayed() {
        return isElementVisible(loginForm);
    }
    public void login(String username, String password) {
        writeOnElement(usernameField, username);
        writeOnElement(passwordField, password);
        clickOnElement(loginButton);
    }

    public boolean isErrorMessageDisplayed() {
        return isElementVisible(errorMessage);
    }

    public String getErrorMessageText() {
        return getElement(errorMessage).getText();
    }

}


