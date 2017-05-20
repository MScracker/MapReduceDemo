package com.datamining.job;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.util.ToolRunner;
import org.apache.mahout.common.AbstractJob;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;
import org.apache.mahout.math.hadoop.similarity.cooccurrence.Vectors;

import java.io.IOException;
import java.util.Map;

/**
 * Created by wanghuali1 on 2017/5/20.
 */
public class CountObservationsJob extends AbstractJob {
	private static final String OBSERVATIONS_PER_COLUMN_PATH = CountObservationsJob.class + ".observationsPerColumnPath";

	public static void main(String[] args) throws Exception {
		args = new String[4];
		args[0] = "--input";
		args[1] = "D:/workspace/mahout/mr/debugout/tmp/preparePreferenceMatrix/ratingMatrix/part-r-00000";
		args[2] = "--output";
		args[3] = "./output";
		ToolRunner.run(new CountObservationsJob(), args);
	}

	@Override
	public int run(String[] args) throws Exception {
		addInputOption();
		addOutputOption();
		Map map = parseArguments(args);
		if (map == null || map.isEmpty()) {
			return -1;
		}
		Path observationsPerColumnPath = getTempPath("observationsPerColumn.bin");
		System.out.println("input:" + getInputPath());
		System.out.println("output:" + getOutputPath());
		FileSystem fs = FileSystem.get(new Configuration());
		if (fs.exists(getOutputPath())) {
			fs.delete(getOutputPath(), true);
		}
		Job countObservations = prepareJob(getInputPath(), getOutputPath("notUsed"), CountObservationsMapper.class,
				NullWritable.class, VectorWritable.class, SumObservationsReducer.class, NullWritable.class,
				VectorWritable.class);
//		countObservations.setInputFormatClass(SequenceFileInputFormat.class);
		countObservations.setCombinerClass(VectorSumCombiner.class);
		countObservations.getConfiguration().set(OBSERVATIONS_PER_COLUMN_PATH, observationsPerColumnPath.toString());
		countObservations.setNumReduceTasks(1);
		countObservations.waitForCompletion(true);
		return 0;
	}

	public static class CountObservationsMapper extends Mapper<IntWritable, VectorWritable, NullWritable, VectorWritable> {

		private Vector columnCounts = new RandomAccessSparseVector(Integer.MAX_VALUE);

		@Override
		protected void setup(Context context) throws IOException, InterruptedException {
			Configuration conf = context.getConfiguration();
			for (Map.Entry entry : conf) {
				System.out.println(entry.getKey() + ":" + entry.getValue());
			}

		}

		@Override
		protected void map(IntWritable rowIndex, VectorWritable rowVectorWritable, Context ctx)
				throws IOException, InterruptedException {
			Vector row = rowVectorWritable.get();
			System.out.println("CountObservationsMapper:" + rowIndex.get() + ":" + row);
			for (Vector.Element elem : row.nonZeroes()) {
				System.out.println("CountObservationsMapper->before:" + elem.index() + ":" + columnCounts.getQuick(elem.index()));
				columnCounts.setQuick(elem.index(), columnCounts.getQuick(elem.index()) + 1);
			}
		}

		@Override
		protected void cleanup(Context ctx) throws IOException, InterruptedException {
			ctx.write(NullWritable.get(), new VectorWritable(columnCounts));
		}
	}

	public static class SumObservationsReducer extends Reducer<NullWritable, VectorWritable, NullWritable, VectorWritable> {
		@Override
		protected void reduce(NullWritable nullWritable, Iterable<VectorWritable> partialVectors, Context ctx)
				throws IOException, InterruptedException {
			Vector counts = Vectors.sum(partialVectors.iterator());
			Vectors.write(counts, new Path(ctx.getConfiguration().get(OBSERVATIONS_PER_COLUMN_PATH)), ctx.getConfiguration());
		}
	}

	public static class VectorSumCombiner
			extends Reducer<WritableComparable<?>, VectorWritable, WritableComparable<?>, VectorWritable> {

		private final VectorWritable result = new VectorWritable();

		@Override
		protected void reduce(WritableComparable<?> key, Iterable<VectorWritable> values, Context ctx)
				throws IOException, InterruptedException {
			result.set(Vectors.sum(values.iterator()));
			ctx.write(key, result);
		}
	}
}
