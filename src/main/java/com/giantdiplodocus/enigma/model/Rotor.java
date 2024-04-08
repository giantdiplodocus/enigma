package com.giantdiplodocus.enigma.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class Rotor {
    private int position;
    private final String configuration;
    private final char[] configArray;
    private final HashMap<String, Integer> reverseMapping;
    private final int notch;

    public Rotor(String configuration, int position, int notch) {
        this.configuration = configuration;
        this.notch = notch;
        this.configArray = configuration.toCharArray();
        this.position = position % configuration.length();
        this.reverseMapping = new HashMap<>();
        for (int i = 0; i < configArray.length; i++) {
            reverseMapping.put(String.valueOf(configArray[i]), i);
        }
    }

    public void rotate() {
        this.position = (this.position + 1) % configuration.length();
    }

    public boolean atNotch() {
        return position == notch;
    }

    public int forwardWiring(int inputPin) {
        int offset = Math.floorMod(inputPin + position, 26);
        return Math.floorMod((configArray[offset] - 'A') - position, 26);
    }

    public int backwardWiring(int inputPin) {
        int offset = Math.floorMod(inputPin + position, 26);
        return Math.floorMod(reverseMapping.get(Character.toString((char) ('A' + offset))) - position, 26);
    }

}
