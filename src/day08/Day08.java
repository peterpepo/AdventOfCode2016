package day08;

public class Day08 {

    public static void solve() {
        RingBuffer rb1 = new RingBuffer(new Character[]{'.', '.', '.', '.', '#', '.', '#'});
        
        for (Character c : rb1.getChars()) {
            System.out.print(c);
        }

        rb1.getChars();

        System.out.println();
        for (Character c : rb1.getChars()) {
            System.out.print(c);
        }
        System.out.println();
    }

}
