package com.example.resumeCvSystem.Common;

public class ExceptionMessage {
    public static final String USER_AGE_TOO_SHORT_EXCEPTION_MESSAGE =
            "Only users that are greater than 16 years old could be registered.";
    public static final String USER_NAME_INVALID_EXCEPTION_MESSAGE =
            "Username is not valid. It can't be empty or longer than 128 bytes";
    public static final String USER_AVATAR_INVALID_EXCEPTION_MESSAGE =
            "Avatar link is not valid. The link should between 8 and 512 bytes";
    public static final String USER_DESCRIPTION_INVALID_EXCEPTION_MESSAGE =
            "Description is not valid. It can't exceed 1024 bytes";
}
