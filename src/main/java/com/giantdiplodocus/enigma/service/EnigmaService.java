package com.giantdiplodocus.enigma.service;

import com.giantdiplodocus.enigma.config.RotorConfigurationInitializer;
import com.giantdiplodocus.enigma.model.MessageLog;
import com.giantdiplodocus.enigma.model.Reflector;
import com.giantdiplodocus.enigma.model.Rotor;
import com.giantdiplodocus.enigma.model.RotorType;
import com.giantdiplodocus.enigma.repository.MessageLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EnigmaService {

    private final Map<RotorType, Rotor> rotors;
    private final Reflector reflector;
    private final MessageLogRepository messageLogRepository;

    @Autowired
    public EnigmaService(RotorConfigurationInitializer rotorConfig, MessageLogRepository messageLogRepository, Reflector reflector) {
        this.messageLogRepository = messageLogRepository;
        this.rotors = rotorConfig.getRotors();
        this.reflector = reflector;
    }

    public String processMessage(String message, String rotorSettings) {
        String encryptedMessage = encryptMessage(message, rotorSettings);
        MessageLog mLogs = new MessageLog();
        mLogs.setOriginalMessage(message);
        mLogs.setProcessedMessage(encryptedMessage);
        mLogs.setRotorPosition(rotorSettings);
        messageLogRepository.save(mLogs);
        return encryptedMessage;
    }

    private String encryptMessage(String message, String rotorSettings) {
        Rotor leftRotor = rotors.get(RotorType.LEFT);
        Rotor centerRotor = rotors.get(RotorType.CENTER);
        Rotor rightRotor = rotors.get(RotorType.RIGHT);
        leftRotor.setPosition(rotorSettings.charAt(0) - 'A');
        centerRotor.setPosition(rotorSettings.charAt(1) - 'A');
        rightRotor.setPosition(rotorSettings.charAt(2) - 'A');

        StringBuilder encryptedMessage = new StringBuilder();

        for (char c : message.toCharArray()) {
            if (c < 'A' || c > 'Z') {
                encryptedMessage.append(c);
                continue;
            }
            if(rightRotor.atNotch()) {
                if (centerRotor.atNotch()) {
                    leftRotor.rotate();
                }
                centerRotor.rotate();
            }
            rightRotor.rotate();
            int outputPin = rightRotor.forwardWiring(c - 'A');
            outputPin = centerRotor.forwardWiring(outputPin);
            outputPin = leftRotor.forwardWiring(outputPin);
            outputPin = reflector.forwardWiring(outputPin);
            outputPin = leftRotor.backwardWiring(outputPin);
            outputPin = centerRotor.backwardWiring(outputPin);
            outputPin = rightRotor.backwardWiring(outputPin);
            char encryptedChar = (char) (outputPin + 'A');
            encryptedMessage.append(encryptedChar);
        }
        return encryptedMessage.toString();
    }
}
