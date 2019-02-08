package org.shaheen.nazarov.utility.crypto;

import java.nio.charset.StandardCharsets;

public class Base64 {

    public static String encodeString(byte[] bytes){
        return java.util.Base64.getEncoder().encodeToString(bytes);
    }

    public static byte[] encode(byte[] bytes){
        return java.util.Base64.getEncoder().encode(bytes);
    }

    public static String encodeString(String text){
        return encodeString(text.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] decode(byte[] bytes){
        return java.util.Base64.getDecoder().decode(bytes);
    }

    public static byte[] decode(String text){
        return decode(text.getBytes(StandardCharsets.UTF_8));
    }

    public static String decodeString(byte[] bytes){
        return new String(java.util.Base64.getDecoder().decode(bytes));
    }
}
