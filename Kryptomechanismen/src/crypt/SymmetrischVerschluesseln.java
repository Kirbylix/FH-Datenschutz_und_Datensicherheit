package crypt;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Symmetrische Verschlüsselung nach AES oder DES
 */
public class SymmetrischVerschluesseln <K> {

    /**
     * symmetrische Verschlüsselung
     * @param plain Klartext
     * @param sk Schlüssel
     * @param algorithm AES / DES
     * @return verschlüsselter Text
     */
    public static String encrypt(String plain, SecretKey sk, String algorithm){
        String secret = null;
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, sk);
            byte[] crypticBytes = cipher.doFinal(plain.getBytes());
            byte[] plainBytes = Base64.getEncoder().encode(crypticBytes);
            secret = new String(plainBytes);
        } catch (NoSuchAlgorithmException exception) {
            System.err.println("Exception: NoSuchAlgorithmException");
        } catch (NoSuchPaddingException exception) {
            System.err.println("Exception: NoSuchPaddingException");
        } catch (BadPaddingException exception) {
            System.err.println("Exception: BadPaddingException");
        } catch (IllegalBlockSizeException exception) {
            System.err.println("Exception: IllegalBlockSizeException");
        } catch (InvalidKeyException exception) {
            System.err.println("Exception: InvalidKeyException");
        }
        return secret;
    }

    /**
     * symmetrische Entschlüsselung
     * @param cryptic verschlüsselter Text
     * @param sk Schlüssel
     * @param algorithm AES / DES
     * @return Klartext
     */
    public static String decrypt(String cryptic, SecretKey sk, String algorithm){
        String plain = null;
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, sk);
            byte[] crypticBytes = Base64.getDecoder().decode(cryptic.getBytes());
            byte[] plainBytes = cipher.doFinal(crypticBytes);
            plain = new String(plainBytes);
        }  catch (NoSuchAlgorithmException exception) {
            System.err.println("Exception: NoSuchAlgorithmException");
        } catch (InvalidKeyException exception) {
            System.err.println("Exception: InvalidKeyException");
        } catch (NoSuchPaddingException exception) {
            System.err.println("Exception: NoSuchPaddingException");
        } catch (BadPaddingException exception) {
            System.err.println("Exception: BadPaddingException");
        } catch (IllegalBlockSizeException exception) {
            System.err.println("Exception: IllegalBlockSizeException");
        }
        return plain;
    }

    /**
     * erstellen eines AES / DES Schlüssels
     * @param algorithm AES / DES
     * @return Schlüssel
     */
    public static SecretKey generateKey(String algorithm){
        SecretKey key = null;
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
            key = keyGen.generateKey();

            System.out.println(algorithm + " Schlüssel wurde erstellt");
        } catch (NoSuchAlgorithmException exception) {
            System.err.println("Exception: NoSuchAlgorithmException");
        }
        return key;
    }
}