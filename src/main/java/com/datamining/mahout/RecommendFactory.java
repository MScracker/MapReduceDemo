package com.datamining.mahout;

import org.apache.lucene.search.similarities.Similarity;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.*;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveArrayIterator;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.model.GenericBooleanPrefDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.IDRescorer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.Rescorer;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wanghuali1 on 2017/5/26.
 */
public abstract class RecommendFactory {
	public enum EVALUATOR {
		RMSE, AVG_ABS_DIFF
	}

	//构造数据模型
	public DataModel buildFileDataModel(File file) throws IOException, TasteException {
		return new FileDataModel(file);
	}

	public DataModel buildNonbooleanDataModel(File file) throws TasteException, IOException {
		return new GenericDataModel(new FileDataModel(file));
	}

	public DataModel buildBooleanDataModel(File file) throws IOException, TasteException {
		return new GenericBooleanPrefDataModel(new FileDataModel(file));
	}

	//构造相似度计算方法

	//构造最近邻算法模型

	//构造推荐算法模型
	public abstract RecommenderBuilder buildRecommendModel(DataModel dataModel);
	//构造算法评估器
	public abstract RecommenderEvaluator buildRecommenderEvaluator(EVALUATOR type);
	public abstract GenericRecommenderIRStatsEvaluator buildClassificationStaticsEvaluator();
	//推荐结果
	public void recommendItems(DataModel dataModel, RecommenderBuilder recommenderBuilder, int howMany) throws TasteException {
		LongPrimitiveIterator userIDs = dataModel.getUserIDs();
		List<RecommendedItem> items = new ArrayList<RecommendedItem>();
		while (userIDs.hasNext()) {
			long uid = userIDs.nextLong();
			items = recommenderBuilder.buildRecommender(dataModel).recommend(uid, howMany);
			if (items == null) {
				showItems(uid, items);
			}
		}

	}

	public static void showItems(long uid, List<RecommendedItem> recommendations) {

		if (recommendations.size() <= 0) {
			return;
		}
		System.out.printf("uid:%s,", uid);
		for (RecommendedItem recommendation : recommendations) {
			System.out.printf("(%s,%f)", recommendation.getItemID(), recommendation.getValue());
		}
		System.out.println();
	}

	public void evaluateRecommenderModel(DataModel dataModel, RecommenderBuilder recommenderBuilder, RecommenderEvaluator evaluator,
										 double trainingPercentage, double evaluationPercentage) throws TasteException {
		if (dataModel == null || evaluator == null) {
			return;
		}
		double rmse = evaluator.evaluate(recommenderBuilder, null, dataModel, trainingPercentage, evaluationPercentage);
		System.out.println("rmse:" + rmse);
	}

	public void evaluateRecommenderModel(RecommenderBuilder recommenderBuilder,
										 DataModel dataModel,
										 IDRescorer rescorer,
										 int topN,
										 double evaluationPercentage,
										 RecommenderIRStatsEvaluator recommenderIRStatsEvaluator) throws TasteException {
		if (recommenderBuilder == null || dataModel == null) {
			return;
		}

		IRStatistics irStatistics = recommenderIRStatsEvaluator.evaluate(recommenderBuilder, null, dataModel, rescorer, topN,
				GenericRecommenderIRStatsEvaluator.CHOOSE_THRESHOLD, evaluationPercentage);
		System.out.println("percision:" + irStatistics.getPrecision());
		System.out.println("recall:" + irStatistics.getRecall());
		System.out.println("f1 score:" + irStatistics.getF1Measure());
		System.out.println("ndcg:" + irStatistics.getNormalizedDiscountedCumulativeGain());
	}

}
