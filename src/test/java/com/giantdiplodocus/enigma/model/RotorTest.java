package com.giantdiplodocus.enigma.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RotorTest {

    @Test
    void testRotorConfiguration() {
        String configuration = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
        int position = 0; // Starting position
        int notch = 4; // Example notch position

        Rotor rotor = new Rotor(configuration, position, notch);

        assertEquals(configuration, rotor.getConfiguration());
        assertEquals(position, rotor.getPosition());
        assertEquals(notch, rotor.getNotch());
    }

    @Test
    void testRotate() {
        String configuration = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
        int position = 0; // Starting position
        int notch = 4; // Example notch position

        Rotor rotor = new Rotor(configuration, position, notch);

        rotor.rotate();
        assertEquals(1, rotor.getPosition());

        // Rotate multiple times
        rotor.rotate();
        rotor.rotate();
        assertEquals(3, rotor.getPosition());
    }

    @Test
    void testAtNotch() {
        String configuration = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
        int position = 0; // Starting position
        int notch = 4; // Example notch position

        Rotor rotor = new Rotor(configuration, position, notch);

        assertFalse(rotor.atNotch());

        // Rotate to the notch position
        for (int i = 0; i < notch; i++) {
            rotor.rotate();
        }
        assertTrue(rotor.atNotch());
    }

    @Test
    void testForwardWiring() {
        String configuration = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
        int position = 0; // Starting position
        int notch = 4; // Example notch position

        Rotor rotor = new Rotor(configuration, position, notch);

        // Test forward wiring for each character
        for (int i = 0; i < configuration.length(); i++) {
            assertEquals(configuration.charAt(i)-'A', rotor.forwardWiring(i));
        }
    }

    @Test
    void testBackwardWiring() {
        String configuration = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
        int position = 0; // Starting position
        int notch = 4; // Example notch position

        Rotor rotor = new Rotor(configuration, position, notch);

        // Test backward wiring for each character
        for (int i = 0; i < configuration.length(); i++) {
            assertEquals(configuration.indexOf((char) i+65), rotor.backwardWiring(i));
        }
    }
}
