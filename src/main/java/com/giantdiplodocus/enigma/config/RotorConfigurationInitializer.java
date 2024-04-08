package com.giantdiplodocus.enigma.config;

import com.giantdiplodocus.enigma.model.Rotor;
import com.giantdiplodocus.enigma.model.RotorConfiguration;
import com.giantdiplodocus.enigma.model.RotorType;
import com.giantdiplodocus.enigma.repository.RotorConfigurationRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RotorConfigurationInitializer implements CommandLineRunner {
    private final RotorConfigurationRepository rotorConfigurationRepository;

    @Getter
    private final Map<RotorType, Rotor> rotors = new HashMap<>();

    @Value("${rotor.left.type:I}")
    private String leftRotor;

    @Value("${rotor.center.type:II}")
    private String centerRotor;

    @Value("${rotor.right.type:III}")
    private String rightRotor;

    @Autowired
    public RotorConfigurationInitializer(RotorConfigurationRepository rotorConfigurationRepository) {
        this.rotorConfigurationRepository = rotorConfigurationRepository;
    }

    @Override
    public void run(String... args) {
        initializeRotor(RotorType.LEFT, leftRotor);
        initializeRotor(RotorType.RIGHT, rightRotor);
        initializeRotor(RotorType.CENTER, centerRotor);
    }

    private void initializeRotor(RotorType type, String rotorName) {
        RotorConfiguration rc = rotorConfigurationRepository.findByName(rotorName);
        Rotor rotor = new Rotor(rc.getConfiguration(), 0, rc.getNotch() - 'A');
        rotors.put(type, rotor);
    }

}
