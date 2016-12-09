package day08;

public class Day08 {

    public static void solve() {
        Display d = new Display(5, 60);
        d.createRectangle(2, 3);
        System.out.println(d);
        d.rotateColumn(1, 1);
        System.out.println(d);
        d.rotateRow(0, 4);
        System.out.println(d);
        d.rotateColumn(1, 1);
        System.out.println(d);
        System.out.println(d.getOnCount());
    }

}
