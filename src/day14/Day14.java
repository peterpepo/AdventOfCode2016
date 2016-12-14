package day14;

import java.util.ArrayList;
import java.util.List;

public class Day14 {

    private static final String SALT_PUZZLE = "jlmsuwbz";

    public static void solve() {

        // PUZZLE-01
        Md5HashGenerator testMd5Generator = new Md5HashGenerator(SALT_PUZZLE);
        HashDealer hdSimpleMd5 = new HashDealer(testMd5Generator);

        for (int i = 0; i < 64; i++) {
            hdSimpleMd5.getNextOneTime();
        }
        System.out.println("First puzzle: " + hdSimpleMd5.currentHashIndex());

        // PUZZLE-02
        Md5Level2016Generator md5Level2016Generator = new Md5Level2016Generator(SALT_PUZZLE);
        HashDealer hdLevel2016Md5 = new HashDealer(md5Level2016Generator);

        for (int i = 0; i < 64; i++) {
            hdLevel2016Md5.getNextOneTime();
        }
        System.out.println("Second puzzle: " + hdLevel2016Md5.currentHashIndex());

    }

}
