package com.example.resumeCvSystem.domain;

import com.example.resumeCvSystem.common.GlobalVariables;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    private Long year;
    @Size(min = GlobalVariables.MINIMUM_EDUCATION_TITLE_BYTES,
        max = GlobalVariables.MAXIMUM_EDUCATION_TITLE_BYTES,
        message = GlobalVariables.USER_EDUCATION_TITLE_INVALID_EXCEPTION_MESSAGE)
    private String title;
    @Size(min = GlobalVariables.MINIMUM_EDUCATION_DESC_BYTES,
            max = GlobalVariables.MAXIMUM_EDUCATION_DESC_BYTES,
            message = GlobalVariables.USER_EDUCATION_DESC_INVALID_EXCEPTION_MESSAGE)
    private String description;
}
