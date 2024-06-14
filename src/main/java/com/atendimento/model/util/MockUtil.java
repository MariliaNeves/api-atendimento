package com.atendimento.model.util;

import com.atendimento.model.entity.AttendantEntity;
import com.atendimento.model.entity.ServiceRequestEntity;
import com.atendimento.model.enums.ServiceStatus;
import com.atendimento.model.enums.Subject;
import com.atendimento.model.enums.Team;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockUtil {

    private List<AttendantEntity> attendantEntityList;
    private List<ServiceRequestEntity> serviceRequestEntityList;

    public MockUtil() {
        attendantEntityList = new ArrayList<>();
        serviceRequestEntityList = new ArrayList<>();
    }

    public AttendantEntity mockCreateAttendantEntity(AttendantEntity entity) {
        Long id = (long)this.attendantEntityList.size()+1;
        entity.setId(id.longValue());
        this.attendantEntityList.add(entity);
        return entity;
    }

    public AttendantEntity mockUpdateAttendantEntity(Long id, AttendantEntity entity) {
        this.attendantEntityList.add(entity);
        return entity;
    }

    public ServiceRequestEntity mockCreateServiceRequestEntity(ServiceRequestEntity entity) {
        Long id = (long)this.serviceRequestEntityList.size()+1;
        entity.setId(id.longValue());
        this.serviceRequestEntityList.add(entity);
        return entity;
    }

    public List<AttendantEntity> mockAttendantEntitySemServiceRequests() {

        // Criação de AttendantEntity
        AttendantEntity attendant = new AttendantEntity();
        attendant.setId(1L);
        attendant.setName("Luiza");
        attendant.setTeam(Team.CARTOES);
        this.attendantEntityList.add(attendant);

        attendant = new AttendantEntity();
        attendant.setId(2L);
        attendant.setName("Joao");
        attendant.setTeam(Team.EMPRESTIMOS);

        attendant = new AttendantEntity();
        attendant.setId(3L);
        attendant.setName("Joao");
        attendant.setTeam(Team.OUTROS_ASSUNTOS);

        attendant = new AttendantEntity();
        attendant.setId(4L);
        attendant.setName("Joao");
        attendant.setTeam(Team.CARTOES);

        return attendantEntityList;

    }

    public  List<AttendantEntity> mockAttendantEntityComServiceRequests() {
        Result result = getServiceRequestEntitys();

        // Criação de AttendantEntity
        AttendantEntity attendant1 = new AttendantEntity();
        attendant1.setId(2L);
        attendant1.setName("Joao");
        attendant1.setTeam(Team.EMPRESTIMOS);
        attendant1.setServiceRequestEntity(result.serviceRequests());

        // Vinculação das solicitações de serviço com o atendente
        result.serviceRequest1().setAttendantEntity(attendant1);
        result.serviceRequest2().setAttendantEntity(attendant1);
        result.serviceRequest3().setAttendantEntity(attendant1);

        // Criação do Optional<AttendantEntity>
        this.attendantEntityList.add(attendant1);
        return this.attendantEntityList;

    }

    private Result getServiceRequestEntitys() {
        ServiceRequestEntity serviceRequest1 = new ServiceRequestEntity();
        serviceRequest1.setId(1L);
        serviceRequest1.setCreateAt(new Date());
        serviceRequest1.setUpdateAt(new Date());
        serviceRequest1.setDescription("uigwefuigqwieufg");
        serviceRequest1.setSubject(Subject.CONTRATACAO_DE_EMPRESTIMO);
        serviceRequest1.setServiceStatus(ServiceStatus.EM_ATENDIMENTO);

        ServiceRequestEntity serviceRequest2 = new ServiceRequestEntity();
        serviceRequest2.setId(2L);
        serviceRequest2.setCreateAt(new Date());
        serviceRequest2.setUpdateAt(new Date());
        serviceRequest2.setDescription("310847r138047fvc8y38v");
        serviceRequest2.setSubject(Subject.CONTRATACAO_DE_EMPRESTIMO);
        serviceRequest2.setServiceStatus(ServiceStatus.EM_ATENDIMENTO);

        ServiceRequestEntity serviceRequest3 = new ServiceRequestEntity();
        serviceRequest3.setId(3L);
        serviceRequest3.setCreateAt(new Date());
        serviceRequest3.setUpdateAt(new Date());
        serviceRequest3.setDescription("9nb3149thg4385bgvf");
        serviceRequest3.setSubject(Subject.CONTRATACAO_DE_EMPRESTIMO);
        serviceRequest3.setServiceStatus(ServiceStatus.EM_ATENDIMENTO);

        // Lista de ServiceRequestEntity

        this.serviceRequestEntityList.add(serviceRequest1);
        this.serviceRequestEntityList.add(serviceRequest2);
        this.serviceRequestEntityList.add(serviceRequest3);
        Result result = new Result(serviceRequest1, serviceRequest2, serviceRequest3, this.serviceRequestEntityList);
        return result;
    }

    private record Result(ServiceRequestEntity serviceRequest1, ServiceRequestEntity serviceRequest2,
                          ServiceRequestEntity serviceRequest3, List<ServiceRequestEntity> serviceRequests) {
    }

    public List<AttendantEntity> getAttendantEntityList() {
        return attendantEntityList;
    }

    public List<ServiceRequestEntity> getServiceRequestEntityList() {
        return serviceRequestEntityList;
    }
}

