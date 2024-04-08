package com.giantdiplodocus.enigma.service;

import com.giantdiplodocus.enigma.config.RotorConfigurationInitializer;
import com.giantdiplodocus.enigma.model.MessageLog;
import com.giantdiplodocus.enigma.model.Reflector;
import com.giantdiplodocus.enigma.model.Rotor;
import com.giantdiplodocus.enigma.model.RotorType;
import com.giantdiplodocus.enigma.repository.MessageLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnigmaServiceTest {

    @Mock
    private RotorConfigurationInitializer rotorConfig;

    @Mock
    private MessageLogRepository messageLogRepository;

    @InjectMocks
    private EnigmaService enigmaService;


    @BeforeEach
    void setUp() {
        Map<RotorType, Rotor> rotors = new HashMap<>();
        rotors.put(RotorType.LEFT, new Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ", 0,'Q'-'A'));
        rotors.put(RotorType.CENTER, new Rotor("AJDKSIRUXBLHWTMCQGZNPYFVOE", 0,'E'-'A'));
        rotors.put(RotorType.RIGHT, new Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO",0, 'V'-'A'));
        when(rotorConfig.getRotors()).thenReturn(rotors);
        enigmaService = new EnigmaService(rotorConfig, messageLogRepository, new Reflector());
    }

    @Test
    void testProcessMessage() {
        String message = "QMJIDOMZWZJFJR";
        String rotorSettings = "MCK";
        String processedMessage = enigmaService.processMessage(message, rotorSettings);
        assertEquals("ENIGMAREVEALED", processedMessage);
        verify(messageLogRepository, times(1)).save(any(MessageLog.class));
    }

    @Test
    void testEncryptAndDecrypt() {
        String message = "QMJIDOMZWZJFJR";
        String rotorSettings = "MCK";
        String processedMessage = enigmaService.processMessage(message, rotorSettings);
        processedMessage = enigmaService.processMessage(processedMessage,rotorSettings);
        assertEquals(message, processedMessage);
        verify(messageLogRepository, times(2)).save(any(MessageLog.class));
    }
}
