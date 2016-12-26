package day17;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5HashGenerator {

    private static MessageDigest md;

    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Invalid algorithm specified.");
        }
    }

    private static byte[] getHash(String source) {
        md.update(source.getBytes());
        byte[] byteData = md.digest();

        return byteData;
    }

    public static String getHashString(String source) {
        StringBuffer result = new StringBuffer();
        byte[] byteData = getHash(source);
        
        for (int i = 0; i < byteData.length; i++) {
            result.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        return result.toString();
    }
}
