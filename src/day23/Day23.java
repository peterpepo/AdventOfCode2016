package day23;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day23 {

    public static void solve() {

        // Create map of registers, indexed by Character A-D
        Map<Character, Integer> registers = new HashMap<>();
        registers.put('a', new Integer(7));
        registers.put('b', new Integer(0));
        registers.put('c', new Integer(0));
        registers.put('d', new Integer(0));

        // Create test List of instructions - replace by reading from file instead
        List<String> instructions = new ArrayList<>();

        // Dummy instructions
//        instructions.add("cpy 2 a");
//        instructions.add("tgl a");
//        instructions.add("tgl a");
//        instructions.add("tgl a");
//        instructions.add("cpy 1 a");
//        instructions.add("dec a");
//        instructions.add("dec a");
        //real instructions
        instructions.add("cpy a b");
        instructions.add("dec b");
        instructions.add("cpy a d");
        instructions.add("cpy 0 a");
        instructions.add("cpy b c");
        instructions.add("inc a");
        instructions.add("dec c");
        instructions.add("jnz c -2");
        instructions.add("dec d");
        instructions.add("jnz d -5");
        instructions.add("dec b");
        instructions.add("cpy b c");
        instructions.add("cpy c d");
        instructions.add("dec d");
        instructions.add("inc c");
        instructions.add("jnz d -2");
        instructions.add("tgl c");
        instructions.add("cpy -16 c");
        instructions.add("jnz 1 c");
        instructions.add("cpy 95 c");
        instructions.add("jnz 99 d");
        instructions.add("inc a");
        instructions.add("inc d");
        instructions.add("jnz d -2");
        instructions.add("inc c");
        instructions.add("jnz c -5");
        // Loop through list of instructions
        int i = 0;
        Pattern p;
        Matcher m;

        while (i < instructions.size()) {
            System.out.println("[INFO]\tCurrent instruction: " + i);
//            System.out.println("[INFO]\tA: " + registers.get('a') + "\tB: " + registers.get('b') + "\tC: " + registers.get('c') + "\tD:" + registers.get('d'));
            String instruction = instructions.get(i);

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

//                p = Pattern.compile("(\\w+)");
                p = Pattern.compile("\\-*[\\w]+");

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

                p = Pattern.compile("\\-*[\\w]+");
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
                String id2 = identifiers.get(1);
                Integer newValue2;
                // It's a number, not a register name
                if (id2.length() > 1) {
                    newValue2 = Integer.parseInt(id2);
                } else // it can be either register or register name
                {
                    // It's a register
                    if (registers.containsKey(id2.charAt(0))) {
                        newValue2 = new Integer(registers.get(id2.charAt(0)));
                    } else {
                        newValue2 = Integer.parseInt(id2);
                    }
                }
                if (!newValue.equals(0)) {
                    i = i + newValue2;
                    continue;
                }
                // It can be only register name
//                Integer amount = Integer.parseInt(identifiers.get(1));
//                if (newValue != 0) {
////                    System.out.println("amount: "+amount);
//                    if (amount < 0) {
//                        i = i + amount;
//                        continue;
//                    } else {
//                        i = i + amount;
//                        continue;
//                    }
//                }
            } // Handle TGL
            else if (instruction.contains("tgl")) {
//                System.out.println("JNZ");
                List<String> identifiers = new ArrayList<>();

                p = Pattern.compile("\\-*[\\w]+");
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

                if ((i + newValue) < instructions.size() && (i + newValue) >= 0) {
                    String origInstruction = instructions.get(i + newValue);
                    String newInstruction = "";
                    if (origInstruction.contains("inc")) {
                        newInstruction = origInstruction.replace("inc", "dec");
                    } else if (origInstruction.contains("dec")) {
                        newInstruction = origInstruction.replace("dec", "inc");
                    } else if (origInstruction.contains("tgl")) {
                        newInstruction = origInstruction.replace("tgl", "inc");
                    } else if (origInstruction.contains("jnz")) {
                        newInstruction = origInstruction.replace("jnz", "cpy");
                    } else if (origInstruction.contains("cpy")) {
                        newInstruction = origInstruction.replace("cpy", "jnz");
                    }
                    instructions.remove((i + newValue));
                    instructions.add((i + newValue), newInstruction);
                } else {
                    System.out.println("[WARNING]\tInvalid reference!:\t" + (i + newValue));
                }
            }
            i++;
        }
        System.out.println(registers.get('a'));
    }
}
