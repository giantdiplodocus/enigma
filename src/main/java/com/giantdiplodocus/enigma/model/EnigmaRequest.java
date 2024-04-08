package com.giantdiplodocus.enigma.model;

import jakarta.validation.constraints.NotNull;

public record EnigmaRequest(@NotNull String message, @NotNull String rotorSettings) {
    public EnigmaRequest(String message, String rotorSettings) {
        this.message = message;
        this.rotorSettings = rotorSettings;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public String rotorSettings() {
        return rotorSettings;
    }

}
