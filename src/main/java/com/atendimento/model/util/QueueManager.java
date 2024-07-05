package com.atendimento.model.util;

import com.atendimento.model.dto.AttendantDTO;
import com.atendimento.model.dto.ServiceRequestDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class QueueManager {

    private static final String QUEUE_SERVICE_NAME = "service-request.v1.service-created";
    private static final String QUEUE_ATTENDANTS_NAME = "attendants.v1.attendant-created";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private MockUtil mockUtil;

    public QueueManager() {
        this.mockUtil = new MockUtil();
    }

    @PostConstruct
    public void init() {
        AttendantDTO attendantDTO = mockUtil.mockAttendantDTOSemServiceRequests();
        addAttendantQueue(attendantDTO);

        attendantDTO = mockUtil.mockAttendantDTOComServiceRequests();
        addAttendantQueue(attendantDTO);

        ServiceRequestDTO serviceRequestDTO = mockUtil.mockCreateServiceRequestDTO();
        addServiceRequestQueue(serviceRequestDTO);
    }

    public ServiceRequestDTO addServiceRequestQueue(ServiceRequestDTO dto) {

        List<ServiceRequestDTO>  serviceRequestDTOS = getServiceRequestQueue();
        if(dto.getId() == null ){
            int id = serviceRequestDTOS.size()+1;
            dto.setId(id);
        }
        serviceRequestDTOS.add(dto);
        serviceRequestDTOS.forEach(item -> rabbitTemplate.convertAndSend(QUEUE_SERVICE_NAME, item));
        return dto;
    }

    public AttendantDTO addAttendantQueue(AttendantDTO dto) {

        List<AttendantDTO>  attendantDTOS = getAttendantQueue();
        if(dto.getId() == null ){
            int id = attendantDTOS.size()+1;
            dto.setId(id);
        }
        attendantDTOS.add(dto);
        attendantDTOS.forEach(item -> rabbitTemplate.convertAndSend(QUEUE_ATTENDANTS_NAME, item));
        return dto;
    }

    public List<ServiceRequestDTO> getServiceRequestQueue() {
        List<ServiceRequestDTO> serviceRequestList = new ArrayList<>();

        ServiceRequestDTO serviceRequestDTO;
        while ((serviceRequestDTO = (ServiceRequestDTO) rabbitTemplate.receiveAndConvert(QUEUE_SERVICE_NAME)) != null) {
            serviceRequestList.add(serviceRequestDTO);
        }
        serviceRequestList.forEach(item -> rabbitTemplate.convertAndSend(QUEUE_SERVICE_NAME, item));
        return serviceRequestList;
    }


    public List<AttendantDTO> getAttendantQueue() {
        List<AttendantDTO> attendantDTOList = new ArrayList<>();

        AttendantDTO attendantDTO;
        while ((attendantDTO = (AttendantDTO) rabbitTemplate.receiveAndConvert(QUEUE_ATTENDANTS_NAME)) != null) {
            attendantDTOList.add(attendantDTO);
        }
        attendantDTOList.forEach(item -> rabbitTemplate.convertAndSend(QUEUE_ATTENDANTS_NAME, item));
        return attendantDTOList;
    }

}
