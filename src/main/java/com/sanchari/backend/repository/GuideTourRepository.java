package com.sanchari.backend.repository;

import com.sanchari.backend.model.GuideTour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuideTourRepository extends JpaRepository<GuideTour, Long> {
}
