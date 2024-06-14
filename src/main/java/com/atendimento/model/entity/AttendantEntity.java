package com.atendimento.model.entity;

import com.atendimento.model.enums.Team;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class AttendantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Team team;
    private List<ServiceRequestEntity> serviceRequest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<ServiceRequestEntity> getServiceRequestEntity() {
        return serviceRequest;
    }

    public void setServiceRequestEntity(List<ServiceRequestEntity> serviceRequest) {
        this.serviceRequest = serviceRequest;
    }




}