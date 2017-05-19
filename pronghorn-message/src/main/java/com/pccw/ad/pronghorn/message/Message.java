package com.pccw.ad.pronghorn.message;

import com.pccw.ad.pronghorn.message.exception.MessageException;
import com.pccw.ad.pronghorn.model.profile.Profile;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by FaustineP on 03/04/2017.
 */
public class Message extends AbstractMessage {

    private static final long serialVersionUID = 200614354L;

    private Profile profile;

    public Message() {
        super();
    }

    public Message(Profile profile) throws MessageException {
        super();
        this.profile = profile;
    }

    public Profile getProfile() {
        return this.profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public List<String> getRoute() {
        return this.route;
    }

    @Override
    public void setRoute(List<String> route) {
        this.route = route;
    }

    @Override
    protected Date getDateCreated() {
        return this.dateCreated;
    }


    @Override
    public String toString() {
        return "Message{" +
                "uuid=" + uuid +
                ", profile=" + profile +
                ", route=" + route +
                ", dateCreated=" + dateCreated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        return profile.equals(message.profile);
    }

    @Override
    public int hashCode() {
        return profile.hashCode();
    }
}
