package com.faust.pronghorn.model.profile;

import com.faust.pronghorn.model.tc.TestCase;
import com.faust.pronghorn.model.exception.ServiceException;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by FaustineP on 06/04/2017.
 */
@Entity
@Table(name = "TBL_SERVICE")
public class Service implements Nameable, Serializable {

    private static final long serialVersionUID = 200614354L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROFILE_ID")
    private Profile profile;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @JoinColumn(name = "SERVICE_ID")
    private HashSet<TestCase> testCases;

    public Service() {
    }

    public Service(String name, HashSet<TestCase> testCases) throws ServiceException {
        if (name == null || name.isEmpty()) throw new ServiceException("name in Service must not be null or empty");
        if (testCases == null || testCases.isEmpty())
            throw new ServiceException("test cases in Service must not be null or empty");
        this.name = name;
        this.testCases = testCases;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Set<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(HashSet<TestCase> testCases) {
        this.testCases = testCases;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "Service{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", testCases=" + testCases +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Service service = (Service) o;

        return name.equals(service.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
