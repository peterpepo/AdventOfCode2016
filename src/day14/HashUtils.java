package day14;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HashUtils {

    private static final String TRIPLET_PATTERN = "(\\w)\\1{2,}";

    /**
     * Return first character from an input sequence, which occurred 3 times in
     * a row. If triplet is found, returns null.
     */
    public static Character getFirstTripletChar(String sourceString) {
        Pattern p = Pattern.compile(TRIPLET_PATTERN);
        Matcher m = p.matcher(sourceString);

        // If we find a triplet, return it's first Character
        if (m.find()) {
            return (m.group().charAt(0));
        }

        return null;
    }

    /**
     * Check whether specified text contains qintent(5 times in a row)
     * of character specified.
     */
    public static boolean containsQintentOf(String sourceString, Character wantedChar) {
        Pattern p = Pattern.compile("(" + wantedChar + ")\\1{4,}");
        Matcher m = p.matcher(sourceString);

        return m.find();
    }

    /**
     * Converts array of bytes into hex representation
     */
    public static String getHexOfByteArray(byte[] byteData) {
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < byteData.length; i++) {
            result.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return result.toString();
    }

}
