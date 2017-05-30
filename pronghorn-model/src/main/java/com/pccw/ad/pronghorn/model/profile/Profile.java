package com.pccw.ad.pronghorn.model.profile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by FaustineP on 28/03/2017.
 */
@Entity
@Table(name = "TBL_PROFILE")
public class Profile implements Nameable, Serializable {

    private static final long serialVersionUID = 200614354L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @JoinColumn(name = "PROFILE_ID")
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Service> services;

    public Profile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(LinkedHashSet<Service> services) {
        this.services = services;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", services=" + services +
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
