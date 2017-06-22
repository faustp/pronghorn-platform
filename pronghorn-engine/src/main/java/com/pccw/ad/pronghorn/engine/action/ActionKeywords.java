package com.pccw.ad.pronghorn.engine.action;


import com.pccw.ad.pronghorn.common.validation.Validate;
import com.pccw.ad.pronghorn.engine.data.Constant;
import com.pccw.ad.pronghorn.engine.data.DocObjectModel;
import com.pccw.ad.pronghorn.engine.exception.ActionKeywordException;
import com.pccw.ad.pronghorn.model.tc.Script;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.pccw.ad.pronghorn.engine.data.Constant.OUTPUT_BASE_PATH;
import static com.pccw.ad.pronghorn.engine.data.ObjectType.LIST_BOX;
import static com.pccw.ad.pronghorn.engine.data.ObjectType.TXTBOX;
import static java.util.concurrent.TimeUnit.SECONDS;


/**
 * This class contains all the methods pertaining to the event of particular DOM element
 * <p>
 * Created by FaustineP on 07/03/2017.
 */
public class ActionKeywords {

    private final static Logger logger = Logger.getLogger(ActionKeywords.class);

    public static WebDriver WEB_DRIVER = null;
    public static String PARENT_WINDOW = null;
    public static int WINDOW_INDX;
    private static App APP_DRIVER = null;


    public static HashMap<String, String> REPOSITORY = new HashMap<>();

    /**
     * @param report     IExtentTestClass interface for report logging
     * @param script     Script object contains information such as object key, value, actionKeyword and input data
     * @param testCaseId Test Case Identifier
     */
    public static void openApplication(IExtentTestClass report, Script script, String testCaseId) {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        switch (script.getInputData()) {
            case "IE":
            case "Internet Explorer":
                System.setProperty(Constant.KEY_IE_DRIVE, Constant.FILE_PATH_DRIVER_IE_X32_EXE);
                capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                WEB_DRIVER = new InternetExplorerDriver(capabilities);
                WEB_DRIVER.manage().window().maximize();

                report.log(LogStatus.INFO, script.getDescription(), script.getInputData().toLowerCase());
                break;
            case "FF":
            case "FireFox":
                System.setProperty(Constant.KEY_GECKO_DRIVER, Constant.FILE_PATH_GECKO_DRIVER_EXE);
                WEB_DRIVER = new FirefoxDriver();
                WEB_DRIVER.manage().window().maximize();
                report.log(LogStatus.INFO, script.getDescription(), script.getInputData().toUpperCase());
                break;
            case "Chrome":
                System.setProperty(Constant.KEY_CHROME_DRIVER, Constant.FILE_PATH_CHROME_DRIVER_EXE);
                capabilities = DesiredCapabilities.chrome();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("test-type");
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                WEB_DRIVER = new ChromeDriver(capabilities);
                WEB_DRIVER.manage().window().maximize();
                report.log(LogStatus.INFO, script.getDescription(), script.getInputData().toUpperCase());
                break;
            default:
                APP_DRIVER = App.open(script.getInputData());
                report.log(LogStatus.INFO, script.getDescription(), script.getInputData().toUpperCase());
        }
        PARENT_WINDOW = WEB_DRIVER.getWindowHandle();
        WEB_DRIVER.manage().timeouts().implicitlyWait(10, SECONDS);
        logger.info("openApplication " + script.getInputData());
    }


    /**
     * @param report     IExtentTestClass interface for report logging
     * @param script     Script object contains information such as object key, value, actionKeyword and input data
     * @param testCaseId Test Case Identifier
     */
    public static void navigate(IExtentTestClass report, Script script, String testCaseId) {
        WEB_DRIVER.manage().timeouts().implicitlyWait(10, SECONDS);
        WEB_DRIVER.get(script.getInputData());
        report.log(LogStatus.INFO, script.getDescription(), script.getInputData());
        logger.info("navigate " + script.getInputData());
    }

