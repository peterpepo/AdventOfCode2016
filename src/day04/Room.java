package day04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Room {

    private String encodedRoomNumber;

    private int roomSectorId;
    private String roomNumber;
    private String roomHash;

    private final char DASH = '-';

    public Room() {

    }

    public Room(String encodedRoomNumber) {
        this.encodedRoomNumber = encodedRoomNumber;
        this.splitEncodedRoomNumber();
    }

    public int getRoomSectorId() {
        return roomSectorId;
    }

    private void splitEncodedRoomNumber() {

        Pattern p;
        Matcher m;

        // Find room hash from encoded number - finds [xyz] value      
        p = Pattern.compile("\\[[a-zA-Z]+\\]");
        m = p.matcher(this.encodedRoomNumber);

        while (m.find()) {
            String hashInclSquareBrackets = m.group();
            // Trim off leading and trailing space bracket
            this.roomHash = hashInclSquareBrackets.substring(1, hashInclSquareBrackets.length() - 1);
        }

        // Find sector id - sequence of numbers, e.g 123
        p = Pattern.compile("\\d+");
        m = p.matcher(this.encodedRoomNumber);

        while (m.find()) {
            this.roomSectorId = Integer.parseInt(m.group());
        }

        // Find room number - "abc-abc-abc-" (with trailing dash)
        p = Pattern.compile("(([a-zA-Z]+)-)+");
        m = p.matcher(this.encodedRoomNumber);

        if (m.find()) {
            roomNumber = m.group(0);
            // Trim off trailing dash
            roomNumber = roomNumber.substring(0, roomNumber.length() - 1);
        }
    }

    private String calculateHash() {
        Map<Character, Integer> charOccurences = new TreeMap<>();

        // Count each character occurence in the room number (exclude dashes)
        for (char c : this.roomNumber.toCharArray()) {

            // Don't consider dashes
            if (c == DASH) {
                continue;
            }

            // If map already contains value for this char, increment it, otherwise add count of 1
            if (charOccurences.containsKey(c)) {
                Integer charCount = charOccurences.get(c);
                charCount++;
                charOccurences.replace(c, charCount);
            } else {
                charOccurences.put(c, 1);
            }
        }

        // Convert character  + count of it's occurence into "CharacterOccurence", which implements custom compareTo
        List<CharacterOccurence> sortedCharOccurences = new ArrayList<>();
        for (Character c : charOccurences.keySet()) {
            sortedCharOccurences.add(new CharacterOccurence(c, charOccurences.get(c)));
        }
        
        // Sort collection using "natural" ordering (uses compareTo overriden in CharacterOccurence class
        Collections.sort(sortedCharOccurences);

        String resultHash = "";

        // Take five most occured characters
        for (int i = 0; i <= 4; i++) {
            resultHash += sortedCharOccurences.get(i).getChar();
        }

        return resultHash;
    }

    public boolean isHashValid() {
        return roomHash.equals(this.calculateHash());
    }

    // Not required by puzzle itself, used for debugging
    public String getDecodedData() {
        return "room number: " + roomNumber + ", sector id: " + roomSectorId + ", room hash: " + roomHash;
    }

    public String getDecryptedRoomNumber() {
        String decryptedRoomNumber = "";

        int alphabetStart = (int) 'a';
        int alphabetEnd = (int) 'z';
        int alphabetLength = alphabetEnd - alphabetStart + 1;

        /*
        How many chars to shift (e.g if the alphabet length is 5 and we need to
        shift 6, we actually shift only one character
        */
        int shift = roomSectorId % alphabetLength;

        // Shift every character
        for (char c : roomNumber.toCharArray()) {

            int newChar;
            
            // If it's dash, convert to space
            if (c == DASH) {
                newChar = (int) ' ';
            } else {

                newChar = (int) c + shift;

                // If we shifted beyond alphabet end, we need to adjust back
                if (newChar > alphabetEnd) {
                    newChar -= alphabetLength;
                }
            }

            decryptedRoomNumber += (char) newChar;
        }

        return decryptedRoomNumber;
    }

}
