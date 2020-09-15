package com.example.resumeCvSystem.Common;

import java.text.SimpleDateFormat;

public class GlobalVariables {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sssX':00'");

    public static final long MINIMUM_NAME_BYTES = 1;
    public static final long MAXIMUM_NAME_BYTES = 128;
    public static final long MINIMUM_AVATAR_BYTES = 8;
    public static final long MAXIMUM_AVATAR_BYTES = 512;
    public static final long MAXIMUM_DESC_BYTES = 1024;

}
