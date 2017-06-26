package com.datamining.mahout;

import org.apache.lucene.search.similarities.Similarity;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.DataModelBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.IDRescorer;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

/**
 * Created by wanghuali1 on 2017/5/27.
 */
public class ItemCfFactory extends RecommendFactory {


	@Override
	public RecommenderBuilder buildRecommendModel(DataModel dataModel) {
		RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
			@Override
			public Recommender buildRecommender(DataModel dataModel) throws TasteException {
				LogLikelihoodSimilarity similarity = new LogLikelihoodSimilarity(dataModel);
				GenericBooleanPrefItemBasedRecommender itemBasedRecommender = new GenericBooleanPrefItemBasedRecommender(dataModel, similarity);
				return itemBasedRecommender;
			}
		};
		return recommenderBuilder;
	}

	@Override
	public RecommenderEvaluator buildRecommenderEvaluator(EVALUATOR type) {
		if (type.equals(EVALUATOR.RMSE)) {
			return new RMSRecommenderEvaluator();
		} else if (type.equals(EVALUATOR.AVG_ABS_DIFF)) {
			return new AverageAbsoluteDifferenceRecommenderEvaluator();
		}
		return new RMSRecommenderEvaluator();
	}

	@Override
	public GenericRecommenderIRStatsEvaluator buildClassificationStaticsEvaluator() {
		return new GenericRecommenderIRStatsEvaluator();
	}


}
