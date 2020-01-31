package com.musala.hardwareregistry.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.musala.hardwareregistry.entity.Equipment;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * @author NIsaev on 23.01.2020
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GatewayRequest implements Serializable {

    @JsonProperty(value = "id")
    private UUID id;

    @JsonProperty(value = "name", required = true)
    private String name;

    @JsonProperty(value = "address", required = true)
    private String address;

    @JsonProperty(value = "equipments")
    private List<EquipmentRequest> equipments;

    public GatewayRequest(){

    }

    public GatewayRequest(UUID id, String name, String address) {
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

    public List<EquipmentRequest> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<EquipmentRequest> equipments) {
        this.equipments = equipments;
    }
}
