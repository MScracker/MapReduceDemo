package com.datamining.mahout;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;
import org.apache.mahout.cf.taste.hadoop.item.RecommenderJob;

/**
 * Created by wanghuali1 on 2017/6/8.
 */
public class ItemCFOnHadoop {
	public static void main(String[] args) throws Exception {
		RecommenderJob recommenderJob = new RecommenderJob();
		args = new String[]{
				"--input","/user/pms/recsys/algorithm/cf/see_also_see/data/processed_data/2017-06-04",
				"--output","/user/pms/recsys/workspace/wanghuali/cf/mahoutItemCF/recommendReult",
				"--numRecommendations", "10",
				"--usersFile", "/user/pms/recsys/workspace/wanghuali/cf/usersFile",
				"--booleanData","true",
				"--similarityClassname","SIMILARITY_COOCCURRENCE",
				"--outputPathForSimilarityMatrix","/user/pms/recsys/workspace/wanghuali/cf/mahoutItemCF/SimilarityMatrix"
		};
//		recommenderJob.run(args);
		Configuration conf = new Configuration();
		conf.set("mapred.job.queue.name","pms");
		ToolRunner.run(conf, new RecommenderJob(), args);
	}
}
