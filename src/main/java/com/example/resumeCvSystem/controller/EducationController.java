package com.example.resumeCvSystem.controller;

// GTB: - ↑ package name 里一般不会使用大写字母
import com.example.resumeCvSystem.collections.UserCollection;
import com.example.resumeCvSystem.service.EducationService;
import com.example.resumeCvSystem.domain.Education;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public UserCollection addEducationRecordByUserId(@PathVariable String id,
                                                     @RequestBody Education education) {
        return this.educationService.addEducationRecordByUserId(id,education);
    }

    @GetMapping("/{id}/educations")
    public UserCollection findEducationRecordByUserId(@PathVariable String id) {
        return this.educationService.findEducationRecordByUserId(id);
    }
}
