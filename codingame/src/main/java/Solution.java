import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        PaintedWall paintedWall = new PaintedWall(L);
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            int st = in.nextInt();
            int ed = in.nextInt();
            paintedWall.reportInterval(new Interval(st, ed));
        }

        System.out.println(getMissingIntervalsAsString(paintedWall));
    }

    private static String getMissingIntervalsAsString(PaintedWall paintedWall) {
        Set<Interval> missingIntervals = paintedWall.getUnPaintedIntervals();
        if (missingIntervals.isEmpty()) {
            return "All painted";
        }
        return missingIntervals.stream()
                .map(Interval::toString)
                .collect(Collectors.joining("\n"));
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

    private static class Interval implements Comparable<Interval> {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int length() {
            return end - start;
        }

        @Override
        public String toString() {
            return start + " " + end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Interval interval = (Interval) o;
            return start == interval.start &&
                    end == interval.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }

        @Override
        public int compareTo(Interval interval) {
            return start-interval.getStart();
        }
    }
}