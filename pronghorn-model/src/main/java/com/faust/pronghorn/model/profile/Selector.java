package com.faust.pronghorn.model.profile;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by FaustineP on 07/04/2017.
 */
@Entity
@Table(name = "TBL_SELECTOR")
public class Selector implements Serializable {


    public Selector() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;

    @Column(name = "SERVICE_ID", nullable = false)
    @JoinColumn(name = "SERVICE_ID")
    private Service service;

    @Column(name = "KEY", nullable = false)
    private String key;

    @Column(name = "VALUE", nullable = false)
    private String value;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Selector{" +
                "id=" + id +
                ", service=" + service +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
