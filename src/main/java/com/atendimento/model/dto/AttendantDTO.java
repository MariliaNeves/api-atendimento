package com.atendimento.model.dto;

import com.atendimento.model.enums.Team;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by  Marília
 * Date: 13/06/2024
 */

@JsonRootName(value = "attendant")
public class AttendantDTO implements Serializable {

    private Integer id;
    private String name;
    private Team team;
    @JsonProperty(value = "serviceRequest")
    private List<ServiceRequestDTO> serviceRequestDTO;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<ServiceRequestDTO> getServiceRequestDTO() {
        return serviceRequestDTO;
    }

    public AttendantDTO() {}

    public AttendantDTO(String name, Team team) {
        this.name = name;
        this.team = team;
    }

    public void setServiceRequestDTO(List<ServiceRequestDTO> serviceRequestDTO) {
        this.serviceRequestDTO = serviceRequestDTO;
    }

    @Override
    public String toString() {
        return "Team{" +
                "idTeam=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
