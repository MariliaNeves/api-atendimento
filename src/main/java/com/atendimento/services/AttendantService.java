package com.atendimento.services;

import com.atendimento.model.dto.AttendantDTO;
import com.atendimento.model.dto.ServiceRequestDTO;
import com.atendimento.model.entity.AttendantEntity;
import com.atendimento.model.enums.Team;
import com.atendimento.model.util.ConverterUtil;
import com.atendimento.model.util.MockUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by  Marília
 * Date: 13/06/2024
 */


@Service
public class AttendantService {

//    @Autowired
//    private AttendantRepository attendantRepository; TODO implementar repository
    private MockUtil mockUtil;
    List<AttendantEntity> attendantEntityList;

    public AttendantService() {
        this.mockUtil = new MockUtil();
        this.mockUtil.mockAttendantEntitySemServiceRequests(); //TODO remover apos implementar repository
        attendantEntityList = this.mockUtil.mockAttendantEntityComServiceRequests();
    }

    public List<AttendantDTO> getAllAttendants() {
        List<AttendantEntity> entities = mockUtil.getAttendantEntityList();
        return ConverterUtil.convertAttendantEntityListToDTO(entities);
        //return attendantRepository.findAll(); // TODO implementar repository
    }

    public List<AttendantEntity> getAllAttendantByTeam(Team team) {
        return mockUtil.mockAttendantEntityComServiceRequests();
        //return attendantRepository.findByTeam(team); // TODO implementar repository
    }

    public AttendantDTO createAttendant(AttendantDTO attendant) {
        AttendantEntity entity = mockUtil.mockCreateAttendantEntity(ConverterUtil.convertAttendantDTOToAttendantEntity(attendant));
        return ConverterUtil.convertAttendantEntityToDTO(entity);
        //return attendantRepository.save(attendantEntity); // TODO implementar repository
    }

    public AttendantEntity updateAttendant(Long id, AttendantEntity entity) {
        return entity;
//        return attendantRepository.findById(id)  // TODO implementar repository
//                .map(attendant -> {
//                    attendant.setName(attendantDTODetails.getName());
//                    attendant.setServiceRequestEntity(null);
//                    attendant.setTeam(attendantDetails.getTeam());
//                    return attendantRepository.save(attendant);
//                })
//                .orElseThrow(() -> new RuntimeException("Attendant not found"));
    }

//    public void deleteAttendant(Long id) {
//        attendantRepository.deleteById(id);
//    }
}


