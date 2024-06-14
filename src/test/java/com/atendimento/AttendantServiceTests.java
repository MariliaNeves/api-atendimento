package com.atendimento;


import com.atendimento.model.dto.AttendantDTO;
import com.atendimento.model.enums.Subject;
import com.atendimento.model.util.InvalidRequestException;
import com.atendimento.model.util.MockUtil;
import com.atendimento.services.AttendantService;
import com.atendimento.services.AttendantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AttendantServiceTests {

    @Mock
    private MockUtil mockUtil;

    @InjectMocks
    private AttendantService attendantService;
    private AttendantDTO attendantDTO;


    @BeforeEach
    void setUp() {
        attendantDTO = new AttendantDTO();
    }

    @Test
    void testCreateAttendant_withNullName() {

        InvalidRequestException exception = assertThrows(InvalidRequestException.class, () -> {
            attendantService.createAttendant(attendantDTO);
        });
        assertEquals("Name cannot be null", exception.getMessage());
    }

    @Test
    void testCreateAttendant_withNullTeam() {

        attendantDTO.setName("test");
        InvalidRequestException exception = assertThrows(InvalidRequestException.class, () -> {
            attendantService.createAttendant(attendantDTO);
        });
        assertEquals("Team cannot be null", exception.getMessage());
    }


}


