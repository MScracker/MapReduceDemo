package com.datamining.mahout;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.model.DataModel;

import java.io.File;
import java.io.IOException;

/**
 * Created by wanghuali1 on 2017/5/27.
 */
public class CFTest {

	public static void main(String[] args) throws IOException, TasteException {
		ItemCfFactory itemCfFactory = new ItemCfFactory();
		DataModel dataModel=itemCfFactory.buildFileDataModel(new File("../../data/ml-latest-small/ratings_nohead.csv"));
		RecommenderBuilder recommenderBuilder = itemCfFactory.buildRecommendModel(dataModel);
		RecommenderIRStatsEvaluator evaluator = itemCfFactory.buildClassificationStaticsEvaluator();
		itemCfFactory.evaluateRecommenderModel(recommenderBuilder,dataModel,null,5,0.5,evaluator);
//		itemCfFactory.recommendItems(dataModel,recommenderBuilder,5);
	}

}
