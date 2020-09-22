package com.example.resumeCvSystem.common;

public class ExceptionMessage {
    public static final String USER_AGE_TOO_SHORT_EXCEPTION_MESSAGE =
            "Only users that are greater than 16 years old could be registered.";
    public static final String USER_NAME_INVALID_EXCEPTION_MESSAGE =
            "Username is not valid. It can't be empty or longer than 128 bytes";
    public static final String USER_AVATAR_INVALID_EXCEPTION_MESSAGE =
            "Avatar link is not valid. The link should between 8 and 512 bytes";
    public static final String USER_DESCRIPTION_INVALID_EXCEPTION_MESSAGE =
            "Description is not valid. It can't exceed 1024 bytes";

    public static final String USER_EDUCATION_TITLE_INVALID_EXCEPTION_MESSAGE =
            "User education title is not valid. It can't be empty or longer than 256 bytes";
    public static final String USER_EDUCATION_DESC_INVALID_EXCEPTION_MESSAGE =
            "User education description is not valid. It can't be empty or longer than 4096 bytes";

    public static final String USER_INVALID_ID_EXCEPTION_MESSAGE =
            "User ID can't be null and should be consists of numbers";

    public static final String USER_NOT_FOUND_EXCEPTION_MESSAGE =
            "User is not existed currently, please register";

    public static final String EDUCATION_NOT_FOUND_EXCEPTION_MESSAGE =
            "User currently has no education information";
}
