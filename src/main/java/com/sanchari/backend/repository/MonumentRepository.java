package com.sanchari.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sanchari.backend.model.Monument;
import java.util.List;

public interface MonumentRepository extends JpaRepository<Monument, Long> {
    List<Monument> findAllByOrderByIdDesc();
}
