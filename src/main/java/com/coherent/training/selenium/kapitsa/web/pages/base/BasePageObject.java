package com.coherent.training.selenium.kapitsa.web.pages.base;

import lombok.SneakyThrows;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BasePageObject {
    protected WebDriver driver;
    protected Select selector;
    protected WebDriverWait wbWait;
    private JavascriptExecutor js;
    private Actions action;
    private static final int DEFAULT_WAIT_TIME = 30;
    private static final long FREQUENCY_TIME = 500L;

    public BasePageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void waitForWebElement(ExpectedCondition<WebElement> expectedCondition, Integer timeoutInSeconds) {
        timeoutInSeconds = timeoutInSeconds != null ? timeoutInSeconds : DEFAULT_WAIT_TIME;

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(FREQUENCY_TIME))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        wait.until(expectedCondition);
    }

    private void waitForWebElements(ExpectedCondition<List<WebElement>> expectedCondition, Integer timeoutInSeconds) {
        timeoutInSeconds = timeoutInSeconds != null ? timeoutInSeconds : DEFAULT_WAIT_TIME;

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(FREQUENCY_TIME))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        wait.until(expectedCondition);
    }

    private void waitForBoolean(ExpectedCondition<Boolean> booleanExpectedCondition, Integer timeoutInSeconds) {
        timeoutInSeconds = timeoutInSeconds != null ? timeoutInSeconds : DEFAULT_WAIT_TIME;

        wbWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wbWait.until(booleanExpectedCondition);
    }

    protected void waitForElementToBeVisibleAt(WebElement element, Integer... timeoutInSeconds) {
        waitForWebElement(ExpectedConditions.visibilityOf(element),
                timeoutInSeconds.length > 0 ? timeoutInSeconds[0] : null);
    }

    protected void waitForElementsToBeVisibleBy(By locator, Integer... timeoutInSeconds){
        waitForWebElements(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator),
                timeoutInSeconds.length > 0 ? timeoutInSeconds[0] : null);
    }

    protected void waitForElementToBeStaleness(WebElement element, Integer... timeoutInSeconds){
        waitForBoolean(ExpectedConditions.stalenessOf(element),
                timeoutInSeconds.length > 0 ? timeoutInSeconds[0] : null);
    }

    protected void waitForElementsToBeStaleness(List<WebElement> elements){
        for(WebElement element : elements){
            waitForElementToBeStaleness(element);
        }
    }

    protected void waitForElementToBeClickable(WebElement locator, Integer... timeoutInSeconds) {
        waitForWebElement(ExpectedConditions.elementToBeClickable(locator),
                timeoutInSeconds.length > 0 ? timeoutInSeconds[0] : null);
    }

    protected void waitUntilTextToBePresentedInElementLocated(WebElement locator, String text, Integer... timeoutInSeconds) {
        waitForBoolean(ExpectedConditions.textToBePresentInElement(locator, text),
                timeoutInSeconds.length > 0 ? timeoutInSeconds[0] : null);

    }

    protected void waitUntilAttributeToBeNotEmpty(WebElement element, String attribute, Integer... timeoutInSeconds) {
        waitForBoolean(ExpectedConditions.attributeToBeNotEmpty(element, attribute),
                timeoutInSeconds.length > 0 ? timeoutInSeconds[0] : null);
    }

    protected List<WebElement> findAll(By locator) {
        waitForElementsToBeVisibleBy(locator);

        return driver.findElements(locator);
    }

    protected WebElement findElementAtElement(WebElement parentElement, By childLocator) {
        return parentElement.findElement(childLocator);
    }

    protected List<WebElement> findElementsAtElement(WebElement parentElement, By childLocator) {
        return parentElement.findElements(childLocator);
    }

    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    protected void clickOn(WebElement element) {
        waitForElementToBeClickable(element);

        element.click();
    }

    protected void type(WebElement element, String value) {
        element.sendKeys(value);
    }

    protected void refreshPage() {
        driver.navigate().refresh();
    }

    protected List<WebElement> multiselect(WebElement list, int numberToSelect) {
        Random random = new Random();

        selector = new Select(list);

        int numberOfOptions = selector.getOptions().size();

        assert numberOfOptions >= numberToSelect;

        for (int i = 0; i < numberToSelect - 1; i++) {
            int nextIndex = random.nextInt(numberOfOptions);
            selector.selectByIndex(nextIndex);
        }

        return selector.getAllSelectedOptions();
    }

    protected void selectDropdownOptionByValue(WebElement dropdown, String value) {
        selector = new Select(dropdown);

        selector.selectByValue(value);
    }

    protected Alert openAlert(WebElement alertButton) {
        clickOn(alertButton);

        return driver.switchTo().alert();
    }

    protected void confirmAlert(WebElement alertButton) {
        openAlert(alertButton).accept();
    }

    protected void declineAlert(WebElement alertButton) {
        openAlert(alertButton).dismiss();
    }

    protected String getTitle() {
        return driver.getTitle();
    }

    protected void scrollToElement(WebElement element) {
        js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    protected void hoverOver(WebElement hoverElement) {
        action = new Actions(driver);

        action.moveToElement(hoverElement).perform();
    }

    protected void switchToCurrentWindow() {
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    protected void selectCheckbox(WebElement checkbox) {
        scrollToElement(checkbox);

        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkbox);
    }

    @SneakyThrows
    protected By getWebElementsBy(List<WebElement> element) {
        Object proxyOrigin = FieldUtils.readField(element, "h", true);
        Object locator = FieldUtils.readField(proxyOrigin, "locator", true);
        Object findBy = FieldUtils.readField(locator, "by", true);

        if (findBy != null) {
            return (By) findBy;
        }

        return null;
    }
}
