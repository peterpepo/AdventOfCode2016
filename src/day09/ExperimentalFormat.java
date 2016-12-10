package day09;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExperimentalFormat {

    private static int depth = 0;

    /*
    Calculates length of fully decompressed String iteratively, 1 marker at time:
        1) Checks whether input contains any marker
        2) If it doesn't, returns length() of input
        3) If it does, adds length of part preceeding marker to total length
        4) Finds first marker
        5) Find affected section length (recursively) , multiples based on marker
            and add to total length
        6) Finds lenght of remaining section recursively
     */
    public static long getDecompressFullLength(String input) {
        long length = 0;
        List<Integer> markerParameters = new ArrayList<>();

        /*
        First check, whether input contains (AxB), if it doesn't return plain
        length of input
         */
        Pattern p = Pattern.compile("\\(\\d+x\\d+\\)");
        Matcher m = p.matcher(input);

        if (!m.find()) {
            length = input.length();
        } else {
            // Add length between start and first occurence of marker
            length += m.start();

            // Read next marker
            Pattern p2 = Pattern.compile("\\d+");
            Matcher m2 = p2.matcher(input);

            // Read only first marker in the remaining section
            for (int i = 1; i <= 2; i++) {
                if (m2.find()) {
                    markerParameters.add(Integer.parseInt(m2.group()));
                }
            }

            /*
            Find out length of section affected by marker
            That equals to its length * times it repeats
             */
            String repeatedSection = input.substring(m.end(), m.end() + markerParameters.get(0));
            length += getDecompressFullLength(repeatedSection) * markerParameters.get(1);

            // Find length of remaining section (after section affected by marker)
            length += getDecompressFullLength(input.substring(m.end() + markerParameters.get(0)));
        }
        return length;

    }

    /*
    Decompress string by one level:
        1) Starts reading
        2) Finds characters prior to first marker, add them to output
        3) Finds first marker
        4) Decodes the marker
        5) Repeats section affected by marker
        6) Move "start" of reading at first character after affected section
        7) Continues by 3)
        8) Adds remaining characters after the last affected section
     */
    public static String decompressOneLevel(String input) {
        StringBuilder output = new StringBuilder();

        Pattern p;
        Matcher m;

        int offset = 0;

        while (offset < input.length()) {
            List<Integer> markerParameters = new ArrayList<>();

            p = Pattern.compile("\\(\\d+x\\d+\\)");

            // Might refactor this a little later on
            m = p.matcher(input);

            if (m.find(offset)) {
                // Potentially should apply only for finding first occurence of marker
                output.append(input.substring(offset, m.start()));

                offset = m.end();

                Pattern p2 = Pattern.compile("\\d+");
                Matcher m2 = p2.matcher(input.substring(m.start(), offset));

                while (m2.find()) {
                    markerParameters.add(Integer.parseInt(m2.group()));
                }

                offset += markerParameters.get(0);

                String repeatedSection = input.substring(m.end(), offset);

                for (int i = 1; i <= markerParameters.get(1); i++) {
                    output.append(repeatedSection);
                }
            } else {
                output.append(input.substring(offset));
                break;
            }
        }

        return output.toString();
    }

    /*
    Decompress string fully, using recursion:
        1) Decompresses input one level by using decompressOneLevel
        2) Checks, whether decompressed string contains markers
        3A) YES -> return to step 1 using decompressed string from step 2
        3B) NO -> returns string from step 2
    !!! PLEASE USE WITH CAUTION !!!
    Whole decompressed output is stored in the memory.
    While it works fine with small sample outputs from Day-09, such as:
        (25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN resulting in
        445 chars length
    It's memory hungry, and for my puzzle input particulary causes
        java.lang.OutOfMemoryError exception. As I found out later, output length
        for my puzzle is: 11107527530. The result string itself only would theoretically
        take up ~10.34GB of memory (1 char = 8 bytes)
    As it's not the most efficient algorithm ever build, char taking up probably
        more than 1 byte, 16 GB ram wasn't enough to store this string, failing
        at recursion level 6.
     */
    public static String decompressFull(String input) {

        System.out.println("decompressFull DEPTH: "+ ++depth);

        String decompressed = decompressOneLevel(input);

        Pattern p = Pattern.compile("\\(\\d+x\\d+\\)");
        Matcher m = p.matcher(decompressed);

        if (m.find()) {
            return decompressFull(decompressed);
        } else {
            return decompressed;
        }

    }
}
