package com.datamining.mapreduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by wanghuali1 on 2017/4/6.
 */
public class RankProductsMapper extends Mapper<LongWritable,Text,Text,Text>{
    private Text outKey = new Text();
    private Text outValue = new Text();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {

    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] splits = value.toString().split("\t");
        String merchantid = splits[0];
        String categoryid = splits[1];
        String productid = splits[2];
        String score = splits[3];
        outKey.set(merchantid+"\t"+categoryid);
        outValue.set(productid+"\t"+score);
        context.write(outKey,outValue);
    }
}
