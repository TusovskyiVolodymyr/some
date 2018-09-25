package elements;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import utils.StringUtils;
import utils.WaitManager;

import java.util.Arrays;
import java.util.List;

import static utils.JSUtils.highlightElement;

public class BaseElement implements IElement {
    private final static Logger log = LogManager.getLogger(BaseElement.class);

    protected WebElement webElement;

    public BaseElement(WebElement webElement) {
        this.webElement = webElement;
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return webElement.getScreenshotAs(outputType);
    }

    @Override
    public void click() {
        WaitManager.waitUntilJSLoad();
        WaitManager.waitUntillBeClickable(webElement);
        highlightElement(webElement);
        webElement.click();
        WaitManager.waitUntilJSLoad();
        log.info(String.format("Element with locator: %s was clicked!", StringUtils.getXpathOfWebElement(webElement)));
    }

    @Override
    public void submit() {
        webElement.submit();
    }

    @Override
    public void sendKeys(CharSequence... charSequences) {
        WaitManager.waitUntilJSLoad();
        WaitManager.waitElementToBeVisible(webElement);
        highlightElement(webElement);
        webElement.sendKeys(charSequences);
        WaitManager.waitUntilJSLoad();
        log.info(String.format("In element with locator: %s was typed: %s", StringUtils.getXpathOfWebElement(webElement),
                Arrays.toString(charSequences)));
    }

    @Override
    public void clear() {
        webElement.click();
    }

    @Override
    public String getTagName() {
        return webElement.getTagName();
    }

    @Override
    public String getAttribute(String s) {
        return webElement.getAttribute(s);
    }

    @Override
    public boolean isSelected() {
        return webElement.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return webElement.isEnabled();
    }

    @Override
    public String getText() {
        WaitManager.waitUntilJSLoad();
        WaitManager.waitElementToBeVisible(webElement);
        highlightElement(webElement);
        String text = webElement.getText();
        log.info(String.format("Getting text from element with locator: %s text: %s",
                StringUtils.getXpathOfWebElement(webElement), webElement.getText()));
        return text;
    }

    @Override
    public List<WebElement> findElements(By by) {
        return webElement.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return webElement.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return webElement.getLocation();
    }

    @Override
    public Dimension getSize() {
        return webElement.getSize();
    }

    @Override
    public Rectangle getRect() {
        return webElement.getRect();
    }

    @Override
    public String getCssValue(String s) {
        return webElement.getCssValue(s);
    }

    @Override
    public String getXpath() {
        return StringUtils.getXpathOfWebElement(webElement);
    }

    @Override
    public String toString() {
        return webElement.toString();
    }

    @Override
    public WebElement getWebElement() {
        return webElement;
    }

    @Override
    public String getInputValue() {
        WaitManager.waitElementToBeVisible(webElement);
        WaitManager.waitUntilJSLoad();
        String text = getAttribute("value");
        log.info(String.format("Getting text from input with locator: %s text: %s", StringUtils.getXpathOfWebElement(webElement), text));
        WaitManager.waitUntilJSLoad();
        return text;
    }
}
