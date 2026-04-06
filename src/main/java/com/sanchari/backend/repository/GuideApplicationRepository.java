package com.sanchari.backend.repository;

import com.sanchari.backend.model.GuideApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuideApplicationRepository extends JpaRepository<GuideApplication, Long> {
}
