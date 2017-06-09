package com.pccw.ad.pronghorn.model.tc;

import com.pccw.ad.pronghorn.model.profile.Selector;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by FaustineP on 28/03/2017.
 */
@Entity
@Table(name = "TBl_SCRIPT")
public class Script implements Serializable {

    private static final long serialVersionUID = 200614354L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    private TestCase testCase;

    @Column(name = "ACTION", nullable = false)
    private String action;

    @Column(name = "SELECTOR_ID")
    @JoinColumn(name = "SELECTOR_ID")
    private Selector selector;

    @Column(name = "INPUT_DATA")
    private String inputData;

    @Column(name = "DESCRIPTION")
    private String description;

    public Script() {
    }

    public Script(String description, Selector selector, String action, String inputData) {
        this.action = action;
        this.selector = selector;
        this.inputData = inputData;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    public String getInputData() {
        return inputData;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    @Override
    public String toString() {
        return "Script{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", selector=" + selector +
                ", inputData='" + inputData + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
