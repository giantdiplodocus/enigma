package com.giantdiplodocus.enigma.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Reflector {

    private final HashMap<Character, Integer> reflectorMapping;

    public Reflector() {
        reflectorMapping = new HashMap<>();
        String reflectorSettings = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
        for (int i = 0; i < reflectorSettings.length(); i++) {
            reflectorMapping.put(reflectorSettings.charAt(i), i);
        }
    }

    public int forwardWiring(int inputPin) {
        return reflectorMapping.get((char) (inputPin + 'A'));
    }
}
