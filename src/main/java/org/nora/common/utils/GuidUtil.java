package org.nora.common.utils;

import java.util.UUID;

public class GuidUtil {

    public static String GenerateGuid()
    {
        return UUID.randomUUID().toString();
    }
}
