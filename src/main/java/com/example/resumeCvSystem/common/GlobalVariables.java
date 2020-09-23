package com.example.resumeCvSystem.common;

import java.text.SimpleDateFormat;
public class GlobalVariables {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sssX':00'");

    public static final int MINIMUM_NAME_BYTES = 1;
    public static final int MAXIMUM_NAME_BYTES = 64;
    public static final int MINIMUM_AVATAR_BYTES = 4;
    public static final int MAXIMUM_AVATAR_BYTES = 256;
    public static final int MAXIMUM_DESC_BYTES = 512;

    public static final int MINIMUM_EDUCATION_TITLE_BYTES = 1;
    public static final int MAXIMUM_EDUCATION_TITLE_BYTES = 128;
    public static final int MINIMUM_EDUCATION_DESC_BYTES = 1;
    public static final int MAXIMUM_EDUCATION_DESC_BYTES = 2048;

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
