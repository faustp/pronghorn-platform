package com.faust.pronghorn.engine.data;

/**
 * Created by FaustineP on 25/04/2017.
 */
public class DocObjectModel {

    public static ObjectType getType(String object) {
        if (object.toLowerCase().endsWith("txt") || object.toLowerCase().endsWith("text")) return ObjectType.TXTBOX;

        if (object.toLowerCase().endsWith("lstbox") || object.toLowerCase().endsWith("listbox"))
            return ObjectType.LIST_BOX;

        if (object.toLowerCase().endsWith("radiobtn") || object.toLowerCase().endsWith("radiobutton"))
            return ObjectType.RADIO_BUTTON;

        if (object.toLowerCase().endsWith("checkbox") || object.toLowerCase().endsWith("chckbox") ||
                object.toLowerCase().endsWith("chkbox"))
            return ObjectType.CHECK_BOX;

        if (object.toLowerCase().endsWith("btn") || object.toLowerCase().endsWith("button"))
            return ObjectType.RADIO_BUTTON;
        return null;
    }
}
