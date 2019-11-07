package codingame;

import java.util.Scanner;


public class MarsLander {

	/**
	 * Auto-generated code below aims at helping you parse
	 * the standard input according to the problem statement.
	 **/
	public static final int MAX_POWER = 4;
	public static final int VSPEED_THRESHOLD = -40;
	public static final int MIN_POWER = 0;

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int surfaceN = in.nextInt(); // the number of points used to draw the surface of Mars.
		for (int i = 0; i < surfaceN; i++) {
			int landX = in.nextInt(); // X coordinate of a surface point. (0 to 6999)
			int landY = in
					.nextInt(); // Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.
		}

		int nextPower = 3;
		// game loop
		while (true) {
			int X = in.nextInt();
			int Y = in.nextInt();
			int hSpeed = in.nextInt(); // the horizontal speed (in m/s), can be negative.
			int vSpeed = in.nextInt(); // the vertical speed (in m/s), can be negative.
			int fuel = in.nextInt(); // the quantity of remaining fuel in liters.
			int rotate = in.nextInt(); // the rotation angle in degrees (-90 to 90).
			int power = in.nextInt(); // the thrust power (0 to 4).

			// Write an action using System.out.println()
			// To debug: System.err.println("Debug messages...");
			if (nextPower < MAX_POWER && vSpeed < VSPEED_THRESHOLD) {
				nextPower++;
			}
			if (nextPower > MIN_POWER && vSpeed > 0) {
				nextPower--;
			}

			// 2 integers: rotate power. rotate is the desired rotation angle (should be 0 for level 1), power is the desired thrust power (0 to 4).
			System.out.println("0 " + nextPower);
		}
	}
}
