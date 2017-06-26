package com.datamining.itemCF;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.GenericBooleanPrefDataModel;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.math.hadoop.similarity.cooccurrence.measures.CosineSimilarity;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by wongleon on 2017/5/22.
 */
public class ItemCFTest {

	public static void main(String[] args) throws IOException, TasteException {
		//构造模型
		DataModel dataModel =
//				new GenericBooleanPrefDataModel(
				new FileDataModel(new File("D:\\data\\ml-100k\\u.data"))
//				)
				;
//                new GroupLensDataModel(new File("/Users/wongleon/data/ml-1m/ratings.dat"));

		//构造推荐引擎
		RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
			@Override
			public Recommender buildRecommender(DataModel dataModel) throws TasteException {
                UncenteredCosineSimilarity similarity = new UncenteredCosineSimilarity(dataModel);
//                TanimotoCoefficientSimilarity similarity = new TanimotoCoefficientSimilarity(dataModel);
//				LogLikelihoodSimilarity similarity = new LogLikelihoodSimilarity(dataModel);
				GenericItemBasedRecommender itemBasedRecommender = new GenericItemBasedRecommender(dataModel, similarity);
//				GenericBooleanPrefItemBasedRecommender itemBasedRecommender = new GenericBooleanPrefItemBasedRecommender(dataModel, similarity);

				return itemBasedRecommender;
			}
		};

		//模型评估
		RecommenderEvaluator RSMEEvaluator = new RMSRecommenderEvaluator();
		double rmse = RSMEEvaluator.evaluate(recommenderBuilder, null, dataModel, 0.8, 0.2);
		System.out.println("rmse:" + rmse);

		RecommenderEvaluator maeEvaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
		double mae = maeEvaluator.evaluate(recommenderBuilder, null, dataModel, 0.8, 0.2);
		System.out.println("mae:" + mae);

		RecommenderIRStatsEvaluator genericRecommenderIRStatsEvaluator = new GenericRecommenderIRStatsEvaluator();
		IRStatistics stats = genericRecommenderIRStatsEvaluator.evaluate(recommenderBuilder, null, dataModel, null, 100, GenericRecommenderIRStatsEvaluator.CHOOSE_THRESHOLD, 1);
		System.out.println("percision:" + stats.getPrecision());
		System.out.println("recall:" + stats.getRecall());

		//推荐
		Recommender recommenderModel = recommenderBuilder.buildRecommender(dataModel);
		LongPrimitiveIterator iter = dataModel.getUserIDs();
		long start = System.currentTimeMillis();
		long t = start;
		int count = 0;
		long deltatime = 0;
		long avgTime = 0;
//        while (iter.hasNext()) {
//            List<RecommendedItem> list = recommenderModel.recommend(iter.nextLong(), 5);
//            count++;
//            deltatime = System.currentTimeMillis() - t;
//            System.out.printf("it takes %d time to recommend one product.\n", deltatime);
////            showItems(iter.nextLong(), list, false);
//            t = System.currentTimeMillis();
//        }
//        avgTime = (System.currentTimeMillis() - start) / count;
//        System.out.printf("it takes %d ms to recommend for per user\n", avgTime);


	}

	public static void showItems(long uid, List<RecommendedItem> recommendations, boolean skip) {
		if (!skip || recommendations.size() > 0) {
			System.out.printf("uid:%s,", uid);
			for (RecommendedItem recommendation : recommendations) {
				System.out.printf("(%s,%f)", recommendation.getItemID(), recommendation.getValue());
			}
			System.out.println();
		}
	}

}
