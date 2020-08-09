package codingame;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CodersStrikeBackTest {

    private final CodersStrikeBack codersStrikeBack = new CodersStrikeBack();

    @Test
    public void boost() {
        codersStrikeBack.move(10000, 10000, 10000, 0);

        assertThat(codersStrikeBack.getVector().getThrust(), is(10000));
    }

    @Test
    public void slowdown_for_turn() {
        codersStrikeBack.move(101, 101, 101, 100);

        assertThat(codersStrikeBack.getVector().getThrust(), is(0));
    }

    @Test
    public void speed_limit() {
        codersStrikeBack.move(5521, 7476, 10144, 0); //BOOST
        codersStrikeBack.move(5521, 7476, 10144, 0);

        assertThat(codersStrikeBack.getVector().getThrust(), Matchers.lessThanOrEqualTo(100));
    }

    @Test
    public void slowdown_for_checkpoint() {
        int nextCheckpointDist = 20;
        codersStrikeBack.move(100, 100, nextCheckpointDist, 0);

        assertThat(codersStrikeBack.getVector().getThrust(), Matchers.lessThanOrEqualTo(nextCheckpointDist));
    }

    @Test
    public void start_moving() {
        codersStrikeBack.move(1, 1, 1, 0);

        assertThat(codersStrikeBack.getVector().getThrust(), Matchers.greaterThan(0));
    }

}
