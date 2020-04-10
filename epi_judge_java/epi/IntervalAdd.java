package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class IntervalAdd {

    @EpiUserType(ctorParams = {int.class, int.class})
    public static class Interval {
        public int start, end;

        public Interval(int l, int r) {
            this.start = l;
            this.end = r;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Interval interval = (Interval) o;
            if (start != interval.start) return false;
            return end == interval.end;
        }

        @Override
        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }

    @EpiTest(testDataFile = "interval_add.tsv")
    public static List<Interval> addInterval(List<Interval> intervals, Interval newInterval) {
        int i = 0;
        List<Interval> result = new ArrayList<>();
        while (i < intervals.size() && newInterval.start > intervals.get(i).end) result.add(intervals.get(i++));
        while (i < intervals.size() && newInterval.end >= intervals.get(i).start) {
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(i++).end);
        }
        result.add(newInterval);
        result.addAll(intervals.subList(i, intervals.size()));
        return result;
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args, "IntervalAdd.java", new Object() {
                                }.getClass().getEnclosingClass()).ordinal());
    }
}
