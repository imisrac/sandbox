package codingame;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;


public class SkynetRevolutionE1Test {

	private SkynetRevolutionE1 skynetRevolutionE1;

	@Test
	public void test_simple() {
		skynetRevolutionE1 = new SkynetRevolutionE1(3);
		skynetRevolutionE1.addLink(1, 2);
		skynetRevolutionE1.addLink(0, 1);
		skynetRevolutionE1.addExitGW(2);

		assertThat(skynetRevolutionE1.attack(1), is("1 2"));
	}

	@Test
	public void test_several_paths() {
		skynetRevolutionE1 = new SkynetRevolutionE1(4);
		skynetRevolutionE1.addLink(0, 1);
		skynetRevolutionE1.addLink(0, 2);
		skynetRevolutionE1.addLink(1, 3);
		skynetRevolutionE1.addLink(2, 3);
		skynetRevolutionE1.addExitGW(3);

		assertThat(skynetRevolutionE1.attack(0), is("0 1"));
		assertThat(skynetRevolutionE1.attack(2), is("2 3"));
	}

	@Ignore
	@Test
	public void test_star() {
		skynetRevolutionE1 = new SkynetRevolutionE1(12);
		skynetRevolutionE1.addLink(11, 6);
		skynetRevolutionE1.addLink(11, 5);
		skynetRevolutionE1.addLink(6, 0);
		skynetRevolutionE1.addExitGW(3);

		assertThat(skynetRevolutionE1.attack(11), is("11 6"));
		assertThat(skynetRevolutionE1.attack(5), is("6 0"));
	}
}