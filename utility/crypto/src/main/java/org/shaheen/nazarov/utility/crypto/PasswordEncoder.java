package org.shaheen.nazarov.utility.crypto;

public interface PasswordEncoder {
    String hash(String password);
    String hash(String password, String salt);
    boolean check(String password, String hash);
    boolean check(String password, String hash, String salt);
}
