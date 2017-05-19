package com.pccw.ad.pronghorn.model.profile;

import com.pccw.ad.pronghorn.model.exception.ServiceException;
import com.pccw.ad.pronghorn.model.tc.TestCase;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by FaustineP on 06/04/2017.
 */
public class Service implements Nameable, Serializable {

    private static final long serialVersionUID = 200614354L;

    private String name;
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

    public Set<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(HashSet<TestCase> testCases) {
        this.testCases = testCases;
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
