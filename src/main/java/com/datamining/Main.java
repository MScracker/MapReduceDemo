package com.datamining;

import com.datamining.util.ReadArbiKV;
import com.datamining.util.ReadBins;
import org.apache.mahout.math.Vector;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

public class Main implements Serializable,Cloneable {

	public static void main(String[] args) throws IOException {
		// write your code here
//		String path = "D:/workspace/mahout/mr/debugout/tmp/observationsPerColumn.bin";
//		Vector vector = ReadBins.getVector(path);
//		System.out.println(vector);

//		String path = "D:/workspace/mahout/mr/debugout/tmp/preparePreferenceMatrix/ratingMatrix/part-r-00000";
//		Map map = ReadArbiKV.readFromFile(path);
//		System.out.println(map);
		String p1 = "../mahout/mr/debugout/tmp/notUsed/part-r-00000";
		String p2 = "../mahout/mr/debugout/tmp/observationsPerColumn.bin";
		Map map = ReadArbiKV.readFromFile(new File(p1).getAbsolutePath());
		System.out.println(map);
		Vector vector = ReadBins.getVector(new File(p2).getAbsolutePath());
		System.out.println(vector);

        String curPath = System.getProperty("user.home");
        System.out.println(curPath);
    }

}
