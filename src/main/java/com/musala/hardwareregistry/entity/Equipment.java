package com.musala.hardwareregistry.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author NIsaev on 23.01.2020
 */

@Entity
@Table(name = "equipment")
public class Equipment implements Serializable {

    @Id
    @SequenceGenerator(name = "equipment_gen", sequenceName = "equipment_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "equipment_gen")
    private Long id;

    @Column(name = "vendor", nullable = false)
    private String vendor;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime created;

    @Column(name = "status_id", nullable = false)
    private String status; // online/offline.

    @Column(name = "gateway_id")
    private UUID gateway;

    public Equipment() {

    }

    public Equipment(String vendor, LocalDateTime created, String status, UUID gateway) {
        this.id = id;
        this.vendor = vendor;
        this.created = created;
        this.status = status;
        this.gateway = gateway;
    }

    public Equipment(Long id, String vendor, LocalDateTime created, String status, UUID gateway) {
        this.vendor = vendor;
        this.created = created;
        this.status = status;
        this.gateway = gateway;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getGateway() {
        return gateway;
    }

    public void setGateway(UUID gateway) {
        this.gateway = gateway;
    }
}
