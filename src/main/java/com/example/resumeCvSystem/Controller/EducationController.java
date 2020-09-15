package com.example.resumeCvSystem.Controller;

import com.example.resumeCvSystem.Service.EducationService;
import com.example.resumeCvSystem.Service.UserService;
import com.example.resumeCvSystem.domain.Education;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class EducationController {
    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @PostMapping("/{id}/educations")
    public Education addEducationRecordByUserId(@PathVariable long id, @RequestBody Education education) {
        return this.educationService.addEducationRecordByUserId(id,education);
    }

    @GetMapping("/{id}/educations")
    public List<Education> findEducationRecordByUserId(@PathVariable long id) {
        return this.educationService.findEducationRecordByUserId(id);
    }
}