    /**
     * @param report     IExtentTestClass interface for report logging
     * @param script     Script object contains information such as object key, value, actionKeyword and input data
     * @param testCaseId Test Case Identifier
     */
    public static void closeApplication(IExtentTestClass report, Script script, String testCaseId) {

        if (APP_DRIVER != null) {
            APP_DRIVER.close();
        }
        if (WEB_DRIVER != null) {
            WEB_DRIVER.quit();
        }
        report.log(LogStatus.INFO, script.getDescription(), script.getInputData());
        logger.info("closing application...");
    }

    /**
     * @param report     IExtentTestClass interface for report logging
     * @param script     Script object contains information such as object key, value, actionKeyword and input data
     * @param testCaseId Test Case Identifier
     */
    public static void inputTxt(IExtentTestClass report, Script script, String testCaseId) throws NoSuchElementException, FindFailed {
        try {
            if (Validate.isCSSSelector(script.getSelector().getValue())) {
                WebElement txtElement;
                try {
                    txtElement = WEB_DRIVER.findElement(By.cssSelector(script.getSelector().getValue()));
                } catch (NoSuchElementException nsee) {
                    logger.info("Element [" + script.getSelector().getKey() + "] failed to locate using CSS selector.");
                    logger.info("Locating element [" + script.getSelector().getKey() + "] by id...");
                    waitUntil(report, script, testCaseId);
                    txtElement = WEB_DRIVER.findElement(By.id(script.getSelector().getValue()));
                }
                txtElement.clear();
                txtElement.sendKeys(script.getInputData());
            } else {
                Screen screen = new Screen();
                Pattern image = new Pattern(script.getSelector().getValue());
                screen.wait(image, 10);
                screen.click();
                screen.type(script.getInputData());
            }

            report.log(LogStatus.INFO, script.getDescription(), script.getInputData());
            logger.info("inputTxt [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
        } catch (NoSuchElementException | FindFailed exception) {
            report.log(LogStatus.ERROR, script.getDescription(), script.getInputData());
            logger.error(exception);
            throw exception;
        }
    }


    /**
     * @param report     IExtentTestClass interface for report logging
     * @param script     Script object contains information such as object key, value, actionKeyword and input data
     * @param testCaseId Test Case Identifier
     */
    public static void click(IExtentTestClass report, Script script, String testCaseId) throws FindFailed, InterruptedException {
        if (Validate.isCSSSelector(script.getSelector().getValue())) {
            WebElement btnElement;
            try {
                btnElement = WEB_DRIVER.findElement(By.cssSelector(script.getSelector().getValue()));
            } catch (NoSuchElementException nsee) {
                logger.info("Element [" + script.getSelector().getKey() + "] failed to locate using CSS selector.");
                logger.info("Locating element [" + script.getSelector().getKey() + "] by id...");
                btnElement = WEB_DRIVER.findElement(By.id(script.getSelector().getValue()));
            }
            btnElement.click();
        } else {
            Screen screen = new Screen();
            Pattern image = new Pattern(script.getSelector().getValue());
            try {
                screen.wait(image, 10);
                screen.click();
                report.log(LogStatus.INFO, script.getDescription(), script.getInputData());
                logger.info("click [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
            } catch (FindFailed findFailed) {
                logger.error(findFailed);
                throw findFailed;
            }
        }
    }


    /**
     * @param report     IExtentTestClass interface for report logging
     * @param script     Script object contains information such as object key, value, actionKeyword and input data
     * @param testCaseId Test Case Identifier
     */
    public static void doubleClick(IExtentTestClass report, Script script, String testCaseId) throws FindFailed {
        try {
            Screen screen = new Screen();
            Pattern image = new Pattern(script.getSelector().getValue());
            screen.wait(image, 10);
            screen.doubleClick();
            report.log(LogStatus.INFO, script.getDescription(), script.getInputData());
            logger.info("click [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
        } catch (FindFailed findFailed) {
            report.log(LogStatus.ERROR, script.getDescription(), script.getInputData());
            logger.error(findFailed);
            throw findFailed;
        }
    }


    /**
     * @param report     IExtentTestClass interface for report logging
     * @param script     Script object contains information such as object key, value, actionKeyword and input data
     * @param testCaseId Test Case Identifier
     */
    public static void rightClick(IExtentTestClass report, Script script, String testCaseId) throws FindFailed {

        try {
            Screen screen = new Screen();
            Pattern image = new Pattern(script.getSelector().getValue());
            screen.wait(image, 10);
            screen.rightClick();
            report.log(LogStatus.INFO, script.getDescription(), script.getInputData());
            logger.info("click [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
        } catch (FindFailed findFailed) {
            report.log(LogStatus.ERROR, script.getDescription(), script.getInputData());
            logger.error(findFailed);
            throw findFailed;
        }
    }


    /**
     * @param report     IExtentTestClass interface for report logging
     * @param script     Script object contains information such as object key, value, actionKeyword and input data
     * @param testCaseId Test Case Identifier
     */
    public static void inputDate(IExtentTestClass report, Script script, String testCaseId)
            throws NoSuchElementException, FindFailed {
        // TODO : provide date format validation
        try {
            if (Validate.isCSSSelector(script.getSelector().getValue())) {
                WebElement dateElement;
                try {
                    dateElement = WEB_DRIVER.findElement(By.cssSelector(script.getSelector().getValue()));
                } catch (NoSuchElementException nsee) {
                    logger.info("Element [" + script.getSelector().getKey() + "] failed to locate using CSS selector.");
                    logger.info("Locating element [" + script.getSelector().getKey() + "] by id...");
                    dateElement = WEB_DRIVER.findElement(By.id(script.getSelector().getValue()));
                }
                dateElement.clear();
                dateElement.sendKeys(script.getInputData());
                dateElement.sendKeys(Keys.TAB);
            } else {
                Screen screen = new Screen();
                Pattern image = new Pattern(script.getSelector().getValue());
                screen.wait(image, 10);
                screen.click();
                screen.type(script.getInputData());
                screen.type(Key.TAB);
            }

            report.log(LogStatus.INFO, script.getDescription(), script.getInputData());
            logger.info("inputDate [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
        } catch (NoSuchElementException | FindFailed exception) {
            report.log(LogStatus.INFO, script.getDescription(), script.getInputData());
            logger.error(exception);
            throw exception;
        }
    }

    /**
     * @param report     IExtentTestClass interface for report logging
     * @param script     Script object contains information such as object key, value, actionKeyword and input data
     * @param testCaseId Test Case Identifier
     */
    public static void select(IExtentTestClass report, Script script, String testCaseId) throws NoSuchElementException, FindFailed {
        WebElement element = null;
        try {
            if (Validate.isCSSSelector(script.getSelector().getValue())) {
                element = WEB_DRIVER.findElement(By.cssSelector(script.getSelector().getValue()));
                try {
                    new Select(element).selectByVisibleText(script.getInputData());
                    report.log(LogStatus.INFO, script.getDescription(), script.getInputData());
                    logger.info("selectFromListBox [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
                } catch (NoSuchElementException nsee) {
                    logger.info("Failed to select from list box, trying to send keys instead");
                    element.sendKeys(script.getInputData());
                    logger.info("selectFromListBox [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
                }
            } else {
                Screen screen = new Screen();
                Pattern image = new Pattern(script.getSelector().getValue());
                try {
                    screen.wait(image, 10);
                    screen.click();
                    Region r = screen.find(image);
                    screen.click(r.offset(170, 0));
                    screen.type(script.getInputData());
                    screen.type(Key.ENTER);

                    report.log(LogStatus.INFO, script.getDescription(), script.getInputData());
                    logger.info("click [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
                } catch (FindFailed findFailed) {
                    logger.error(findFailed);
                    throw findFailed;
                }
            }

        } catch (NoSuchElementException nsee) {
            logger.info("Element [" + script.getSelector().getKey() + "] failed to locate using CSS selector.");
            logger.info("Locating element [" + script.getSelector().getKey() + "] by id...");
            element = WEB_DRIVER.findElement(By.id(script.getSelector().getValue()));
            try {
                new Select(element).selectByVisibleText(script.getInputData());
                report.log(LogStatus.INFO, script.getDescription(), script.getInputData());
                logger.info("selectFromListBox [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
            } catch (NoSuchElementException nsee2) {
                logger.info("Failed to select from list box, trying to send keys instead");
                element.sendKeys(script.getInputData());
                logger.info("selectFromListBox [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
            }
        }
    }

    /**
     * @param report     IExtentTestClass interface for report logging
     * @param script     Script object contains information such as object key, value, actionKeyword and input data
     * @param testCaseId Test Case Identifier
     */
    public static void switchToFrame(IExtentTestClass report, Script script, String testCaseId) {
        WEB_DRIVER.switchTo().frame(WEB_DRIVER.findElement(By.cssSelector(script.getSelector().getValue())));
        logger.info("Switching to frame -> " + script.getSelector().getKey());
    }

    /**
     * @param report     IExtentTestClass interface for report logging
     * @param script     Script object contains information such as object key, value, actionKeyword and input data
     * @param testCaseId Test Case Identifier
     */
    public static void switchToParentFrame(IExtentTestClass report, Script script, String testCaseId) {
        WEB_DRIVER.switchTo().parentFrame();
        logger.info("Switching to parent frame");
    }

    /**
     * @param report     IExtentTestClass interface for report logging
     * @param script     Script object contains information such as object key, value, actionKeyword and input data
     * @param testCaseId Test Case Identifier
     */
    public static void switchToNextWindow(IExtentTestClass report, Script script, String testCaseId) {
        WINDOW_INDX++;
        Set<String> activeWindows = WEB_DRIVER.getWindowHandles();
        Object[] arr = activeWindows.toArray();
        if (activeWindows.size() <= WINDOW_INDX) {
            WEB_DRIVER.switchTo().window((String) arr[WINDOW_INDX]);
        } else {
            WEB_DRIVER.switchTo().window((String) arr[arr.length - 1]);
        }
        logger.info("Switching to next window");
    }

    /**
     * @param report     IExtentTestClass interface for report logging
     * @param script     Script object contains information such as object key, value, actionKeyword and input data
     * @param testCaseId Test Case Identifier
     */
    public static void switchToPrevWindow(IExtentTestClass report, Script script, String testCaseId) {
        WINDOW_INDX--;
        Set<String> activeWindows = WEB_DRIVER.getWindowHandles();
        Object[] arr = activeWindows.toArray();
        if (activeWindows.size() <= WINDOW_INDX) {
            if (WINDOW_INDX > 0) {
                WEB_DRIVER.close();
            }
            WEB_DRIVER.switchTo().window((String) arr[WINDOW_INDX]);
        } else {
            WEB_DRIVER.switchTo().window((String) arr[arr.length - 1]);
        }
        logger.info("Switching to previous window");
    }

    /**
     * @param report     IExtentTestClass interface for report logging
     * @param script     Script object contains information such as object key, value, actionKeyword and input data
     * @param testCaseId Test Case Identifier
     */
    public static void switchToParentWindow(IExtentTestClass report, Script script, String testCaseId) {
        Set<String> activeWindows = WEB_DRIVER.getWindowHandles();
        Object[] arr = activeWindows.toArray();
        int indx;
        for (indx = 1; indx < arr.length; indx++) {
            WEB_DRIVER.switchTo().window((String) arr[indx]);
            WEB_DRIVER.close();
        }
        WEB_DRIVER.switchTo().window(PARENT_WINDOW);
        logger.info("Switching to parent window");
    }

    /**
     * @param report     IExtentTestClass interface for report logging
     * @param script     Script object contains information such as object key, value, actionKeyword and input data
     * @param testCaseId Test Case Identifier
     */
    public static void uploadFile(IExtentTestClass report, Script script, String testCaseId) throws NoSuchElementException {
        WebElement uploadFileElement;
        try {
            uploadFileElement = WEB_DRIVER.findElement(By.cssSelector(script.getSelector().getValue()));
        } catch (NoSuchElementException exception) {
            logger.info("Element [" + script.getSelector().getKey() + "] failed to locate using CSS selector.");
            logger.info("Locating element [" + script.getSelector().getKey() + "] by id...");
            uploadFileElement = WEB_DRIVER.findElement(By.id(script.getSelector().getValue()));
        }
        uploadFileElement.clear();
        uploadFileElement.sendKeys(script.getInputData());
        report.log(LogStatus.INFO, "Upload file[" + script.getInputData() + "]");
        logger.info("uploadFile [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
    }

    /**
     * @param report     IExtentTestClass interface for report logging
     * @param script     Script object contains information such as object key, value, actionKeyword and input data
     * @param testCaseId Test Case Identifier
     */
    public static void snapShot(IExtentTestClass report, Script script, String testCaseId) throws IOException {
        try {
            String FILE_PATH = OUTPUT_BASE_PATH.concat(File.separator).concat("snapshot").
                    concat(File.separator).concat(testCaseId.replaceAll("\\\\/", "\\")).
                    concat(File.separator).concat(script.getInputData() + ".png");
            File scrFile = ((TakesScreenshot) WEB_DRIVER).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(FILE_PATH));
            String relativePath = FILE_PATH.replace(new File(OUTPUT_BASE_PATH).getParent(), "..\\..\\..");
            report.log(LogStatus.INFO, script.getInputData() + "\t" + report.addScreenCapture(relativePath));
            logger.info("snapShot [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
        } catch (IOException ioe) {
            report.log(LogStatus.ERROR, script.getDescription(), script.getInputData());
            logger.error(ioe);
            throw ioe;
        }
    }


    public static void storeInfo(IExtentTestClass report, Script script, String testCaseId) {
        try {
            String info;
            if (DocObjectModel.getType(script.getSelector().getKey()) == TXTBOX) {
                info = WEB_DRIVER.findElement(By.cssSelector(script.getSelector().getValue())).getAttribute("value");
            } else if (DocObjectModel.getType(script.getSelector().getKey()) == LIST_BOX) {
                Select selectItem = new Select(WEB_DRIVER.findElement(By.cssSelector(script.getSelector().getValue())));
                info = selectItem.getFirstSelectedOption().getText();
            } else {
                info = WEB_DRIVER.findElement(By.cssSelector(script.getSelector().getValue())).getText();
            }
            REPOSITORY.put(script.getInputData(), info);
            report.log(LogStatus.INFO, script.getDescription(), script.getInputData());
            logger.info("storeInfo [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
        } catch (NoSuchElementException exception) {
            report.log(LogStatus.ERROR, script.getDescription(), script.getInputData());
            logger.error(exception);
            throw exception;
        }
    }



    public static void retrieveInfo(IExtentTestClass report, Script script, String testCaseId) {
        try {
            String info = REPOSITORY.get(script.getInputData());
            WEB_DRIVER.findElement(By.cssSelector(script.getSelector().getValue())).sendKeys(info);
            logger.info("retrieveInfo [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
        } catch (NoSuchElementException exception) {
            report.log(LogStatus.ERROR, script.getDescription(), script.getInputData());
            logger.error(exception);
            throw exception;
        }
    }


    public static void validateAttributeValue(IExtentTestClass report, Script script, String testCaseId) {
        try {
            String[] info = script.getInputData().split(":");
            String attr = null;
            String expectedValue = null;
            if (info.length > 1) {
                attr = info[0].trim();
                expectedValue = info[1].trim();
                String attrActualValue = WEB_DRIVER.findElement(By.cssSelector(script.getSelector().getValue())).
                        getAttribute(attr);
                if (Objects.equals(expectedValue, attrActualValue)) {
                    report.log(LogStatus.PASS, script.getDescription(), expectedValue);
                } else {
                    report.log(LogStatus.FAIL, script.getDescription(), expectedValue);
                }
            } else {
                report.log(LogStatus.ERROR, script.getDescription(), expectedValue);
            }
            logger.info("validateAttributeValue [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
        } catch (Exception ex) {
            report.log(LogStatus.ERROR, script.getDescription(), "");
            logger.error(ex);
            throw ex;
        }
    }

    /**
     * @param report     IExtentTestClass interface for report logging
     * @param script     Script object contains information such as object key, value, actionKeyword and input data
     * @param testCaseId Test Case Identifier
     */
    public static void isObjectPresent(IExtentTestClass report, Script script, String testCaseId) {
        boolean isFound = false;
        try {
            WebElement element = WEB_DRIVER.findElement(By.cssSelector(script.getSelector().getValue()));
            if (element.isDisplayed()) {
                isFound = true;
            }
            logger.info("isObjectPresent [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
        } catch (NoSuchElementException ignored) {
            report.log(LogStatus.ERROR, script.getDescription(), script.getInputData());
        } finally {
            if (isFound == Boolean.valueOf(script.getInputData())) {
                report.log(LogStatus.PASS, script.getDescription(), script.getInputData());
            } else {
                report.log(LogStatus.FAIL, script.getDescription(), script.getInputData());
            }
        }
    }

    /**
     * @param report     IExtentTestClass interface for report logging
     * @param script     Script object contains information such as object key, value, actionKeyword and input data
     * @param testCaseId Test Case Identifier
     */
    public static void isContentEqualsTo(IExtentTestClass report, Script script, String testCaseId) {
        try {
            if (Validate.isCSSSelector(script.getSelector().getValue())) {
                String strContent = WEB_DRIVER.findElement(By.cssSelector(script.getSelector().getValue())).getText();
                if (strContent.trim().equals(script.getInputData())) {
                    report.log(LogStatus.PASS, script.getDescription(), script.getInputData());
                } else {
                    report.log(LogStatus.FAIL, script.getDescription(), script.getInputData());
                }
            } else {
                Screen screen = new Screen();
                Pattern image = new Pattern(script.getSelector().getValue());
                if (screen.exists(image) != null) {
                    report.log(LogStatus.PASS, script.getDescription(), script.getInputData());
                } else {
                    report.log(LogStatus.FAIL, script.getDescription(), script.getInputData());
                }
            }
            logger.info("isContentEqualsTo [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
        } catch (NoSuchElementException nsee) {
            report.log(LogStatus.ERROR, script.getDescription(), script.getInputData());
            logger.error(nsee);
            throw nsee;
        }
    }

    public static void hoverTo(IExtentTestClass report, Script script, String testCaseId){
        WebElement element = WEB_DRIVER.findElement(By.cssSelector(script.getSelector().getValue()));
        Actions actions = new Actions(WEB_DRIVER);
        actions.moveToElement(element).perform();
        logger.info("hoverTo [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
    }

    /**
     * @param report     IExtentTestClass interface for report logging
     * @param script     Script object contains information such as object key, value, actionKeyword and input data
     * @param testCaseId Test Case Identifier
     */
    public static void isContentContains(IExtentTestClass report, Script script, String testCaseId) {
        try {
            String strContent = WEB_DRIVER.findElement(By.cssSelector(script.getSelector().getValue())).getText();
            if (strContent.trim().contains(script.getInputData())) {
                report.log(LogStatus.PASS, script.getDescription(), script.getInputData());
            } else {
                report.log(LogStatus.FAIL, script.getDescription(), script.getInputData());
            }
            logger.info("isContentContains [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
        } catch (NoSuchElementException nsee) {
            report.log(LogStatus.ERROR, script.getDescription(), script.getInputData());
            logger.error(nsee);
            throw nsee;
        }
    }

    /**
     * @param report     IExtentTestClass interface for report logging
     * @param script     Script object contains information such as object key, value, actionKeyword and input data
     * @param testCaseId Test Case Identifier
     */
    public static void validateStoredInfo(IExtentTestClass report, Script script, String testCaseId) {
        try {
            String info = REPOSITORY.get(script.getInputData());
            String strContent = WEB_DRIVER.findElement(By.cssSelector(script.getSelector().getValue())).getText();
            if (strContent.trim().equals(info)) {
                report.log(LogStatus.PASS, script.getDescription(), strContent + " is equal to" + info);
            } else {
                report.log(LogStatus.FAIL, script.getDescription(), strContent + " not equal to" + info);
            }
            logger.info("validateStoredInfo [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
        } catch (Exception ex) {
            report.log(LogStatus.ERROR, script.getDescription(), script.getInputData());
            logger.error(ex);
            throw ex;
        }
    }

    /**
     * This method perform implicit wait until the object that you are expecting appears
     **/
    private static void waitUntil(IExtentTestClass report, Script script, String testCaseId) {
        WebDriverWait wait = new WebDriverWait(WEB_DRIVER, 90);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(script.getSelector().getValue())));
        logger.info("waitUntil [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
    }

    /**
     * @param report
     * @param script
     * @param testCaseId
     * @throws InterruptedException
     * @deprecated
     */
    public static void waitFor(IExtentTestClass report, Script script, String testCaseId) throws InterruptedException {
        try {
            Thread.sleep(Long.valueOf(script.getInputData()));
            logger.info("waitFor [" + script.getInputData() + "]");
        } catch (InterruptedException ie) {
            logger.error(ie);
            throw ie;
        }
    }


    /**
     * @param report     IExtentTestClass interface for report logging
     * @param testCaseId Test Case Identifier
     * @deprecated This method is customized for BPSS and it will be removed soon
     */
    @Deprecated
    public static void updateSubscription(IExtentTestClass report, Script script, String testCaseId) {
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
            report.log(LogStatus.INFO, script.getDescription(), script.getInputData());
            logger.info("viewInternalDetails [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
        } catch (Exception ex) {
            report.log(LogStatus.ERROR, script.getDescription(), script.getInputData());
            ActionKeywordException ake = new ActionKeywordException(Arrays.toString(ex.getStackTrace()), ex.getCause());
            logger.error(ake);
            throw ake;
        }

    }

    /**
     * @param report     IExtentTestClass interface for report logging
     * @param testCaseId Test Case Identifier
     * @deprecated This method is customized for BPSS and it will be removed soon
     */
    @Deprecated
    public static void inputVerificationCode(IExtentTestClass report, Script script, String testCaseId) throws NoSuchElementException {
        try {
            String code = WEB_DRIVER.findElement(By.cssSelector(script.getSelector().getValue())).getText();
            WEB_DRIVER.findElement(By.cssSelector(script.getSelector().getValue())).sendKeys(code);
            report.log(LogStatus.INFO, script.getDescription(), script.getInputData());
            logger.info("inputVerification [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
        } catch (NoSuchElementException nsee) {
            report.log(LogStatus.ERROR, script.getDescription(), script.getInputData());
            logger.error(nsee);
            throw nsee;
        }
    }

    /**
     * @param report     IExtentTestClass interface for report logging
     * @param testCaseId Test Case Identifier
     * @deprecated This method is customized for BPSS and it will be removed soon
     */
    @Deprecated
    public static void findSubscription(IExtentTestClass report, Script script, String testCaseId) {
        try {
            Thread.sleep(2000);
            WebElement tableElement = WEB_DRIVER.findElement(By.cssSelector("#extProRequestTable"));
            List<WebElement> tableRows = tableElement.findElements(By.tagName("tr"));
            boolean isFound = false;
            String subscriptionRefSource = null;
            String subscriptionRefTarget = REPOSITORY.get(script.getInputData());
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
                report.log(LogStatus.PASS, script.getDescription(), details);

            } else {
                String details = "Source = ".concat(subscriptionRefSource).concat(" Target = ").concat(subscriptionRefTarget);
                report.log(LogStatus.FAIL, script.getDescription(), details);
            }
            logger.info("findSubscription [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
        } catch (Exception ex) {
            report.log(LogStatus.ERROR, script.getDescription(), script.getInputData());
            ActionKeywordException ake = new ActionKeywordException(Arrays.toString(ex.getStackTrace()), ex.getCause());
            logger.error(ake);
            throw ake;
        }
    }

    /**
     * @param report     IExtentTestClass interface for report logging
     * @param testCaseId Test Case Identifier
     * @deprecated This method is customized for BPSS and it will be removed soon
     */
    @Deprecated
    public static void viewInternalDetails(IExtentTestClass report, Script script, String testCaseId) {
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
            report.log(LogStatus.INFO, script.getDescription(), script.getInputData());
            logger.info("viewInternalDetails [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
        } catch (Exception ex) {
            report.log(LogStatus.ERROR, script.getDescription(), script.getInputData());
            ActionKeywordException ake = new ActionKeywordException(Arrays.toString(ex.getStackTrace()), ex.getCause());
            logger.error(ake);
            throw ake;
        }
    }

    /**
     * @param report     IExtentTestClass interface for report logging
     * @param testCaseId Test Case Identifier
     * @deprecated This method is customized for BPSS and it will be removed soon
     */
    @Deprecated
    public static void viewDetails(IExtentTestClass report, Script script, String testCaseId) {
        try {
            WebElement tableElement = WEB_DRIVER.findElement(By.cssSelector("#extProRequestTable"));
            List<WebElement> rows = tableElement.findElements(By.tagName("tr"));
            for (WebElement element : rows) {
                List<WebElement> columns = element.findElements(By.tagName("td"));
                List<WebElement> elements = columns.get(Integer.valueOf(script.getInputData())).findElements(By.tagName("a"));
                for (WebElement element1 : elements) {
                    if (element1.getAttribute("class").equalsIgnoreCase("pointCss")) {
                        element1.click();
                        return;
                    }
                }
            }
            report.log(LogStatus.INFO, script.getDescription(), "");
            logger.info("viewDetails [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
        } catch (Exception ex) {
            report.log(LogStatus.ERROR, script.getDescription(), script.getInputData());
            ActionKeywordException ake = new ActionKeywordException(Arrays.toString(ex.getStackTrace()), ex.getCause());
            logger.error(ake);
            throw ake;
        }
    }

    /**
     * @param report     IExtentTestClass interface for report logging
     * @param testCaseId Test Case Identifier
     * @deprecated This method is customized for BPSS and it will be removed soon
     */
    @Deprecated
    public static void cancelRequest(IExtentTestClass report, Script script, String testCaseId) {
        try {
            Thread.sleep(2000);
            WebElement tableElement = WEB_DRIVER.findElement(By.cssSelector("#extProRequestTable"));
            List<WebElement> tableRows = tableElement.findElements(By.tagName("tr"));
            for (WebElement element : tableRows) {
                List<WebElement> tableColumns = element.findElements(By.tagName("td"));
                List<WebElement> elements = tableColumns.get(Integer.valueOf(script.getInputData())).findElements(By.tagName("a"));
                for (WebElement element1 : elements) {
                    if (element1.getText().equalsIgnoreCase("cancel")) {
                        element1.click();
                        return;
                    }
                }
            }
            report.log(LogStatus.INFO, script.getDescription(), script.getInputData());
            logger.info("cancelRequest [" + script.getSelector().getKey() + "]" + "[" + script.getInputData() + "]");
        } catch (Exception ex) {
            report.log(LogStatus.ERROR, script.getDescription(), script.getInputData());
            ActionKeywordException ake = new ActionKeywordException(Arrays.toString(ex.getStackTrace()), ex.getCause());
            logger.error(ake);
            throw ake;
        }
    }

    public static void navTable(IExtentTestClass report, Script script, String testCaseId) {
        WebElement tableElement = WEB_DRIVER.findElement(By.cssSelector("#extProRequestTable"));
        List<WebElement> rows = tableElement.findElements(By.tagName("tr"));
        for (WebElement element : rows) {
            List<WebElement> columns = element.findElements(By.tagName("td"));
            List<WebElement> elements = columns.get(Integer.valueOf(script.getInputData())).findElements(By.tagName("a"));
            for (WebElement element1 : elements) {
                if (element1.getText().equalsIgnoreCase("cancel")) {
                    element1.click();
                    return;
                }
            }
        }

    }

    public static void clickAlert(IExtentTestClass report, Script script, String testCaseId) {
        try {
            Alert alert = WEB_DRIVER.switchTo().alert();
            if (script.getInputData().equalsIgnoreCase("OK"))
                alert.accept();
            else
                alert.dismiss();
        } catch (Exception ex) {
            report.log(LogStatus.ERROR, script.getDescription(), script.getInputData());
            ActionKeywordException ake = new ActionKeywordException(Arrays.toString(ex.getStackTrace()), ex.getCause());
            logger.error(ake);
            throw ake;
        }

    }
}
