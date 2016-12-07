package day07;

import commons.StringUtils;
//import static commons.StringUtils.isPalindrome;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Address {

    private List<String> addressChunks = new ArrayList<>();
    private List<String> hashChunks = new ArrayList<>();

    public Address(String source) {
//        Pattern p = Pattern.compile("[\\w]+");
        Pattern p = Pattern.compile("(\\[\\w+\\])|\\w+");

        Matcher m = p.matcher(source);

        while (m.find()) {
            if (m.group(1) != null) {
                hashChunks.add(m.group(1));
            } else {
                addressChunks.add(m.group(0));
            }
        }
    }


    public boolean supportsAbba(String source) {
        return StringUtils.containsVariousCharacters(source) && StringUtils.isPalindrome(source) && source.length() == 4;
    }

    public boolean supportsABA(String source) {
        return StringUtils.containsVariousCharacters(source) && StringUtils.isPalindrome(source) && source.length() == 3;
    }

    public boolean containsStringSupportingBAB(String source) {
        for (int i = 0; i < source.length(); i++) {
            for (int j = i; j < source.length(); j++) {
                if (supportsABA(source.substring(i, j + 1))) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean supportsSLS() {

        for (String s : addressChunks) {
            for (int i = 0; i < s.length(); i++) {
                for (int j = i; j < s.length(); j++) {
                    String checkedString = s.substring(i, j + 1);
                    if (supportsABA(checkedString)) {
                        for (String str : hashChunks) {
                            if (str.contains(checkedString.substring(1, 2) + checkedString.substring(0, 1) + checkedString.substring(1, 2))) {
                                return true;
                            }
                        }
                    }
                }
            }

        }
        return false;
    }

    public boolean containsStringSupportingAbba(String source) {
        for (int i = 0; i < source.length(); i++) {
            for (int j = i; j < source.length(); j++) {
                if (supportsAbba(source.substring(i, j + 1))) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean supportsTLS() {
        for (String s : hashChunks) {
            if (containsStringSupportingAbba(s)) {
                return false;
            }
        }

        for (String s : addressChunks) {
            if (containsStringSupportingAbba(s)) {
                return true;
            }
        }

        return false;

    }

}
