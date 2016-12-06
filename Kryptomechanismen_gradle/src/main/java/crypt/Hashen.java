package crypt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hashen nach MD5 oder SHA-1
 */
public class Hashen {
    static final Logger LOG = LogManager.getLogger(Hashen.class.getName());
    /**
     * String hashen
     * @param plain Klartext
     * @param algorithm MD5 / SHA-1
     * @return gehashter Text
     */
    public static String encrypt(String plain, String algorithm){
        String hashtext = null;
        try{
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] inputBytes = plain.getBytes();
            byte[] hashBytes = md.digest(inputBytes);

            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < hashBytes.length; i++) {
                stringBuffer.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            hashtext =  stringBuffer.toString();
        }
        catch (NoSuchAlgorithmException e) {
            LOG.error("Exception: NoSuchAlgorithmException");
        }
        return hashtext;
    }
}
