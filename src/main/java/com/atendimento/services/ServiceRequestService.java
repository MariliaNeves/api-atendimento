package com.atendimento.services;

import com.atendimento.model.dto.ServiceRequestDTO;
import com.atendimento.model.entity.AttendantEntity;
import com.atendimento.model.entity.ServiceRequestEntity;
import com.atendimento.model.enums.ServiceStatus;
import com.atendimento.model.enums.Team;
import com.atendimento.model.util.ConverterUtil;
import com.atendimento.model.util.MockUtil;
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


    private AttendantService attendantService;
    private static final int MAX_SERVICES_REQUESTS = 3;
    private MockUtil mockUtil;

    public ServiceRequestService() {
        this.mockUtil = new MockUtil();
        this.attendantService = new AttendantService();
    }

    public ServiceRequestDTO createServiceRequest(ServiceRequestDTO dto) {
        ServiceRequestEntity entity = ConverterUtil.convertServiceRequestDTOToEntity(dto);
        List<AttendantEntity> attendantEntityList = new ArrayList<>();
        switch (entity.getSubject()){
            case PROBLEMAS_COM_CARTAO:
                attendantEntityList = attendantService.getAllAttendantByTeam(Team.CARTOES);
                break;
            case CONTRATACAO_DE_EMPRESTIMO:
                attendantEntityList = attendantService.getAllAttendantByTeam(Team.EMPRESTIMOS);
                break;
            case OUTROS:
                attendantEntityList = attendantService.getAllAttendantByTeam(Team.OUTROS_ASSUNTOS);
        }

        AttendantEntity attendantEntity = null;
        for (AttendantEntity item : attendantEntityList){
            if(item.getServiceRequestEntity() == null) {
                attendantEntity = item;
                break;
            }
            if(item.getServiceRequestEntity().size() < MAX_SERVICES_REQUESTS) {
                attendantEntity = item;
                break;
            }
        }

        entity.setUpdateAt(new Date());
        entity.setCreateAt(new Date());

        if (attendantEntity != null) {
            entity.setServiceStatus(ServiceStatus.EM_ATENDIMENTO);
            entity.setAttendantEntity(attendantEntity);

            if (attendantEntity.getServiceRequestEntity() != null) {
                attendantEntity.getServiceRequestEntity().add(entity);
            } else {
                List<ServiceRequestEntity> entities = new ArrayList<>();
                entities.add(entity);
                attendantEntity.setServiceRequestEntity(entities);
            }
            mockUtil.mockCreateServiceRequestEntity(entity);
            attendantService.updateAttendant(attendantEntity.getId(), attendantEntity);
        }else {
            entity.setServiceStatus(ServiceStatus.CRIADO);
        }
        return ConverterUtil.convertServiceRequestEntityToDTO(entity);

    }

}


