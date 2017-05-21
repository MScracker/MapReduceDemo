package com.datamining.entity; /**
 * Created by wanghuali1 on 2017/4/27.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RecScorePair {

    public static final Comparator<RecScorePair> COMPARE_BY_SIMILARITY = new RecScorePairComparator(false);
    public static final Comparator<RecScorePair> COMPARE_BY_SIMILARITY_DESC = new RecScorePairComparator(true);
    private final long recId;
    private final double score;

    public RecScorePair(long recId, double score) {
        this.recId = recId;
        this.score = score;
    }

    public long getRecId() {
        return recId;
    }

    public double getScore() {
        return score;
    }

    public String toString() {
        return recId + ":" + score;
    }

    public static class RecScorePairComparator implements Comparator<RecScorePair>, Serializable {
        boolean desc;

        public RecScorePairComparator(boolean descOrNot) {
            this.desc = descOrNot;
        }

        @Override
        public int compare(RecScorePair s1, RecScorePair s2) {
            if (desc)
                return s1.score == s2.score ? 0 : s1.score < s2.score ? 1 : -1;
            else
                return s1.score == s2.score ? 0 : s1.score < s2.score ? -1 : 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecScorePair that = (RecScorePair) o;

        if (recId != that.recId) return false;
        return Double.compare(that.score, score) == 0;

    }

    public static void main(String[] args) {
        List<RecScorePair> list = new ArrayList<RecScorePair>();
        RecScorePair s1 = new RecScorePair(1, 0.1);
        RecScorePair s2 = new RecScorePair(1, 0.1);
        RecScorePair s3 = new RecScorePair(2, 0.1);

        if (!list.contains(s1)) {
            list.add(s1);
        }
        if (!list.contains(s2)) {
            list.add(s2);
        }
        if (!list.contains(s3)) {
            list.add(s3);
        }
        for (RecScorePair r : list) {
            System.out.println(r.toString());
        }
    }

}