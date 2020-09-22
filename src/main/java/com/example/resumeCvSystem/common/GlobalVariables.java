package com.example.resumeCvSystem.common;

import java.text.SimpleDateFormat;
// GTB: + 集中管理 constants
public class GlobalVariables {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sssX':00'");

    public static final long MINIMUM_NAME_BYTES = 1;
    public static final long MAXIMUM_NAME_BYTES = 128;
    public static final long MINIMUM_AVATAR_BYTES = 8;
    public static final long MAXIMUM_AVATAR_BYTES = 512;
    public static final long MAXIMUM_DESC_BYTES = 1024;

    public static final long MINIMUM_EDUCATION_TITLE_BYTES = 1;
    public static final long MAXIMUM_EDUCATION_TITLE_BYTES = 256;
    public static final long MINIMUM_EDUCATION_DESC_BYTES = 1;
    public static final long MAXIMUM_EDUCATION_DESC_BYTES = 4096;
}
