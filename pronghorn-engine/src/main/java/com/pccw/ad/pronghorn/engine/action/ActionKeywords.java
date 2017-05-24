package com.pccw.ad.pronghorn.engine.action;


import com.pccw.ad.pronghorn.engine.data.Constant;
import com.pccw.ad.pronghorn.engine.data.DocObjectModel;
import com.pccw.ad.pronghorn.engine.exception.ActionKeywordException;
import com.relevantcodes.extentreports.IExtentTestClass;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.pccw.ad.pronghorn.common.validation.Validate.isCSSSelector;
import static com.pccw.ad.pronghorn.engine.data.Constant.OUTPUT_BASE_PATH;
import static com.pccw.ad.pronghorn.engine.data.ObjectType.LIST_BOX;
import static com.pccw.ad.pronghorn.engine.data.ObjectType.TXTBOX;


/**
 * Created by FaustineP on 07/03/2017.
 */
public class ActionKeywords {

    final static Logger logger = Logger.getLogger(ActionKeywords.class);

    public static WebDriver WEB_DRIVER = null;
    public static App APP_DRIVER = null;
    public static Screen SCREEN = null;

    public static HashMap<String, String> SELECTOR = new HashMap<>();
    public static HashMap<String, String> REPOSITORY = new HashMap<>();

    public static void addSelector(HashMap<String, String> selector) {
        SELECTOR.putAll(selector);
    }

    public static void clearSelector() {
        SELECTOR.clear();
    }

    /**
     * @param report      IExtentTestClass interface for report logging
     * @param description Step description based on test script .xlsx
     * @param testCaseId  Test Case Identifier
     * @param object      Key property selector to identify the CSS Selector path
     * @param data        Input data
     */
    public static void openApplication(IExtentTestClass report, String description, String testCaseId,
                                       String object, String data) {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        switch (data) {
            case "IE":
            case "Internet Explorer":
                System.setProperty(Constant.KEY_IE_DRIVE, Constant.FILE_PATH_DRIVER_IE_X32_EXE);
                capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                WEB_DRIVER = new InternetExplorerDriver(capabilities);
                WEB_DRIVER.manage().window().maximize();
                report.log(LogStatus.INFO, description, data.toUpperCase());
                break;
            case "FF":
            case "FireFox":
                System.setProperty(Constant.KEY_GECKO_DRIVER, Constant.FILE_PATH_GECKO_DRIVER_EXE);
                WEB_DRIVER = new FirefoxDriver();
                WEB_DRIVER.manage().window().maximize();
                report.log(LogStatus.INFO, description, data.toUpperCase());
                break;
            case "Chrome":
                System.setProperty(Constant.KEY_CHROME_DRIVER, Constant.FILE_PATH_CHROME_DRIVER_EXE);
                capabilities = DesiredCapabilities.chrome();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("test-type");
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                WEB_DRIVER = new ChromeDriver(capabilities);
                WEB_DRIVER.manage().window().maximize();
                report.log(LogStatus.INFO, description, data.toUpperCase());
                break;
            default:
                APP_DRIVER = App.open(data);
                report.log(LogStatus.INFO, description, data.toUpperCase());
        }
        WEB_DRIVER.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("openApplication " + data);
    }

    /**
     * @param report      IExtentTestClass interface for report logging
     * @param description Step description based on test script .xlsx
     * @param testCaseId  Test Case Identifier
     * @param object      Key property selector to identify the CSS Selector path
     * @param data        Input data
     */
    public static void navigate(IExtentTestClass report, String description, String testCaseId,
                                String object, String data) {
        WEB_DRIVER.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WEB_DRIVER.get(data);
        report.log(LogStatus.INFO, description, data);
        logger.info("navigate " + data);
    }

    /**
     * @param report      IExtentTestClass interface for report logging
     * @param description Step description based on test script .xlsx
     * @param testCaseId  Test Case Identifier
     * @param object      Key property selector to identify the CSS Selector path
     * @param data        Input data
     */
    public static void closeApplication(IExtentTestClass report, String description, String testCaseId,
                                        String object, String data) {

        if (APP_DRIVER != null) {
            APP_DRIVER.close();
        }
        if (WEB_DRIVER != null) {
            WEB_DRIVER.quit();
        }
        report.log(LogStatus.INFO, description, data);
        logger.info("closing application...");
    }

