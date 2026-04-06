package com.sanchari.backend.repository;

import com.sanchari.backend.model.GuideQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuideQueryRepository extends JpaRepository<GuideQuery, Long> {
}
