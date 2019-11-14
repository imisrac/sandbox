package bowling;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;


public class BowlingGameTest {

	private Game game;

	@Before
	public void setUp() {
		game = new Game();
	}

	@Test
	public void testScoreRoundTwo() {
		game.roll(1);
		game.roll(2);
		Assert.assertThat(game.score(), is(3));
	}

	@Test
	public void testSpare() {
		game.roll(5);
		game.roll(5);
		game.roll(5);
		game.roll(0);
		Assert.assertThat(game.score(), is(20));
	}
}
