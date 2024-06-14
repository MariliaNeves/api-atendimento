package com.atendimento;

import com.atendimento.controller.AtendimentoController;
import com.atendimento.model.dto.AttendantDTO;
import com.atendimento.model.dto.ServiceRequestDTO;
import com.atendimento.services.AttendantService;
import com.atendimento.services.ServiceRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
public class AtendimentoControllerTests {

    @Mock
    private AttendantService attendantService;

    @Mock
    private ServiceRequestService serviceRequestService;

    @InjectMocks
    private AtendimentoController atendimentoController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(atendimentoController).build();
    }

    @Test
    void testGetAttendants() throws Exception {
        List<AttendantDTO> attendantList = new ArrayList<>();
        AttendantDTO attendant = new AttendantDTO();
        attendant.setName("test");
        attendantList.add(attendant);

        when(attendantService.getAllAttendants()).thenReturn(attendantList);

        mockMvc.perform(MockMvcRequestBuilders.get("/atendimento/attendants"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'name':'test'}]"));

        verify(attendantService, times(1)).getAllAttendants();
    }

    @Test
    void testPostAttendant() throws Exception {
        AttendantDTO dto = new AttendantDTO();
        dto.setName("test");

        when(attendantService.createAttendant(any(AttendantDTO.class))).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/atendimento/attendant")
                        .contentType("application/json")
                        .content("{\"name\":\"test\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'name':'test'}"));

        verify(attendantService, times(1)).createAttendant(any(AttendantDTO.class));
    }

    @Test
    void testGetServiceRequests() throws Exception {
        List<ServiceRequestDTO> serviceRequestList = new ArrayList<>();
        ServiceRequestDTO serviceRequest = new ServiceRequestDTO();
        serviceRequest.setDescription("teste teste teste ...");
        serviceRequestList.add(serviceRequest);

        when(serviceRequestService.getAllServiceRequest()).thenReturn(serviceRequestList);

        mockMvc.perform(MockMvcRequestBuilders.get("/atendimento/serviceRequests"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'description':'teste teste teste ...'}]"));

        verify(serviceRequestService, times(1)).getAllServiceRequest();
    }

    @Test
    void testPostServiceRequest() throws Exception {
        ServiceRequestDTO dto = new ServiceRequestDTO();
        dto.setDescription("teste teste teste ...");

        when(serviceRequestService.createServiceRequest(any(ServiceRequestDTO.class))).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/atendimento/serviceRequest")
                        .contentType("application/json")
                        .content("{\"description\":\"teste teste teste ...\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'description':'teste teste teste ...'}"));

        verify(serviceRequestService, times(1)).createServiceRequest(any(ServiceRequestDTO.class));
    }

    @Test
    void testWork() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/atendimento"))
                .andExpect(status().isOk())
                .andExpect(content().string("It's work!"));
    }
}
