package com.faust.pronghorn.engine;

import com.faust.pronghorn.engine.action.ActionKeywords;
import com.faust.pronghorn.message.Message;
import com.faust.pronghorn.model.profile.Profile;
import com.faust.pronghorn.model.profile.Service;
import com.faust.pronghorn.model.tc.Script;
import com.faust.pronghorn.model.tc.Status;
import com.faust.pronghorn.model.tc.TestCase;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import org.apache.log4j.Logger;
import org.sikuli.script.FindFailed;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by FaustineP on 06/04/2017.
 */
public final class PronghornEngine extends PronghornEngineAbs {

    final static Logger logger = Logger.getLogger(PronghornEngine.class);


    public PronghornEngine(Message message) {
        super(message);
    }

    public PronghornEngine(Profile profile) {
        super(profile);
    }

    @Override
    public void execute() throws InvocationTargetException, IllegalAccessException, FindFailed, IOException {
        ActionKeywords actionKeywords = new ActionKeywords();
        this.methods = actionKeywords.getClass().getMethods();

        for (Service service : this.profile.getServices()) {
            String reportFileName = buildReportFilePath(service.getName());
            REPORT = new ExtentReports(reportFileName, false, DisplayOrder.OLDEST_FIRST);

            for (TestCase testCase : service.getTestCases()) {
                TEST = REPORT.startTest(testCase.getIdentifier(), testCase.getObjective());
                for (Script script : testCase.getScripts()) {
                    try {
                        executeScript(TEST, script, testCase.getIdentifier());
                    } catch (InvocationTargetException | IllegalAccessException | FindFailed | IOException exception) {
                        logger.error(exception.getCause());
                        if (ActionKeywords.WEB_DRIVER != null) {
                            ActionKeywords.WEB_DRIVER.quit();
                        }
                        testCase.setStatus(Status.ERROR);
                        clearResources();
                        break;
                    }
                }
                testCase.setStatus(Status.PASS);
                clearResources();
            }
        }
    }
}
