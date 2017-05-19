package com.pccw.ad.pronghorn.message;

import com.pccw.ad.pronghorn.message.interfaces.Routable;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by FaustineP on 03/04/2017.
 */
public abstract class AbstractMessage implements Serializable, Routable {


    UUID uuid;
    List<String> route;
    Date dateCreated;

    public AbstractMessage() {
        this.uuid = UUID.randomUUID();
        this.dateCreated = new Date();
    }

    protected abstract UUID getUuid();

    protected abstract Date getDateCreated();

}
