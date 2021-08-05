/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digital.signature;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;

/**
 *
 * @author Way4ward Adefuwa
 */
public class GenSig {
    
        boolean verifies = false;
    /* Generate a DSA signature */

       public Boolean GenSig(File file) {

        /* Generate a DSA signature */

        if (file == null) {
            System.out.println("Usage: GenSig nameOfFileToSign");
            verifies = false;
        }
        else try {

        // the rest of the code goes here
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);
        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();
        Signature dsa = Signature.getInstance("SHA1withDSA", "SUN"); 
        dsa.initSign(priv);
        
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bufin = new BufferedInputStream(fis);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = bufin.read(buffer)) >= 0) {
            dsa.update(buffer, 0, len);
        };
        bufin.close();

        byte[] realSig = dsa.sign();
        
        /* save the signature in a file */
        FileOutputStream sigfos = new FileOutputStream("sig");
        sigfos.write(realSig);
        sigfos.close();

        
        /* save the public key in a file */
        byte[] key = pub.getEncoded();
        FileOutputStream keyfos = new FileOutputStream("suepk");
        keyfos.write(key);
        keyfos.close();
        verifies = true;
        
        
        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
        }
        return verifies;
    }
}
