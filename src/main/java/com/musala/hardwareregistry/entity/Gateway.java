package com.musala.hardwareregistry.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author NIsaev on 23.01.2020
 */

@Entity
@Table(name = "gateway")
public class Gateway implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    public Gateway() {

    }

    public Gateway(UUID id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
