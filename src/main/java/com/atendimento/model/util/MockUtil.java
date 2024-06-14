package com.atendimento.model.util;

import com.atendimento.model.entity.AttendantEntity;
import com.atendimento.model.entity.ServiceRequestEntity;
import com.atendimento.model.enums.ServiceStatus;
import com.atendimento.model.enums.Subject;
import com.atendimento.model.enums.Team;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MockUtil {

    private List<AttendantEntity> attendantEntityList;
    private List<ServiceRequestEntity> serviceRequestEntityList;

    public MockUtil() {
        attendantEntityList = new ArrayList<>();
        serviceRequestEntityList = new ArrayList<>();
    }

    public AttendantEntity mockCreateAttendantEntity(AttendantEntity entity) {
        Long id = (long)getAttendantEntityList().size()+1;
        entity.setId(id.longValue());
        getAttendantEntityList().add(entity);
        return entity;
    }

    public AttendantEntity mockUpdateAttendantEntity(Long id, AttendantEntity entity) {

        Optional<AttendantEntity> optionalAttendant = getAttendantEntityList().stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();

        AttendantEntity updatedEntity = null;

        if (optionalAttendant.isPresent()) {
            int index = getAttendantEntityList().indexOf(optionalAttendant.get());
            getAttendantEntityList().remove(entity);
            getAttendantEntityList().add(entity);
            updatedEntity = getAttendantEntityList().get(index);
        }

        for(AttendantEntity item : getAttendantEntityList()){
            if(item.getId().equals(id)){
                item.setServiceRequestEntity(entity.getServiceRequestEntity());
                break;
            }
        }

        return updatedEntity;
    }

    public ServiceRequestEntity mockCreateServiceRequestEntity(ServiceRequestEntity entity, AttendantEntity attendantEntity) {
        ServiceRequestEntity serviceRequest = new ServiceRequestEntity();
        if (entity!= null && attendantEntity != null){

            Long id = (long)getServiceRequestEntityList().size()+1;
            entity.setId(id.longValue());
            getServiceRequestEntityList().add(entity);

            if(attendantEntity != null)
                mockUpdateAttendantEntity(attendantEntity.getId(), attendantEntity);

            Optional<ServiceRequestEntity> optionalAttendant = getServiceRequestEntityList().stream()
                    .filter(item -> item.getId().equals(entity.getId()))
                    .findFirst();
            serviceRequest = optionalAttendant.get();

        }

        return serviceRequest;
    }

    public List<AttendantEntity> mockAttendantEntitySemServiceRequests() {

        AttendantEntity attendant = new AttendantEntity();
        attendant.setId(1L);
        attendant.setName("Luiza");
        attendant.setTeam(Team.CARTOES);
        getAttendantEntityList().add(attendant);
        return attendantEntityList;

    }

    public  List<AttendantEntity> mockAttendantEntityComServiceRequests() {
        getServiceRequestEntitys();

        AttendantEntity attendant1 = new AttendantEntity();
        attendant1.setId(2L);
        attendant1.setName("Joao");
        attendant1.setTeam(Team.EMPRESTIMOS);
        attendant1.setServiceRequestEntity(getServiceRequestEntityList());

        getServiceRequestEntityList().get(0).setAttendantEntity(attendant1);
        getServiceRequestEntityList().get(1).setAttendantEntity(attendant1);
        getServiceRequestEntityList().get(2).setAttendantEntity(attendant1);

        getAttendantEntityList().add(attendant1);
        return getAttendantEntityList();

    }

    public void getServiceRequestEntitys() {
        ServiceRequestEntity serviceRequest = new ServiceRequestEntity();
        serviceRequest.setId(1L);
        serviceRequest.setCreateAt(new Date());
        serviceRequest.setUpdateAt(new Date());
        serviceRequest.setDescription("uigwefuigqwieufg");
        serviceRequest.setSubject(Subject.CONTRATACAO_DE_EMPRESTIMO);
        serviceRequest.setServiceStatus(ServiceStatus.EM_ATENDIMENTO);
        getServiceRequestEntityList().add(serviceRequest);

        serviceRequest = new ServiceRequestEntity();
        serviceRequest.setId(2L);
        serviceRequest.setCreateAt(new Date());
        serviceRequest.setUpdateAt(new Date());
        serviceRequest.setDescription("310847r138047fvc8y38v");
        serviceRequest.setSubject(Subject.CONTRATACAO_DE_EMPRESTIMO);
        serviceRequest.setServiceStatus(ServiceStatus.EM_ATENDIMENTO);
        getServiceRequestEntityList().add(serviceRequest);

        serviceRequest = new ServiceRequestEntity();
        serviceRequest.setId(3L);
        serviceRequest.setCreateAt(new Date());
        serviceRequest.setUpdateAt(new Date());
        serviceRequest.setDescription("9nb3149thg4385bgvf");
        serviceRequest.setSubject(Subject.CONTRATACAO_DE_EMPRESTIMO);
        serviceRequest.setServiceStatus(ServiceStatus.EM_ATENDIMENTO);
        getServiceRequestEntityList().add(serviceRequest);


    }

    public List<AttendantEntity> getAttendantEntityList() {
        return this.attendantEntityList;
    }

    public List<ServiceRequestEntity> getServiceRequestEntityList() {
        return this.serviceRequestEntityList;
    }
}

