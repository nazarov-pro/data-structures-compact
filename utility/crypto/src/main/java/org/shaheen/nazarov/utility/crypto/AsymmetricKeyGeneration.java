package org.shaheen.nazarov.utility.crypto;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

public interface AsymmetricKeyGeneration {
    KeyPair generate() throws NoSuchAlgorithmException;
    KeyPair generate(final int keySize, final String algorithm) throws NoSuchAlgorithmException;
}
