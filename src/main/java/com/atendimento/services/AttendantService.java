package com.atendimento.services;

import com.atendimento.model.dto.AttendantDTO;
import com.atendimento.model.entity.AttendantEntity;
import com.atendimento.model.enums.Team;
import com.atendimento.model.util.ConverterUtil;
import com.atendimento.model.util.MockUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by  Marília
 * Date: 13/06/2024
 */


@Service
public class AttendantService {

    private MockUtil mockUtil;
    List<AttendantEntity> attendantEntityList;

    public AttendantService() {
        this.mockUtil = new MockUtil();
        this.mockUtil.mockAttendantEntitySemServiceRequests();
        attendantEntityList = this.mockUtil.mockAttendantEntityComServiceRequests();
    }

    public List<AttendantDTO> getAllAttendants() {
        List<AttendantEntity> entities = mockUtil.getAttendantEntityList();
        return ConverterUtil.convertAttendantEntityListToDTO(entities);
    }

    public List<AttendantEntity> getAllAttendantByTeam(Team team) {
        return mockUtil.mockAttendantEntityComServiceRequests();
    }

    public AttendantDTO createAttendant(AttendantDTO attendant) {
        AttendantEntity entity = mockUtil.mockCreateAttendantEntity(ConverterUtil.convertAttendantDTOToAttendantEntity(attendant));
        return ConverterUtil.convertAttendantEntityToDTO(entity);
    }

    public AttendantEntity updateAttendant(Long id, AttendantEntity entity) {
        return entity;
    }

}


