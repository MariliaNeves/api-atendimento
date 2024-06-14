package com.atendimento;


import com.atendimento.model.dto.ServiceRequestDTO;
import com.atendimento.model.enums.Subject;
import com.atendimento.model.util.InvalidRequestException;
import com.atendimento.model.util.MockUtil;
import com.atendimento.services.AttendantService;
import com.atendimento.services.ServiceRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

    @ExtendWith(MockitoExtension.class)
    public class ServiceRequestServiceTests {

        @Mock
        private AttendantService attendantService;

        @Mock
        private MockUtil mockUtil;

        @InjectMocks
        private ServiceRequestService serviceRequestService;
        private ServiceRequestDTO serviceRequestDTO;


        @BeforeEach
        void setUp() {
            serviceRequestDTO = new ServiceRequestDTO();

        }

        @Test
        void testCreateServiceRequest_withNullDescription() {

            InvalidRequestException exception = assertThrows(InvalidRequestException.class, () -> {
                serviceRequestService.createServiceRequest(serviceRequestDTO);
            });
            assertEquals("Description cannot be null", exception.getMessage());
        }

        @Test
        void testCreateServiceRequest() {

            serviceRequestDTO.setSubject(Subject.PROBLEMAS_COM_CARTAO);
            serviceRequestDTO.setDescription("test");
            ServiceRequestDTO result = serviceRequestService.createServiceRequest(serviceRequestDTO);
            assertNotNull(result);
            assertNotNull(result.getCreateAt());
            assertNotNull(result.getUpdateAt());

        }

    }


