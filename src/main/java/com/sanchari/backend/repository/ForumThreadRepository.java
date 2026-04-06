package com.sanchari.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sanchari.backend.model.ForumThread;
import java.util.List;

public interface ForumThreadRepository extends JpaRepository<ForumThread, Long> {
    List<ForumThread> findAllByOrderByIdDesc();
}
