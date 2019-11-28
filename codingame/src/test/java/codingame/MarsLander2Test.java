package codingame;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class MarsLander2Test {

	private MarsLander2 marsLander2;

	@Test
	public void init_surface() {
		marsLander2 = new MarsLander2(ImmutableMap.of(0, 1500,
				1000, 2000,
				2000, 500,
				3500, 500,
				6999, 1000));

		assertThat(marsLander2.getStartOfFlatGround(), is(2000));
		assertThat(marsLander2.getEndOfFlatGround(), is(3500));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void init_single_error() {
		marsLander2 = new MarsLander2(ImmutableMap.of(1, 2));
	}

	@Test
	public void easy_on_the_right_safe_landing() {
		setUp_easy_on_the_right();

		final Map<String, Integer> trajectory = marsLander2.land(5123, 151, 0, 0, 550, 0, 0);
		assertEquals(0, trajectory.get("rotate").intValue());
		assertTrue(trajectory.get("power") >= 0);
		assertTrue(trajectory.get("power") <= 4);
	}

	@Test
	public void easy_on_the_right_landing_with_full_thrust() {
		setUp_easy_on_the_right();

		final Map<String, Integer> trajectory = marsLander2.land(5123, 151, 0, -41, 550, 0, 0);
		assertEquals(0, trajectory.get("rotate").intValue());
		assertEquals(4, trajectory.get("power").intValue());
	}

	private void setUp_easy_on_the_right() {
		marsLander2 = new MarsLander2(ImmutableMap.of(0, 0, 5122, 150, 5124, 150));
	}
}