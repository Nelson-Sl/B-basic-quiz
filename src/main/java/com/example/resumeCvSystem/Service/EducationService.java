package com.example.resumeCvSystem.Service;

import com.example.resumeCvSystem.Repository.EducationRepository;
import com.example.resumeCvSystem.domain.Education;
import org.springframework.stereotype.Service;

@Service
public class EducationService {
    private final EducationRepository educationRepository;

    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public Education addEducationRecordById(long id, Education education) {
        return this.educationRepository.addEducationRecordById(id, education);
    }
}