    /**
     * @param report      IExtentTestClass interface for report logging
     * @param description Step description based on test script .xlsx
     * @param testCaseId  Test Case Identifier
     * @param object      Key property selector to identify the CSS Selector path
     * @param data        Input data
     */
    public static void inputTxt(IExtentTestClass report, String description, String testCaseId,
                                String object, String data) throws NoSuchElementException, FindFailed {
        try {
            if (!isCSSSelector(SELECTOR.get(object).toLowerCase())) {
                WEB_DRIVER.findElement(By.cssSelector(SELECTOR.get(object))).clear();
                WEB_DRIVER.findElement(By.cssSelector(SELECTOR.get(object))).sendKeys(data);
            } else {
                SCREEN = new Screen();
                Pattern image = new Pattern(SELECTOR.get(object));
                SCREEN.wait(image, 10);
                SCREEN.click();
                SCREEN.type(data);
            }

            report.log(LogStatus.INFO, description, data);
            logger.info("inputTxt [" + object + "]" + "[" + data + "]");
        } catch (NoSuchElementException | FindFailed exception) {
            report.log(LogStatus.ERROR, description, data);
            logger.error(exception);
            throw exception;
        }
    }

    /**
     * @param report      IExtentTestClass interface for report logging
     * @param description Step description based on test script .xlsx
     * @param testCaseId  Test Case Identifier
     * @param object      Key property selector to identify the CSS Selector path
     * @param data        Input data
     */
    public static void click(IExtentTestClass report, String description, String testCaseId,
                             String object, String data) throws FindFailed {

        if (!isCSSSelector(SELECTOR.get(object).toLowerCase())) {
            WEB_DRIVER.findElement(By.cssSelector(SELECTOR.get(object.trim()))).click();
        } else {
            SCREEN = new Screen();
            Pattern image = new Pattern(SELECTOR.get(object));
            try {
                SCREEN.wait(image, 10);
                SCREEN.click();
                report.log(LogStatus.INFO, description, data);
                logger.info("click [" + object + "]" + "[" + data + "]");
            } catch (FindFailed findFailed) {
                logger.error(findFailed);
                throw findFailed;
            }
        }
    }

    public static void doubleClick(IExtentTestClass report, String description, String testCaseId,
                                   String object, String data) throws FindFailed {
        try {
            SCREEN = new Screen();
            Pattern image = new Pattern(SELECTOR.get(object));
            SCREEN.wait(image, 10);
            SCREEN.doubleClick();
            report.log(LogStatus.INFO, description, data);
            logger.info("click [" + object + "]" + "[" + data + "]");
        } catch (FindFailed findFailed) {
            report.log(LogStatus.ERROR, description, data);
            logger.error(findFailed);
            throw findFailed;
        }
    }

    public static void rightClick(IExtentTestClass report, String description, String testCaseId,
                                  String object, String data) throws FindFailed {

        try {
            SCREEN = new Screen();
            Pattern image = new Pattern(SELECTOR.get(object));
            SCREEN.wait(image, 10);
            SCREEN.rightClick();
            report.log(LogStatus.INFO, description, data);
            logger.info("click [" + object + "]" + "[" + data + "]");
        } catch (FindFailed findFailed) {
            report.log(LogStatus.ERROR, description, data);
            logger.error(findFailed);
            throw findFailed;
        }
    }


    /**
     * @param report      IExtentTestClass interface for report logging
     * @param description Step description based on test script .xlsx
     * @param testCaseId  Test Case Identifier
     * @param object      Key property selector to identify the CSS Selector path
     * @param data        Input data
     */
    public static void inputDate(IExtentTestClass report, String description, String testCaseId, String object,
                                 String data) throws NoSuchElementException, FindFailed {
        // TODO : provide date format validation
        try {
            if (!isCSSSelector(SELECTOR.get(object).toLowerCase())) {
                WEB_DRIVER.findElement(By.cssSelector(SELECTOR.get(object.trim()))).clear();
                WEB_DRIVER.findElement(By.cssSelector(SELECTOR.get(object.trim()))).sendKeys(data);
                WEB_DRIVER.findElement(By.cssSelector(SELECTOR.get(object.trim()))).sendKeys(Keys.TAB);
            } else {
                SCREEN = new Screen();
                Pattern image = new Pattern(SELECTOR.get(object));
                SCREEN.wait(image, 10);
                SCREEN.click();
                SCREEN.type(data);
                SCREEN.type(Key.TAB);
            }

            report.log(LogStatus.INFO, description, data);
            logger.info("inputDate [" + object + "]" + "[" + data + "]");
        } catch (NoSuchElementException | FindFailed exception) {
            report.log(LogStatus.INFO, description, data);
            logger.error(exception);
            throw exception;
        }
    }

