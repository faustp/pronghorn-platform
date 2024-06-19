package com.faust.pronghorn.engine;

import com.faust.pronghorn.engine.action.ActionKeywords;
import com.faust.pronghorn.engine.data.Constant;
import com.faust.pronghorn.message.Message;
import com.faust.pronghorn.model.profile.Profile;
import com.faust.pronghorn.model.tc.Script;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.IExtentTestClass;
import org.sikuli.script.FindFailed;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;

/**
 * Created by FaustineP on 11/05/2017.
 */
public abstract class PronghornEngineAbs implements IEngine {

    protected Message message;
    protected Profile profile;
    protected ExtentTest TEST;
    protected ExtentReports REPORT;
    protected Method[] methods;


    public PronghornEngineAbs(Message message) {
        this.message = message;
        this.profile = this.message.getProfile();
    }

    public PronghornEngineAbs(Profile profile) {
        this.profile = profile;
    }

    protected void executeScript(IExtentTestClass test, Script script, String testCaseId)
            throws InvocationTargetException, IllegalAccessException, FindFailed, NoSuchElementException, IOException {
        for (Method aMethod : this.methods) {
            if (aMethod.getName().equals(script.getAction())) {
                aMethod.invoke(script.getAction(), test, script, testCaseId);
                break;
            }
        }
    }

    protected String buildReportFilePath(String serviceName) {
        String projectName = profile.getName();
        String filePath = Constant.REPORT_BASE_PATH.concat(File.separator).concat(projectName).concat(File.separator).
                concat(serviceName.toUpperCase().concat(".html"));

        return filePath;
    }

    protected void clearResources() {
        REPORT.endTest(TEST);
        REPORT.flush();
        ActionKeywords.REPOSITORY.clear();
    }

}