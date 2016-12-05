package day05;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {

    MessageDigest md;

    public PasswordUtils() {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Invalid algorithm specified.");
        }
    }

    private String getMd5(String source) {
        md.update(source.getBytes());
        byte[] byteData = md.digest();

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    private boolean startsWithFiveZeroes(String source) {
        try {
            for (int i = 0; i < 5; i++) {
                if (source.charAt(i) != '0') {
                    return false;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

        return true;
    }

    public String getDoorKey(String doorNumber) {
        int counter = 0;
        StringBuilder doorKey = new StringBuilder();
        
        while(doorKey.length()<8) {
            String newHash = getMd5(doorNumber+Integer.toString(counter));
            
            if(startsWithFiveZeroes(newHash)) {
                doorKey.append(newHash.charAt(5));
            }
            
            counter++;
        }
        
        return doorKey.toString();
    }

}
