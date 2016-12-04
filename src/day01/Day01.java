package day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day01 {

    public static void solve() {
        List<String> directions = new ArrayList<>();
        // Read from file
        File inputFile = new File("src/Day01/puzzle01.txt");

        try {
            Scanner sc = new Scanner(inputFile);
            sc.useDelimiter(", ");

            while (sc.hasNext()) {
                directions.add(sc.next());
            }
            sc.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

        Person p1 = new Person();
        for (String direction : directions) {
            p1.move(direction);
        }

        System.out.println("First puzzle: " + CityMap.getDistanceAFromB(0, 0, p1.positionX(), p1.positionY()));

        Person p2 = new Person();
        for (String direction : directions) {
            p2.move(direction);
        }

//        System.out.println(CityMap.getDistanceAFromB(p2.getFirstPositionVisitedTwice(), p2.getPosition()));
        System.out.println("Second puzzle: " + CityMap.getDistanceAFromB(p2.getFirstPositionVisitedTwice(), new Position()));

    }

}
