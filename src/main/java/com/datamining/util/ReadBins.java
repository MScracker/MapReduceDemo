package com.datamining.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.hadoop.similarity.cooccurrence.Vectors;

import java.io.IOException;

/**
 * Created by wanghuali1 on 2017/5/18.
 */
public class ReadBins {
	public static Vector getVector(String path){
		Configuration conf=new Configuration();
		Vector vector=null;
		try {
			vector = Vectors.read(new Path(path), conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return vector;
	}
}
