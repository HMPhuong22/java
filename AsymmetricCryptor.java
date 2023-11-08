/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asymmetricapp;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author DzungLM
 */
public class AsymmetricCryptor {
    private Cipher cipher;
    
    
    //load khóa bí mật từ file
    public PrivateKey getPrivateKey(String filename) 
            throws IOException, 
            NoSuchAlgorithmException, 
            InvalidKeySpecException
    {
        byte[] keybytes = Files.readAllBytes(
            new File(filename).toPath());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keybytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }
    public PublicKey getPublicKey(String filename) 
            throws IOException, NoSuchAlgorithmException, InvalidKeySpecException
    {
        byte[] keybytes = Files.readAllBytes(
            new File(filename).toPath());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keybytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }
    //mã hóa
    public String encryptText(String msg, PrivateKey key) 
            throws NoSuchAlgorithmException, 
            NoSuchPaddingException,
            InvalidKeyException,
            UnsupportedEncodingException,
            IllegalBlockSizeException,
            BadPaddingException
    {
        cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encodeToString(
                cipher.doFinal(msg.getBytes("UTF-8")));
    }
    //giải mã
    public String decryptText(String msg, PublicKey key) 
            throws NoSuchAlgorithmException, 
            NoSuchPaddingException, 
            InvalidKeyException, 
            IllegalBlockSizeException, 
            BadPaddingException, 
            UnsupportedEncodingException
    {
        cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(
                Base64.getDecoder().decode(msg)),"UTF-8");
    }
}
