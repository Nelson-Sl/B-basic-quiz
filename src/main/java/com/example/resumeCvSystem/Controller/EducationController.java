package com.example.resumeCvSystem.Controller;

import com.example.resumeCvSystem.Common.ExceptionMessage;
// GTB: - ↑ package name 里一般不会使用大写字母
import com.example.resumeCvSystem.Entity.EducationEntity;
import com.example.resumeCvSystem.Service.EducationService;
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
    public EducationEntity addEducationRecordByUserId(@PathVariable Long id,
                                                @RequestBody Education education) {
        return this.educationService.addEducationRecordByUserId(id,education);
    }

    @GetMapping("/{id}/educations")
    public List<EducationEntity> findEducationRecordByUserId(@PathVariable long id) {
        // GTB: - 没有处理 user 为 null 的场景？
        return this.educationService.findEducationRecordByUserId(id);
    }
}
