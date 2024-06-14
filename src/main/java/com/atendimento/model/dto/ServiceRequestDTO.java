package com.atendimento.model.dto;

import com.atendimento.model.enums.ServiceStatus;
import com.atendimento.model.enums.Subject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Date;

/**
 * Created by  Marília
 * Date: 13/06/2024
 */


@JsonRootName(value = "serviceRequest")
public class ServiceRequestDTO {

    private Integer id;
    private Date createAt;
    private Date updateAt;
    private Subject Subject;
    private String description;
    private ServiceStatus serviceStatus;
    @JsonProperty(value = "attendant")
    private AttendantDTO attendantDTO;

    public AttendantDTO getAttendantDTO() {
        return attendantDTO;
    }

    public void setAttendantDTO(AttendantDTO attendantDTO) {
        this.attendantDTO = attendantDTO;
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public com.atendimento.model.enums.Subject getSubject() {
        return Subject;
    }

    public void setSubject(com.atendimento.model.enums.Subject subject) {
        Subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
