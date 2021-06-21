package br.com.doeme.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UUIDUtil {

    public static final String REPLACE_SOURCE = "-";
    public static final String REPLACE_TARGET = "";

    public static String shortUUID() {
        return getCode(8);
    }

    public static String userCode() {
        return getCode(7);
    }

    public static String validateCode() {
        return getCode(7);
    }

    public static String UUID() {
        return UUID.randomUUID().toString();
    }

    private static String getCode(int i) {
        return UUID().replace(REPLACE_SOURCE, REPLACE_TARGET).substring(1, i).toUpperCase();
    }
}
