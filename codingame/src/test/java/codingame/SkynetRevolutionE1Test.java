package codingame;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;


public class SkynetRevolutionE1Test {

	private SkynetRevolutionE1 skynetRevolutionE1;

	@Test
	public void simple() {
		skynetRevolutionE1 = new SkynetRevolutionE1(3);
		skynetRevolutionE1.addLink(1, 2);
		skynetRevolutionE1.addLink(0, 1);
		skynetRevolutionE1.addExitGW(2);

		assertThat(skynetRevolutionE1.attack(1), is("1 2"));
	}

	@Test
	public void several_paths() {
		skynetRevolutionE1 = new SkynetRevolutionE1(4);
		skynetRevolutionE1.addLink(0, 1);
		skynetRevolutionE1.addLink(0, 2);
		skynetRevolutionE1.addLink(1, 3);
		skynetRevolutionE1.addLink(2, 3);
		skynetRevolutionE1.addExitGW(3);

		assertThat(skynetRevolutionE1.attack(0), is("1 3"));
		assertThat(skynetRevolutionE1.attack(2), is("2 3"));
	}

	@Test
	public void star() {
		skynetRevolutionE1 = new SkynetRevolutionE1(12);
		skynetRevolutionE1.addLink(11, 6);
		skynetRevolutionE1.addLink(11, 5);
		skynetRevolutionE1.addLink(6, 5);
		skynetRevolutionE1.addLink(6, 0);
		skynetRevolutionE1.addLink(5, 4);
		skynetRevolutionE1.addLink(5, 0);
		skynetRevolutionE1.addLink(4, 0);
		skynetRevolutionE1.addExitGW(0);

		assertThat(skynetRevolutionE1.attack(11), is("6 0"));
		assertThat(skynetRevolutionE1.attack(5), is("5 0"));
		assertThat(skynetRevolutionE1.attack(4), is("4 0"));
	}

	@Test
	public void triple_star() {
		skynetRevolutionE1 = new SkynetRevolutionE1(78);
		skynetRevolutionE1.addLink(37, 2);
		skynetRevolutionE1.addLink(35, 28);
		skynetRevolutionE1.addLink(35, 2);
		skynetRevolutionE1.addLink(2, 0);
		skynetRevolutionE1.addExitGW(0);
		skynetRevolutionE1.addExitGW(28);

		assertThat(skynetRevolutionE1.attack(37), is("2 0"));
		assertThat(skynetRevolutionE1.attack(35), is("35 28"));
	}
}