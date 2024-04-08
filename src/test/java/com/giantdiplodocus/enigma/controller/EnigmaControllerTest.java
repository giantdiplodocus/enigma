package com.giantdiplodocus.enigma.controller;

import com.giantdiplodocus.enigma.model.EnigmaRequest;
import com.giantdiplodocus.enigma.service.EnigmaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnigmaControllerTest {

    @Mock
    private EnigmaService enigmaService;

    @InjectMocks
    private EnigmaController enigmaController;

    @Test
    void testProcessMessageSuccess() {
        EnigmaRequest request = new EnigmaRequest("test", "MCK");
        String processedMessage = "Processed message";
        when(enigmaService.processMessage(request.message().toUpperCase(), request.rotorSettings().toUpperCase())).thenReturn(processedMessage);
        ResponseEntity<String> responseEntity = enigmaController.processMessage(request);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(processedMessage, responseEntity.getBody());
    }

    @Test
    void testProcessMessageFailure() {
        EnigmaRequest request = new EnigmaRequest("Test message","");
        when(enigmaService.processMessage(request.message().toUpperCase(), request.rotorSettings().toUpperCase())).thenThrow(RuntimeException.class);
        ResponseEntity<String> responseEntity = enigmaController.processMessage(request);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}
