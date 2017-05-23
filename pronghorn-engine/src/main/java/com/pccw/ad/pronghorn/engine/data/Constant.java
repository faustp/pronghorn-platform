package com.pccw.ad.pronghorn.engine.data;

import java.io.File;

/**
 * Created by FaustineP on 22/02/2017.
 */
public class Constant {

    /**
     * Location of available drivers FireFox,Internet Explorer and Chrome
     */
    public static final String FILE_PATH_GECKO_DRIVER_EXE = System.getProperty("user.dir").concat(File.separator).
            concat("conf").concat(File.separator).concat("drivers").concat(File.separator).concat("geckodriver.exe");

    public static final String DRIVER_IE_X64 = System.getProperty("user.dir").concat(File.separator).
            concat("conf").concat(File.separator).concat("drivers").concat(File.separator).concat("iedriver64.exe");

    public static final String FILE_PATH_DRIVER_IE_X32_EXE = System.getProperty("user.dir").concat(File.separator).
            concat("conf").concat(File.separator).concat("drivers").concat(File.separator).concat("iedriver32.exe");

    public static final String FILE_PATH_DRIVER_IE_X64_EXE = System.getProperty("user.dir").concat(File.separator).
            concat("conf").concat(File.separator).concat("drivers").concat(File.separator).concat("iedriver64.exe");

    public static final String FILE_PATH_CHROME_DRIVER_EXE = System.getProperty("user.dir").concat(File.separator).
            concat("conf").concat(File.separator).concat("drivers").concat(File.separator).concat("chromedriver.exe");

    public static final String PROFILE_PROP_BASE_PATH = System.getProperty("user.dir").concat(File.separator)
            .concat("profiles");

    /**
     * Base path of output report
     */
    public static final String OUTPUT_BASE_PATH = System.getProperty("user.dir").concat(File.separator).concat("out");

    public static final String REPORT_BASE_PATH = OUTPUT_BASE_PATH.concat(File.separator).concat("report");

    public static final String INPUT_BASE_PATH = System.getProperty("user.dir").concat(File.separator).concat("in");

    public static final String IMAGE_INPUT_BASE_PATH = INPUT_BASE_PATH.concat(File.separator).concat("images");
    /**
     * Key properties for drivers
     */
    public static final String KEY_GECKO_DRIVER = "webdriver.gecko.driver";
    public static final String KEY_IE_DRIVE = "webdriver.ie.driver";
    public static final String KEY_CHROME_DRIVER = "webdriver.chrome.driver";

}
