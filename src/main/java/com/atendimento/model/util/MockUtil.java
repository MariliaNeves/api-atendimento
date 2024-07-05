package com.atendimento.model.util;

import com.atendimento.model.dto.AttendantDTO;
import com.atendimento.model.dto.ServiceRequestDTO;
import com.atendimento.model.enums.ServiceStatus;
import com.atendimento.model.enums.Subject;
import com.atendimento.model.enums.Team;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockUtil {


    public ServiceRequestDTO mockCreateServiceRequestDTO() {
        ServiceRequestDTO serviceRequest = new ServiceRequestDTO();
        serviceRequest.setId(4);
        serviceRequest.setCreateAt(new Date());
        serviceRequest.setUpdateAt(new Date());
        serviceRequest.setDescription("awwwfocbweofwqwdwfdvb");
        serviceRequest.setSubject(Subject.PROBLEMAS_COM_CARTAO);
        serviceRequest.setServiceStatus(ServiceStatus.CRIADO);
        return serviceRequest;
    }

    public AttendantDTO mockAttendantDTOSemServiceRequests() {

        AttendantDTO attendant = new AttendantDTO();
        attendant.setId(1);
        attendant.setName("Luiza");
        attendant.setTeam(Team.CARTOES);
        return  attendant;

    }

    public AttendantDTO mockAttendantDTOComServiceRequests() {

        List<ServiceRequestDTO> dtos = getServiceRequestDTOs();
        AttendantDTO attendant = new AttendantDTO();
        attendant.setId(2);
        attendant.setName("Joao");
        attendant.setTeam(Team.EMPRESTIMOS);
        attendant.setServiceRequestDTO(dtos);
        return attendant;

    }

    public List<ServiceRequestDTO>  getServiceRequestDTOs() {

        ServiceRequestDTO serviceRequest = new ServiceRequestDTO();
        List<ServiceRequestDTO> serviceRequestDTOList = new ArrayList<>();
        serviceRequest.setId(1);
        serviceRequest.setCreateAt(new Date());
        serviceRequest.setUpdateAt(new Date());
        serviceRequest.setDescription("uigwefuigqwieufg");
        serviceRequest.setSubject(Subject.CONTRATACAO_DE_EMPRESTIMO);
        serviceRequest.setServiceStatus(ServiceStatus.EM_ATENDIMENTO);
        serviceRequestDTOList.add(serviceRequest);

        serviceRequest = new ServiceRequestDTO();
        serviceRequest.setId(2);
        serviceRequest.setCreateAt(new Date());
        serviceRequest.setUpdateAt(new Date());
        serviceRequest.setDescription("310847r138047fvc8y38v");
        serviceRequest.setSubject(Subject.CONTRATACAO_DE_EMPRESTIMO);
        serviceRequest.setServiceStatus(ServiceStatus.EM_ATENDIMENTO);
        serviceRequestDTOList.add(serviceRequest);

        serviceRequest = new ServiceRequestDTO();
        serviceRequest.setId(3);
        serviceRequest.setCreateAt(new Date());
        serviceRequest.setUpdateAt(new Date());
        serviceRequest.setDescription("9nb3149thg4385bgvf");
        serviceRequest.setSubject(Subject.CONTRATACAO_DE_EMPRESTIMO);
        serviceRequest.setServiceStatus(ServiceStatus.EM_ATENDIMENTO);
        serviceRequestDTOList.add(serviceRequest);
        return serviceRequestDTOList;

    }

}

