package com.datamining.job;

import com.datamining.mapreduce.RankProductsMapper;
import com.datamining.mapreduce.RankProductsReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.LazyOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by wanghuali1 on 2017/5/2.
 */
public class HotSaleRankJob extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        Job job = Job.getInstance(conf, "rank job");
        job.setJarByClass(HotSaleRankJob.class);
        job.setMapperClass(RankProductsMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setReducerClass(RankProductsReducer.class);
        job.setOutputKeyClass(NullWritable.class); //指定输出的key类型
//        job.setOutputValueClass(TextOutputFormat.class); //指定输出reduce的value类型
        LazyOutputFormat.setOutputFormatClass(job, TextOutputFormat.class);
        FileSystem fs = FileSystem.get(conf);
        Path inputPath = new Path(args[0]);
        Path outputPath = new Path(args[1]);
        if (fs.exists(outputPath)) {
            fs.delete(outputPath, true);
        }
        FileInputFormat.addInputPath(job, inputPath); //指定输入路径
        FileOutputFormat.setOutputPath(job, outputPath);//指定输出路径
        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.err.printf("Usage: %s [generic options] <input> <output>");
            ToolRunner.printGenericCommandUsage(System.err);
        }
        Configuration conf = new Configuration();
        conf.set("mapred.reduce.tasks", "2"); //会被外部-D覆盖
        int ret = ToolRunner.run(conf, new HotSaleRankJob(), args);
        System.exit(ret);
    }
}
