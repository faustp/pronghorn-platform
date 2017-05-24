package com.pccw.ad.pronghorn.model.tc;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pccw.ad.pronghorn.model.exception.TestCaseException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

import static com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion.NON_NULL;

/**
 * Created by FaustineP on 28/03/2017.
 */
@JsonSerialize(include = NON_NULL)
@Document
public class TestCase implements Serializable {

    @Id
    @Indexed
    private String id;
    private String identifier;
    private String objective;
    private String preCondition;
    private Result result;
    private boolean isActive;
    private String author;
    @Indexed
    private String executedBy;
    @Indexed
    private Status status;
    private String remarks;
    private List<Script> scripts;

    private static final long serialVersionUID = 200614354L;

    private TestCase(Builder builder) {
        this.author = builder.author;
        this.executedBy = builder.executedBy;
        this.identifier = builder.identifier;
        this.isActive = builder.isActive;
        this.objective = builder.objective;
        this.preCondition = builder.preCondition;
        this.remarks = builder.remarks;
        this.result = builder.result;
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

    public Result getResult() {
        return result;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "identifier='" + identifier + '\'' +
                ", objective='" + objective + '\'' +
                ", preCondition='" + preCondition + '\'' +
                ", result=" + result +
                ", isActive=" + isActive +
                ", author='" + author + '\'' +
                ", executedBy='" + executedBy + '\'' +
                ", status=" + status +
                ", remarks='" + remarks + '\'' +
                ", scripts=" + scripts +
                '}';
    }

    public static class Builder {

        private String identifier;
        private String objective;
        private String preCondition;
        private Result result;
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
