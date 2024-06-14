package com.atendimento.controller;

import com.atendimento.model.dto.AttendantDTO;
import com.atendimento.model.dto.ServiceRequestDTO;
import com.atendimento.services.AttendantService;
import com.atendimento.services.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by  Marília
 * Date: 13/06/2024
 */


@RestController
@RequestMapping("/atendimento")
@CrossOrigin(origins = {"http://localhost:8080"},
methods = {RequestMethod.GET})
public class AtendimentoController {

    @Autowired
    private AttendantService attendantService;
    @Autowired
    private ServiceRequestService serviceRequestService;

    @GetMapping(value = "/attendants")
    public ResponseEntity getAttendants(){
        List<AttendantDTO> attendants = attendantService.getAllAttendants();
        return ResponseEntity.status(HttpStatus.OK).body(attendants);
    }

    @PostMapping(value = "/attendant")
    public ResponseEntity postAttendants(@RequestBody AttendantDTO attendantDTO){
        AttendantDTO attendant = attendantService.createAttendant(attendantDTO);
        return ResponseEntity.status(HttpStatus.OK).body(attendant);
    }

    @GetMapping(value = "/serviceRequests")
    public ResponseEntity getServiceRequests(){
        List<ServiceRequestDTO> dto = List.of();// = atendimentoService.getAllAttendants();
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping(value = "/serviceRequest")
    public ResponseEntity postAttendants(@RequestBody ServiceRequestDTO dto){
        ServiceRequestDTO serviceRequest = serviceRequestService.createServiceRequest(dto);
        return ResponseEntity.status(HttpStatus.OK).body(serviceRequest);
    }

    @GetMapping(value = "")
    public ResponseEntity work(){
        return ResponseEntity.status(HttpStatus.OK).body("It's work!");
    }
}
