package com.ase.authenticationservice.security.jwt;

import com.ase.authenticationservice.security.SecurityConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;

@Component
public class KeyStoreManager {
    private KeyStore keyStore;
    private String keyAlias;
    private char[] password = SecurityConstants.KEY_PASSWORD.toCharArray();

    public KeyStoreManager() throws KeyStoreException, IOException {
        loadKeyStore();
    }

    public void loadKeyStore() throws KeyStoreException, IOException {
        keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream fis = null;
        try {
            // Get the path to the keystore file in the resources folder
            File keystoreFile = ResourceUtils.getFile("classpath:" + SecurityConstants.KEY_FILE);
            fis = new FileInputStream(keystoreFile);
            keyStore.load(fis, password);
            keyAlias = keyStore.aliases().nextElement();
        } catch (Exception e) {
            System.err.println("Error when loading KeyStore");
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }

    protected PublicKey getPublicKey() {
        try {
            // TODO: return the public key in the keystore
            Key key = keyStore.getKey(keyAlias, password);
            if (key instanceof PrivateKey) {
                // Get certificate of public key
                Certificate cert = keyStore.getCertificate(keyAlias);
                // Get public key
                PublicKey publicKey = cert.getPublicKey();

                // Return a key pair
                return publicKey;
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    protected Key getPrivateKey() {
        try {
            Key key = keyStore.getKey(keyAlias, password);
            return key;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
