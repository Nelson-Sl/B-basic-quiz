package com.example.resumeCvSystem.Service;

import com.example.resumeCvSystem.Repository.EducationRepository;
import com.example.resumeCvSystem.domain.Education;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {
    private final EducationRepository educationRepository;

    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public Education addEducationRecordByUserId(Long id, Education education) {
        return this.educationRepository.addEducationRecordByUserId(id, education);
    }

    public List<Education> findEducationRecordByUserId(Long id) {
        return this.educationRepository.findEducationRecordByUserId(id);
    }
}
