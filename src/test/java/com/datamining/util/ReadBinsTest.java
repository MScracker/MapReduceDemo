package com.datamining.util;

import org.apache.mahout.math.Vector;


/**
 * Created by wongleon on 2017/5/21.
 */
public class ReadBinsTest {
    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void getVector() throws Exception {
        String bins = "/Users/wongleon/workspace/mahout/output/tmp/observationsPerColumn.bin";
        Vector v = ReadBins.getVector(bins);
        System.out.println(v);
    }

}