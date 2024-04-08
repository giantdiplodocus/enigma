package com.giantdiplodocus.enigma.controller;

import com.giantdiplodocus.enigma.model.EnigmaRequest;
import com.giantdiplodocus.enigma.service.EnigmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class EnigmaController {
    private final EnigmaService enigmaService;

    @Autowired
    public EnigmaController(EnigmaService enigmaService) {
        this.enigmaService = enigmaService;
    }

    @PostMapping("/process")
    public ResponseEntity<String> processMessage(@Valid @RequestBody EnigmaRequest request) {
        try {
            String processedMessage = enigmaService.processMessage(request.message().toUpperCase(), request.rotorSettings().toUpperCase());
            return ResponseEntity.ok(processedMessage);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error");
        }
    }

}
