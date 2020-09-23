package com.example.resumeCvSystem.domain;

import com.example.resumeCvSystem.common.GlobalVariables;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Size(min = GlobalVariables.MINIMUM_NAME_BYTES,
            max = GlobalVariables.MAXIMUM_NAME_BYTES,
        message = GlobalVariables.USER_NAME_INVALID_EXCEPTION_MESSAGE)
    private String name;
    @Min(value=17, message = GlobalVariables.USER_AGE_TOO_SHORT_EXCEPTION_MESSAGE)
    private Long age;
    @Size(min = GlobalVariables.MINIMUM_AVATAR_BYTES,
            max = GlobalVariables.MAXIMUM_AVATAR_BYTES,
            message = GlobalVariables.USER_AVATAR_INVALID_EXCEPTION_MESSAGE)
    private String avatar;
    @Size(max = GlobalVariables.MAXIMUM_DESC_BYTES,
            message = GlobalVariables.USER_DESCRIPTION_INVALID_EXCEPTION_MESSAGE)
    private String description;
}
