package com.atendimento.services;

import com.atendimento.model.dto.AttendantDTO;
import com.atendimento.model.dto.ServiceRequestDTO;
import com.atendimento.model.enums.ServiceStatus;
import com.atendimento.model.enums.Team;
import com.atendimento.model.util.InvalidRequestException;
import com.atendimento.model.util.MockUtil;
import com.atendimento.model.util.QueueManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by  Marília
 * Date: 13/06/2024
 */


@Service
public class ServiceRequestService {

    @Autowired
    private AttendantService attendantService;
    private static final int MAX_SERVICES_REQUESTS = 3;
    @Autowired
    private QueueManager queueManager;

    private MockUtil mockUtil;


    public ServiceRequestService() {
        this.mockUtil = new MockUtil();
    }

    public List<ServiceRequestDTO> getAllServiceRequest() {
        return queueManager.getServiceRequestQueue();
    }

    public ServiceRequestDTO createServiceRequest(ServiceRequestDTO dto) {

        if (dto.getDescription() == null) {
            throw new InvalidRequestException("Description cannot be null");
        }

        dto.setUpdateAt(new Date());
        dto.setCreateAt(new Date());

        List<AttendantDTO> attendantDTOList = getAttendantBySubject(dto);
        AttendantDTO attendantDTO = selectUnattendedAttendant(attendantDTOList);

        if (attendantDTO != null) {
            dto.setServiceStatus(ServiceStatus.EM_ATENDIMENTO);
            dto.setAttendantDTO(attendantDTO);
            addServiceToAttendant(dto, attendantDTO);
            queueManager.addAttendantQueue(attendantDTO);
        }else {
            dto.setServiceStatus(ServiceStatus.CRIADO);
        }

        return queueManager.addServiceRequestQueue(dto);


    }

    private static void addServiceToAttendant(ServiceRequestDTO dto, AttendantDTO attendantDTO) {
        if (attendantDTO.getServiceRequestDTO() != null) {
            attendantDTO.getServiceRequestDTO().add(dto);
        } else {
            List<ServiceRequestDTO> dtos = new ArrayList<>();
            dtos.add(dto);
            attendantDTO.setServiceRequestDTO(dtos);
        }
    }

    private static AttendantDTO selectUnattendedAttendant(List<AttendantDTO> attendantDTOList) {
        AttendantDTO attendantDTO = null;
        for (AttendantDTO item : attendantDTOList){
            if(item.getServiceRequestDTO() == null) {
                attendantDTO = item;
                break;
            }
            if(item.getServiceRequestDTO().size() < MAX_SERVICES_REQUESTS) {
                attendantDTO = item;
                break;
            }
        }
        return attendantDTO;
    }

    private List<AttendantDTO> getAttendantBySubject(ServiceRequestDTO dto) {
        List<AttendantDTO> attendantDTOList = new ArrayList<>();
        switch (dto.getSubject()){
            case PROBLEMAS_COM_CARTAO:
                attendantDTOList = attendantService.getAllAttendantByTeam(Team.CARTOES);
                break;
            case CONTRATACAO_DE_EMPRESTIMO:
                attendantDTOList = attendantService.getAllAttendantByTeam(Team.EMPRESTIMOS);
                break;
            case OUTROS:
                attendantDTOList = attendantService.getAllAttendantByTeam(Team.OUTROS_ASSUNTOS);
        }
        return attendantDTOList;
    }



}


