/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digital.signature;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * @author Way4ward Adefuwa
 */
public class VerSig {
        boolean verifies = false;
     public Boolean verify(File file, File key, File signature) {

        /* Verify a DSA signature */

        if (file == null) {
            System.out.println("Usage: VerSig " +
                "publickeyfile signaturefile " + "datafile");
                verifies = false;
        }
        else try {

        // the rest of the code goes here
        
        FileInputStream keyfis = new FileInputStream(key);
        byte[] encKey = new byte[keyfis.available()];  
        keyfis.read(encKey);

        keyfis.close();

        
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
        KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
        PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);
        
        
        FileInputStream sigfis = new FileInputStream(signature);
        byte[] sigToVerify = new byte[sigfis.available()]; 
        sigfis.read(sigToVerify);
        sigfis.close();
        
        Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
        sig.initVerify(pubKey);
        
        FileInputStream datafis = new FileInputStream(file);
        BufferedInputStream bufin = new BufferedInputStream(datafis);

        byte[] buffer = new byte[1024];
        int len;
        while (bufin.available() != 0) {
            len = bufin.read(buffer);
            sig.update(buffer, 0, len);
        };

        bufin.close();
        
        verifies = sig.verify(sigToVerify);

        System.out.println("signature verifies: " + verifies);
        
        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
        }
        return verifies;
    }
}
