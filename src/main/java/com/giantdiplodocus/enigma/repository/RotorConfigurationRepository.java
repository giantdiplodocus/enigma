package com.giantdiplodocus.enigma.repository;

import com.giantdiplodocus.enigma.model.RotorConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RotorConfigurationRepository extends JpaRepository<RotorConfiguration, Long> {
    RotorConfiguration findByName(String name);
}
