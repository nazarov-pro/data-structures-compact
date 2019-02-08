package org.shaheen.nazarov.utility.crypto;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public interface SymmetricKeyGeneration {
    SecretKey generate(final String password) throws NoSuchAlgorithmException;
    SecretKey generate(final String password, final String algorithm) throws NoSuchAlgorithmException;
}
