package com.datamining;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		// write your code here
//		String path = "D:/workspace/mahout/mr/debugout/tmp/observationsPerColumn.bin";
//		Vector vector = ReadBins.getVector(path);
//		System.out.println(vector);

//		String path = "D:/workspace/mahout/mr/debugout/tmp/preparePreferenceMatrix/ratingMatrix/part-r-00000";
//		Map map = ReadArbiKV.readFromFile(path);
//		System.out.println(map);
//		String p1 = "./notUsed/part-r-00000";
//		String p2 = "/Users/wongleon/workspace/mahout/output/tmp/observationsPerColumn.bin";
////		Map map = ReadArbiKV.readFromFile(new File(p1).getAbsolutePath());
////		System.out.println(map);
//		Vector vector = ReadBins.getVector(new File(p2).getAbsolutePath());
//		System.out.println(vector);
        String curPath = System.getProperty("user.home");
        System.out.println(curPath);
    }
}
