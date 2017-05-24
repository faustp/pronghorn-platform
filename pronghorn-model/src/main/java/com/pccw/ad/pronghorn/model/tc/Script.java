package com.pccw.ad.pronghorn.model.tc;

import com.pccw.ad.pronghorn.model.profile.Selector;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by FaustineP on 28/03/2017.
 */
@Document
public class Script implements Serializable {

    private static final long serialVersionUID = 200614354L;
    private String action;
    private String objectKey;
    private String inputData;
    private String description;


    public Script() {
    }

    public Script(String description, String objectKey, String action, String inputData) {
        this.action = action;
        this.objectKey = objectKey;
        this.inputData = inputData;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }

    public String getInputData() {
        return inputData;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Script{" +
                "description='" + description + '\'' +
                ", objectKey='" + objectKey + '\'' +
                ", action='" + action + '\'' +
                ", inputData='" + inputData + '\'' +
                '}';
    }
}
