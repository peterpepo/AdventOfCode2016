package day05;

public class Day05 {

    private static final String PUZZLE_INPUT = "cxdnnyjw";

    public static void solve() {
        System.out.println("First puzzle: " + new PasswordUtils().getDoorKey(PUZZLE_INPUT));
        System.out.println("Second puzzle: " + new PasswordUtils().getSecondLevelDoorKey(PUZZLE_INPUT));

    }

}