    /**
     * @param report      IExtentTestClass interface for report logging
     * @param description Step description based on test script .xlsx
     * @param testCaseId  Test Case Identifier
     * @param object      Key property selector to identify the CSS Selector path
     * @param data        Input data
     */
    public static void select(IExtentTestClass report, String description, String testCaseId, String object,
                              String data) throws NoSuchElementException {
        WebElement element = null;
        try {
            element = WEB_DRIVER.findElement(By.cssSelector(SELECTOR.get(object.trim())));
            new Select(element).selectByVisibleText(data);
            report.log(LogStatus.INFO, description, data);
            logger.info("selectFromListBox [" + object + "]" + "[" + data + "]");
        } catch (NoSuchElementException nsee) {
            logger.info("Failed to select from list box, trying to send keys instead");
            try {
                if (element == null) {
                    throw nsee;
                }
                element.sendKeys(data);
                logger.info("selectFromListBox [" + object + "]" + "[" + data + "]");
            } catch (NoSuchElementException nsee2) {
                report.log(LogStatus.ERROR, description, data);
                logger.error(nsee2);
            }
        }
    }

    /**
     * @param report      IExtentTestClass interface for report logging
     * @param description Step description based on test script .xlsx
     * @param testCaseId  Test Case Identifier
     * @param object      Key property selector to identify the CSS Selector path
     * @param data        Input data
     */
    public static void waitFor(IExtentTestClass report, String description, String testCaseId, String object,
                               String data) throws InterruptedException {
        try {
            Thread.sleep(Long.valueOf(data));
            logger.info("waitFor [" + data + "]");
        } catch (InterruptedException ie) {
            logger.error(ie);
            throw ie;
        }
    }

    /**
     * @param report      IExtentTestClass interface for report logging
     * @param description Step description based on test script .xlsx
     * @param testCaseId  Test Case Identifier
     * @param object      Key property selector to identify the CSS Selector path
     * @param data        Input data
     */
    public static void uploadFile(IExtentTestClass report, String description, String testCaseId, String object,
                                  String data) throws NoSuchElementException {
        try {
            WEB_DRIVER.findElement(By.cssSelector(SELECTOR.get(object.trim()))).clear();
            WEB_DRIVER.findElement(By.cssSelector(SELECTOR.get(object.trim()))).sendKeys(data);
            report.log(LogStatus.INFO, "Upload file[" + data + "]");
            logger.info("uploadFile [" + object + "]" + "[" + data + "]");
        } catch (NoSuchElementException nsee) {
            report.log(LogStatus.ERROR, description, data);
            logger.error(nsee);
            throw nsee;
        }
    }

