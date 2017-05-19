package com.pccw.ad.pronghorn.model.profile;

import com.pccw.ad.pronghorn.model.exception.ProfileException;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.*;

/**
 * Created by FaustineP on 28/03/2017.
 */
@Document
public class Profile implements Nameable, Serializable {

    private static final long serialVersionUID = 200614354L;

    @Indexed
    private String name;
    private LinkedHashSet<Service> services;
    private Selector selector;

    public Profile() {
    }

    public Profile(String name, LinkedHashSet<Service> services) throws ProfileException {
        if (name == null || name.isEmpty()) throw new ProfileException("name in Profile must not be null pr empty");
        if (services == null || services.isEmpty())
            throw new ProfileException("services in Profile must not be null or empty");
        this.name = name;
        this.services = services;
    }

    public LinkedHashSet<Service> getServices() {
        return services;
    }

    public void setServices(LinkedHashSet<Service> services) {
        this.services = services;
    }


    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", services=" + services +
                ", selector=" + selector +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        if (!name.equals(profile.name)) return false;
        return services.equals(profile.services);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + services.hashCode();
        return result;
    }
}
