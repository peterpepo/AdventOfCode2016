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

    public byte[] getNextLevel2016Hash() {
        byte[] byteData = this.getNextHash();

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");

//            byte[] byteData = hash.getBytes();
            String hash = HashUtils.getHexOfByteArray(byteData);
            for (int i = 0; i < 2016; i++) {
                md.update(hash.getBytes());
                byteData = md.digest();
                hash = HashUtils.getHexOfByteArray(byteData);
            }

            return byteData;

        } catch (NoSuchAlgorithmException e) {
            System.err.println("Invalid algorithm specified.");
        }
        return null;
    }
}
