package com.pccw.ad.pronghorn.model.profile;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by FaustineP on 07/04/2017.
 */
public class Selector implements Serializable {

    HashMap<String, HashMap<String, String>> selectors;

    public Selector() {
    }

    public Selector(HashMap<String, HashMap<String, String>> selectors) {
        this.selectors = selectors;
    }

    public HashMap<String, HashMap<String, String>> getSelectors() {
        return selectors;
    }

    public void setSelectors(HashMap<String, HashMap<String, String>> selectors) {
        this.selectors = selectors;
    }

    @Override
    public String toString() {
        return "Selector{" +
                "selectors=" + selectors +
                '}';
    }
}
