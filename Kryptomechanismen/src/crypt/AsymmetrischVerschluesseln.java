package crypt;

import javax.crypto.*;
import java.security.*;
import java.util.Base64;

/**
 * Asymmetrische Verschlüsselung nach RSA
 */
public class AsymmetrischVerschluesseln {

    /**
     * Asymmetrische Verschlüsselung
     * @param plain Klartext
     * @param key Public Key
     * @return verschlüsselter Text
     */
    public static String encrypt(String plain, Key key){
        String cryptic = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] crypticBytes = cipher.doFinal(plain.getBytes());
            byte[] plainBytes = Base64.getEncoder().encode(crypticBytes);
            cryptic = new String(plainBytes);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Exception: NoSuchAlgorithmException");
        } catch (NoSuchPaddingException e) {
            System.err.println("Exception: NoSuchPaddingException");
        } catch (InvalidKeyException e) {
            System.err.println("Exception: InvalidKeyException");
        } catch (BadPaddingException e) {
            System.err.println("Exception: BadPaddingException");
        } catch (IllegalBlockSizeException e) {
            System.err.println("Exception: IllegalBlockSizeException");
        }
        return cryptic;
    }

    /**
     * Asymmetrische Entschlüsselung
     * @param cryptic verschlüsselter Text
     * @param key Private Key
     * @return Klartext
     */
    public static String decrypt(String cryptic, Key key){
        String plain = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] crypticByte = Base64.getDecoder().decode(cryptic.getBytes());
            byte[] plainBytes = cipher.doFinal(crypticByte);
            plain = new String(plainBytes);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Exception: NoSuchAlgorithmException");
        } catch (NoSuchPaddingException e) {
            System.err.println("Exception: NoSuchPaddingException");
        } catch (BadPaddingException e) {
            System.err.println("Exception: BadPaddingException");
        } catch (IllegalBlockSizeException e) {
            System.err.println("Exception: IllegalBlockSizeException");
        } catch (InvalidKeyException e) {
            System.err.println("Exception: InvalidKeyException");
        }
        return plain;
    }

    /**
     * generieren eines Schlüsselpaares (PrivateKey + PrivateKey)
     * @return Schlüsselpaar
     */
    public static KeyPair generateKeyPair(){
        KeyPair pair = null;
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            pair = kpg.generateKeyPair();
            PrivateKey priv = pair.getPrivate();
            PublicKey pub = pair.getPublic();
            System.out.println("RSA Schlüsselpaar wurde erstellt");
        } catch (NoSuchAlgorithmException exception) {
            System.err.println("Exception: NoSuchAlgorithmException");
        }
        return pair;
    }
}
