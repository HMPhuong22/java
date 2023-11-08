/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asymmetricapp;

import java.io.IOException;

/**
 *
 * @author DzungLM
 */
public class AsymmetricApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
            throws Exception {
        // TODO code application logic here
        AsymmetricCryptor AC = new AsymmetricCryptor();
        String msg = "Nguyễn Văn An";
        String encrypted_msg = AC.encryptText(msg, 
                AC.getPrivateKey("D:/Keys/PrivateKey"));
        System.out.println("Plain text: " + msg);
        System.out.println("Encrypted text: " + encrypted_msg);
        String decrypted_msg = AC.decryptText(encrypted_msg, 
                AC.getPublicKey("D:/Keys/PublicKey"));
        System.out.println("Decrypted text: " + decrypted_msg);
        
    }
    
}
