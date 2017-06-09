package com.pccw.ad.pronghorn.model.tc;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pccw.ad.pronghorn.model.exception.TestCaseException;
import com.pccw.ad.pronghorn.model.profile.Service;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion.NON_NULL;

/**
 * Created by FaustineP on 28/03/2017.
 */
@JsonSerialize(include = NON_NULL)
@Entity
@Table(name = "TBL_TESTCASE")
public class TestCase implements Serializable {

    private static final long serialVersionUID = 200614354L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "SERVICE_ID")
    private Service service;

    @Column(name = "IDENTIFIER", unique = true, nullable = false)
    private String identifier;

    @Column(name = "OBJECTIVE", nullable = false)
    private String objective;

    @Column(name = "PRE_CONDITION")
    private String preCondition;

    @Column(name = "ACTUAL_RESULT")
    private String actualResult;

    @Column(name = "EXPECTED_RESULT")
    private String expectedResult;

    @Column(name = "ACTIVE")
    private boolean isActive;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "EXECUTED_BY")
    private String executedBy;

    @Column(name = "STATUS")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "REMARKS")
    private String remarks;

    @OneToMany
    private List<Script> scripts;


    private TestCase(Builder builder) {
        this.author = builder.author;
        this.executedBy = builder.executedBy;
        this.identifier = builder.identifier;
        this.isActive = builder.isActive;
        this.objective = builder.objective;
        this.preCondition = builder.preCondition;
        this.actualResult = builder.actualResult;
        this.expectedResult = builder.expectedResult;
        this.remarks = builder.remarks;
        this.scripts = builder.scripts;
        this.status = builder.status;
    }

    private TestCase() {
    }

    public TestCase(String identifier, String objective) {
        this.identifier = identifier;
        this.objective = objective;
    }


    public void setActive(boolean active) {
        isActive = active;
    }


    public String getIdentifier() {
        return identifier;
    }

    public String getObjective() {
        return objective;
    }

    public String getPreCondition() {
        return preCondition;
    }


    public boolean isActive() {
        return isActive;
    }

    public String getAuthor() {
        return author;
    }

    public String getExecutedBy() {
        return executedBy;
    }

    public Status getStatus() {
        return status;
    }

    public String getRemarks() {
        return remarks;
    }

    public List<Script> getScripts() {
        return scripts;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public String getActualResult() {
        return actualResult;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "id=" + id +
                ", identifier='" + identifier + '\'' +
                ", objective='" + objective + '\'' +
                ", preCondition='" + preCondition + '\'' +
                ", isActive=" + isActive +
                ", author='" + author + '\'' +
                ", executedBy='" + executedBy + '\'' +
                ", status=" + status +
                ", remarks='" + remarks + '\'' +
                '}';
    }

    public static class Builder {

        private String identifier;
        private String objective;
        private String preCondition;
        private String actualResult;
        private String expectedResult;
        private boolean isActive;
        private String author;
        private String executedBy;
        private Status status;
        private String remarks;
        private List<Script> scripts;

        public TestCase build() throws TestCaseException {
            if (identifier == null || identifier.isEmpty())
                throw new TestCaseException("identifier in TestCase must not be null or empty");
            if (objective == null || objective.isEmpty())
                throw new TestCaseException("objective in TestCase must not be null or empty");
            if (author == null || author.isEmpty())
                throw new TestCaseException("author in TestCase must not be null or empty");
            return new TestCase(this);
        }

        public Builder addIdentifier(String identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder addObjective(String objective) {
            this.objective = objective;
            return this;
        }

        public Builder addPrecondition(String preCondition) {
            this.preCondition = preCondition;
            return this;
        }

        public Builder addIsActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder addAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder addExecutedBy(String executedBy) {
            this.executedBy = executedBy;
            return this;
        }

        public Builder addStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder addRemarks(String remarks) {
            this.remarks = remarks;
            return this;
        }

        public Builder addScripts(List<Script> scripts) {
            this.scripts = scripts;
            return this;
        }

        public Builder addExpectedResult(String expectedResult) {
            this.expectedResult = expectedResult;
            return this;
        }

        public Builder addActualResult(String actualResult) {
            this.actualResult = actualResult;
            return this;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestCase testCase = (TestCase) o;

        return identifier.equals(testCase.identifier);

    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }


}