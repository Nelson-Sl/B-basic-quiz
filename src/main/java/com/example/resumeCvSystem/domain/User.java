package com.example.resumeCvSystem.domain;

import com.example.resumeCvSystem.common.ExceptionMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    @Min(value=17, message = ExceptionMessage.USER_AGE_TOO_SHORT_EXCEPTION_MESSAGE)
    private Long age;
    private String avatar;
    private String description;
}
