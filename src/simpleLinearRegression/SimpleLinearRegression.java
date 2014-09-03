package simpleLinearRegression;

import java.util.HashMap;
import java.util.Map;

public class SimpleLinearRegression {
	private String featureName;
	private double interceptCoef;
	private double predictorCoef;
	private double y;
	private int n; //number of elements.
	private double[] featureValues;
	private double[] predictionValues;
	private double[] errorTerms;
	private double meanX;
	private double meanY;
	private double Rss; //Residual Sum of Squares.
	private double SEPredictorCoef; //Standard Error for the Predictor Coefficient.
	private double SEInterceptor; //Standard Error for the Interceptor Coefficient.
	private double Rse; //Residual Standard Error.
	private double Tss; //Total Sum of Squares, used for the calculation of R-squared.
	private double Rsquared; //R-squared.
	private double AdjustedRSquared; //Adjusted R-squared.
	
	//Control variables.
	private int DATASET_SIZE_IRREGULARITY = 0;
	
	//Error Messages HashMap.
	private Map<String, String> errorMessages;
	
	/* Gets and sets of the class SimpleLinearRegression */
	
	public String getFeatureName() {
		return featureName;
	}
	
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	
	public double getInterceptCoef() {
		return interceptCoef;
	}

	public void setInterceptCoef(double interceptCoef) {
		this.interceptCoef = interceptCoef;
	}

	public double getPredictorCoef() {
		return predictorCoef;
	}

	public void setPredictorCoef(double predictorCoef) {
		this.predictorCoef = predictorCoef;
	}
	
	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double[] getFeatureValues() {
		return featureValues;
	}
	
	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public void setFeatureValues(double[] featureValues) {
		this.featureValues = featureValues;
	}
	
	public double[] getPredictionValues() {
		return predictionValues;
	}
	
	public void setPredictionValues(double[] predictionValues) {
		this.predictionValues = predictionValues;
	}
	
	public double[] getErrorTerms() {
		return errorTerms;
	}

	public void setErrorTerms(double[] errorTerms) {
		this.errorTerms = errorTerms;
	}
	
	public double getRss() {
		return Rss;
	}
	
	public void setRss(double rss){
		this.Rss = rss;
	}

	public double getRse() {
		return Rse;
	}

	public void setRse(double rse) {
		Rse = rse;
	}

	public double getTss() {
		return Tss;
	}

	public void setTss(double tss) {
		Tss = tss;
	}

	public double getRsquared() {
		return Rsquared;
	}

	public void setRsquared(double rsquared) {
		Rsquared = rsquared;
	}

	public double getAdjustedRSquared() {
		return AdjustedRSquared;
	}

	public void setAdjustedRSquared(double adjustedRSquared) {
		AdjustedRSquared = adjustedRSquared;
	}

	/*
	 * Constructor.
	 */
	public SimpleLinearRegression(double[] x, double[] y, String xName) {
		this.featureValues = x;
		this.predictionValues = y;
		this.n = predictionValues.length;
		this.featureName = xName;
		this.meanX = this.calculateMean(x);
		this.meanY = this.calculateMean(y);
		this.errorTerms = new double[y.length];
		this.Rss = 0;
		this.Rse = 0;
		this.Tss = 0;
		this.Rsquared = 0;
		this.errorMessages = new HashMap<String, String>();
		this.buildErrorMessagesStructure();
	}
	
	/*
	 * Contains the keys and values of error messages
	 * to be displayed.
	 */
	public void buildErrorMessagesStructure() {
		
		errorMessages.put("FEATURE_VALUES_ZERO", 
						  "The number of elements in the feature values array is 0."
						  + " Please make sure that this array is filled with the same amount of elements"
						  + " as the prediction values array.");
		
		errorMessages.put("PREDICTION_VALUES_ZERO",
						  "The number of elements in the prediction values array is 0."
						  + " Please make sure that this array is filled with the same amount of elements"
						  + " as the feature values array.");
		
		errorMessages.put("X_SIZE_DIFFERENT_OF_Y",
						  "The number of predicting elements is different of the number of feature elements."
						  + " Please make sure their number of elements is the same.");
		
		errorMessages.put("ARRAY_SIZED_ZERO", 
						  "The number of elements in the array is 0, hence the mean cannot be calculated.");
		
		errorMessages.put("DENOMINATOR_VALUE_ZERO_SE", 
						  "The denominator in the calculation of the Standard Error is zero.");
	}
	
