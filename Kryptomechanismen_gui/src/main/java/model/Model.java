package model;

import crypt.AsymmetrischVerschluesseln;
import crypt.SymmetrischVerschluesseln;
import util.File;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;

/**
 * Created by Kai on 03.05.2016.
 */
public class Model {
    private SecretKey KEY_AES;
    private SecretKey KEY_DES;
    private KeyPair KEY_PAIR_RSA;
    private PublicKey PUBLIC_KEY;
    private PrivateKey PRIVATE_KEY;
    private String PATH;
    private List<String> KLARTEXT;

    public Model(){
        KEY_AES = SymmetrischVerschluesseln.generateKey("AES");
        KEY_DES = SymmetrischVerschluesseln.generateKey("DES");
        KEY_PAIR_RSA = AsymmetrischVerschluesseln.generateKeyPair();
        PUBLIC_KEY = KEY_PAIR_RSA.getPublic();
        PRIVATE_KEY = KEY_PAIR_RSA.getPrivate();
        PATH = System.getProperty("user.dir") + "\\txt\\";
        KLARTEXT = File.readFile(PATH, "Klartext.txt");
    }

    public SecretKey getKEY_AES() {
        return KEY_AES;
    }

    public void setKEY_AES(SecretKey KEY_AES) {
        this.KEY_AES = KEY_AES;
    }

    public SecretKey getKEY_DES() {
        return KEY_DES;
    }

    public void setKEY_DES(SecretKey KEY_DES) {
        this.KEY_DES = KEY_DES;
    }

    public KeyPair getKEY_PAIR_RSA() {
        return KEY_PAIR_RSA;
    }

    public void setKEY_PAIR_RSA(KeyPair KEY_PAIR_RSA) {
        this.KEY_PAIR_RSA = KEY_PAIR_RSA;
    }

    public PublicKey getPUBLIC_KEY() {
        return PUBLIC_KEY;
    }

    public void setPUBLIC_KEY(PublicKey PUBLIC_KEY) {
        this.PUBLIC_KEY = PUBLIC_KEY;
    }

    public PrivateKey getPRIVATE_KEY() {
        return PRIVATE_KEY;
    }

    public void setPRIVATE_KEY(PrivateKey PRIVATE_KEY) {
        this.PRIVATE_KEY = PRIVATE_KEY;
    }

    public String getPATH() {
        return PATH;
    }

    public void setPATH(String PATH) {
        this.PATH = PATH;
    }

    public List<String> getKLARTEXT() {
        return KLARTEXT;
    }

    public void setKLARTEXT(List<String> KLARTEXT) {
        this.KLARTEXT = KLARTEXT;
    }
}
