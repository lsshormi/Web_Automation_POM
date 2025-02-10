package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.List;

import static utilities.DriverSetup.getDriver;

public class BasePage {
    private final int DEFAULT_TIMEOUT = 10;

    public WebElement getElement(By locator) {
        return getDriver().findElement(locator);
    }

    public List<WebElement> getElements(By locator) {
        return getDriver().findElements(locator);
    }

    public void clickOnElement(By locator) {
        try {
            waitForElementToBeClickable(locator);
            getElement(locator).click();
        } catch (ElementClickInterceptedException e) {
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].click();", getElement(locator));
        }
    }

    public void writeOnElement(By locator, String text) {
        waitForElementToBeVisible(locator);
        WebElement element = getElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void loadAWebPage(String url) {
        getDriver().get(url);
    }

    public String getLoadedPageUrl() {
        return getDriver().getCurrentUrl();
    }

    public String getLoadedPageTitle() {
        return getDriver().getTitle();
    }

    public Boolean isElementVisible(By locator) {
        try {
            return getElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean isElementPresent(By locator) {
        try {
            return !getElements(locator).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementToBeInvisible(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public String getElementText(By locator) {
        waitForElementToBeVisible(locator);
        return getElement(locator).getText();
    }

    public void selectFromDropdown(By locator, String visibleText) {
        waitForElementToBeVisible(locator);
        Select dropdown = new Select(getElement(locator));
        dropdown.selectByVisibleText(visibleText);
    }

    public void selectFromDropdownByValue(By locator, String value) {
        waitForElementToBeVisible(locator);
        Select dropdown = new Select(getElement(locator));
        dropdown.selectByValue(value);
    }

    public void selectFromDropdownByIndex(By locator, int index) {
        waitForElementToBeVisible(locator);
        Select dropdown = new Select(getElement(locator));
        dropdown.selectByIndex(index);
    }

    public void hoverOverElement(By locator) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(getElement(locator)).perform();
    }

    public void scrollToElement(By locator) {
        WebElement element = getElement(locator);
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void refreshPage() {
        getDriver().navigate().refresh();
    }

    public String getElementAttribute(By locator, String attribute) {
        waitForElementToBeVisible(locator);
        return getElement(locator).getAttribute(attribute);
    }

    public void addScreenshot(String name) {
        Allure.addAttachment(name, new ByteArrayInputStream(
                ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES)));
    }

    public void switchToFrame(By frameLocator) {
        waitForElementToBeVisible(frameLocator);
        getDriver().switchTo().frame(getElement(frameLocator));
    }

    public void switchToDefaultContent() {
        getDriver().switchTo().defaultContent();
    }

    public void acceptAlert() {
        getDriver().switchTo().alert().accept();
    }

    public void dismissAlert() {
        getDriver().switchTo().alert().dismiss();
    }

    public String getAlertText() {
        return getDriver().switchTo().alert().getText();
    }

    protected void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }
}