package commons;

public class StringUtils {

    public static boolean isPalindrome(String source) {
        if (source.length() == 1) {
            return false;
        }
        for (int i = 0; i < source.length(); i++) {
            if (((source.length() % 2) == 1) && (i == Math.ceil(source.length() / 2))) {
                continue;
            }
            if (source.charAt(i) != source.charAt(source.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean containsVariousCharacters(String source) {
        for (int i = 0; i < source.length() - 1; i++) {
            if (source.charAt(i) != source.charAt(i + 1)) {
                return true;
            }
        }
        return false;
    }


    
}
