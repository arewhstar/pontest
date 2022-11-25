package hu.ponte.hr.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class DigitalSign {
    private byte[] privateKey;

    //The constructor read in the private key from the file as array of bytes
    public DigitalSign(String path) throws IOException {
        this.privateKey = Files.readAllBytes(Paths.get(path));
    }
    //Creating digital signature based on the given image (bytes)
    public String sign(byte[] image) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        //Creating the PKCS8 key from the bytes
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        Signature privateSignature = Signature.getInstance("SHA256withRSA");

        privateSignature.initSign(keyFactory.generatePrivate(keySpec));
        privateSignature.update(image);
        byte[] s = privateSignature.sign();
        return Base64.getEncoder().encodeToString(s);

    }
}
