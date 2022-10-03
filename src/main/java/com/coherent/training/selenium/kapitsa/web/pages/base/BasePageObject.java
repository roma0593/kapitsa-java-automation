package com.coherent.training.selenium.kapitsa.web.pages.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BasePageObject {
    protected WebDriver driver;
    protected Select selector;
    protected WebDriverWait wbWait;
    private static final int DEFAULT_WAIT_TIME = 30;
    private static final long FREQUENCY_TIME = 250L;

    public BasePageObject(WebDriver driver) {
        this.driver = driver;
    }

    private void waitForWebElement(ExpectedCondition<WebElement> expectedCondition, Integer timeoutInSeconds){
        timeoutInSeconds = timeoutInSeconds != null ? timeoutInSeconds : DEFAULT_WAIT_TIME;

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(FREQUENCY_TIME))
                .ignoring(NoSuchElementException.class);
        wait.until(expectedCondition);
    }

    private void waitForBoolean(ExpectedCondition<Boolean> booleanExpectedCondition, Integer timeoutInSeconds){
        timeoutInSeconds = timeoutInSeconds != null ? timeoutInSeconds : DEFAULT_WAIT_TIME;

        wbWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wbWait.until(booleanExpectedCondition);
    }

    protected void waitForElementToBeVisibleAt(By locator, Integer... timeoutInSeconds){
        waitForWebElement(ExpectedConditions.visibilityOfElementLocated(locator),
                timeoutInSeconds.length > 0 ? timeoutInSeconds[0] : null);
    }

    protected void waitUntilTextToBePresentedInElementLocated(By locator, String text, Integer... timeoutInSeconds){
        waitForBoolean(ExpectedConditions.textToBePresentInElementLocated(locator, text),
                timeoutInSeconds.length > 0 ? timeoutInSeconds[0] : null);

    }

    protected void waitUntilAttributeToBeNotEmpty(By locator, String attribute, Integer... timeoutInSeconds){
        waitForBoolean(ExpectedConditions.attributeToBeNotEmpty(find(locator), attribute),
                timeoutInSeconds.length > 0 ? timeoutInSeconds[0] : null);
    }

    protected WebElement find(By locator){
        return driver.findElement(locator);
    }

    protected WebElement findElementAtElement(By parentLocator, By childLocator){
        return find(parentLocator).findElement(childLocator);
    }

    protected List<WebElement> findElementsAtElement(WebElement parentElement, By childLocator){
        return parentElement.findElements(childLocator);
    }

    protected void clickOn(By locator){
        find(locator).click();
    }

    protected void type(By locator, String value){
        find(locator).sendKeys(value);
    }

    protected void refreshPage(){
        driver.navigate().refresh();
    }

    protected List<WebElement> multiselect(By list, int numberToSelect){
        Random random = new Random();

        selector = new Select(find(list));

        int numberOfOptions = selector.getOptions().size();

        assert numberOfOptions >= numberToSelect;

        for (int i = 0; i < numberToSelect - 1; i++) {
            int nextIndex = random.nextInt(numberOfOptions);
            selector.selectByIndex(nextIndex);
        }

        return selector.getAllSelectedOptions();
    }

    protected void selectDropdownOptionByValue(By dropdown, String value){
        selector = new Select(find(dropdown));

        selector.selectByValue(value);
    }

    protected Alert openAlert(By alertButton){
        clickOn(alertButton);

        return driver.switchTo().alert();
    }

    protected void confirmAlert(By alertButton){
        openAlert(alertButton).accept();
    }

    protected void declineAlert(By alertButton){
        openAlert(alertButton).dismiss();
    }

    protected String getPageTitle(){
        return driver.getTitle();
    }
}
