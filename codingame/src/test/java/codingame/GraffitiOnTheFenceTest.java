package codingame;

import lombok.Value;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertTrue;

public class GraffitiOnTheFenceTest {

    private PaintedWall paintedWall;

    @Test
    public void one_wall_without_painting() {
        initWall(1);

        assertThat(getUnPaintedIntervals(), containsInAnyOrder(new Interval(0, 1)));
    }

    private Set<Interval> getUnPaintedIntervals() {
        return paintedWall.getUnPaintedIntervals();
    }

    private void initWall(int i) {
        paintedWall = new PaintedWall(i);
    }

    @Test
    public void one_wall_full_painted() {
        initWall(1);

        paintedWall.reportInterval(new Interval(0, 1));

        assertTrue(paintedWall.getUnPaintedIntervals().isEmpty());
    }

    @Test
    public void missing_end() {
        initWall(2);

        paintedWall.reportInterval(new Interval(0, 1));

        assertThat(getUnPaintedIntervals(), containsInAnyOrder(new Interval(1, 2)));
    }

    @Test
    public void middle_missing() {
        initWall(3);

        paintedWall.reportInterval(new Interval(0, 1));
        paintedWall.reportInterval(new Interval(2, 3));

        assertThat(getUnPaintedIntervals(), containsInAnyOrder(new Interval(1, 2)));
    }

    @Test
    public void interval_cover() {
        initWall(10);

        paintedWall.reportInterval(new Interval(1, 4));
        paintedWall.reportInterval(new Interval(2, 3));

        assertThat(getUnPaintedIntervals(), containsInAnyOrder(Arrays.asList(new Interval(0, 1), new Interval(4, 10)).toArray()));
    }

    @Test
    public void cover_two() {
        initWall(10);

        paintedWall.reportInterval(new Interval(6, 7));
        paintedWall.reportInterval(new Interval(2, 5));
        paintedWall.reportInterval(new Interval(1, 6));

        assertThat(getUnPaintedIntervals(), containsInAnyOrder(Arrays.asList(new Interval(0, 1), new Interval(7, 10)).toArray()));
    }

    private static class PaintedWall {
        private int length;
        private Set<Interval> paintedIntervals = new TreeSet<>();

        public PaintedWall(int length) {
            this.length = length;
        }

        public Set<Interval> getUnPaintedIntervals() {
            if (paintedIntervals.isEmpty()) {
                return Collections.singleton(new Interval(0, length));
            }

            Set<Interval> missingIntervals = getMissingIntervals(getUncoveredIntervals(paintedIntervals));

            missingIntervals.add(new Interval(0, getStartOfFirstInterval(paintedIntervals)));
            missingIntervals.add(new Interval(getEndOfLastInterval(paintedIntervals), length));

            return getValidIntervals(missingIntervals);
        }

        private Set<Interval> getValidIntervals(Set<Interval> intervals) {
            return intervals.stream()
                    .filter(interval -> interval.length() > 0)
                    .collect(Collectors.toSet());
        }

        private List<Interval> getUncoveredIntervals(Set<Interval> intervals) {
            return intervals.stream()
                    .filter(interval -> !isAlreadyCovered(interval, intervals))
                    .sorted(Interval::compareTo)
                    .collect(Collectors.toList());
        }

        private Set<Interval> getMissingIntervals(List<Interval> intervals) {
            return IntStream.rangeClosed(0, intervals.size() - 2)
                    .boxed()
                    .filter(integer -> intervals.get(integer).getEnd() < intervals.get(integer + 1).getStart())
                    .map(integer -> new Interval(intervals.get(integer).getEnd(), intervals.get(integer + 1).getStart()))
                    .collect(Collectors.toSet());
        }

        private int getEndOfLastInterval(Set<Interval> intervals) {
            return intervals.stream()
                    .mapToInt(Interval::getEnd)
                    .max()
                    .orElse(length);
        }

        private int getStartOfFirstInterval(Set<Interval> intervals) {
            return intervals.stream()
                    .mapToInt(Interval::getStart)
                    .min()
                    .orElse(0);
        }

        public void reportInterval(Interval interval) {
            paintedIntervals.add(interval);
        }

        private boolean isAlreadyCovered(Interval interval, Set<Interval> intervals) {
            return intervals.stream()
                    .filter(interval1 -> !interval.equals(interval1))
                    .filter(painted -> interval.getStart() >= painted.getStart())
                    .anyMatch(painted -> interval.getEnd() <= painted.getEnd());
        }
    }

    @Value
    private static class Interval implements Comparable<Interval> {
        int start;
        int end;

        public int length() {
            return end - start;
        }

        @Override
        public int compareTo(Interval interval) {
            return start - interval.getStart();
        }
    }
}