	/*
	 * Calculates the mean of a given array of floats.
	 */
	public double calculateMean(double[] arr) {
		if(arr.length == 0){
			System.out.println("ERROR: " + this.errorMessages.get("ARRAY_SIZED_ZERO"));
			return 0;
		}
		
		double mean = 0, sum = 0;
		for(int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		mean = sum / arr.length;
		return mean;
	}
	
	/*
	 * Calculates the standard error of the predictor coefficient
	 * and of the intercept.
	 * Needs to calculate the mean of the error terms and the 
	 * variance of the error terms first.
	 */
	public void calculateSEs() {
		double se = 0;
		double meanErrorTerms = 0;
		double variance = 0;
		double lowerPartSE = 0;
		//calculate the mean of the errorTerms
		for(int i = 0; i < this.n; i++) {
			meanErrorTerms += errorTerms[i];
		}
		meanErrorTerms = meanErrorTerms / this.n;
		
		//calculate variance of errorTerms
		for(int i = 0; i < this.n; i++) {
			variance = (float) Math.pow((errorTerms[i] - meanErrorTerms),2);
		}
		variance = variance / this.n;
		
		//calculate lower part of the standard error calculation
		for(int i = 0; i < this.n; i++) {
			lowerPartSE = (float) Math.pow(featureValues[i] - this.meanX, 2);
		}
		
		if(lowerPartSE != 0) {
			se = variance / lowerPartSE;
			this.SEPredictorCoef = se;
		}
		else {
			System.out.println("ERROR: " + this.errorMessages.get("DENOMINATOR_VALUE_ZERO_SE"));
			this.SEPredictorCoef = 0;
		}
		
		this.SEInterceptor = variance * ((1/this.featureValues.length) + (this.meanX / lowerPartSE));
	}
	
	/*
	 * Does the linear regression.
	 */
	public void doSimpleLinearRegression() {
		if( (this.featureName == "") || (this.featureName == null) ) {
			this.featureName = "Beta1";
		}
		
		if( this.featureValues.length == 0 ){
			this.DATASET_SIZE_IRREGULARITY = 1;
			System.out.println("ERROR: " + this.errorMessages.get("FEATURE_VALUES_ZERO"));
			return;
		}
		else if( this.predictionValues.length == 0 ) {
			this.DATASET_SIZE_IRREGULARITY = 1;
			System.out.println("ERROR: " + this.errorMessages.get("PREDICTION_VALUES_ZERO"));
			return;
		}
		else if( this.featureValues.length != this.predictionValues.length ){
			this.DATASET_SIZE_IRREGULARITY = 1;
			System.out.println("ERROR: " + this.errorMessages.get("X_SIZE_DIFFERENT_OF_Y"));
			return;
		}
		
		else {
			float upperFeature = 0;
			float lowerFeature = 0;
			for(int i = 0; i < this.n; i++) {
				upperFeature += ( this.featureValues[i] - this.meanX)
							  * ( this.predictionValues[i] - this.meanY);
			}
			for(int i = 0; i < this.n; i++) {
				lowerFeature += Math.pow(( this.featureValues[i] - this.meanX), 2);
			}
			
			this.predictorCoef = upperFeature / lowerFeature;
			this.interceptCoef = this.meanY - this.predictorCoef * this.meanX;
			
			//Calculates error terms, Residual Sum of Squares.
			for(int i = 0; i < this.n; i++) {
				errorTerms[i] = this.predictionValues[i] 
							- ( this.interceptCoef + this.predictorCoef 
							* this.featureValues[i]);
				this.Rss += Math.pow(errorTerms[i], 2);
			}
			//Calculation of SEs.
			this.calculateSEs();
			
			//Calculation of the RSE.
			this.Rse = (float) Math.sqrt((1.0/(float)(this.n - 2)) * this.Rss);
			
			//Calculation of TSS.
			for(int i = 0; i < this.n; i++) {
				this.Tss += Math.pow(this.predictionValues[i] - this.meanY, 2);
			}
			
			//Calculation of R-squared.
			this.Rsquared = (this.Tss - this.Rss) / this.Tss;
			
			this.AdjustedRSquared = this.Rsquared - ((1 - this.Rsquared) * (1/(this.n - 2)));
			
		}
	}
	
	/*
	 * Predicts for a new given xValue.
	 */
	public void predict(double xVal){
		if(this.DATASET_SIZE_IRREGULARITY == 0) {
			this.y = this.interceptCoef + this.predictorCoef*xVal;
			System.out.println();
			System.out.println("Prediction for " + xVal + ": " + this.y);	
		}
		else return;
	}
	
	public void printSimpleRegression() {
		if(this.DATASET_SIZE_IRREGULARITY == 0) {
			System.out.println("General Formula: ");
			System.out.println(" y = " + this.interceptCoef + " + " + this.predictorCoef + " * x");
			System.out.println();
			
			System.out.println("Number of elements in the dataset: " + this.n);
			System.out.println("Mean x: " + this.meanX);
			System.out.println("Mean y: " + this.meanY);
			System.out.println("Intercept: " + this.interceptCoef);
			System.out.println("Predictor Coefficient: " + this.predictorCoef);
			System.out.println("RSS: " + this.Rss);
			System.out.println("SE beta0: " + this.SEInterceptor);
			System.out.println("SE beta1: " + this.SEPredictorCoef);
			System.out.println("RSE: " + this.Rse);
			System.out.println("TSS: " + this.Tss);
			System.out.println("R-squared: " + this.Rsquared);
			System.out.println();
			
			System.out.println("95% Confidence interval on the real value of " + this.featureName + " coefficient: ");
			double leftSide = this.predictorCoef - (2 * this.SEPredictorCoef);
			double rightSide = this.predictorCoef + (2 * this.SEPredictorCoef);
			System.out.println("[" + leftSide + ", " + rightSide + "]");
		}
		else return;
	}

}
