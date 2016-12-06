import crypt.AsymmetrischVerschluesseln;
import crypt.Hashen;
import crypt.SymmetrischVerschluesseln;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.File;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;
import java.util.stream.Collectors;

public class Kryptomechanismen {
    private static final Logger LOG = LogManager.getLogger(Kryptomechanismen.class.getName());

    private static SecretKey KEY_AES = SymmetrischVerschluesseln.generateKey("AES");
    private static SecretKey KEY_DES = SymmetrischVerschluesseln.generateKey("DES");
    private static KeyPair KEY_PAIR_RSA = AsymmetrischVerschluesseln.generateKeyPair();
    private static PublicKey PUBLIC_KEY = KEY_PAIR_RSA.getPublic();
    private static PrivateKey PRIVATE_KEY = KEY_PAIR_RSA.getPrivate();
    private static String PATH = System.getProperty("user.dir") + "\\txt\\";
    private static List<String> KLARTEXT = File.readFile(PATH, "Klartext.txt");

    public static void main(String[] args) {
        if (KLARTEXT.size() != 0) {

            // Symmetrische Verschlüsselung
            // AES
            if (KEY_AES != null) {
                symmetrischeVerschluesselung("AES");
                symmetrischeEntschluesselnung("AES");
            }

            //DES
            if (KEY_DES != null) {
                symmetrischeVerschluesselung("DES");
                symmetrischeEntschluesselnung("DES");
            }

            // Asymmetrische Verschlüsselung
            //RSA
            if (KEY_PAIR_RSA != null) {
                asymmetrischeVerschluesselung();
                asymmetrischeEntschluesselnung();
            }

            //MD5
            hashing("MD5");

            //SHA-1
            hashing("SHA-1");

            // Digitale Signatur
            if (KEY_PAIR_RSA != null) {
                digitalSignieren("MD5");
                verifizieren("MD5");
            }
        }
    }

    /**
     * Symmetrische Verschlüsselung
     * @param algorithm AES oder DES
     */
    private static void symmetrischeVerschluesselung(String algorithm) {
        if (algorithm == "AES" || algorithm == "DES") {
            SecretKey key = (algorithm == "AES") ? KEY_AES : KEY_DES;
            // Klartext Verschlüsseln
            List<String> crypt = KLARTEXT.stream().map(s -> SymmetrischVerschluesseln.encrypt(s, key, algorithm)).collect(Collectors.toList());
            LOG.info("Der Text wurde mit: " + algorithm + " Verschlüsselt");
            File.writeFile(crypt, PATH, algorithm + "_crypt.txt");
        } else {
            LOG.info(algorithm + " ist kein Unterstützer Algorithmus");
        }
    }

    /**
     * Symmetrische Entschlüsselung
     * @param algorithm AES oder DES
     */
    private static void symmetrischeEntschluesselnung(String algorithm) {
        if (algorithm == "AES" || algorithm == "DES") {
            // Crypt Text lesen (laden)
            List<String> crypt = File.readFile(PATH, algorithm + "_crypt.txt");
            SecretKey key = (algorithm == "AES") ? KEY_AES : KEY_DES;
            // Crypt Text Entschlüsseln
            List<String> plain = crypt.stream().map(s -> SymmetrischVerschluesseln.decrypt(s, key, algorithm)).collect(Collectors.toList());
            LOG.info("Der Text wurde mit: " + algorithm + " Entschlüsselt");
            File.writeFile(plain, PATH, algorithm + "_plain.txt");
        } else {
            LOG.info(algorithm + " ist kein Unterstützer Algorithmus");
        }
    }

    /**
     * Asymmetrische Verschlüsselung
     */
    private static void asymmetrischeVerschluesselung() {
        // Klartext verschlüsseln
        List<String> cryptRSA = KLARTEXT.stream().map(s -> AsymmetrischVerschluesseln.encrypt(s, PUBLIC_KEY)).collect(Collectors.toList());
        LOG.info("Der Text wurde mit: RSA Verschlüsselt");
        File.writeFile(cryptRSA, PATH, "RSA_crypt.txt");
    }

    /**
     * Asymmetrische Entschlüsselung
     */
    private static void asymmetrischeEntschluesselnung() {
        // Crypt Text lesen (laden)
        List<String> cryptRSA = File.readFile(PATH, "RSA_crypt.txt");
        // Crypt Text entschlüsseln
        List<String> plainRSA = cryptRSA.stream().map(s -> AsymmetrischVerschluesseln.decrypt(s, PRIVATE_KEY)).collect(Collectors.toList());
        LOG.info("Der Text wurde mit: RSA Entschlüsselt");
        File.writeFile(plainRSA, PATH, "RSA_plain.txt");
    }

    /**
     * Hashen
     * @param algorithm MD5 oder SHA-1
     */
    private static void hashing(String algorithm) {
        if (algorithm == "MD5" || algorithm == "SHA-1") {
            // Klartext hashen
            List<String> crypt = KLARTEXT.stream().map(s -> Hashen.encrypt(s, algorithm)).collect(Collectors.toList());
            LOG.info("Der Text wurde mit: " + algorithm + " gehasht");
            File.writeFile(crypt, PATH, algorithm + "_crypt.txt");
        } else {
            LOG.info(algorithm + " ist kein Unterstützer Algorithmus");
        }
    }

    /**
     * erstellen einer Digitalen Signatur
     * @param hashAlgorithm MD5 oder SHA-1
     */
    private static void digitalSignieren(String hashAlgorithm) {
        // Klartext hashen
        List<String> hash = KLARTEXT.stream().map(s -> Hashen.encrypt(s, hashAlgorithm)).collect(Collectors.toList());
        LOG.info("Digitale Signatur: Der Text wurde mit: " + hashAlgorithm + " gehasht");
        // Hash verschlüsseln -> RSA -> Private Key
        List<String> fingerprint = hash.stream().map(s -> AsymmetrischVerschluesseln.encrypt(s, PRIVATE_KEY)).collect(Collectors.toList());
        LOG.info("Digitale Signatur: Fingerprint wurde erstellt");
        // Fingerprint in Datei schreiben
        File.writeFile(fingerprint, PATH, "fingerprint_crypt.txt");
    }

    /**
     * eine digitale Signatur verifizieren
     * @param hashAlgorithm MD5 oder SHA-1
     */
    private static void verifizieren(String hashAlgorithm) {
        // Klartext hashen
        List<String> hashB = KLARTEXT.stream().map(s -> Hashen.encrypt(s, hashAlgorithm)).collect(Collectors.toList());
        LOG.info("Digitale Signatur: Der Text wurde mit: " + hashAlgorithm + " gehasht");
        // Fingerprint lesen (laden)
        List<String> getFingerprint = File.readFile(PATH, "fingerprint_crypt.txt");
        // Fingerprint entschlüsseln -> RSA -> PublicKey
        List<String> hashA = getFingerprint.stream().map(s -> AsymmetrischVerschluesseln.decrypt(s, PUBLIC_KEY)).collect(Collectors.toList());
        // überprüfen ob beide hash-Werte identisch sind
        boolean same = true;
        if (hashA.size() == hashB.size()) {
            int i = 0;
            for (String a : hashA) {
                if (!a.equals(hashB.get(i))) {
                    same = false;
                }
                i++;
            }
        } else {
            same = false;
        }
        if (same) {
            LOG.info("DIE SIGNATUR STIMMT ÜBEREIN !!!!");
        } else {
            LOG.info("die Signatur stimmt --->> NICHT <<--- überein !!!!");
        }
    }
}