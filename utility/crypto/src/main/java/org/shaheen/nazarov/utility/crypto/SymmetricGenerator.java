package org.shaheen.nazarov.utility.crypto;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class SymmetricGenerator implements SymmetricKeyGeneration {

    @Override
    public SecretKey generate(String password) throws NoSuchAlgorithmException {
        return null;
    }

    @Override
    public SecretKey generate(String password, String algorithm) throws NoSuchAlgorithmException {
        return null;
    }
}
