package codingame;

import java.util.Scanner;


public class ShadowsOfTheKnightE1 {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int width = in.nextInt(); // width of the building.
		int height = in.nextInt(); // height of the building.
		int numberOfTurns = in.nextInt(); // maximum number of turns before game over.
		int positionX = in.nextInt(); //horizontal
		int positionY = in.nextInt(); //vertical
		int infX = 0;
		int infY = 0;

		// game loop
		while (numberOfTurns > 0) {
			String bombDirection = in.next(); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)
			if (bombDirection.contains("U")) {
				height = positionY - 1;
				positionY = positionY - Math.floorDiv((positionY - infY + 1), 2);
			}
			if (bombDirection.contains("D")) {
				infY = positionY + 1;
				positionY = (height + positionY + 1) / 2;
			}
			if (bombDirection.contains("R")) {
				infX = positionX + 1;
				positionX = (width + positionX + 1) / 2;
			}
			if (bombDirection.contains("L")) {
				width = positionX - 1;
				positionX = positionX - Math.floorDiv((positionX - infX + 1), 2);
			}

			System.out.println(positionX + " " + positionY);
			numberOfTurns--;
		}
	}
}
