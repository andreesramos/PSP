package org.example;

import java.io.FileOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Main {
    public static void main(String[] args) {
        try{
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");

            // Se inicializa el generador de claves
            SecureRandom numero = SecureRandom.getInstance("SHA1PRNG");
            keyGen.initialize(2048, numero);

            // Se crea el par de claves privada y publica
            KeyPair keyPair = keyGen.generateKeyPair();
            PrivateKey clavepriv = keyPair.getPrivate();
            PublicKey clavepub = keyPair.getPublic();

            PKCS8EncodedKeySpec pk8Spec = new PKCS8EncodedKeySpec(clavepriv.getEncoded());
            // Escribir a fichero binario la clave privada
            FileOutputStream outpriv = new FileOutputStream("Clave.privada");
            outpriv.write(clavepriv.getEncoded());
            outpriv.close();

            X509EncodedKeySpec pkX509 = new X509EncodedKeySpec(clavepub.getEncoded());
            // Escribir a fichero la clave publica
            FileOutputStream outpub = new FileOutputStream("Clave.publica");
            outpub.write(clavepub.getEncoded());
            outpub.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    } // main
} //actividad