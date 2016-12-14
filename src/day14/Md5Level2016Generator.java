package day14;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Level2016Generator implements HashGenerator {

    private Md5HashGenerator baseHashGenerator;

    public Md5Level2016Generator(String salt) {
        this.baseHashGenerator = new Md5HashGenerator(salt);
    }

    public byte[] getNextHash() {
        byte[] byteData = baseHashGenerator.getNextHash();

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");

            // Get string representation of md5 based on specified salt
            String hash = HashUtils.getHexOfByteArray(byteData);

            // Stretch hash 2016 times
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
