import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

	private static final int MIN_VSPEED = -40;
	private static final int MAX_POWER = 4;

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int surfaceN = in.nextInt(); // the number of points used to draw the surface of Mars.
		for (int i = 0; i < surfaceN; i++) {
			int landX = in.nextInt(); // X coordinate of a surface point. (0 to 6999)
			int landY = in
					.nextInt(); // Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.
		}

		// game loop
		while (true) {
			int X = in.nextInt();
			int Y = in.nextInt();
			int hSpeed = in.nextInt(); // the horizontal speed (in m/s), can be negative.
			int vSpeed = in.nextInt(); // the vertical speed (in m/s), can be negative.
			int fuel = in.nextInt(); // the quantity of remaining fuel in liters.
			int rotate = in.nextInt(); // the rotation angle in degrees (-90 to 90).
			int power = in.nextInt(); // the thrust power (0 to 4).

			if (fuel > 0) {
				if (vSpeed < MIN_VSPEED) {
					if (power < MAX_POWER) {
						power++;
					}
				} else
					if (vSpeed > MIN_VSPEED) {
						if (power > 0) {
							power--;
						}
					}
			} else {
				power = 0;
			}

			System.out.println("0 " + power);
		}
	}
}