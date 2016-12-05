package day05;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PasswordUtils {

    MessageDigest md;

    class NiceHash {

        private String source;
        private int count;

        MessageDigest md;

        public NiceHash(String source) {
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                System.err.println("Invalid algorithm specified.");
            }

            this.source = source;
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

        public String getNextNiceHash() {
            String newHash;
            while (true) {
                newHash = getMd5(this.source + Integer.toString(count++));

                if (startsWithFiveZeroes(newHash)) {
                    break;
                }
            }
            return newHash;
        }
    }

    public PasswordUtils() {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Invalid algorithm specified.");
        }
    }

    public String getDoorKey(String doorNumber) {
        NiceHash nh = new NiceHash(doorNumber);

        StringBuilder doorKey = new StringBuilder();

        while (doorKey.length() < 8) {
            doorKey.append(nh.getNextNiceHash().charAt(5));
        }

        return doorKey.toString();
    }

    public String getSecondLevelDoorKey(String doorNumber) {

        NiceHash nh = new NiceHash(doorNumber);

        Character[] password = new Character[8];
        boolean isPasswordComplete = false;

        while (isPasswordComplete == false) {

            String currentHash = nh.getNextNiceHash();

            /*
            Skip this hash, if it returns invalid password position (position>7)
            or this position has already been calculated, thus avoid overwriting
             */
            if (Character.getNumericValue(currentHash.charAt(5)) > 7 || password[Character.getNumericValue(currentHash.charAt(5))] != null) {
                continue;
            }

            password[Character.getNumericValue(currentHash.charAt(5))] = currentHash.charAt(6);
            for (Character c : password) {
                if (c == null) {
                    isPasswordComplete = false;
                    break;
                } else {
                    isPasswordComplete = true;
                }
            }

        }

        StringBuilder resultPassword = new StringBuilder();
        for (Character c : password) {
            resultPassword.append(c);
        }
        return resultPassword.toString();
    }
    
    public String getSecondLevelDoorKeyMulti(String doorNumber) {
        Md5Sync md5s = Md5Sync.getInstance();

        for(int i=1; i<=4; i++) {
            new Md5Thread(doorNumber).start();
        }

        
        while(md5s.isPasswordComplete()==false) {

            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(PasswordUtils.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        return md5s.getPassword();
        
    }

}
