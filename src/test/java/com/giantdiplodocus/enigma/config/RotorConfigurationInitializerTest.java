package com.giantdiplodocus.enigma.config;

import com.giantdiplodocus.enigma.model.Rotor;
import com.giantdiplodocus.enigma.model.RotorConfiguration;
import com.giantdiplodocus.enigma.model.RotorType;
import com.giantdiplodocus.enigma.repository.RotorConfigurationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RotorConfigurationInitializerTest {

    @Mock
    private RotorConfigurationRepository rotorConfigurationRepository;

    @InjectMocks
    private RotorConfigurationInitializer rotorConfigurationInitializer;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(rotorConfigurationInitializer, "leftRotor", "I");
        ReflectionTestUtils.setField(rotorConfigurationInitializer, "centerRotor", "II");
        ReflectionTestUtils.setField(rotorConfigurationInitializer, "rightRotor", "III");

        RotorConfiguration leftRotorConfig = new RotorConfiguration();
        leftRotorConfig.setName("I");
        leftRotorConfig.setConfiguration("EKMFLGDQVZNTOWYHXUSPAIBRC");
        leftRotorConfig.setNotch('Y');

        RotorConfiguration rightRotorConfig = new RotorConfiguration();
        rightRotorConfig.setName("III");
        rightRotorConfig.setConfiguration("BDFHJLCPRTXVZNYEIWGAKMUSQO");
        rightRotorConfig.setNotch('Y');

        RotorConfiguration centerRotorConfig = new RotorConfiguration();
        centerRotorConfig.setName("II");
        centerRotorConfig.setConfiguration("BDFHJLCPRTXVZNYEIWGAKMUSQO");
        centerRotorConfig.setNotch('Y');

        // Mock behavior of RotorConfigurationRepository
        when(rotorConfigurationRepository.findByName("I")).thenReturn(leftRotorConfig);
        when(rotorConfigurationRepository.findByName("II")).thenReturn(centerRotorConfig);
        when(rotorConfigurationRepository.findByName("III")).thenReturn(rightRotorConfig);

    }
    @Test
    void testRotorInitialization()  {

        rotorConfigurationInitializer.run();

        Map<RotorType, Rotor> rotors = rotorConfigurationInitializer.getRotors();
        assertEquals(3, rotors.size());
        assertEquals("EKMFLGDQVZNTOWYHXUSPAIBRC", rotors.get(RotorType.LEFT).getConfiguration());
    }

}