    /**
     * @param report      IExtentTestClass interface for report logging
     * @param description Step description based on test script .xlsx
     * @param testCaseId  Test Case Identifier
     * @param object      Key property selector to identify the CSS Selector path
     * @param data        Input data
     */
    public static void snapShot(IExtentTestClass report, String description, String testCaseId, String object,
                                String data) throws IOException {
        try {
            String FILE_PATH = OUTPUT_BASE_PATH.concat(File.separator).concat("snapshot").
                    concat(File.separator).concat(testCaseId.replaceAll("\\\\/", "\\")).
                    concat(File.separator).concat(data + ".png");
            File scrFile = ((TakesScreenshot) WEB_DRIVER).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(FILE_PATH));
            String relativePath = FILE_PATH.replace(new File(OUTPUT_BASE_PATH).getParent(), "..\\..\\..");
            report.log(LogStatus.INFO, data + "\t" + report.addScreenCapture(relativePath));
            logger.info("snapShot [" + object + "]" + "[" + data + "]");
        } catch (IOException ioe) {
            report.log(LogStatus.ERROR, description, data);
            logger.error(ioe);
            throw ioe;
        }
    }


    /**
     * @param report      IExtentTestClass interface for report logging
     * @param description Step description based on test script .xlsx
     * @param testCaseId  Test Case Identifier
     * @param object      Key property selector to identify the CSS Selector path
     * @param data        Input data
     */
    public static void isContentEqualsTo(IExtentTestClass report, String description, String testCaseId,
                                         String object, String data) {
        try {
            if (!isCSSSelector(SELECTOR.get(object).toLowerCase())) {
                String strContent = WEB_DRIVER.findElement(By.cssSelector(SELECTOR.get(object))).getText();
                if (strContent.trim().equals(data)) {
                    report.log(LogStatus.PASS, description, data);
                } else {
                    report.log(LogStatus.FAIL, description, data);
                }
            } else {
                SCREEN = new Screen();
                Pattern image = new Pattern(SELECTOR.get(object));
                if (SCREEN.exists(image) != null) {
                    report.log(LogStatus.PASS, description, data);
                } else {
                    report.log(LogStatus.FAIL, description, data);
                }
            }
            logger.info("isContentEqualsTo [" + object + "]" + "[" + data + "]");
        } catch (NoSuchElementException nsee) {
            report.log(LogStatus.ERROR, description, data);
            logger.error(nsee);
            throw nsee;
        }
    }

    /**
     * @param report      IExtentTestClass interface for report logging
     * @param description Step description based on test script .xlsx
     * @param testCaseId  Test Case Identifier
     * @param object      Key property selector to identify the CSS Selector path
     * @param data        Input data
     */
    public static void isContentContains(IExtentTestClass report, String description, String testCaseId,
                                         String object, String data) {
        try {
            String strContent = WEB_DRIVER.findElement(By.cssSelector(SELECTOR.get(object))).getText();
            if (strContent.trim().contains(data)) {
                report.log(LogStatus.PASS, description, data);
            } else {
                report.log(LogStatus.FAIL, description, data);
            }
            logger.info("isContentContains [" + object + "]" + "[" + data + "]");
        } catch (NoSuchElementException nsee) {
            report.log(LogStatus.ERROR, description, data);
            logger.error(nsee);
            throw nsee;
        }
    }

    /**
     * @param report      IExtentTestClass interface for report logging
     * @param description Step description based on test script .xlsx
     * @param testCaseId  Test Case Identifier
     * @param object      Key property selector where to get the info that will be stored
     * @param data        Key property reference for the info
     */
    public static void storeInfo(IExtentTestClass report, String description, String testCaseId,
                                 String object, String data) {
        try {
            String info;
            if (DocObjectModel.getType(object) == TXTBOX) {
                info = WEB_DRIVER.findElement(By.cssSelector(SELECTOR.get(object))).getAttribute("value");
            } else if (DocObjectModel.getType(object) == LIST_BOX) {
                Select selectItem = new Select(WEB_DRIVER.findElement(By.cssSelector(SELECTOR.get(object))));
                info = selectItem.getFirstSelectedOption().getText();
            } else {
                info = WEB_DRIVER.findElement(By.cssSelector(SELECTOR.get(object))).getText();
            }
            REPOSITORY.put(data, info);
            report.log(LogStatus.INFO, description, data);
            logger.info("storeInfo [" + object + "]" + "[" + data + "]");
        } catch (NoSuchElementException nsee) {
            report.log(LogStatus.ERROR, description, data);
            logger.error(nsee);
            throw nsee;
        }
    }

    /**
     * @param report      IExtentTestClass interface for report logging
     * @param description Step description based on test script .xlsx
     * @param testCaseId  Test Case Identifier
     * @param object      Key property selector where to put the retrieved info
     * @param data        Key property of info that will be retrieved from REPOSITORY
     */
    public static void retrieveInfo(IExtentTestClass report, String description, String testCaseId,
                                    String object, String data) {
        try {
            String info = REPOSITORY.get(data);
            WEB_DRIVER.findElement(By.cssSelector(SELECTOR.get(object))).sendKeys(info);
            logger.info("retrieveInfo [" + object + "]" + "[" + data + "]");
        } catch (Exception ex) {
            report.log(LogStatus.ERROR, description, data);
            logger.error(ex);
            throw ex;
        }
    }

    public static void validateAttributeValue(IExtentTestClass report, String description, String testCaseId,
                                              String object, String data) {
        try {
            String[] info = data.split(":");
            String attr = null;
            String expectedValue = null;
            if (info.length > 1) {
                attr = info[0].trim();
                expectedValue = info[1].trim();
                String attrActualValue = WEB_DRIVER.findElement(By.cssSelector(SELECTOR.get(object))).getAttribute(attr);
                if (Objects.equals(expectedValue, attrActualValue)) {
                    report.log(LogStatus.PASS, description, expectedValue);
                } else {
                    report.log(LogStatus.FAIL, description, expectedValue);
                }
            } else {
                report.log(LogStatus.ERROR, description, expectedValue);
            }
            logger.info("validateAttributeValue [" + object + "]" + "[" + data + "]");
        } catch (Exception ex) {
            report.log(LogStatus.ERROR, description, "");
            logger.error(ex);
            throw ex;
        }
    }

    public static void isObjectPresent(IExtentTestClass report, String description, String testCaseId,
                                       String object, String data) {
        boolean isFound = false;
        try {
            WebElement element = WEB_DRIVER.findElement(By.cssSelector(SELECTOR.get(object)));
            if (element.isDisplayed()) {
                isFound = true;
            }
            logger.info("isObjectPresent [" + object + "]" + "[" + data + "]");
        } catch (NoSuchElementException ignored) {
        } finally {
            if (isFound == Boolean.valueOf(data)) {
                report.log(LogStatus.PASS, description, data);
            } else {
                report.log(LogStatus.FAIL, description, data);
            }
        }
    }

    public static void validateStoredInfo(IExtentTestClass report, String description, String testCaseId,
                                          String object, String data) {
        try {
            String info = REPOSITORY.get(data);
            String strContent = WEB_DRIVER.findElement(By.cssSelector(SELECTOR.get(object))).getText();
            if (strContent.trim().equals(info)) {
                report.log(LogStatus.PASS, description, strContent + " is equal to" + info);
            } else {
                report.log(LogStatus.FAIL, description, strContent + " not equal to" + info);
            }
            logger.info("validateStoredInfo [" + object + "]" + "[" + data + "]");
        } catch (Exception ex) {
            report.log(LogStatus.ERROR, description, data);
            logger.error(ex);
            throw ex;
        }
    }

    public static void waitUntil(IExtentTestClass report, String description, String testCaseId,
                                 String object, String data) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(WEB_DRIVER, 90);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(SELECTOR.get(object))));
        try {
            Thread.sleep(1000);
            logger.info("waitUntil [" + object + "]" + "[" + data + "]");
        } catch (InterruptedException e) {
            logger.error(e);
            throw e;
        }


    }

    public static void updateSubscription(IExtentTestClass report, String description, String testCaseId,
                                          String object, String data) {
        try {
            WebElement tableElement = WEB_DRIVER.findElement(By.cssSelector("#monthlyReportTable"));
            List<WebElement> tableRows = tableElement.findElements(By.tagName("tr"));
            for (WebElement element : tableRows) {
                List<WebElement> tableColumns = element.findElements(By.tagName("td"));
                List<WebElement> elements = tableColumns.get(10).findElements(By.tagName("a"));
                for (WebElement element1 : elements) {
                    element1.click();
                    return;
                }
            }
            report.log(LogStatus.INFO, description, data);
            logger.info("viewInternalDetails [" + object + "]" + "[" + data + "]");
        } catch (Exception ex) {
            report.log(LogStatus.ERROR, description, data);
            ActionKeywordException ake = new ActionKeywordException(Arrays.toString(ex.getStackTrace()), ex.getCause());
            logger.error(ake);
            throw ake;
        }

    }

    public static void inputVerificationCode(IExtentTestClass report, String description, String testCaseId,
                                             String object, String data) throws NoSuchElementException {
        try {
            String code = WEB_DRIVER.findElement(By.cssSelector(SELECTOR.get(object))).getText();
            WEB_DRIVER.findElement(By.cssSelector(SELECTOR.get("loginVerificationTxt"))).sendKeys(code);
            report.log(LogStatus.INFO, description, data);
            logger.info("inputVerification [" + object + "]" + "[" + data + "]");
        } catch (NoSuchElementException nsee) {
            report.log(LogStatus.ERROR, description, data);
            logger.error(nsee);
            throw nsee;
        }
    }

    public static void findSubscription(IExtentTestClass report, String description, String testCaseId,
                                        String object, String data) {
        try {
            Thread.sleep(2000);
            WebElement tableElement = WEB_DRIVER.findElement(By.cssSelector("#extProRequestTable"));
            List<WebElement> tableRows = tableElement.findElements(By.tagName("tr"));
            boolean isFound = false;
            String subscriptionRefSource = null;
            String subscriptionRefTarget = REPOSITORY.get(data);
            for (WebElement element : tableRows) {
                List<WebElement> tableColumns = element.findElements(By.tagName("td"));
                subscriptionRefSource = tableColumns.get(3).getText();
                if (subscriptionRefSource == null || subscriptionRefTarget == null) {
                } else {
                    if (subscriptionRefSource.trim().equalsIgnoreCase(subscriptionRefTarget.trim())) {
                        isFound = true;
                        break;
                    }
                }
            }
            if (isFound) {
                String details = "Source = ".concat(subscriptionRefSource).concat(" Target = ").concat(subscriptionRefTarget);
                report.log(LogStatus.PASS, description, details);

            } else {
                String details = "Source = ".concat(subscriptionRefSource).concat(" Target = ").concat(subscriptionRefTarget);
                report.log(LogStatus.FAIL, description, details);
            }
            logger.info("findSubscription [" + object + "]" + "[" + data + "]");
        } catch (Exception ex) {
            report.log(LogStatus.ERROR, description, data);
            ActionKeywordException ake = new ActionKeywordException(Arrays.toString(ex.getStackTrace()), ex.getCause());
            logger.error(ake);
            throw ake;
        }
    }

    public static void viewInternalDetails(IExtentTestClass report, String description, String testCaseId,
                                           String object, String data) {
        try {
            WebElement tableElement = WEB_DRIVER.findElement(By.cssSelector("#srResultTable"));
            List<WebElement> tableRows = tableElement.findElements(By.tagName("tr"));
            for (WebElement element : tableRows) {
                List<WebElement> tableColumns = element.findElements(By.tagName("td"));
                List<WebElement> elements = tableColumns.get(1).findElements(By.tagName("a"));
                for (WebElement element1 : elements) {
                    element1.click();
                    return;
                }
            }
            report.log(LogStatus.INFO, description, data);
            logger.info("viewInternalDetails [" + object + "]" + "[" + data + "]");
        } catch (Exception ex) {
            report.log(LogStatus.ERROR, description, data);
            ActionKeywordException ake = new ActionKeywordException(Arrays.toString(ex.getStackTrace()), ex.getCause());
            logger.error(ake);
            throw ake;
        }
    }

    public static void viewDetails(IExtentTestClass report, String description, String testCaseId,
                                   String object, String data) {
        try {
            WebElement tableElement = WEB_DRIVER.findElement(By.cssSelector("#extProRequestTable"));
            List<WebElement> tableRows = tableElement.findElements(By.tagName("tr"));
            for (WebElement element : tableRows) {
                List<WebElement> tableColumns = element.findElements(By.tagName("td"));
                List<WebElement> elements = tableColumns.get(Integer.valueOf(data)).findElements(By.tagName("a"));
                for (WebElement element1 : elements) {
                    if (element1.getAttribute("class").equalsIgnoreCase("pointCss")) {
                        element1.click();
                        return;
                    }
                }
            }
            report.log(LogStatus.INFO, description, "");
            logger.info("viewDetails [" + object + "]" + "[" + data + "]");
        } catch (Exception ex) {
            report.log(LogStatus.ERROR, description, data);
            ActionKeywordException ake = new ActionKeywordException(Arrays.toString(ex.getStackTrace()), ex.getCause());
            logger.error(ake);
            throw ake;
        }
    }

    public static void cancelRequest(IExtentTestClass report, String description, String testCaseId,
                                     String object, String data) {
        try {
            Thread.sleep(2000);
            WebElement tableElement = WEB_DRIVER.findElement(By.cssSelector("#extProRequestTable"));
            List<WebElement> tableRows = tableElement.findElements(By.tagName("tr"));
            for (WebElement element : tableRows) {
                List<WebElement> tableColumns = element.findElements(By.tagName("td"));
                List<WebElement> elements = tableColumns.get(Integer.valueOf(data)).findElements(By.tagName("a"));
                for (WebElement element1 : elements) {
                    if (element1.getText().equalsIgnoreCase("cancel")) {
                        element1.click();
                        return;
                    }
                }
            }
            report.log(LogStatus.INFO, description, data);
            logger.info("cancelRequest [" + object + "]" + "[" + data + "]");
        } catch (Exception ex) {
            report.log(LogStatus.ERROR, description, data);
            ActionKeywordException ake = new ActionKeywordException(Arrays.toString(ex.getStackTrace()), ex.getCause());
            logger.error(ake);
            throw ake;
        }
    }
}
