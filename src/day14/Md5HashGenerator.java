package day14;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5HashGenerator implements HashGenerator {

    private String salt;
    private Integer index;
    private MessageDigest md;

    public Md5HashGenerator(String salt) {
        this.salt = salt;
        index = 0;
        try {
            this.md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Invalid algorithm specified.");
        }
    }
    
    @Override
    public byte[] getNextHash() {
        md.update((salt + Integer.toString(index++)).getBytes());
        byte[] byteData = md.digest();

        return byteData;
    }
}
