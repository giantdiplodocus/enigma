package com.giantdiplodocus.enigma.repository;

import com.giantdiplodocus.enigma.model.MessageLog;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MessageLogRepository extends JpaRepository<MessageLog,Long> {
}
