package org.shaheen.nazarov.utility.crypto;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

public class Coder {

    public String generateSecretKey(PrivateKey privateKey, PublicKey publicKey)
            throws NoSuchAlgorithmException, InvalidKeyException {
        // Alice creates and initializes her DH KeyAgreement object
        KeyAgreement agreement = KeyAgreement.getInstance(Constants.Crypt.AGREEMENT_ALGORITHM);
        agreement.init(privateKey);
        agreement.doPhase(publicKey, true);
        byte[] sharedSecret = agreement.generateSecret();
        return Base64.encodeString(sharedSecret);
    }


    public PrivateKey loadPrivateKey(String key64) throws GeneralSecurityException {
        byte[] clear = Base64.decode(key64);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
        KeyFactory fact = KeyFactory.getInstance(Constants.Crypt.ALGORITHM);
        PrivateKey priv = fact.generatePrivate(keySpec);
        Arrays.fill(clear, (byte) 0);
        return priv;
    }

    public PublicKey loadPublicKey(String stored) throws GeneralSecurityException {
        byte[] data = Base64.decode(stored);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
        KeyFactory fact = KeyFactory.getInstance(Constants.Crypt.ALGORITHM);
        return fact.generatePublic(spec);
    }


    public String encrypt(final String data, final String key)
            throws NoSuchPaddingException, UnsupportedEncodingException,
            IllegalBlockSizeException, BadPaddingException,
            NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.decode(key), Constants.Crypt.ALGORITHM_SECRET);
        Cipher cipher = Cipher.getInstance(Constants.Crypt.ALGORITHM_ENCRYPTION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] cleartext = data.getBytes("UTF-8");
        byte[] ciphertext = cipher.doFinal(cleartext);
        return Base64.encodeString(ciphertext);
    }

    public String decrypt(final String data, final String key)
            throws NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException,
            NoSuchAlgorithmException, InvalidKeyException {

        SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.decode(key),
                Constants.Crypt.ALGORITHM_SECRET);
        Cipher cipher = Cipher.getInstance(Constants.Crypt.ALGORITHM_ENCRYPTION);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] recovered = cipher.doFinal(Base64.decode(data));
        return new String(recovered);
    }

    public String shortenSecretKey(final String sharedKey) {
        try {
            final byte[] shortenedKey = new byte[8];
            System.arraycopy(Base64.decode(sharedKey), 0, shortenedKey, 0, shortenedKey.length);
            return Base64.encodeString(shortenedKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
