package com.datamining.mapreduce;

import com.datamining.entity.RecScorePair;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by wanghuali1 on 2017/4/6.
 */
public class RankProductsReducer extends Reducer<Text, Text, NullWritable, Text> {
    private MultipleOutputs<NullWritable, Text> mos;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        mos = new MultipleOutputs<NullWritable, Text>(context);
    }

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        List<RecScorePair> pairList = new ArrayList<RecScorePair>();

        for (Text v : values) {
            String[] splits = v.toString().split("\t");
            pairList.add(new RecScorePair(Long.valueOf(splits[0]), Double.valueOf(splits[1])));
        }
        Collections.sort(pairList, RecScorePair.COMPARE_BY_SIMILARITY_DESC);
        StringBuffer stringBuffer = new StringBuffer();

        if (pairList.isEmpty()) {
            System.out.println(" List<com.datamining.entity.RecScorePair> pairList is null");
            return;
        }

        int num = 0;
        for (RecScorePair pair : pairList) {
            if (num < pairList.size() - 1) {
                stringBuffer.append(pair.getRecId()).append(",");
            } else {
                stringBuffer.append(pair.getRecId());
            }
            num++;
            mos.write(NullWritable.get(), new Text(key.toString() + "\t" + pair.getRecId() + "\t" + pair.getScore()), "original/part");
        }
        mos.write(NullWritable.get(), new Text(key.toString() + "\t" + stringBuffer.toString()), "merge/part");

    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        mos.close();
    }
}
