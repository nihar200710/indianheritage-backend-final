package com.sanchari.backend.repository;

import com.sanchari.backend.model.GuideEarning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuideEarningRepository extends JpaRepository<GuideEarning, Long> {
}
