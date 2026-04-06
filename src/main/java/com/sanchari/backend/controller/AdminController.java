package com.sanchari.backend.controller;

import com.sanchari.backend.model.GuideApplication;
import com.sanchari.backend.model.UserReport;
import com.sanchari.backend.repository.GuideApplicationRepository;
import com.sanchari.backend.repository.UserReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private GuideApplicationRepository guideAppRepo;

    @Autowired
    private UserReportRepository userReportRepo;

    @GetMapping("/guides")
    public List<GuideApplication> getAllGuides() {
        return guideAppRepo.findAll();
    }

    @PutMapping("/guides/{id}/status")
    public ResponseEntity<?> updateGuideStatus(@PathVariable Long id, @RequestBody GuideApplication update) {
        Optional<GuideApplication> appOpt = guideAppRepo.findById(id);
        if (appOpt.isPresent()) {
            GuideApplication app = appOpt.get();
            app.setStatus(update.getStatus());
            guideAppRepo.save(app);
            return ResponseEntity.ok(app);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/reports")
    public List<UserReport> getAllReports() {
        return userReportRepo.findAll();
    }

    @PutMapping("/reports/{id}/resolve")
    public ResponseEntity<?> resolveReport(@PathVariable Long id) {
        Optional<UserReport> reportOpt = userReportRepo.findById(id);
        if (reportOpt.isPresent()) {
            UserReport report = reportOpt.get();
            report.setStatus("Resolved");
            userReportRepo.save(report);
            return ResponseEntity.ok(report);
        }
        return ResponseEntity.notFound().build();
    }
}
