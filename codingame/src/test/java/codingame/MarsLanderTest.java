package codingame;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MarsLanderTest {

	private MarsLander marsLander = new MarsLander(3.71F);

	@Test
	public void land_level_1() {
		marsLander.land(1, 1);

		assertEquals(0, marsLander.getPower());
		assertEquals(-4, getRoundedVspeed());
	}

	@Test
	public void land_level_10() {
		marsLander.land(1, 10);

		assertEquals(0, marsLander.getPower());
		assertEquals(-37, getRoundedVspeed());
	}

	@Test
	public void land_level_11() {
		marsLander.land(1, 11);

		assertEquals(1, marsLander.getPower());
		assertEquals(-40, getRoundedVspeed());
	}

	@Test
	public void crash_level_12() {
		marsLander.land(5, 12);

		assertEquals(2, marsLander.getPower());
		assertEquals(-40, getRoundedVspeed());
	}

	@Test
	public void land_level_38() {
		marsLander.land(104, 38);

		assertEquals(4, marsLander.getPower());
		assertEquals(-40, getRoundedVspeed());
	}

	private int getRoundedVspeed() {
		return Math.round(marsLander.getVSpeed());
	}
}