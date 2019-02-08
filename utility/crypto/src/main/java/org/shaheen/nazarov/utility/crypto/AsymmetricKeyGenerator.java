package org.shaheen.nazarov.utility.crypto;

import java.security.*;

public class AsymmetricKeyGenerator implements AsymmetricKeyGeneration {

    @Override
    public KeyPair generate() throws NoSuchAlgorithmException {
        return generate(Constants.Crypt.DEFAULT_KEY_SIZE, Constants.Crypt.EC_ALGORITHM);
    }

    @Override
    public KeyPair generate(int keySize, String algorithm) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.generateKeyPair();
    }
}
