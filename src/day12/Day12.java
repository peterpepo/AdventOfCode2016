package day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day12 {

    public static void solve() {

        // Create map of registers, indexed by Character A-D
        Map<Character, Integer> registers = new HashMap<>();
        registers.put('a', new Integer(0));
        registers.put('b', new Integer(0));
        registers.put('c', new Integer(0));
        registers.put('d', new Integer(0));

        // Create test List of instructions - replace by reading from file instead
        List<String> instructions = new ArrayList<>();

        // Dummy instructions
//        instructions.add("cpy 41 a");
//        instructions.add("inc a");
//        instructions.add("inc a");
//        instructions.add("dec a");
//        instructions.add("jnz a 2");
//        instructions.add("dec a");

        // Real instructions
        instructions.add("inc c");    // UNCOMMENT FOR PUZZLE2
        instructions.add("cpy 1 a");
        instructions.add("cpy 1 b");
        instructions.add("cpy 26 d");
        instructions.add("jnz c 2");
        instructions.add("jnz 1 5");
        instructions.add("cpy 7 c");
        instructions.add("inc d");
        instructions.add("dec c");
        instructions.add("jnz c -2");
        instructions.add("cpy a c");
        instructions.add("inc a");
        instructions.add("dec b");
        instructions.add("jnz b -2");
        instructions.add("cpy c b");
        instructions.add("dec d");
        instructions.add("jnz d -6");
        instructions.add("cpy 17 c");
        instructions.add("cpy 18 d");
        instructions.add("inc a");
        instructions.add("dec d");
        instructions.add("jnz d -2");
        instructions.add("dec c");
        instructions.add("jnz c -5");

        // Loop through list of instructions
        int i = 0;
        Pattern p;
        Matcher m;

        while (i < instructions.size()) {
            String instruction = instructions.get(i);
//            System.out.println("Instruction: "+i);

            // Handle INC
            if (instruction.contains("inc")) {
                p = Pattern.compile("(\\w+)");
                m = p.matcher(instruction.substring(4));

                if (m.find()) {
                    Character registerId = m.group().charAt(0);
                    Integer newValue = registers.get(registerId);
                    newValue++;
                    registers.replace(registerId, newValue);
                }
            } // Handle DEC
            else if (instruction.contains("dec")) {
                p = Pattern.compile("(\\w+)");
                m = p.matcher(instruction.substring(4));

                if (m.find()) {
                    Character registerId = m.group().charAt(0);
                    Integer newValue = registers.get(registerId);
                    newValue--;
                    registers.replace(registerId, newValue);
                }
            } // Handle CPY
            else if (instruction.contains("cpy")) {
//                System.out.println("CPY");
                List<String> identifiers = new ArrayList<>();

                p = Pattern.compile("(\\w+)");
                m = p.matcher(instruction.substring(4));

                while (m.find()) {
                    identifiers.add(m.group());
//                    System.out.println("added");
                }

                // Get source
                String id1 = identifiers.get(0);
                Integer newValue;
                // It's a number, not a register name
                if (id1.length() > 1) {
                    newValue = Integer.parseInt(id1);
                } else // it can be either register or register name
                {
                    // It's a register
                    if (registers.containsKey(id1.charAt(0))) {
                        newValue = new Integer(registers.get(id1.charAt(0)));
                    } else {
                        newValue = Integer.parseInt(id1);
                    }
                }

                // Get target
                // It can be only register name
                String id2 = identifiers.get(1);
                registers.replace(id2.charAt(0), newValue);
            } // Handle JNZ
            else if (instruction.contains("jnz")) {
//                System.out.println("JNZ");
                List<String> identifiers = new ArrayList<>();

                p = Pattern.compile("\\-*[\\w+]");
                m = p.matcher(instruction.substring(4));

                while (m.find()) {
                    identifiers.add(m.group());
//                    System.out.println("added");
                }

                // Get source
                String id1 = identifiers.get(0);
                Integer newValue;
                // It's a number, not a register name
                if (id1.length() > 1) {
                    newValue = Integer.parseInt(id1);
                } else // it can be either register or register name
                {
                    // It's a register
                    if (registers.containsKey(id1.charAt(0))) {
                        newValue = new Integer(registers.get(id1.charAt(0)));
                    } else {
                        newValue = Integer.parseInt(id1);
                    }
                }

                // Get amount
                // It can be only register name
                Integer amount = Integer.parseInt(identifiers.get(1));
                if (newValue != 0) {
//                    System.out.println("amount: "+amount);
                    if (amount < 0) {
                        i = i + amount;
                        continue;
                    } else {
                        i = i + amount;
                        continue;
                    }
                }
            }
            i++;
        }
        System.out.println(registers.get('a'));
    }
}
