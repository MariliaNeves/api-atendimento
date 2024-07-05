package com.atendimento.services;


import com.atendimento.model.dto.AttendantDTO;
import com.atendimento.model.enums.Team;
import com.atendimento.model.util.InvalidRequestException;
import com.atendimento.model.util.MockUtil;
import com.atendimento.model.util.QueueManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by  Marília
 * Date: 13/06/2024
 */


@Service
public class AttendantService {

    private MockUtil mockUtil;
    @Autowired
    private QueueManager queueManager;


    public AttendantService() {
        this.mockUtil = new MockUtil();
    }

    public List<AttendantDTO> getAllAttendants() {
        return queueManager.getAttendantQueue();
    }

    public List<AttendantDTO> getAllAttendantByTeam(Team team) {
        List<AttendantDTO> attendantDTOList = queueManager.getAttendantQueue();

        return attendantDTOList.stream()
                .filter(item -> item.getTeam().equals(team))
                .collect(Collectors.toList());
    }

    public AttendantDTO createAttendant(AttendantDTO dto) {
        if (dto.getName() == null) {
            throw new InvalidRequestException("Name cannot be null");
        }

        if (dto.getTeam() == null) {
            throw new InvalidRequestException("Team cannot be null");
        }
        return queueManager.addAttendantQueue(dto);

    }

}


