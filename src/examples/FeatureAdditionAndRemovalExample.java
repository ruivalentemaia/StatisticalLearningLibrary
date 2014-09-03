package examples;

import multipleLinearRegression.Dependent;
import multipleLinearRegression.Feature;
import multipleLinearRegression.MultipleLinearRegression;


public class FeatureAdditionAndRemovalExample {
	public static void main(String[] args)  {
		double[] valuesFeatureOne = {1.0, 3.5, 6.6, 7.6, 7.9, 11.4, 12.1, 12.3};
		double[] valuesFeatureTwo = {0.1, 0.14, 0.15, 0.42, 0.33, 0.44, 0.67, 0.69};
		double[] valuesFeatureThree = {5.6, 8.8, 8.9, 11.5, 11.9, 12.1, 12.67, 13.0};
		double[] valuesFeatureFour = {1.112, 1.118, 1.434, 1.622, 1.632, 1.888, 1.998, 1.999};
		double[] valuesDependent = {0.5, 0.6, 0.7, 0.8, 0.9, 1, 1.1, 1.2};
		
		Feature f1 = new Feature(1, "x1", valuesFeatureOne);
		Feature f2 = new Feature(2, "x2", valuesFeatureTwo);
		
		Feature[] features1 = new Feature[1];
		features1[0] = f1;
		
		Dependent d = new Dependent(valuesDependent);
		
		MultipleLinearRegression r = new MultipleLinearRegression(features1, d);
		
		//Adds f2.
		r.addFeature(f2);
		r.doMultipleLinearRegression();
		
		//Removes f2.
		r.removeFeature(2);
		r.doMultipleLinearRegression();
		
		//Creates f3 and f4.
		Feature f3 = new Feature(r.getFeatures().length, "x3", valuesFeatureThree);
		Feature f4 = new Feature(r.getFeatures().length + 1, "x4", valuesFeatureFour);
		
		//Adds them to features2 array.
		Feature[] features2 = new Feature[2];
		features2[0] = f3;
		features2[1] = f4;
		r.addFeatures(features2);
		
		/*
		 * It's necessary to create a new MultipleLinearRegression object due to features
		 * array sizes change.
		 */
		MultipleLinearRegression r2 = new MultipleLinearRegression(r.getFeatures(), r.getPredictor());
		r2.doMultipleLinearRegression();
	}
}
