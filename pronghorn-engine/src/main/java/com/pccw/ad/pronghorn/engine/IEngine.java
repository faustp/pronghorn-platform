package com.pccw.ad.pronghorn.engine;

import com.pccw.ad.pronghorn.model.exception.ProfileException;
import org.sikuli.script.FindFailed;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by FaustineP on 06/04/2017.
 */
public interface IEngine {

    void execute() throws ProfileException, InvocationTargetException, IllegalAccessException, IOException, FindFailed;

}
