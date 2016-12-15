package day15;

import java.util.ArrayList;
import java.util.List;

public class Day15 {

	public static void solve() {

		List<Disc> discList = new ArrayList<>();
		// PUZZLE-01
		discList.add(new Disc(17, 15));
		discList.add(new Disc(3, 2));
		discList.add(new Disc(19, 4));
		discList.add(new Disc(13, 2));
		discList.add(new Disc(7, 2));
		discList.add(new Disc(5, 0));
		
		/*
		 * PUZZLE-02
		 * Adds additional disc with size of 11 and initial position 0
		 * Un/comment in order to solve puzzle 02 / puzzle 01
		 */
		discList.add(new Disc(11, 0));

		boolean ballHasFallenThrough = false;
		Integer time = 0;

		while (true) {
			ballHasFallenThrough = true;

			for (int currentDiscOrder = 0; currentDiscOrder < discList.size(); currentDiscOrder++) {
				Disc currentDisk = discList.get(currentDiscOrder).getShallowCopy();

				for (int timeToReachThisDisk = 0; timeToReachThisDisk < currentDiscOrder + 1; timeToReachThisDisk++) {
					currentDisk.rotate();
				}

				if (currentDisk.getCurrentPosition() != 0) {
					ballHasFallenThrough = false;
				}
				discList.get(currentDiscOrder).rotate();
			}

			if (ballHasFallenThrough) {
				System.out.println("Ball falls through set of disk at soonest at time of: " + Integer.toString(time));
				break;
			}
			time++;
		}
	}

}
