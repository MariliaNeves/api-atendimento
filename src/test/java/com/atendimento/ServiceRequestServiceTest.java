package com.atendimento;


import com.atendimento.model.dto.AttendantDTO;
import com.atendimento.model.dto.ServiceRequestDTO;
import com.atendimento.model.enums.ServiceStatus;
import com.atendimento.model.enums.Subject;
import com.atendimento.model.enums.Team;
import com.atendimento.model.util.InvalidRequestException;
import com.atendimento.model.util.QueueManager;
import com.atendimento.services.AttendantService;
import com.atendimento.services.ServiceRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceRequestServiceTest {

    @InjectMocks
    private ServiceRequestService serviceRequestService;

    @Mock
    private AttendantService attendantService;

    @Mock
    private QueueManager queueManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllServiceRequest() {
        ServiceRequestDTO request1 = new ServiceRequestDTO();
        ServiceRequestDTO request2 = new ServiceRequestDTO();

        when(queueManager.getServiceRequestQueue()).thenReturn(Arrays.asList(request1, request2));

        List<ServiceRequestDTO> result = serviceRequestService.getAllServiceRequest();

        assertEquals(2, result.size());
        assertTrue(result.contains(request1));
        assertTrue(result.contains(request2));
    }

    @Test
    void testCreateServiceRequest() {
        ServiceRequestDTO request = new ServiceRequestDTO();
        request.setDescription("test test test...");
        request.setSubject(Subject.PROBLEMAS_COM_CARTAO);

        AttendantDTO attendant = new AttendantDTO("Maria", Team.CARTOES);
        when(attendantService.getAllAttendantByTeam(Team.CARTOES)).thenReturn(Arrays.asList(attendant));
        when(queueManager.addServiceRequestQueue(request)).thenReturn(request);

        ServiceRequestDTO result = serviceRequestService.createServiceRequest(request);

        assertNotNull(result);
        assertEquals(ServiceStatus.EM_ATENDIMENTO, result.getServiceStatus());
        assertNotNull(result.getAttendantDTO());
        assertEquals(attendant, result.getAttendantDTO());
    }

    @Test
    void testCreateServiceRequestInvalidRequestException() {
        ServiceRequestDTO invalidRequest = new ServiceRequestDTO();
        invalidRequest.setDescription(null);

        InvalidRequestException exception = assertThrows(InvalidRequestException.class, () -> {
            serviceRequestService.createServiceRequest(invalidRequest);
        });

        assertEquals("Description cannot be null", exception.getMessage());
    }

    @Test
    void testCreateServiceRequestWithNoAvailableAttendant() {
        ServiceRequestDTO request = new ServiceRequestDTO();
        request.setDescription("test test test...");
        request.setSubject(Subject.OUTROS);

        when(attendantService.getAllAttendantByTeam(Team.OUTROS_ASSUNTOS)).thenReturn(Arrays.asList());

        ServiceRequestDTO result = serviceRequestService.createServiceRequest(request);

        assertNotNull(result);
        assertEquals(ServiceStatus.CRIADO, result.getServiceStatus());
        assertNull(result.getAttendantDTO());
    }

}

