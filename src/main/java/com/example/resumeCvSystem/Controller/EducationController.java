package com.example.resumeCvSystem.Controller;

import com.example.resumeCvSystem.Service.EducationService;
import com.example.resumeCvSystem.Service.UserService;
import com.example.resumeCvSystem.domain.Education;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class EducationController {
    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @PostMapping("/{id}/educations")
    public Education addEducationRecordById(@PathVariable long id, @RequestBody Education education) {
        return this.educationService.addEducationRecordById(id,education);
    }
}
