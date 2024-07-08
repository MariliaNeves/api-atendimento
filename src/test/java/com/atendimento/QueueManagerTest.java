package com.atendimento;


import com.atendimento.model.dto.AttendantDTO;
import com.atendimento.model.dto.ServiceRequestDTO;
import com.atendimento.model.util.MockUtil;
import com.atendimento.model.util.QueueManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QueueManagerTest {

    @InjectMocks
    private QueueManager queueManager;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private MockUtil mockUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddServiceRequestQueue() {
        ServiceRequestDTO serviceRequestDTO = new ServiceRequestDTO();
        List<ServiceRequestDTO> serviceRequestQueue = new ArrayList<>();
        when(rabbitTemplate.receiveAndConvert(anyString())).thenReturn(null);

        ServiceRequestDTO result = queueManager.addServiceRequestQueue(serviceRequestDTO);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(rabbitTemplate, times(1)).convertAndSend(anyString(), any(ServiceRequestDTO.class));
    }

    @Test
    void testAddAttendantQueue() {
        AttendantDTO attendantDTO = new AttendantDTO();
        List<AttendantDTO> attendantQueue = new ArrayList<>();
        when(rabbitTemplate.receiveAndConvert(anyString())).thenReturn(null);

        AttendantDTO result = queueManager.addAttendantQueue(attendantDTO);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(rabbitTemplate, times(1)).convertAndSend(anyString(), any(AttendantDTO.class));
    }

    @Test
    void testGetServiceRequestQueue() {
        ServiceRequestDTO serviceRequestDTO = new ServiceRequestDTO();
        when(rabbitTemplate.receiveAndConvert(anyString())).thenReturn(serviceRequestDTO).thenReturn(null);

        List<ServiceRequestDTO> result = queueManager.getServiceRequestQueue();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains(serviceRequestDTO));
    }

    @Test
    void testGetAttendantQueue() {
        AttendantDTO attendantDTO = new AttendantDTO();
        when(rabbitTemplate.receiveAndConvert(anyString())).thenReturn(attendantDTO).thenReturn(null);

        List<AttendantDTO> result = queueManager.getAttendantQueue();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains(attendantDTO));
    }

    @Test
    void testInit() {
        AttendantDTO attendantSemService = new AttendantDTO();
        AttendantDTO attendantComService = new AttendantDTO();
        ServiceRequestDTO serviceRequest = new ServiceRequestDTO();

        when(mockUtil.mockAttendantDTOSemServiceRequests()).thenReturn(attendantSemService);
        when(mockUtil.mockAttendantDTOComServiceRequests()).thenReturn(attendantComService);
        when(mockUtil.mockCreateServiceRequestDTO()).thenReturn(serviceRequest);

        queueManager.init();

        verify(mockUtil, times(1)).mockAttendantDTOSemServiceRequests();
        verify(mockUtil, times(1)).mockAttendantDTOComServiceRequests();
        verify(mockUtil, times(1)).mockCreateServiceRequestDTO();

    }
}
