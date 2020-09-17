package com.example.resumeCvSystem.Controller;

import com.example.resumeCvSystem.Common.ExceptionMessage;
import com.example.resumeCvSystem.Service.EducationService;
import com.example.resumeCvSystem.domain.Education;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    public Education addEducationRecordByUserId(@PathVariable Long id,
                                                @RequestBody Education education) {
        return this.educationService.addEducationRecordByUserId(id,education);
    }

    @GetMapping("/{id}/educations")
    public List<Education> findEducationRecordByUserId(@PathVariable Long id) {
        return this.educationService.findEducationRecordByUserId(id);
    }
}
