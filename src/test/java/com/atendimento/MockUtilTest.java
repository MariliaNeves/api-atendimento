package com.atendimento;

import com.atendimento.model.dto.AttendantDTO;
import com.atendimento.model.dto.ServiceRequestDTO;
import com.atendimento.model.enums.ServiceStatus;
import com.atendimento.model.enums.Subject;
import com.atendimento.model.enums.Team;
import com.atendimento.model.util.MockUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MockUtilTest {

    private MockUtil mockUtil;

    @BeforeEach
    void setUp() {
        mockUtil = new MockUtil();
    }

    @Test
    void testMockCreateServiceRequestDTO() {
        ServiceRequestDTO serviceRequestDTO = mockUtil.mockCreateServiceRequestDTO();
        assertNotNull(serviceRequestDTO);
        assertEquals(4, serviceRequestDTO.getId());
        assertNotNull(serviceRequestDTO.getCreateAt());
        assertNotNull(serviceRequestDTO.getUpdateAt());
        assertEquals("awwwfocbweofwqwdwfdvb", serviceRequestDTO.getDescription());
        assertEquals(Subject.PROBLEMAS_COM_CARTAO, serviceRequestDTO.getSubject());
        assertEquals(ServiceStatus.CRIADO, serviceRequestDTO.getServiceStatus());
    }

    @Test
    void testMockAttendantDTOSemServiceRequests() {
        AttendantDTO attendantDTO = mockUtil.mockAttendantDTOSemServiceRequests();
        assertNotNull(attendantDTO);
        assertEquals(1, attendantDTO.getId());
        assertEquals("Luiza", attendantDTO.getName());
        assertEquals(Team.CARTOES, attendantDTO.getTeam());
        assertNull(attendantDTO.getServiceRequestDTO());
    }

    @Test
    void testMockAttendantDTOComServiceRequests() {
        AttendantDTO attendantDTO = mockUtil.mockAttendantDTOComServiceRequests();
        assertNotNull(attendantDTO);
        assertEquals(2, attendantDTO.getId());
        assertEquals("Joao", attendantDTO.getName());
        assertEquals(Team.EMPRESTIMOS, attendantDTO.getTeam());
        assertNotNull(attendantDTO.getServiceRequestDTO());
        assertEquals(3, attendantDTO.getServiceRequestDTO().size());
    }

    @Test
    void testGetServiceRequestDTOs() {
        List<ServiceRequestDTO> serviceRequestDTOList = mockUtil.getServiceRequestDTOs();
        assertNotNull(serviceRequestDTOList);
        assertEquals(3, serviceRequestDTOList.size());

        ServiceRequestDTO serviceRequest1 = serviceRequestDTOList.get(0);
        assertEquals(1, serviceRequest1.getId());
        assertNotNull(serviceRequest1.getCreateAt());
        assertNotNull(serviceRequest1.getUpdateAt());
        assertEquals("uigwefuigqwieufg", serviceRequest1.getDescription());
        assertEquals(Subject.CONTRATACAO_DE_EMPRESTIMO, serviceRequest1.getSubject());
        assertEquals(ServiceStatus.EM_ATENDIMENTO, serviceRequest1.getServiceStatus());

        ServiceRequestDTO serviceRequest2 = serviceRequestDTOList.get(1);
        assertEquals(2, serviceRequest2.getId());
        assertNotNull(serviceRequest2.getCreateAt());
        assertNotNull(serviceRequest2.getUpdateAt());
        assertEquals("310847r138047fvc8y38v", serviceRequest2.getDescription());
        assertEquals(Subject.CONTRATACAO_DE_EMPRESTIMO, serviceRequest2.getSubject());
        assertEquals(ServiceStatus.EM_ATENDIMENTO, serviceRequest2.getServiceStatus());

        ServiceRequestDTO serviceRequest3 = serviceRequestDTOList.get(2);
        assertEquals(3, serviceRequest3.getId());
        assertNotNull(serviceRequest3.getCreateAt());
        assertNotNull(serviceRequest3.getUpdateAt());
        assertEquals("9nb3149thg4385bgvf", serviceRequest3.getDescription());
        assertEquals(Subject.CONTRATACAO_DE_EMPRESTIMO, serviceRequest3.getSubject());
        assertEquals(ServiceStatus.EM_ATENDIMENTO, serviceRequest3.getServiceStatus());
    }
}

