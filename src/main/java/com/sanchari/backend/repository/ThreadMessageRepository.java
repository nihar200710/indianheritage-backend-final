package com.sanchari.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sanchari.backend.model.ThreadMessage;

public interface ThreadMessageRepository extends JpaRepository<ThreadMessage, Long> {
}
