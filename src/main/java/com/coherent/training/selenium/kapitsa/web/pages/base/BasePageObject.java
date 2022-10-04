package com.coherent.training.selenium.kapitsa.web.pages.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
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
        PageFactory.initElements(driver, this);
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

    protected void waitForElementToBeVisibleAt(WebElement element, Integer... timeoutInSeconds){
        waitForWebElement(ExpectedConditions.visibilityOf(element),
                timeoutInSeconds.length > 0 ? timeoutInSeconds[0] : null);
    }

    protected void waitUntilTextToBePresentedInElementLocated(WebElement element, String text, Integer... timeoutInSeconds){
        waitForBoolean(ExpectedConditions.textToBePresentInElement(element, text),
                timeoutInSeconds.length > 0 ? timeoutInSeconds[0] : null);

    }

    protected void waitUntilAttributeToBeNotEmpty(WebElement element, String attribute, Integer... timeoutInSeconds){
        waitForBoolean(ExpectedConditions.attributeToBeNotEmpty(element, attribute),
                timeoutInSeconds.length > 0 ? timeoutInSeconds[0] : null);
    }

    protected WebElement findElementAtElement(WebElement parentElement, By childLocator){
        return parentElement.findElement(childLocator);
    }

    protected List<WebElement> findElementsAtElement(WebElement parentElement, By childLocator){
        return parentElement.findElements(childLocator);
    }

    protected void clickOn(WebElement element){
        element.click();
    }

    protected void type(WebElement element, String value){
        element.sendKeys(value);
    }

    protected void refreshPage(){
        driver.navigate().refresh();
    }

    protected List<WebElement> multiselect(WebElement list, int numberToSelect){
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

    protected void selectDropdownOptionByValue(WebElement dropdown, String value){
        selector = new Select(dropdown);

        selector.selectByValue(value);
    }

    protected Alert openAlert(WebElement alertButton){
        clickOn(alertButton);

        return driver.switchTo().alert();
    }

    protected void confirmAlert(WebElement alertButton){
        openAlert(alertButton).accept();
    }

    protected void declineAlert(WebElement alertButton){
        openAlert(alertButton).dismiss();
    }

    protected String getTitle(){
        return driver.getTitle();
    }
}
