package day05;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Thread implements Runnable {

    private String source;

    public Md5Thread(String source) {
        this.source = source;
    }

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

        public String getMd5(String source) {
            md.update(source.getBytes());
            byte[] byteData = md.digest();

            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        }

        public boolean startsWithFiveZeroes(String source) {
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

    @Override
    public void run() {
        Md5Sync md5s = Md5Sync.getInstance();

        NiceHash nh = new NiceHash(source);

        while (md5s.isPasswordComplete() == false) {

            String currentHash = nh.getMd5(source + md5s.getCounter());

            if (nh.startsWithFiveZeroes(currentHash)) {
                md5s.setCharacter(Character.getNumericValue(currentHash.charAt(5)), currentHash.charAt(6));
            }

        }

    }

    private Thread t;

    public void start() {
        System.out.println("Starting ");
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

}
