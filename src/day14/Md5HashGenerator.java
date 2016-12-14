package day14;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5HashGenerator {

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
    
    public String getNextHash() {
        md.update((salt + Integer.toString(index++)).getBytes());
        byte[] byteData = md.digest();

        return HashUtils.getHexOfByteArray(byteData);
    }

    public String getNextLevel2016Hash() {
        String hash = this.getNextHash();

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");

//            byte[] byteData = hash.getBytes();

            for (int i = 0; i < 2016; i++) {
                md.update(hash.getBytes());
                hash = HashUtils.getHexOfByteArray(md.digest());
            }

            return hash;

        } catch (NoSuchAlgorithmException e) {
            System.err.println("Invalid algorithm specified.");
        }
        return null;
    }

    public Integer getIndex() {
        return index;
    }

}
