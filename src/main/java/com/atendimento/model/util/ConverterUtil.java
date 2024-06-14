package com.atendimento.model.util;

import com.atendimento.model.dto.AttendantDTO;
import com.atendimento.model.dto.ServiceRequestDTO;
import com.atendimento.model.entity.AttendantEntity;
import com.atendimento.model.entity.ServiceRequestEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ConverterUtil {

    public static AttendantEntity convertAttendantDTOToAttendantEntity(AttendantDTO dto) {
        AttendantEntity entity = new AttendantEntity();
        entity.setId(dto.getId() != null ? dto.getId().longValue() : null);
        entity.setName(dto.getName());
        entity.setTeam(dto.getTeam());
        if(dto.getServiceRequestDTO() != null){
            entity.setServiceRequestEntity(convertServiceRequestDTOListToEntity(dto.getServiceRequestDTO()));
        }
        return entity;
    }

    private static List<ServiceRequestEntity> convertServiceRequestDTOListToEntity(List<ServiceRequestDTO> dto) {
        return dto.stream().map(item -> {
            var entity = new ServiceRequestEntity();
            entity.setId(Optional.ofNullable(item.getId()).map(Integer::longValue).orElse(null));
            entity.setCreateAt(item.getCreateAt());
            entity.setUpdateAt(item.getUpdateAt());
            entity.setDescription(item.getDescription());
            entity.setSubject(item.getSubject());
            entity.setServiceStatus(item.getServiceStatus());
            return entity;
        }).collect(Collectors.toList());
    }

    public static ServiceRequestEntity convertServiceRequestDTOToEntity(ServiceRequestDTO dto) {
            var entity = new ServiceRequestEntity();
            entity.setId(Optional.ofNullable(dto.getId()).map(Integer::longValue).orElse(null));
            entity.setCreateAt(dto.getCreateAt());
            entity.setUpdateAt(dto.getUpdateAt());
            entity.setSubject(dto.getSubject());
            entity.setDescription(dto.getDescription());
            entity.setServiceStatus(dto.getServiceStatus());
            return entity;
            }

    public static AttendantDTO convertAttendantEntityToDTO(AttendantEntity entity) {
        AttendantDTO dto = new AttendantDTO();
        dto.setId(entity.getId() != null ? entity.getId().intValue() : null);
        dto.setName(entity.getName());
        dto.setTeam(entity.getTeam());
        return dto;
    }

    public static List<AttendantDTO> convertAttendantEntityListToDTO(List<AttendantEntity> entity) {

        return entity.stream()
                .map(item -> {
                    AttendantDTO dto = new AttendantDTO();
                    dto.setId(item.getId() != null ? item.getId().intValue() : null);
                    dto.setName(item.getName());
                    dto.setTeam(item.getTeam());
                    if(item.getServiceRequestEntity() != null){
                        dto.setServiceRequestDTO(convertServiceRequestEntityToServiceRequestDTO(item.getServiceRequestEntity()));
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public static ServiceRequestDTO convertServiceRequestEntityToDTO(ServiceRequestEntity entity) {
        ServiceRequestDTO dto = new ServiceRequestDTO();
        dto.setId(entity.getId() != null ? entity.getId().intValue() : null);
        dto.setCreateAt(entity.getCreateAt());
        dto.setUpdateAt(entity.getUpdateAt());
        dto.setSubject(entity.getSubject());
        dto.setDescription(entity.getDescription());
        dto.setServiceStatus(entity.getServiceStatus());
        dto.setAttendantDTO(
                entity.getAttendantEntity() != null ? convertAttendantEntityToDTO(entity.getAttendantEntity()) : null
        );
        return dto;
    }

    private static List<ServiceRequestDTO> convertServiceRequestEntityToServiceRequestDTO(List<ServiceRequestEntity> serviceRequests) {
        return serviceRequests.stream()
                .map(item -> {
                    ServiceRequestDTO dto = new ServiceRequestDTO();
                    dto.setId(item.getId() != null ? item.getId().intValue() : null);
                    dto.setCreateAt(item.getCreateAt());
                    dto.setUpdateAt(item.getUpdateAt());
                    dto.setSubject(item.getSubject());
                    dto.setDescription(item.getDescription());
                    dto.setServiceStatus(item.getServiceStatus());
                    return dto;
                })
                .collect(Collectors.toList());
    }


}
