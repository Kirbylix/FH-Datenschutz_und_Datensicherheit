package crypt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.crypto.*;
import java.security.*;
import java.util.Base64;

/**
 * Asymmetrische Verschlüsselung nach RSA
 */
public class AsymmetrischVerschluesseln {
    static final Logger LOG = LogManager.getLogger(AsymmetrischVerschluesseln.class.getName());

    /**
     * Asymmetrische Verschlüsselung
     * @param plain Klartext
     * @param publicKey Public Key
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
            LOG.error("Exception: NoSuchAlgorithmException");
        } catch (NoSuchPaddingException e) {
            LOG.error("Exception: NoSuchPaddingException");
        } catch (InvalidKeyException e) {
            LOG.error("Exception: InvalidKeyException");
        } catch (BadPaddingException e) {
            LOG.error("Exception: BadPaddingException");
        } catch (IllegalBlockSizeException e) {
            LOG.error("Exception: IllegalBlockSizeException");
        }
        return cryptic;
    }

    /**
     * Asymmetrische Entschlüsselung
     * @param cryptic verschlüsselter Text
     * @param privateKey Private Key
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
            LOG.error("Exception: NoSuchAlgorithmException");
        } catch (NoSuchPaddingException e) {
            LOG.error("Exception: NoSuchPaddingException");
        } catch (BadPaddingException e) {
            LOG.error("Exception: BadPaddingException");
        } catch (IllegalBlockSizeException e) {
            LOG.error("Exception: IllegalBlockSizeException");
        } catch (InvalidKeyException e) {
            LOG.error("Exception: InvalidKeyException");
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
            LOG.info("RSA Schlüsselpaar wurde erstellt");
        } catch (NoSuchAlgorithmException exception) {
            LOG.error("Exception: NoSuchAlgorithmException");
        }
        return pair;
    }
}
