package codingame;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MarsLanderTest {

	private static final float GRAVITY = 3.71F;
	private MarsLander marsLander = new MarsLander();

	@Test
	public void land_level_1() {
		marsLander.land(1, 1, GRAVITY);

		assertEquals(0, marsLander.getPower());
		assertEquals(-4, getRoundedVspeed());
	}

	@Test
	public void land_level_10() {
		marsLander.land(1, 10, GRAVITY);

		assertEquals(0, marsLander.getPower());
		assertEquals(-37, getRoundedVspeed());
	}

	@Test
	public void land_level_11() {
		marsLander.land(1, 11, GRAVITY);

		assertEquals(1, marsLander.getPower());
		assertEquals(-40, getRoundedVspeed());
	}

	@Ignore
	@Test
	public void crash_level_12() {
		marsLander.land(5, 12, GRAVITY);

		assertEquals(2, marsLander.getPower());
		assertEquals(-40, getRoundedVspeed());
	}

	@Test
	public void land_level_38() {
		marsLander.land(104, 38, GRAVITY);

		assertEquals(4, marsLander.getPower());
		assertEquals(-40, getRoundedVspeed());
	}

	private int getRoundedVspeed() {
		return Math.round(marsLander.getVSpeed());
	}
}