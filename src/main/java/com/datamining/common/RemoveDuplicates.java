package com.datamining.common;

import com.datamining.entity.ProductInfo;
import com.datamining.entity.SelectedProductInfo;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.HashSet;

/**
 * Created by wanghuali1 on 2017/4/26.
 */
public class RemoveDuplicates {
    public static class RemoveDuplicatesMapper extends Mapper<LongWritable, Text, Text, Text> {
        private Text outkey = new Text();
        private Text outValue = new Text();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            FileSplit fileSplit = (FileSplit) context.getInputSplit();
            String tableName = fileSplit.getPath().getParent().toString();
            System.out.println("path:" + tableName);
            if (tableName.endsWith("selected_city_pm_info")) {
                SelectedProductInfo selectedProductInfo = new SelectedProductInfo(value.toString());
                if (selectedProductInfo.getDeleteFlag().equals("0")
                        && selectedProductInfo.getGiftFlag().equals("0")
                        && selectedProductInfo.getSaleFlag().equals("1")
                        && selectedProductInfo.getShowFlag().equals("1")) {
                    String prodid = selectedProductInfo.getProdid();
                    String merchantId = selectedProductInfo.getSaleMerchantId();
                    String categoryId = selectedProductInfo.getCategoryId();
                    outkey.set(prodid);
                    outValue.set("1" + merchantId);
                    context.write(outkey, outValue);
                }
            } else {
                ProductInfo productInfo = new ProductInfo(value.toString());
                String prodid = productInfo.getProdId();
                String categoryId = productInfo.getCategLvlId();
                String categoryLevel3Id = productInfo.getCategLvl3Id();
                String productType = productInfo.getProdType();
                String businessType = productInfo.getBisinessType();
                String merchantId = productInfo.getMerchantId();
                outkey.set(prodid);
                outValue.set("2" + productType + "\t" + businessType + "\t" + merchantId + "\t" + categoryId + "\t" + categoryLevel3Id);
                context.write(outkey, outValue);
            }
        }
    }

    public static class RemoveDuplicatesReducer extends Reducer<Text, Text, Text, Text> {
        private Text outkey = new Text();
        private Text outValue = new Text();

        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            System.out.println("key:" + key.toString());
            String prodId = key.toString();
            HashSet<String> hashSet = new HashSet<String>();
            HashSet<Text> left = new HashSet<Text>();
            HashSet<Text> right = new HashSet<Text>();

            for (Text val : values) {
//                System.out.println(val.toString());
                if (val.toString().charAt(0) == '1') {
                    String cityId = val.toString().substring(1);
                    System.out.println("1:" + cityId);
                    left.add(new Text(cityId));
                } else {
                    String tokens = val.toString().substring(1);
                    System.out.println("2:" + tokens);
                    right.add(new Text(tokens));
                }
            }
            System.out.println("left size:" + left.size());
            System.out.println("right size:" + right.size());
            if (left.size() > 0 && right.size() > 0) {
                for (Text v : left) {
                    for (Text w : right) {
                        outValue.set(v + "\t" + w);
                        context.write(key, outValue);
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("mapred.reduce.tasks", "64");
        Job job = Job.getInstance(conf, "Remove Duplicates");
        job.setJarByClass(RemoveDuplicates.class);
        job.setMapperClass(RemoveDuplicatesMapper.class);
        job.setReducerClass(RemoveDuplicatesReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        for (int i = 0; i < args.length; i++) {
            System.out.println("arg" + i + ":" + args[i]);
        }
        FileSystem fs = FileSystem.get(conf);
        Path out = new Path(args[2]);
        if (fs.exists(out)) {
            fs.delete(out, true);
        }

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileInputFormat.addInputPath(job, new Path(args[1]));
        FileOutputFormat.setOutputPath(job, new Path(args[2]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
