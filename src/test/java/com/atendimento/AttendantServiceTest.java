package com.atendimento;
import com.atendimento.model.dto.AttendantDTO;
import com.atendimento.model.enums.Team;
import com.atendimento.model.util.InvalidRequestException;
import com.atendimento.model.util.QueueManager;
import com.atendimento.services.AttendantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AttendantServiceTest {

    @InjectMocks
    private AttendantService attendantService;

    @Mock
    private QueueManager queueManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAttendants() {
        AttendantDTO attendant1 = new AttendantDTO("Luiza", Team.CARTOES);
        AttendantDTO attendant2 = new AttendantDTO("Maria", Team.EMPRESTIMOS);

        when(queueManager.getAttendantQueue()).thenReturn(Arrays.asList(attendant1, attendant2));

        List<AttendantDTO> result = attendantService.getAllAttendants();

        assertEquals(2, result.size());
        assertTrue(result.contains(attendant1));
        assertTrue(result.contains(attendant2));
    }

    @Test
    void testGetAllAttendantByTeam() {
        AttendantDTO attendant1 = new AttendantDTO("Luiza", Team.CARTOES);
        AttendantDTO attendant2 = new AttendantDTO("Maria", Team.EMPRESTIMOS);

        when(queueManager.getAttendantQueue()).thenReturn(Arrays.asList(attendant1, attendant2));

        List<AttendantDTO> result = attendantService.getAllAttendantByTeam(Team.CARTOES);

        assertEquals(1, result.size());
        assertTrue(result.contains(attendant1));
        assertFalse(result.contains(attendant2));
    }

    @Test
    void testCreateAttendant() {
        AttendantDTO attendant = new AttendantDTO("Luiza", Team.CARTOES);

        when(queueManager.addAttendantQueue(attendant)).thenReturn(attendant);

        AttendantDTO result = attendantService.createAttendant(attendant);

        assertNotNull(result);
        assertEquals("Luiza", result.getName());
        assertEquals(Team.CARTOES, result.getTeam());
    }

    @Test
    void testCreateAttendantInvalidRequestException() {
        AttendantDTO invalidAttendant = new AttendantDTO(null, Team.CARTOES);

        InvalidRequestException exception = assertThrows(InvalidRequestException.class, () -> {
            attendantService.createAttendant(invalidAttendant);
        });

        assertEquals("Name cannot be null", exception.getMessage());
    }
}
